package us.ihmc.jagconnectpg.model;


import javax.persistence.*;

@Entity(name = "Analysis")
@Table(name = "analysis")
public class Analysis {

    private String id;
    private String description;
    private String name;
    private String rootJagNodeId;
    private String rootJagActivityUrn;
    private String teamId;

    public Analysis(String id, String description, String name, String rootJagNodeId, String rootJagActivityUrn, String teamId) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.rootJagNodeId = rootJagNodeId;
        this.rootJagActivityUrn = rootJagActivityUrn;
        this.teamId = teamId;
    }

    public Analysis() {    }

    @Id
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "description", nullable = true)
    public String getDescription() {
        return description;
    }
    public void setDescription(String firstName) {
        this.description = description;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String lastName) {
        this.name = name;
    }

    @Column(name = "root", nullable = false)
    public String getRootJagNodeId() { return rootJagNodeId; }
    public void setRootJagNodeId(String rootJagNodeId) { this.rootJagNodeId = rootJagNodeId; }

    @Column(name = "rootUrn", nullable = false)
    public String getRootJagActivityUrn() { return rootJagActivityUrn; }
    public void setRootJagActivityUrn(String rootJagActivityUrn) {
        this.rootJagActivityUrn = rootJagActivityUrn;
    }

    @Column(name = "team", nullable = false)
    public String getTeamId() { return teamId; }
    public void setTeamId(String teamId) { this.teamId = teamId; }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", rootJagNodeId='" + rootJagNodeId + '\'' +
                ", rootJagActivityUrn='" + rootJagActivityUrn + '\'' +
                ", teamId='" + teamId + '\'' +
                '}';
    }
}