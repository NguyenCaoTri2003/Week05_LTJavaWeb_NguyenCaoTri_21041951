package vn.edu.iuh.fit.week05.backend.models;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class JobSkillId implements Serializable {
    private Long jobId;
    private Long skillId;

    public JobSkillId() {}

    public JobSkillId(Long jobId, Long skillId) {
        this.jobId = jobId;
        this.skillId = skillId;
    }


    // Constructors, getters, setters, hashCode, equals
}