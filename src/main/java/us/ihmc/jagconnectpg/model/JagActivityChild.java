package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity(name = "ActivityChild")
@Table(name = "ActivityChild")
public class JagActivityChild {
    @Id
    @Column(name = "activity_child_pk", nullable = false)
    private String id;

    @Column(name = "activity_child_urn", nullable = false)
    private String urn;




    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonBackReference
    @JoinColumn(name="activity_child_parent_fk", nullable=false)
    private JagActivity jagActivity;

    // public JagActivityChild(String id, String urn, JagActivity jagActivity) {
    public JagActivityChild(String id, String urn, JagActivity jagActivity) {
        this.id = id;
        this.urn = urn;
        this.jagActivity = jagActivity;
    }

    public JagActivityChild() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrn() {
        return urn;
    }

    public void setUrn(String urn) {
        this.urn = urn;
    }

    public JagActivity getJagActivity() {
        return jagActivity;
    }

    public void setJagActivity(JagActivity jagActivity) {
        this.jagActivity = jagActivity;
    }

    @Override
    public String toString() {
        return "JagActivityChild{" +
                "id='" + id + '\'' +
                ", urn='" + urn + '\'' +
                ", jagActivity=" + jagActivity +
                '}';
    }
}
