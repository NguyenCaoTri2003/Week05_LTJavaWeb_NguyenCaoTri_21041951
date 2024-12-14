package vn.edu.iuh.fit.week05.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.week05.backend.models.Candidate;
import vn.edu.iuh.fit.week05.backend.models.Job;
import vn.edu.iuh.fit.week05.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.week05.backend.repositories.JobRepository;
import vn.edu.iuh.fit.week05.backend.services.EmailServices;
import vn.edu.iuh.fit.week05.backend.services.JobServices;

import java.util.List;

@Controller
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobServices jobService;
    @Autowired
    private EmailServices emailService;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private JobRepository jobRepository;

//    @GetMapping("/suggestions/{candidateId}")
//    public String suggestJobsForCandidate(@PathVariable Long candidateId, Model model) {
//        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
//        List<Job> jobs = jobService.suggestJobsForCandidate(candidate);
//        model.addAttribute("jobs", jobs);
//        return "suggestions";
//    }
    @GetMapping("/suggestions/{candidateId}")
    public String suggestJobsForCandidate(@PathVariable Long candidateId, Model model) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        List<Job> jobs = jobService.suggestJobsForCandidate(candidate);
        if (jobs.isEmpty()) {
            // Log lỗi nếu không có việc làm
            System.out.println("No jobs found for candidate: " + candidateId);
        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("candidate", candidate);  // Thêm thông tin ứng viên vào model
        return "suggestions";
    }

    // Gửi email mời ứng viên
    @PostMapping("/send-invite/{candidateId}")
    public String sendInviteToCandidate(@PathVariable Long candidateId, @RequestParam Long jobId) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        Job job = jobRepository.findById(jobId).orElseThrow();

        // Gửi email mời ứng viên
        emailService.sendEmail(candidate.getEmail(), "Lời mời ứng tuyển",
                "Chúng tôi muốn mời bạn tham gia ứng tuyển cho vị trí: " + job.getName());

        // Sau khi gửi email, chuyển hướng lại trang suggestions của ứng viên
        return "redirect:/jobs/suggestions/" + candidateId;
    }
}

