package vn.edu.iuh.fit.week05.frontend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.week05.backend.enums.SkillLevel;
import vn.edu.iuh.fit.week05.backend.models.Candidate;
import vn.edu.iuh.fit.week05.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.week05.backend.services.CandidateServices;
import vn.edu.iuh.fit.week05.backend.services.EmailServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateServices candidateServices;
    @Autowired
    private EmailServices emailService;

//    @GetMapping("/list")
//    public String showCandidateList(Model model) {
//        model.addAttribute("candidates", candidateRepository.findAll());
//        return "candidates/candidates";
//    }

//    @GetMapping("/list")
//    public String listCandidates(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            Model model) {
//        Page<Candidate> candidatePage = candidateServices.getAllCandidates(PageRequest.of(page, size));
//        int totalPages = candidatePage.getTotalPages();
//        List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
//                .boxed()
//                .collect(Collectors.toList());
//
//        model.addAttribute("candidatePage", candidatePage);
//        model.addAttribute("pageNumbers", pageNumbers);
//        return "candidates/candidates";
//    }

        @GetMapping("/list")
        public String showCandidateList(
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size,
                Model model) {

            Pageable pageable = PageRequest.of(page, size);
            Page<Candidate> candidatePage = candidateRepository.findAll(pageable);

            model.addAttribute("candidates", candidatePage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", candidatePage.getTotalPages());
            model.addAttribute("totalItems", candidatePage.getTotalElements());

            return "candidates/candidates";
        }

    @GetMapping("/candidates")
    public String showCandidateListPaging(Model model,
                                          @RequestParam("page") Optional<Integer> page,
                                          @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Candidate> candidatePage= candidateServices.findAll(
                currentPage - 1,pageSize,"id","asc");
        model.addAttribute("candidatePage", candidatePage);
        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "candidates/candidates-paging";
    }

//    @GetMapping("/jobs/{id}/candidates")
//    public String findCandidatesByJobId(@PathVariable("id") Long jobId, Model model) {
//        List<Candidate> candidates = candidateServices.findCandidatesByJobId(jobId);
//        model.addAttribute("candidates", candidates);
//        model.addAttribute("jobId", jobId);
//        return "job-candidates";
//    }
//
//    @GetMapping("/search")
//    public String searchCandidatesByJobId(@RequestParam("jobId") Long jobId, Model model) {
//        return "redirect:/jobs/" + jobId + "candidates/candidates";
//    }
    @GetMapping("/search")
    public String searchCandidates(@RequestParam("jobSkills") List<String> jobSkills,
                                   @RequestParam("requiredSkillLevel") SkillLevel requiredSkillLevel,
                                   @RequestParam("search") String search, Model model) {
        List<Candidate> candidates = candidateServices.findCandidatesByJobSkillsAndLevelAndSearch(jobSkills, requiredSkillLevel, search);
        model.addAttribute("candidates", candidates);
        return "candidates/candidates";
    }

    @GetMapping("/search-candidates")
    public String searchCandidates(@RequestParam(name = "skills", required = false) String skills,
                                   @RequestParam(name = "skillLevel", required = false) SkillLevel skillLevel,
                                   @RequestParam(name = "search", required = false) String search,
                                   Model model) {
        // Xử lý danh sách kỹ năng
        List<String> skillList = (skills != null && !skills.isEmpty()) ?
                List.of(skills.split(",")) : List.of();

        // Tìm ứng viên phù hợp
        List<Candidate> candidates = candidateServices.findCandidatesByJobSkillsAndLevelAndSearch(
                skillList, skillLevel, search);
        model.addAttribute("candidates", candidates);
        return "candidates/candidates";
    }

    @GetMapping("/search-by-skill")
    public ResponseEntity<List<Candidate>> searchCandidatesBySkill(@RequestParam String skillName) {
        List<Candidate> candidates = candidateServices.findCandidatesBySkillName(skillName);
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/candidates/search")
    public String searchCandidatesBySkill(@RequestParam(value = "skillName", required = false) String skillName, Model model) {
        List<Candidate> candidates = new ArrayList<>();

        if (skillName != null && !skillName.isEmpty()) {
            candidates = candidateServices.findCandidatesBySkillName(skillName);
        }

        model.addAttribute("candidates", candidates);
        return "candidates/candidates";  // Chuyển đến trang candidates.html
    }

    @PostMapping("/invite")
    public String sendInvite(
            @RequestParam("candidateId") Long candidateId,
            Model model) {
        // Lấy thông tin ứng viên
        Candidate candidate = candidateServices.findById(candidateId);

        // Nội dung email
        String subject = "Job Invitation";
        String body = "Dear " + candidate.getFullName() + ",\n\n"
                + "We are pleased to invite you to apply for a position at our company. "
                + "If you're interested, please contact us.\n\n"
                + "Best regards,\nYour Company";

        // Gửi email
        emailService.sendEmail(candidate.getEmail(), subject, body);

        model.addAttribute("message", "Invitation sent to " + candidate.getFullName());
        return "redirect:/candidates/search";
    }

}