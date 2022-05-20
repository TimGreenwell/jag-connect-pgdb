package us.ihmc.jagconnectpg.model;

import javax.persistence.*;

@Entity(name = "Binding")
@Table(name = "Binding")
public class Binding {
    @Id
    @GeneratedValue
    @Column(name = "binding_id")
    private String id;
    @Column(name = "binding_in", nullable = false)
    private String in;
    @Column(name = "binding_out", nullable = false)
    private String out;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="binding_activity_fk", nullable=false)
    private JagActivity jagActivity;


    public Binding(String id, String in, String out) {
        this.id = id;
        this.in = in;
        this.out = out;
    }

    public Binding() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getIn() {
        return in;
    }
    public void setIn(String in) {
        this.in = in;
    }

    public String getOut() {
        return out;
    }
    public void setOut(String out) {
        this.out = out;
    }

    public JagActivity getJagActivity() {
        return jagActivity;
    }
    public void setJagActivity(JagActivity jagActivity) {
        this.jagActivity = jagActivity;
    }

    @Override
    public String toString() {
        return "Binding{" +
                "id='" + id + '\'' +
                ", in='" + in + '\'' +
                ", out='" + out + '\'' +
                ", jagActivity=" + jagActivity +
                '}';
    }
}


