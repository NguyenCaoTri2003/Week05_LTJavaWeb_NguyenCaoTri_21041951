package vn.edu.iuh.fit.week05.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate dob;
    private String email;
    @Column(name = "full_name")
    private String fullName;
    private String phone;
    @OneToOne
    @JoinColumn(name = "address")
    private Address address;
    @OneToMany(mappedBy = "candidate")
    private List<CandidateSkill> candidateSkills;

    public Candidate() {
    }

    public Candidate(LocalDate dob, String email, String fullName, String phone, Address address, List<CandidateSkill> candidateSkills) {
        this.dob = dob;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.candidateSkills = candidateSkills;
    }
}
