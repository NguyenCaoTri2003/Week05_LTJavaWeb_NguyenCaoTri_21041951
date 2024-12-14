package vn.edu.iuh.fit.week05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.week05.backend.enums.SkillLevel;
import vn.edu.iuh.fit.week05.backend.enums.SkillType;
import vn.edu.iuh.fit.week05.backend.models.*;
import vn.edu.iuh.fit.week05.backend.repositories.*;
import com.neovisionaries.i18n.CountryCode;

import java.time.LocalDate;
import java.util.Random;

@SpringBootApplication
public class Week05Application implements CommandLineRunner {
	@Autowired
	private CandidateRepository candidateRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private SkillRepository skillRepository;
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private CandidateSkillRepository candidateSkillRepository;
	@Autowired
	private JobSkillRepository jobSkillRepository;
	@Autowired
	private CompanyRepository companyRepository;

	public static void main(String[] args) {
		SpringApplication.run(Week05Application.class, args);
	}

	@Override
	public void run (String... args) throws Exception {
		System.out.println("Hello World");
	}

	//Random dữ liệu cho Address và Candidate
//	@Bean
//	CommandLineRunner initData(){
//		return args -> {
//			Random rnd =new Random();
//			for (int i = 1; i < 1000; i++) {
//				Address add = new Address(
//						"HCM", // Thành phố
//						CountryCode.VN, // Quốc gia
//						rnd.nextInt(1, 1000) + "",   // Số nhà
//						"Quang Trung", // Đường
//						rnd.nextInt(70000, 80000) + "" // Mã bưu chính
//				);
//
//				addressRepository.save(add);
//				Candidate can = new Candidate(
//						LocalDate.of(1998, rnd.nextInt(1, 13), rnd.nextInt(1, 29)), // dob
//						"email_" + i + "@gmail.com", // email
//						"Name #" + i, // fullName
//						rnd.nextLong(1111111111L, 9999999999L) + "", // phone
//						add // address
//				);
//				candidateRepository.save(can);
//				System.out.println("Added: " +can);
//			}
//		};
//	}
//	@Bean
//	CommandLineRunner initData() {
//		return args -> {
//			Random rnd = new Random();

//			// Tạo dữ liệu cho Skill
//			for (int i = 1; i <= 10; i++) {
//				Skill skill = new Skill(
//						"Description for skill #" + i,
//						"Skill #" + i,
//						i % 2 == 0 ? SkillType.TECHNICAL_SKILL : SkillType.SOFT_SKILL,
//						null
//				);
//				skillRepository.save(skill);
//				System.out.println("Added Skill: " + skill);
//			}
//
//			// Tạo dữ liệu cho Company
//			for (int i = 1; i <= 10; i++) {
//				Address address = new Address(
//						"HCM", // Thành phố
//						CountryCode.VN, // Quốc gia
//						rnd.nextInt(1, 1000) + "", // Số nhà
//						"Phan Van Tri", // Đường
//						rnd.nextInt(70000, 80000) + "" // Mã bưu chính
//				);
//				addressRepository.save(address);
//
//				Company company = new Company(
//						"About company #" + i,
//						"company" + i + "@gmail.com",
//						"Company #" + i,
//						"0" + (1000000000 + rnd.nextInt(900000000)) ,
//						"https://company" + i + ".com",
//						address
//				);
//				companyRepository.save(company);
//				System.out.println("Added Company: " + company);
//			}
//
//			// Tạo dữ liệu cho Job
//			for (int i = 1; i <= 10; i++) {
//				Company company = companyRepository.findById(rnd.nextInt(1, 11)).orElse(null);
//
//				Job job = new Job();
//				job.setName("Job #" + i);
//				job.setDescription("Description for job #" + i);
//				job.setCompany(company);
//				jobRepository.save(job);
//				System.out.println("Added Job: " + job);
//			}

//			// Tạo dữ liệu cho JobSkill
//			for (int i = 1; i <= 10; i++) {
//				Job job = jobRepository.findById(rnd.nextInt(1, 10)).orElse(null);
//				Skill skill = skillRepository.findById(rnd.nextInt(51, 60)).orElse(null);
//
//				if (job != null && skill != null) {
//					JobSkill jobSkill = new JobSkill();
//					//Tạo CandidateSkillId cho composite primary key
//					JobSkillId id = new JobSkillId();
//					id.setJobId(job.getId());
//					id.setSkillId(skill.getId());
//					jobSkill.setId(id);
//
//					jobSkill.setJob(job);
//					jobSkill.setSkill(skill);
//					jobSkill.setMoreInfo("More information about job skill #" + i);
//					jobSkill.setSkillLevel(SkillLevel.values()[rnd.nextInt(SkillLevel.values().length)]);
//
//					jobSkillRepository.save(jobSkill);
//					System.out.println("Added JobSkill: " + jobSkill);
//				}
//			}

//			// Tạo dữ liệu cho CandidateSkill
//			for (int i = 1; i <= 10; i++) {
//				Candidate candidate = candidateRepository.findById(rnd.nextInt(1, 101)).orElse(null);
//				Skill skill = skillRepository.findById(rnd.nextInt(51, 61)).orElse(null);
//
//				if (candidate != null && skill != null) {
//					CandidateSkill candidateSkill = new CandidateSkill();
//
//					// Tạo CandidateSkillId cho composite primary key
//					CandidateSkillId id = new CandidateSkillId();
//					id.setCandidateId(candidate.getId());
//					id.setSkillId(skill.getId());
//					candidateSkill.setId(id);
//
//					candidateSkill.setCandidate(candidate);
//					candidateSkill.setSkill(skill);
//					candidateSkill.setMoreInfo("Candidate Skill #" + i);
//					candidateSkill.setSkillLevel(SkillLevel.values()[rnd.nextInt(SkillLevel.values().length)]);
//
//					candidateSkillRepository.save(candidateSkill);
//					System.out.println("Added CandidateSkill: " + candidateSkill);
//				}
//			}
//		};
//	}
//
//
}