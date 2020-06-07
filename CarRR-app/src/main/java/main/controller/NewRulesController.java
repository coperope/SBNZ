package main.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.MainApp;
import main.dto.CategoryRuleDTO;
import main.facts.Category;
import main.repository.CategoryRepo;

@RestController
@RequestMapping(value = "rule")
public class NewRulesController {
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@PostMapping(path = "/categorization", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> newCategorisationRule(@RequestBody CategoryRuleDTO categoryRuleDTO) {

        String category = categoryRuleDTO.getCategoryName();
        
        Category check = categoryRepo.findByName(category);
		if (check != null) {
			return new ResponseEntity<>("Category with this name alreary exists", HttpStatus.BAD_REQUEST);
		}
		
        String condition = "";
		try {
			condition = getCategorizationConditionString(categoryRuleDTO);
		} catch (Exception e1) {
			e1.printStackTrace();
			return new ResponseEntity<>("Invalid rule condition", HttpStatus.INTERNAL_SERVER_ERROR);
		}
        
        File initialFile = new File(MainApp.kjarPath + "\\src\\main\\resources\\rule_templates\\category_rule_template.drt");
        InputStream template = null;
		try {
			template = new FileInputStream(initialFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Cant find template", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
        String featuresCondition = "Number(intValue " + (categoryRuleDTO.getFeatureOperation() != null ? categoryRuleDTO.getFeatureOperation() : ">=")  + " " + categoryRuleDTO.getFeatures() + ") from accumulate(\n"
        		+ "            ExtraFeatures(\n"
        		+ "            	$id: id\n"
        		+ "            ) from $features,\n"
        		+ "            count($id)\n"
        		+ "        )\n";
        DataProvider dataProvider = new ArrayDataProvider(new String[][]{
            new String[]{category, featuresCondition, condition}
        });
        DataProviderCompiler converter = new DataProviderCompiler();
        String drl = converter.compile(dataProvider, template);
        
        
        // Validate rule
        KieHelper helper = new KieHelper();
        helper.addContent(drl, ResourceType.DRL);
        Results result = helper.verify();
        
        if (result.hasMessages(Message.Level.WARNING, Message.Level.ERROR)) {
        	return new ResponseEntity<>("Rule compilation error, check logs", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        System.out.println(drl);
        
        try {
        	// Save to file system
			saveCategorizationRule(drl, category);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Cant save rule", HttpStatus.INTERNAL_SERVER_ERROR);
		}

        // Save in database only if rule creation is finished
        createCategoryIfNotExists(category);
        // Build kjar with new rule
        buildKjar();
        return new ResponseEntity<>("Rule successfuly added", HttpStatus.OK);
    }
	
	
	private String getCategorizationConditionString(CategoryRuleDTO category) throws Exception {
		String condition = "";
		
		if (category.getConditions().size() == 0) {
			condition += "$features: features";
			return condition;
		}
		
		for (int i = 0; i < category.getConditions().size(); i++) {
			List<String> cond = category.getConditions().get(i);
			if (cond.size() != 3) {
				throw new Exception();
			}
			condition += cond.get(0) + " " + cond.get(1) + " " + cond.get(2) + ", ";
			if (i == category.getConditions().size() - 1) {
				condition += "$features: features";
			}
		}
		return condition;
	}
	
	private void createCategoryIfNotExists(String name) {
		Category check = categoryRepo.findByName(name);
		if (check != null) {
			return;
		}
		
		Category category = new Category();
		category.setName(name);
		categoryRepo.save(category);
	}

	private void saveCategorizationRule(String drl, String name) throws IOException {
		FileWriter myWriter = new FileWriter(MainApp.kjarPath + "\\src\\main\\resources\\categorisation_tagging_rules\\" + name + "_category_rule.drl");
		myWriter.write(drl);
		myWriter.close();
	}
	
	
	public static void buildKjar() {
		InvocationRequest request = new DefaultInvocationRequest();
		// request.setPomFile( new File( "H:\\FTN\\8. semestar\\Sistemi bazirani na znanju\\Projekat\\SBNZ\\CarRR-kjar\\pom.xml" ) );
        request.setPomFile( new File( MainApp.kjarPath + "\\pom.xml" ) );
        request.setGoals( Arrays.asList( "install" ) );

        Invoker invoker = new DefaultInvoker();
        try {
			invoker.execute( request );
		} catch (MavenInvocationException e) {
			e.printStackTrace();
		}
	}
}
