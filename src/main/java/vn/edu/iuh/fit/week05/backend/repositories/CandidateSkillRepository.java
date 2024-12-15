package vn.edu.iuh.fit.week05.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.week05.backend.enums.SkillLevel;
import vn.edu.iuh.fit.week05.backend.models.Candidate;
import vn.edu.iuh.fit.week05.backend.models.CandidateSkill;
import vn.edu.iuh.fit.week05.backend.models.CandidateSkillId;

import java.util.List;

public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, CandidateSkillId> {
    List<CandidateSkill> findByCandidateId(Long candidateId);

    List<CandidateSkill> findBySkillId(Long skillId);
    @Query("""
        SELECT cs.candidate 
        FROM CandidateSkill cs 
        WHERE cs.skill.skillName IN :skills AND cs.skillLevel >= :requiredSkillLevel
    """)
    List<Candidate> findCandidatesBySkillsAndLevel(
            @Param("skills") List<String> skills,
            @Param("requiredSkillLevel") SkillLevel requiredSkillLevel
    );
}
