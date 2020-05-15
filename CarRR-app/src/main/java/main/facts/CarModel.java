package main.facts;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class CarModel implements Serializable {

    private static final long serialVersionUID = 1L;
    // TODO: 5/13/2020 Add annotations.
    @Id
    @SequenceGenerator(name="model_id_seq",sequenceName="model_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="model_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Brand brand;

    public CarModel(){

    }
    public CarModel(Long id, String name, Brand brand) {
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
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
