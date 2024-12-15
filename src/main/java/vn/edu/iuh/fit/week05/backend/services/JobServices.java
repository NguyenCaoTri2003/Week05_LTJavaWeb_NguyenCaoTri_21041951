package vn.edu.iuh.fit.week05.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week05.backend.models.Company;
import vn.edu.iuh.fit.week05.backend.models.Job;
import vn.edu.iuh.fit.week05.backend.models.JobSkill;
import vn.edu.iuh.fit.week05.backend.models.Skill;
import vn.edu.iuh.fit.week05.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.week05.backend.repositories.JobRepository;
import vn.edu.iuh.fit.week05.backend.repositories.JobSkillRepository;
import vn.edu.iuh.fit.week05.backend.repositories.SkillRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServices {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobSkillRepository jobSkillRepository;

    @Autowired
    private SkillRepository skillRepository;  // Thêm SkillRepository

    @Autowired
    private CompanyRepository companyRepository;  // Thêm CompanyRepository

    // Lấy tất cả công việc với phân trang
    public Page<Job> getAllJobs(Pageable pageable) {
        return jobRepository.findAll(pageable);
    }

    // Tìm kiếm công việc theo kỹ năng và tên
    public List<Job> findJobsBySkillsAndSearch(List<String> skills, String search) {
        if (skills.isEmpty() && (search == null || search.isEmpty())) {
            return jobRepository.findAll();  // Nếu không có tìm kiếm, trả về tất cả công việc
        } else if (!skills.isEmpty()) {
            // Tìm kiếm JobSkill theo kỹ năng
            List<Skill> skillEntities = skillRepository.findBySkillNameIn(skills);
            List<JobSkill> jobSkills = jobSkillRepository.findBySkillIn(skillEntities);
            return jobSkills.stream()
                    .map(JobSkill::getJob)
                    .distinct()
                    .collect(Collectors.toList());
        } else {
            return jobRepository.findByNameContaining(search);
        }
    }

    // Tìm công việc theo ID
    public Job findJobById(Long jobId) {
        return jobRepository.findById(jobId).orElse(null);
    }


    // Lưu công việc mới
    public void saveJob(Job job) {
        jobRepository.save(job);
    }

    // Xóa công việc
    public void deleteJob(Long jobId) {
        jobRepository.deleteById(jobId);
    }
    // Lấy tất cả công ty
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // Lấy tất cả kỹ năng
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

}
