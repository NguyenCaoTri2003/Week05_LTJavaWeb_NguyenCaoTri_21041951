package vn.edu.iuh.fit.week05.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week05.backend.models.*;
import vn.edu.iuh.fit.week05.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.week05.backend.repositories.CandidateSkillRepository;
import vn.edu.iuh.fit.week05.backend.repositories.JobRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServices {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    // Gợi ý công việc cho ứng viên dựa trên kỹ năng
    public List<Job> suggestJobsForCandidate(Candidate candidate) {
        // Lấy danh sách kỹ năng của ứng viên từ CandidateSkill
        List<CandidateSkill> candidateSkills = candidateSkillRepository.findByCandidateId(candidate.getId());

        List<Job> suggestedJobs = new ArrayList<>();
        for (CandidateSkill candidateSkill : candidateSkills) {
            // Tìm công việc theo kỹ năng
            suggestedJobs.addAll(jobRepository.findByJobSkills_SkillId(candidateSkill.getSkill().getId()));
        }

        return suggestedJobs;
    }

    // Tìm ứng viên phù hợp với công việc
    public List<Candidate> findCandidatesForJob(Job job) {
        List<Candidate> suitableCandidates = new ArrayList<>();
        for (JobSkill jobSkill : job.getJobSkills()) {
            // Lấy danh sách CandidateSkill theo kỹ năng từ JobSkill
            List<CandidateSkill> candidateSkills = candidateSkillRepository.findBySkillId(jobSkill.getSkill().getId());
            for (CandidateSkill candidateSkill : candidateSkills) {
                suitableCandidates.add(candidateSkill.getCandidate());
            }
        }
        return suitableCandidates;
    }
}


