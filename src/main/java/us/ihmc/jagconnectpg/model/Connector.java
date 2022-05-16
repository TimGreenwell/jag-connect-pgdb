package us.ihmc.jagconnectpg.model;

import javax.persistence.*;

@Entity(name = "Connector")
@Table(name = "connector")
public class Connector {

    private String execution;
    private String operator;
    private Long id;

    public Connector(String execution, String operator) {
        this.execution = execution;
        this.operator = operator;
    }

    public Connector() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "execution", nullable = false)
    public String getExecution() {
        return execution;
    }

    public void setExecution(String execution) {
        this.execution = execution;
    }

    @Column(name = "operator", nullable = false)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "Connector{" +
                "execution='" + execution + '\'' +
                ", operator='" + operator + '\'' +
                '}';
    }

}
