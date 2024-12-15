package vn.edu.iuh.fit.week05.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.week05.backend.models.Job;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    // Tìm công việc theo tên (search)
    List<Job> findByNameContaining(String name);
}
