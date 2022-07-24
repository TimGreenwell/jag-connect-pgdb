package us.ihmc.jagconnectpg.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Node")
@Table(name = "Node")
public class JagCell {
    @Id
    @Column(name = "node_pk", nullable = false)
    private String id;
    @Column(name = "node_jag", nullable = true)
    private String jagUrn;

    @Column(name = "node_childId", nullable = true)
    private String childId;

    @Column(name = "node_projectId", nullable = true)
    private String projectId;

    @Column(name = "node_expanded", nullable = true)
    private Boolean expanded;
    @Column(name = "node_isLocked", nullable = true)
    private Boolean isLocked;

    @Column(name = "node_conName", nullable = true)
    private String contextualName;
    @Column(name = "node_conDesc", nullable = true)
    private String contextualDescription;

    @Column(name = "node_x", nullable = true)
    private int x;
    @Column(name = "node_y", nullable = true)
    private int y;

    @OneToMany(
            mappedBy = "jagCell",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Subscription> subscriptions = new ArrayList<>();


    @Column(name = "node_returnValue", nullable = true)
    private String returnValue;
    @Column(name = "node_returnState", nullable = true)
    private String returnState;

    @Column(name = "node_testReturnValue", nullable = true)
    private String testReturnValue;
    @Column(name = "node_testReturnState", nullable = true)
    private String testReturnState;

    @OneToMany(
            mappedBy = "jagCell",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<JagCell> children = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="node_cell_fk", nullable=true)
    @JsonBackReference
    private JagCell jagCell;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="node_parent_fk", nullable = true)
//    @JsonBackReference
//    private JagCell jagCell;



//    public JagCell(String id, String jagUrn, String linkStatus, String color, Boolean collapsed, Integer length, List<JagCell> children, JagCell jagCell) {
//        this.id = id;
//        this.jagUrn = jagUrn;
//        this.linkStatus = linkStatus;
//        this.color = color;
//        this.collapsed = collapsed;
//        this.length = length;
//        this.children = children;
//        this.jagCell = jagCell;
//    }


    public JagCell(String id,
                   String jagUrn,
                   String childId,
                   String projectId,
                   Boolean expanded,
                   Boolean isLocked,
                   String contextualName,
                   String contextualDescription,
                   int x, int y,
                   List<Subscription> subscriptions,
                   String returnValue,
                   String returnState,
                   String testReturnValue,
                   String testReturnState,
                   List<JagCell> children,
                   JagCell jagCell) {

        this.id = id;
        this.jagUrn = jagUrn;
        this.childId = childId;
        this.projectId = projectId;
        this.expanded = expanded;
        this.isLocked = isLocked;
        this.contextualName = contextualName;
        this.contextualDescription = contextualDescription;
        this.x = x;
        this.y = y;
        this.subscriptions = subscriptions;
        this.returnValue = returnValue;
        this.returnState = returnState;
        this.testReturnValue = testReturnValue;
        this.testReturnState = testReturnState;
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

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    public String getContextualName() {
        return contextualName;
    }

    public void setContextualName(String contextualName) {
        this.contextualName = contextualName;
    }

    public String getContextualDescription() {
        return contextualDescription;
    }

    public void setContextualDescription(String contextualDescription) {
        this.contextualDescription = contextualDescription;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public String getReturnState() {
        return returnState;
    }

    public void setReturnState(String returnState) {
        this.returnState = returnState;
    }

    public String getTestReturnValue() {
        return testReturnValue;
    }

    public void setTestReturnValue(String testReturnValue) {
        this.testReturnValue = testReturnValue;
    }

    public String getTestReturnState() {
        return testReturnState;
    }

    public void setTestReturnState(String testReturnState) {
        this.testReturnState = testReturnState;
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
                ", childId='" + childId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", expanded=" + expanded +
                ", isLocked=" + isLocked +
                ", contextualName='" + contextualName + '\'' +
                ", contextualDescription='" + contextualDescription + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", subscriptions=" + subscriptions +
                ", returnValue='" + returnValue + '\'' +
                ", returnState='" + returnState + '\'' +
                ", testReturnValue='" + testReturnValue + '\'' +
                ", testReturnState='" + testReturnState + '\'' +
                ", children=" + children +
                ", jagCell=" + jagCell +
                '}';
    }
}


