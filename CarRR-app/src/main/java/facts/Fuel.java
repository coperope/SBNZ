package facts;

import java.io.Serializable;
import java.util.Objects;

public class Fuel implements Serializable {
    private static final long serialVersionUID = 1L;
    // TODO: 5/13/2020 Add annotations.
    private Long id;
    private String name;

    public Fuel(Long id, String name) {
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

    @Override
    public String toString() {
        return "Fuel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fuel fuel = (Fuel) o;
        return getId().equals(fuel.getId()) &&
                getName().equals(fuel.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
