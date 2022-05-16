package us.ihmc.jagconnectpg.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Team")
@Table(name = "team")
public class Team {

    private String id;
    private String name;
    private List<Agent> agents = new ArrayList<>();
    private List<Performer> performers = new ArrayList<>();

    public Team(String name, List<Agent> agents, List<Performer> performers) {
        this.name = name;
        this.agents = agents;
        this.performers = performers;
    }

    public Team() {
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

    @Column(name = "agent", nullable = false)
    @OneToMany(
            mappedBy = "team",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    @Column(name = "performer", nullable = true)
    @OneToMany(
            mappedBy = "team",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public List<Performer> getPerformers() {
        return performers;
    }
    public void setPerformers(List<Performer> performers) {
        this.performers = performers;
    }


}

