package us.ihmc.jagconnectpg.model;

import lombok.Data;
import javax.persistence.*;

@Entity(name = "Analysis")
@Table(name = "Analysis")
@Data
public class Analysis {
    @Id
    @Column(name = "analysis_pk", nullable = true)
    private String id;

    @Column(name = "analysis_name", nullable = false)
    private String name;

    @Column(name = "analysis_desc", nullable = true)
    private String description;

    @Column(name = "analysis_root_urn", nullable = false)
    private String rootUrn;

    @Column(name = "analysis_team", nullable = false)
    private String teamId;

    @Column(name = "analysis_is_locked", nullable = false)
    private String isLocked;

}