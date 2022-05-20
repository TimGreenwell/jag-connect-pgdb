package us.ihmc.jagconnectpg.model;

import javax.persistence.*;

@Entity(name = "Produce")
@Table(name = "Produce")
public class Output {
    @Id
    @Column(name = "produce_pk", nullable = false)
    private String name;
    @Column(name = "produce_type", nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="produce_activity_fk", nullable=false)
    private JagActivity jagActivity;

    public Output(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Output() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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
                ", jagActivity=" + jagActivity +
                '}';
    }
}