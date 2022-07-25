package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Performer")
@Table(name = "Performer")
@Data
public class Performer {
    @Id
    @Column(name = "performer_pk", nullable = false)
    private String id;

    @Column(name = "performer_name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="performer_team_fk", nullable=false)
    @JsonBackReference
    private Team team;

//
//    public Performer(String id, String name, Team team) {
//        this.id = id;
//        this.name = name;
//        this.team = team;
//    }
//
//    public Performer() {
//    }
//
//
//    public String getId() {
//        return id;
//    }
//    public void setId(String id) {
//        this.id = id;
//    }
//
//
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//
//
//    public Team getTeam() {
//        return team;
//    }
//    public void setTeam(Team team) {
//        this.team = team;
//    }
//
//    @Override
//    public String toString() {
//        return "Performer{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                '}';
//    }
}