package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Activity")
@Table(name = "Activity")


public class Activity {
    @Id
    @Column(name = "activity_urn")
    private String urn;

    @Column(name = "activity_name", nullable = false)
    private String name;

    @Column(name = "activity_description", nullable = true)
    private String description;

    @Embedded
    private Connector connector = new Connector();

    @OneToMany(
            mappedBy = "activity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Input> inputs;

    @OneToMany(
            mappedBy = "activity" ,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Output> outputs;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy="activity"  ,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Subactivity> children;


    @OneToMany(
            mappedBy = "activity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Binding> bindings;

    @Column(name = "activity_author", nullable = true)
    private String author;

    @Column(name = "activity_created_date", nullable = true)
    private Date createdDate;

    @Column(name = "activity_modified_date", nullable = true)
    private Date modifiedDate;

    @Column(name = "activity_locked_by", nullable = true)
    private String lockedBy;

    @Column(name = "activity_is_locked", nullable = true)
    private Boolean isLocked;

    @Column(name = "activity_collapsed", nullable = true)
    private Boolean collapsed;




    @JsonManagedReference
    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }
    @JsonManagedReference
    public void setOutputs(List<Output> outputs) {
        this.outputs = outputs;
    }
    @JsonManagedReference
    public void setChildren(List<Subactivity> children) {
        this.children = children;
    }
    @JsonManagedReference
    public void setBindings(List<Binding> bindings) {
        this.bindings = bindings;
    }

    public String getUrn() {
        return urn;
    }

    public void setUrn(String urn) {
        this.urn = urn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Connector getConnector() {
        return connector;
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public List<Output> getOutputs() {
        return outputs;
    }

    public List<Subactivity> getChildren() {
        return children;
    }

    public List<Binding> getBindings() {
        return bindings;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getLockedBy() {
        return lockedBy;
    }

    public void setLockedBy(String lockedBy) {
        this.lockedBy = lockedBy;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    public Boolean getCollapsed() {
        return collapsed;
    }

    public void setCollapsed(Boolean collapsed) {
        this.collapsed = collapsed;
    }

    public Activity() {
    }
    public Activity(String urn, String name, String description, Connector connector, List<Input> inputs, List<Output> outputs, List<Subactivity> children, List<Binding> bindings, String author, Date createdDate, Date modifiedDate, String lockedBy, Boolean isLocked, Boolean collapsed) {
        this.urn = urn;
        this.name = name;
        this.description = description;
        this.connector = connector;
        this.inputs = inputs;
        this.outputs = outputs;
        this.children = children;
        this.bindings = bindings;
        this.author = author;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.lockedBy = lockedBy;
        this.isLocked = isLocked;
        this.collapsed = collapsed;
    }



    @Override
    public String toString() {
        return "Activity{" +
                "urn='" + urn + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", connector=" + connector +
                ", inputs=" + inputs +
                ", outputs=" + outputs +
                ", children=" + children +
                ", bindings=" + bindings +
                ", author='" + author + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", lockedBy='" + lockedBy + '\'' +
                ", isLocked=" + isLocked +
                ", collapsed=" + collapsed +
                '}';
    }


    //    public Connector getConnector() {
//        return connector;
//    }
//    public void setConnector(Connector connector) {
//        this.connector.setExecution(connector.getExecution());
//        this.connector.setReturns(connector.getReturns());
//        this.connector.setOperator(connector.getOperator());
//    }
}

//    public void setChildren(List<JagActivityChild> children) {
//        this.children.clear();
//        this.children.addAll(children);
//    }


//
//    public void setInputs(List<Input> inputs) {
//        this.inputs.clear();
//        this.inputs.addAll(inputs);
//    }
//
//    public List<Output> getOutputs() {
//        return outputs;
//    }
//    public void setOutputs(List<Output> outputs) {
//        this.outputs.clear();
//        this.outputs.addAll(outputs);
//    }
//
//    public List<Binding> getBindings() {
//        return bindings;
//    }
//    public void setBindings(List<Binding> bindings) {
//        this.bindings.clear();
//        this.bindings.addAll(bindings);
//    }
//


