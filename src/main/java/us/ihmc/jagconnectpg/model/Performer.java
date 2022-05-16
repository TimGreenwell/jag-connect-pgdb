package us.ihmc.jagconnectpg.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Performer")
@Table(name = "performers")
public class Performer {

    private String id;
    private String name;
    private Team team;


    public Performer(String id, String name, Team team) {
        this.id = id;
        this.name = name;
        this.team = team;
    }

    public Performer() {
    }

    @Id
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Performer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}