package vn.edu.iuh.fit.week05.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week05.backend.enums.SkillLevel;
import vn.edu.iuh.fit.week05.backend.models.Candidate;
import vn.edu.iuh.fit.week05.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.week05.backend.repositories.CandidateSkillRepository;

import java.util.Collections;
import java.util.List;
@Service
public class CandidateServices {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;


    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return candidateRepository.findAll(pageable);//findFirst.../findTop...
    }

    public List<Candidate> findCandidatesByJobSkillsAndLevel(List<String> jobSkills, SkillLevel requiredSkillLevel) {
        return candidateSkillRepository.findCandidatesBySkillsAndLevel(jobSkills, requiredSkillLevel);
    }

//    public List<Candidate> findCandidatesByJobId(Long jobId) {
//        // Thực hiện tìm kiếm qua CandidateRepository hoặc custom query
//        return candidateRepository.findByJobId(jobId);
//    }

    public List<Candidate> findCandidatesByJobSkillsAndLevelAndSearch(List<String> jobSkills, SkillLevel requiredSkillLevel, String search) {
        return candidateRepository.findBySkillsAndLevelAndSearch(jobSkills, requiredSkillLevel, search);
    }

    public List<Candidate> findCandidatesBySkillName(String skillName) {
        return candidateRepository.findCandidatesBySkillName(skillName);
    }

    public Page<Candidate> getAllCandidates(Pageable pageable) {
        return candidateRepository.findAll(pageable);
    }

    public Candidate findById(Long id) {
        return candidateRepository.findById(id).orElse(null);
    }
}