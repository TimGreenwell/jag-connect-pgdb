package us.ihmc.jagconnectpg.model;

import javax.persistence.*;

@Entity(name = "Output")
@Table(name = "output")
public class Output {

    private String name;
    private String type;
    private JagActivity jagActivity;

    public Output(String name, String type, JagActivity jagActivity) {
        this.name = name;
        this.type = type;
        this.jagActivity = jagActivity;
    }

    public Output() {
    }

    @Id
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "type", nullable = false)
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
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
        return "Output{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
