package vn.edu.iuh.fit.week05.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.week05.backend.models.Company;
import vn.edu.iuh.fit.week05.backend.models.Job;
import vn.edu.iuh.fit.week05.backend.models.JobSkill;
import vn.edu.iuh.fit.week05.backend.models.Skill;
import vn.edu.iuh.fit.week05.backend.services.JobServices;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class JobController {

    @Autowired
    private JobServices jobServices;

    // Hiển thị danh sách công việc
    @GetMapping("/jobs")
    public String showJobList(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Job> jobPage = jobServices.getAllJobs(pageable);

        model.addAttribute("jobs", jobPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", jobPage.getTotalPages());
        model.addAttribute("totalItems", jobPage.getTotalElements());

        return "jobs";  // Trả về view để hiển thị danh sách công việc
    }

//    // Tìm kiếm công việc theo kỹ năng và tên
//    @GetMapping("/search-jobs")
//    public String searchJobs(@RequestParam(name = "skills", required = false) String skills,
//                             @RequestParam(name = "search", required = false) String search,
//                             Model model) {
//        List<String> skillList = (skills != null && !skills.isEmpty()) ?
//                List.of(skills.split(",")) : List.of();
//
//        List<Job> jobs = jobServices.findJobsBySkillsAndSearch(skillList, search);
//        model.addAttribute("jobs", jobs);
//
//        return "jobs";  // Trả về trang công việc sau khi tìm kiếm
//    }
//
//    // API tìm kiếm công việc theo kỹ năng (sử dụng Ajax hoặc React)
//    @GetMapping("/api/jobs/search")
//    public ResponseEntity<List<Job>> searchJobsApi(@RequestParam(value = "skills", required = false) String skills,
//                                                   @RequestParam(value = "search", required = false) String search) {
//        List<String> skillList = (skills != null && !skills.isEmpty()) ?
//                List.of(skills.split(",")) : List.of();
//
//        List<Job> jobs = jobServices.findJobsBySkillsAndSearch(skillList, search);
//        return ResponseEntity.ok(jobs);
//    }

    // Hiển thị công việc chi tiết khi nhấp vào công việc
    @GetMapping("/jobs/{id}")
    public String showJobDetails(@PathVariable("id") Long jobId, Model model) {
        Job job = jobServices.findJobById(jobId);
        model.addAttribute("job", job);
        return "job-detail";  // Trang chi tiết công việc
    }

    // Hiển thị form thêm công việc
//    @GetMapping("/jobs/new")
//    public String showAddJobForm(Model model) {
//        model.addAttribute("job", new Job());
//        return "add-job";  // Trả về trang form để thêm công việc
//    }

    // Thêm công việc mới
//    @PostMapping("/jobs/new")
//    public String addJob(@ModelAttribute Job job) {
//        jobServices.saveJob(job);  // Lưu công việc mới vào cơ sở dữ liệu
//        return "redirect:/jobs";  // Quay lại danh sách công việc
//    }

    // Xóa công việc
    @PostMapping("/jobs/delete")
    public String deleteJob(@RequestParam("jobId") Long jobId) {
        jobServices.deleteJob(jobId);  // Xóa công việc theo ID
        return "redirect:/jobs";  // Quay lại danh sách công việc
    }

    @GetMapping("/jobs/new")
    public String showJobForm(Model model) {
        model.addAttribute("companies", jobServices.getAllCompanies());
        model.addAttribute("skills", jobServices.getAllSkills());
        model.addAttribute("job", new Job());
        return "add-job";  // jobForm là tên file HTML của bạn
    }

    @PostMapping("/jobs/new")
    public String addJob(Job job, @RequestParam("company") Long companyId, @RequestParam("skills") List<Long> skillIds) {
        Company company = new Company();
        company.setId(companyId);
        job.setCompany(company);

        List<JobSkill> jobSkills = skillIds.stream()
                .map(id -> {
                    JobSkill jobSkill = new JobSkill();
                    jobSkill.setJob(job);
                    jobSkill.setSkill(new Skill());  // Giả sử bạn có constructor với ID cho Skill
                    return jobSkill;
                })
                .collect(Collectors.toList());
        job.setJobSkills(jobSkills);

        jobServices.saveJob(job);
        return "redirect:/jobs";  // Redirect về trang danh sách công việc hoặc trang khác
    }
}


