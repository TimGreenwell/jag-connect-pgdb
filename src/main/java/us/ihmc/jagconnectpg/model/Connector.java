package us.ihmc.jagconnectpg.model;

import lombok.Data;

import javax.persistence.*;

@Embeddable
@Table(name = "Connector")
@Data
public class Connector {
    @Column(name = "connector_exec", nullable = false)
    private String execution;

    @Column(name = "connector_rtns", nullable = true)
    private String returns;

    @Column(name = "connector_oper", nullable = false)
    private String operator;

//    public Connector(String execution, String returns, String operator) {
//        this.execution = execution;
//        this.returns = returns;
//        this.operator = operator;
//    }
//
//
//    public Connector() {
//    }
//
//    public String getExecution() {
//        return execution;
//    }
//    public void setExecution(String execution) {
//        this.execution = execution;
//    }
//
//
//    public String getReturns() { return returns; }
//    public void setReturns(String returns) { this.returns = returns; }
//
//    public String getOperator() {
//        return operator;
//    }
//    public void setOperator(String operator) {
//        this.operator = operator;
//    }
//
//    @Override
//    public String toString() {
//        return "Connector{" +
//                "execution='" + execution + '\'' +
//                ", returns='" + returns + '\'' +
//                ", operator='" + operator + '\'' +
//                '}';
//    }
}
