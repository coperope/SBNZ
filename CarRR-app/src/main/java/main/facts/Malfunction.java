package main.facts;

import javax.persistence.*;

@Entity
public class Malfunction {

    @Id
    @SequenceGenerator(name="malfunction_id_seq",sequenceName="malfunction_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="malfunction_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private MalfunctionType type;

    public Malfunction() {
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

    public MalfunctionType getType() {
        return type;
    }

    public void setType(MalfunctionType type) {
        this.type = type;
    }
}
