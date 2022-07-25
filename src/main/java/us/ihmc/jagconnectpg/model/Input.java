package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "Consume")
@Table(name = "Consume")
@Data
public class Input {
    @Id
    @Column(name = "consume_pk", nullable = false)
    private String name;

    @Column(name = "consume_type", nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="consume_activity_fk", nullable=false)
    @JsonBackReference
    private JagActivity jagActivity;

//
//    public Input(String name, String type) {
//        this.name = name;
//        this.type = type;
//    }
//
//    public Input() {
//    }
//
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//
//
//    public String getType() {
//        return type;
//    }
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public JagActivity getJagActivity() {
//        return jagActivity;
//    }
//    public void setJagActivity(JagActivity jagActivity) {
//        this.jagActivity = jagActivity;
//    }
//
//    @Override
//    public String toString() {
//        return "Input{" +
//                "name='" + name + '\'' +
//                ", type='" + type + '\'' +
//                ", jagActivity=" + jagActivity +
//                '}';
//    }
}
