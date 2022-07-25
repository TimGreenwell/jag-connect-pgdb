package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.*;
import java.util.*;

@Entity(name = "Subscription")
@Table(name = "Subscription")
@Data
public class Subscription {
    @Id
    @Column(name = "sub_pk", nullable = false)
    private String name;

    @Column(name = "sub_lrt", nullable = true)
    private Date lastReportTime;

    @Column(name = "sub_data", nullable = true)
    private String lastReportData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sub_node_fk", nullable=false)
    @JsonBackReference
    private Node node;

//
//    public Subscription(String name, Date lastReportTime, String lastReportData) {
//        this.name = name;
//        this.lastReportTime = lastReportTime;
//        this.lastReportData = lastReportData;
//    }
//
//    public Subscription() {
//    }
//
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Date getLastReportTime() {
//        return lastReportTime;
//    }
//
//    public void setLastReportTime(Date lastReportTime) {
//        this.lastReportTime = lastReportTime;
//    }
//
//    public String getLastReportData() {
//        return lastReportData;
//    }
//
//    public void setLastReportData(String lastReportData) {
//        this.lastReportData = lastReportData;
//    }
//
//    public JagCell getJagCell() {
//        return jagCell;
//    }
//
//    public void setJagCell(JagCell jagCell) {
//        this.jagCell = jagCell;
//    }
//
//    @Override
//    public String toString() {
//        return "Subscription{" +
//                "name='" + name + '\'' +
//                ", lastReportTime=" + lastReportTime +
//                ", lastReportData='" + lastReportData + '\'' +
//                ", jagCell=" + jagCell +
//                '}';
//    }
}
