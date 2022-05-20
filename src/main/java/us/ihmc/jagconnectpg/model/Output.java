package us.ihmc.jagconnectpg.model;

import javax.persistence.*;

@Entity(name = "Output")
@Table(name = "OUTPUT_DATA")
public class Output {
    @Id
    @Column(name = "output_id", nullable = false)
    private String name;
    @Column(name = "output_type", nullable = false)
    private String type;


//    @JoinColumn(name = "activity_urn", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
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