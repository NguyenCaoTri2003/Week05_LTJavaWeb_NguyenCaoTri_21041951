package vn.edu.iuh.fit.week05.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.week05.backend.models.Candidate;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    //List<Candidate> findBySkills_Id(Long skillId);
    Optional<Candidate> findById(int candidateId);
}
