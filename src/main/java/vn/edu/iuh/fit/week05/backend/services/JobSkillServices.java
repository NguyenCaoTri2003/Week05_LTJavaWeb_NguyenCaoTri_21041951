package vn.edu.iuh.fit.week05.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week05.backend.models.JobSkill;
import vn.edu.iuh.fit.week05.backend.repositories.JobSkillRepository;

import java.util.List;

@Service
public class JobSkillServices {

    @Autowired
    private JobSkillRepository jobSkillRepository;

    // Lấy tất cả kỹ năng cho công việc
    public List<JobSkill> getSkillsForJob(long jobId) {
        return jobSkillRepository.findAllByJobId(jobId);  // Assuming we have a custom query to fetch skills by jobId
    }
}
