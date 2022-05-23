package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.*;

@Entity(name = "Activity")
@Table(name = "Activity")
public class JagActivity {
    @Id
    @Column(name = "activity_urn")
    private String urn;
    @Column(name = "activity_description", nullable = true)
    private String description;
    @Column(name = "activity_name", nullable = false)
    private String name;
    @Column(name = "activity_type", nullable = true)
    private String type;
    @OneToMany(
            mappedBy = "jagActivity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<JagActivityChild> children = new ArrayList<>();
    @OneToMany(
            mappedBy = "jagActivity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Input> inputs = new ArrayList<>();
    @OneToMany(
            mappedBy = "jagActivity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Output> outputs = new ArrayList<>();
    @OneToMany(
            mappedBy = "jagActivity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Binding> bindings = new ArrayList<>();

    @Embedded
    private Connector connector = new Connector();

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

    public String getUrn() {
        return urn;
    }
    public void setUrn(String urn) {
        this.urn = urn;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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

    public List<JagActivityChild> getChildren() {
        return children;
    }
    public void setChildren(List<JagActivityChild> children) {
        this.children.clear();
        this.children.addAll(children);
    }

    public Connector getConnector() {
        return connector;
    }
    public void setConnector(Connector connector) {
        this.connector.setExecution(connector.getExecution());
        this.connector.setOperator(connector.getOperator());
    }

    //@Column(name = "activity_inputs", nullable = true)

    public List<Input> getInputs() {
        return inputs;
    }
    public void setInputs(List<Input> inputs) {
        this.inputs.clear();
        this.inputs.addAll(inputs);
    }

    public List<Output> getOutputs() {
        return outputs;
    }
    public void setOutputs(List<Output> outputs) {
        this.outputs.clear();
        this.outputs.addAll(outputs);
    }

    public List<Binding> getBindings() {
        return bindings;
    }
    public void setBindings(List<Binding> bindings) {
        this.bindings.clear();
        this.bindings.addAll(bindings);
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

