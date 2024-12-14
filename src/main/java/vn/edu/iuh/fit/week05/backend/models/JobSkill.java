package vn.edu.iuh.fit.week05.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.edu.iuh.fit.week05.backend.enums.SkillLevel;

@Getter
@Setter
@ToString
@Entity
@Table(name = "job_skill")
public class JobSkill {
    @EmbeddedId
    private JobSkillId id; // Embedded composite primary key

    @MapsId("jobId") // Maps to the jobId field in JobSkillId
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @MapsId("skillId") // Maps to the skillId field in JobSkillId
    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @Column(name = "more_infos")
    private String moreInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "skill_level")
    private SkillLevel skillLevel;
}