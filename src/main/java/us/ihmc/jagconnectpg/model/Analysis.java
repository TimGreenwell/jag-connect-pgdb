package us.ihmc.jagconnectpg.model;


import lombok.Data;

import javax.persistence.*;

@Entity(name = "Analysis")
@Table(name = "Analysis")
@Data
public class Analysis {
    @Id
    @Column(name = "analysis_pk", nullable = true)
    private String id;

    @Column(name = "analysis_desc", nullable = true)
    private String description;

    @Column(name = "analysis_name", nullable = false)
    private String name;

    @Column(name = "analysis_root", nullable = false)
    private String rootJagNodeId;

    @Column(name = "analysis_root_urn", nullable = false)
    private String rootJagActivityUrn;

    @Column(name = "analysis_team", nullable = false)
    private String teamId;
    @Column(name = "is_locked", nullable = false)
    private String isLocked;

    public Analysis(String id, String description, String name, String rootJagNodeId, String rootJagActivityUrn, String teamId) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.rootJagNodeId = rootJagNodeId;
        this.rootJagActivityUrn = rootJagActivityUrn;
        this.teamId = teamId;
    }

    public Analysis() {    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

//
//    public Analysis(String id, String description, String name, String rootJagNodeId, String rootJagActivityUrn, String teamId) {
//        this.id = id;
//        this.description = description;
//        this.name = name;
//        this.rootJagNodeId = rootJagNodeId;
//        this.rootJagActivityUrn = rootJagActivityUrn;
//        this.teamId = teamId;
//    }
//
//    public Analysis() {    }
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
//    public String getDescription() {
//        return description;
//    }
//    public void setDescription(String firstName) {
//        this.description = description;
//    }
//
//
//    public String getName() {
//        return name;
//    }
//    public void setName(String lastName) {
//        this.name = name;
//    }
//
//
//    public String getRootJagNodeId() { return rootJagNodeId; }
//    public void setRootJagNodeId(String rootJagNodeId) { this.rootJagNodeId = rootJagNodeId; }
//
//
//    public String getRootJagActivityUrn() { return rootJagActivityUrn; }
//    public void setRootJagActivityUrn(String rootJagActivityUrn) {
//        this.rootJagActivityUrn = rootJagActivityUrn;
//    }
//
//
//    public String getTeamId() { return teamId; }
//    public void setTeamId(String teamId) { this.teamId = teamId; }
//
//    @Override
//    public String toString() {
//        return "{" +
//                "id='" + id + '\'' +
//                ", description='" + description + '\'' +
//                ", name='" + name + '\'' +
//                ", rootJagNodeId='" + rootJagNodeId + '\'' +
//                ", rootJagActivityUrn='" + rootJagActivityUrn + '\'' +
//                ", teamId='" + teamId + '\'' +
//                '}';
//    }
}