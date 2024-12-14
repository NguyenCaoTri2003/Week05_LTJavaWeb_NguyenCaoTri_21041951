package vn.edu.iuh.fit.week05.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.edu.iuh.fit.week05.backend.enums.SkillType;

import java.util.List;

@Getter
@Setter
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private long id;
    @Column(name = "skill_description")
    private String skillDescription;
    @Column(name = "skill_name")
    private String skillName;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private SkillType skillType;
    @OneToMany(mappedBy = "skill")
    private List<CandidateSkill> candidateSkills;


    public Skill() {
    }

    public Skill(String skillDescription, String skillName, SkillType skillType, List<CandidateSkill> candidateSkills) {
        this.skillDescription = skillDescription;
        this.skillName = skillName;
        this.skillType = skillType;
        this.candidateSkills = candidateSkills;
    }
}
