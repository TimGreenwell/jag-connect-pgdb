package us.ihmc.jagconnectpg.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Nodes")
@Table(name = "NODE")
public class JagCell {
    @Id
    @Column(name = "node_id", nullable = false)
    private String id;
    @Column(name = "node_jag", nullable = false)
    private String jagUrn;
    @Column(name = "node_link_status", nullable = false)
    private String linkStatus;
    @Column(name = "node_color", nullable = false)
    private String color;
    @Column(name = "node_collapsed", nullable = false)
    private Boolean collapsed;
    @Column(name = "node_length", nullable = false)
    private Integer length;
    @Column(name = "node_children", nullable = false)
    @OneToMany(
        //    mappedBy = "jagCell",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<JagCell> children = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private JagCell jagCell;

    public JagCell(String id, String jagUrn, String linkStatus, String color, Boolean collapsed, Integer length, List<JagCell> children, JagCell jagCell) {
        this.id = id;
        this.jagUrn = jagUrn;
        this.linkStatus = linkStatus;
        this.color = color;
        this.collapsed = collapsed;
        this.length = length;
        this.children = children;
        this.jagCell = jagCell;
    }

    public JagCell() {
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getJagUrn() {
        return jagUrn;
    }
    public void setJagUrn(String jagUrn) {
        this.jagUrn = jagUrn;
    }


    public String getLinkStatus() {
        return linkStatus;
    }
    public void setLinkStatus(String linkStatus) {
        this.linkStatus = linkStatus;
    }


    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }


    public Boolean getCollapsed() {
        return collapsed;
    }
    public void setCollapsed(Boolean collapsed) {
        this.collapsed = collapsed;
    }


    public Integer getLength() {
        return length;
    }
    public void setLength(Integer length) {
        this.length = length;
    }


    public List<JagCell> getChildren() {
        return children;
    }
    public void setChildren(List<JagCell> children) {
        this.children = children;
    }


    public JagCell getJagCell() {
        return jagCell;
    }
    public void setJagCell(JagCell jagCell) {
        this.jagCell = jagCell;
    }

    @Override
    public String toString() {
        return "JagCell{" +
                "id='" + id + '\'' +
                ", jagUrn='" + jagUrn + '\'' +
                ", linkStatus='" + linkStatus + '\'' +
                ", color='" + color + '\'' +
                ", collapsed=" + collapsed +
                ", length=" + length +
                ", children=" + children +
                '}';
    }
}


