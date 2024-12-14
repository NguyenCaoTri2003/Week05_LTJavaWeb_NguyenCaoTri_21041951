package vn.edu.iuh.fit.week05.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private long id;
    @Column(name = "job_name")
    private String name;
    @OneToOne
    @JoinColumn(name = "company")
    private Company company;
    @OneToMany(mappedBy = "job")
    private List<JobSkill> jobSkills;

    @Column(name = "job_desc")
    private String description;

    public Job() {
    }

    public Job(String name, Company company, List<JobSkill> jobSkills, String description) {
        this.name = name;
        this.company = company;
        this.jobSkills = jobSkills;
        this.description = description;
    }
}

