package main.dto;

import java.util.Set;

public class BrandDTO {

    private Long id;
    private String name;
    Set<CarModelDTO> models;

    public BrandDTO() {
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

    public Set<CarModelDTO> getModels() {
        return models;
    }

    public void setModels(Set<CarModelDTO> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "BrandDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", models=" + models +
                '}';
    }
}
