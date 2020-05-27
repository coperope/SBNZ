package main.facts;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Brand implements Serializable {
    private static final long serialVersionUID = 1L;
    // TODO: 5/13/2020 Add annotations.

    @Id
    @SequenceGenerator(name="brand_id_seq",sequenceName="brand_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="brand_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    List<CarModel> models;

    public Brand(){

    }
    public Brand(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CarModel> getModels() {
        return models;
    }

    public void setModels(List<CarModel> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", models=" + models +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return getId().equals(brand.getId()) &&
                getName().equals(brand.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
