package us.ihmc.jagconnectpg.model;

import lombok.*;
import javax.persistence.*;

@Embeddable
@Table(name = "Connector")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
@EqualsAndHashCode
@Builder

public class Connector {

    @Column(name = "connector_exec", nullable = false)
    private String execution;

    @Column(name = "connector_rtns", nullable = true)
    private String returns;

    @Column(name = "connector_oper", nullable = false)
    private String operator;

}
