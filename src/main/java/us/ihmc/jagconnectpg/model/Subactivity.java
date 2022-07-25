package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.*;

@Entity(name = "Subactivity")
@Table(name = "Subactivity")
@Data
public class Subactivity {
    @Id
    @Column(name = "subactivity_pk", nullable = false)
    private String id;

    @Column(name = "subactivity_urn", nullable = false)
    private String urn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="subactivity_parent_fk", nullable=false)
    @JsonBackReference
    private Activity activity;

//    public JagActivityChild(String id, String urn, JagActivity jagActivity) {
//        this.id = id;
//        this.urn = urn;
//        this.jagActivity = jagActivity;
//    }
//
//    public JagActivityChild() {
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getUrn() {
//        return urn;
//    }
//
//    public void setUrn(String urn) {
//        this.urn = urn;
//    }
//
//    public JagActivity getJagActivity() {
//        return jagActivity;
//    }
//
//    public void setJagActivity(JagActivity jagActivity) {
//        this.jagActivity = jagActivity;
//    }
//
//    @Override
//    public String toString() {
//        return "JagActivityChild{" +
//                "id='" + id + '\'' +
//                ", urn='" + urn + '\'' +
//                ", jagActivity=" + jagActivity +
//                '}';
//    }
}
