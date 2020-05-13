package facts;

import java.io.Serializable;
import java.util.Objects;

public class CarModel implements Serializable {

    private static final long serialVersionUID = 1L;
    // TODO: 5/13/2020 Add annotations.
    private Long id;
    private String name;
    private main.java.facts.Brand brand;

    public CarModel(Long id, String name, main.java.facts.Brand brand) {
        this.id = id;
        this.name = name;
        this.brand = brand;
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

    public main.java.facts.Brand getBrand() {
        return brand;
    }

    public void setBrand(main.java.facts.Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand=" + brand +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarModel carModel = (CarModel) o;
        return getId().equals(carModel.getId()) &&
                getName().equals(carModel.getName()) &&
                getBrand().equals(carModel.getBrand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBrand());
    }
}
