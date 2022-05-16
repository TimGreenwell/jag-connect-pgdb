package us.ihmc.jagconnectpg.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "JagActivity")
@Table(name = "joint_activity_graph")
public class JagActivity {

    private String urn;
    private String description;
    private String name;
    private String type;
    private List<JagActivityChild> children = new ArrayList<>();
    private Connector connector;
    private List<Input> inputs = new ArrayList<>();
    private List<Output> outputs = new ArrayList<>();
    private List<Binding> bindings = new ArrayList<>();


    public JagActivity(String urn, String description, String name, String type,
                       List<JagActivityChild> children, Connector connector,
                       List<Input> inputs, List<Output> outputs, List<Binding> bindings) {
        this.urn = urn;
        this.description = description;
        this.name = name;
        this.type = type;
        this.children = children;
        this.connector = connector;
        this.inputs = inputs;
        this.outputs = outputs;
        this.bindings = bindings;
    }

    public JagActivity() { }



    @Id
    @Column(name = "activity_urn")
    public String getUrn() {
        return urn;
    }
    public void setUrn(String urn) {
        this.urn = urn;
    }

    @Column(name = "activity_description", nullable = true)
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "activity_name", nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "activity_type", nullable = true)
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "activity_children", nullable = true)
    @OneToMany(
            mappedBy = "jagActivity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public List<JagActivityChild> getChildren() {
        return children;
    }
    public void setChildren(List<JagActivityChild> children) {
        this.children = children;
    }

    @OneToOne
    public Connector getConnector() {
        return connector;
    }
    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    @Column(name = "activity_inputs", nullable = true)
    @OneToMany(
            mappedBy = "jagActivity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public List<Input> getInputs() {
        return inputs;
    }
    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

    @Column(name = "activity_outputs", nullable = true)
    @OneToMany(
            mappedBy = "jagActivity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public List<Output> getOutputs() {
        return outputs;
    }
    public void setOutputs(List<Output> outputs) {
        this.outputs = outputs;
    }

    @Column(name = "bindings", nullable = true)
    @OneToMany(
            mappedBy = "jagActivity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public List<Binding> getBindings() {
        return bindings;
    }
    public void setBindings(List<Binding> bindings) {
        this.bindings = bindings;
    }

    @Override
    public String toString() {
        return "JagActivity{" +
                "urn='" + urn + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", children=" + children +
                ", connector=" + connector +
                ", inputs=" + inputs +
                ", outputs=" + outputs +
                ", bindings=" + bindings +
                '}';
    }
}

