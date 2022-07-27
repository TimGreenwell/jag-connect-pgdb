package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity(name = "Subactivity")
@Table(name = "Subactivity")


public class Subactivity {
    @Id
    @Column(name = "subactivity_pk", nullable = false)
    private String id;

    @Column(name = "subactivity_urn", nullable = false)
    private String urn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="subactivity_parent_fk")
    private Activity activity;

    @JsonBackReference
    public void setActivity(Activity activity) {
        this.activity = activity;
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

    public Activity getActivity() {
        return activity;
    }

    public Subactivity() {
    }

    public Subactivity(String id, String urn, Activity activity) {
        this.id = id;
        this.urn = urn;
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "Subactivity{" +
                "id='" + id + '\'' +
                ", urn='" + urn + '\'' +
                ", activity=" + activity +
                '}';
    }
}
