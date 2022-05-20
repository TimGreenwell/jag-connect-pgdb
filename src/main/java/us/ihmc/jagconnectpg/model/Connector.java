package us.ihmc.jagconnectpg.model;

import javax.persistence.*;

@Embeddable
@Table(name = "Connector")
public class Connector {
    @Column(name = "connector_exec", nullable = false)
    private String execution;
    @Column(name = "connector_oper", nullable = false)
    private String operator;

    public Connector(String execution, String operator) {
        this.execution = execution;
        this.operator = operator;
    }

    public Connector() {
    }

    public String getExecution() {
        return execution;
    }
    public void setExecution(String execution) {
        this.execution = execution;
    }

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
