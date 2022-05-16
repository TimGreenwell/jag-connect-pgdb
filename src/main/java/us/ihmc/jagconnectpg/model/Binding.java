package us.ihmc.jagconnectpg.model;

import javax.persistence.*;

@Entity(name = "Binding")
@Table(name = "binding")
public class Binding {
    private String in;
    private String out;
    private Long id;
    private JagActivity jagActivity;

    public Binding(String in, String out, Long id, JagActivity jagActivity) {
        this.in = in;
        this.out = out;
        this.id = id;
        this.jagActivity = jagActivity;
    }

    public Binding() {
    }

    @Id
    @GeneratedValue
    @Column(name = "binding_id")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "binding_in", nullable = false)
    public String getIn() {
        return in;
    }
    public void setIn(String in) {
        this.in = in;
    }

    @Column(name = "binding_out", nullable = false)
    public String getOut() {
        return out;
    }
    public void setOut(String out) {
        this.out = out;
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
        return "Binding{" +
                "in='" + in + '\'' +
                ", out='" + out + '\'' +
                '}';
    }




}


