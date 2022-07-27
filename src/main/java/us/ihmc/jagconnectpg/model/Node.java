package us.ihmc.jagconnectpg.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Node")
@Table(name = "Node")

public class Node {

    @Id
    @Column(name = "node_pk", nullable = false)
    private String id;


    @Column(name = "node_urn", nullable = true)
    private String urn;


    @Column(name = "node_child_id", nullable = true)
    private String childId;


    @Column(name = "node_parent_id", nullable = true)
    private String parentId;


    @Column(name = "node_project_id", nullable = true)
    private String projectId;


    @JsonProperty("isExpanded")
    @Column(name = "node_is_expanded", nullable = true)
    private Boolean isExpanded;

    @JsonProperty("isLocked")
    @Column(name = "node_is_locked", nullable = true)
    private Boolean isLocked;


    @Column(name = "node_con_name", nullable = true)
    private String contextualName;


    @Column(name = "node_con_desc", nullable = true)
    private String contextualDescription;


    @Column(name = "node_x", nullable = true)
    private int x;
    @Column(name = "node_y", nullable = true)
    private int y;


    @OneToMany(
            mappedBy = "node",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Subscription> subscriptions = new ArrayList<>();

    @Column(name = "node_return_value", nullable = true)
    private String returnValue;

    @Column(name = "node_return_state", nullable = true)
    private String returnState;

    @Column(name = "node_test_return_value", nullable = true)
    private String testReturnValue;

    @Column(name = "node_test_return_state", nullable = true)
    private String testReturnState;


    @OneToMany(
            mappedBy = "node",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public List<Node> children = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="node_child_parent_fk", nullable=true)
    private Node node;




    @JsonManagedReference
    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @JsonManagedReference
    public void setChildren(List<Node> children) {
        this.children = children;
    }

    @JsonBackReference
    public void setNode(Node node) {
        this.node = node;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrn() {
        return urn;
    }

    public void setUrn(String urn) {
        this.urn = urn;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Boolean getExpanded() {
        return isExpanded;
    }

    public void setExpanded(Boolean expanded) {
        isExpanded = expanded;
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

    public List<Node> getChildren() {
        return children;
    }

    public Node getNode() {
        return node;
    }

    public Node(String id, String urn, String childId, String parentId, String projectId, Boolean isExpanded, Boolean isLocked, String contextualName, String contextualDescription, int x, int y, List<Subscription> subscriptions, String returnValue, String returnState, String testReturnValue, String testReturnState, List<Node> children, Node node) {
        this.id = id;
        this.urn = urn;
        this.childId = childId;
        this.parentId = parentId;
        this.projectId = projectId;
        this.isExpanded = isExpanded;
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
        this.node = node;
    }

    public Node() {
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id + '\'' +
                ", urn='" + urn + '\'' +
                ", childId='" + childId + '\'' +
                ", parentId='" + parentId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", isExpanded=" + isExpanded +
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
                ", node=" + node +
                '}';
    }
}


