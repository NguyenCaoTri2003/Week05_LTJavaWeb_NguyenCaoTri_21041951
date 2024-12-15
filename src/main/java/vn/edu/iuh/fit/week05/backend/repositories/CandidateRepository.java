package vn.edu.iuh.fit.week05.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.week05.backend.enums.SkillLevel;
import vn.edu.iuh.fit.week05.backend.models.Candidate;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    //List<Candidate> findBySkills_Id(Long skillId);
    Optional<Candidate> findById(int candidateId);
    //List<Candidate> findByJobId(Long jobId);

    @Query("SELECT DISTINCT c FROM Candidate c " +
            "JOIN c.candidateSkills cs " +
            "JOIN cs.skill s " +
            "WHERE s.skillName IN :jobSkills " +
            "AND cs.skillLevel = :requiredSkillLevel " +
            "AND (c.fullName LIKE %:search% OR s.skillName LIKE %:search%)")
    List<Candidate> findBySkillsAndLevelAndSearch(List<String> jobSkills, SkillLevel requiredSkillLevel, String search);

    @Query("""
           SELECT DISTINCT c 
           FROM Candidate c 
           JOIN c.candidateSkills cs 
           JOIN cs.skill s 
           WHERE s.skillName = :skillName
           """)
    List<Candidate> findCandidatesBySkillName(@Param("skillName") String skillName);
}
