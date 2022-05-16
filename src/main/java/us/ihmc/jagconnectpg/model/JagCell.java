package us.ihmc.jagconnectpg.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Nodes")
@Table(name = "nodes")
public class JagCell {

    private String id;
    private String jagUrn;
    private String linkStatus;
    private String color;
    private Boolean collapsed;
    private Integer length;
    private List<JagCell> children = new ArrayList<>();
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

    @Id
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "jag", nullable = false)
    public String getJagUrn() {
        return jagUrn;
    }
    public void setJagUrn(String jagUrn) {
        this.jagUrn = jagUrn;
    }

    @Column(name = "link_status", nullable = false)
    public String getLinkStatus() {
        return linkStatus;
    }
    public void setLinkStatus(String linkStatus) {
        this.linkStatus = linkStatus;
    }

    @Column(name = "color", nullable = false)
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "collapsed", nullable = false)
    public Boolean getCollapsed() {
        return collapsed;
    }
    public void setCollapsed(Boolean collapsed) {
        this.collapsed = collapsed;
    }

    @Column(name = "length", nullable = false)
    public Integer getLength() {
        return length;
    }
    public void setLength(Integer length) {
        this.length = length;
    }

    @Column(name = "children", nullable = false)
    @OneToMany(
            mappedBy = "jagCell",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public List<JagCell> getChildren() {
        return children;
    }
    public void setChildren(List<JagCell> children) {
        this.children = children;
    }

    @ManyToOne(fetch = FetchType.LAZY)
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


