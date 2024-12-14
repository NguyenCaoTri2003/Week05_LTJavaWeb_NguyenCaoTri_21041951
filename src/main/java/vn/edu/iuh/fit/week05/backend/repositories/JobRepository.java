package vn.edu.iuh.fit.week05.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.week05.backend.models.Job;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByJobSkills_SkillId(Long skillId);
    Optional<Job> findById(int jobId);
}
