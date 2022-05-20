package us.ihmc.jagconnectpg.model;

import javax.persistence.*;

@Entity(name = "JagActivityChild")
@Table(name = "ACTIVITY_CHILD")
public class JagActivityChild {
    @Id
    @Column(name = "activity_child_id", nullable = false)
    private String id;

    @Column(name = "activity_child_urn", nullable = false)
    private String urn;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="parent_fk", nullable=false)
    private JagActivity jagActivity;

    public JagActivityChild(String id, String urn) {
        this.id = id;
        this.urn = urn;
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
