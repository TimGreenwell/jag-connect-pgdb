package us.ihmc.jagconnectpg.model;
import javax.persistence.*;

@Entity(name = "JagActivityChild")
@Table(name = "jagActivityChild")
public class JagActivityChild {

    private String urn;
    private String id;
    private JagActivity jagActivity;

    public JagActivityChild(String urn, String id, JagActivity jagActivity) {
        this.urn = urn;
        this.id = id;
        this.jagActivity = jagActivity;
    }

    public JagActivityChild() {
    }

    @Id
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "urn", nullable = false)
    public String getUrn() {
        return urn;
    }
    public void setUrn(String urn) {
        this.urn = urn;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public JagActivity getJagActivity() {
        return jagActivity;
    }
    public void setJagActivity(JagActivity jagActivity) {
        this.jagActivity = jagActivity;
    }

    @Override
    public String toString() {
        return "JagActivityChild{" +
                "urn='" + urn + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
