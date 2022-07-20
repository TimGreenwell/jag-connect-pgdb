package us.ihmc.jagconnectpg.model;


import javax.persistence.*;

@Entity(name = "Analysis")
@Table(name = "Analysis")
public class Analysis {
    @Id
    @Column(name = "analysis_pk", nullable = true)
    private String id;
    @Column(name = "analysis_name", nullable = false)
    private String name;
    @Column(name = "analysis_desc", nullable = true)
    private String description;
    @Column(name = "analysis_rooturn", nullable = false)
    private String rootUrn;

    @Column(name = "analysis_team", nullable = false)
    private String teamId;
    @Column(name = "is_locked", nullable = false)
    private String isLocked;

    public Analysis(String id, String name, String description, String rootUrn, String teamId, String isLocked) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rootUrn = rootUrn;
        this.teamId = teamId;
        this.isLocked = isLocked;
    }

    public Analysis() {    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRootUrn() {
        return rootUrn;
    }

    public void setRootUrn(String rootUrn) {
        this.rootUrn = rootUrn;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    @Override
    public String toString() {
        return "Analysis{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rootUrn='" + rootUrn + '\'' +
                ", teamId='" + teamId + '\'' +
                ", isLocked='" + isLocked + '\'' +
                '}';
    }
}