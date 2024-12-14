package vn.edu.iuh.fit.week05.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.edu.iuh.fit.week05.backend.enums.SkillLevel;

@Getter
@Setter
@Entity
@Table(name = "candidate_skill")
public class CandidateSkill {

    @EmbeddedId
    private CandidateSkillId id;

    @ManyToOne
    @MapsId("candidateId") // Map trường candidateId từ composite key
    @JoinColumn(name = "can_id", nullable = false)
    private Candidate candidate;

    @ManyToOne
    @MapsId("skillId") // Map trường skillId từ composite key
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @Column(name = "more_infos")
    private String moreInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "skill_level")
    private SkillLevel skillLevel;

    @Override
    public String toString() {
        return "CandidateSkill{" +
                "id=" + id +
                ", moreInfo='" + moreInfo + '\'' +
                ", skillLevel=" + skillLevel +
                '}';
    }
}
