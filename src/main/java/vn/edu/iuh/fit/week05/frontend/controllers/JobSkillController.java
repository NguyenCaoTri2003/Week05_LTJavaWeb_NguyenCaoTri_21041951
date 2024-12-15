package vn.edu.iuh.fit.week05.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.week05.backend.models.JobSkill;
import vn.edu.iuh.fit.week05.backend.services.JobSkillServices;

import java.util.List;

@RestController
@RequestMapping("/api/job-skills")
public class JobSkillController {

    @Autowired
    private JobSkillServices jobSkillService;

    @GetMapping("/job/{jobId}")
    public List<JobSkill> getSkillsForJob(@PathVariable long jobId) {
        return jobSkillService.getSkillsForJob(jobId);
    }
}
