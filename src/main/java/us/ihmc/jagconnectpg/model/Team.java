package us.ihmc.jagconnectpg.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Team")
@Table(name = "TEAM")
public class Team {
    @Id
    @Column(name = "team_id", nullable = false)
    private String id;
    @Column(name = "team_name", nullable = false)
    private String name;
    @Column(name = "agent", nullable = false)
    @OneToMany(
      //      mappedBy = "team",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Agent> agents = new ArrayList<>();
    @Column(name = "performer", nullable = true)
    @OneToMany(
      //      mappedBy = "team",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Performer> performers = new ArrayList<>();

    public Team(String name, List<Agent> agents, List<Performer> performers) {
        this.name = name;
        this.agents = agents;
        this.performers = performers;
    }

    public Team() {
    }



    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }


    public List<Performer> getPerformers() {
        return performers;
    }
    public void setPerformers(List<Performer> performers) {
        this.performers = performers;
    }


}

