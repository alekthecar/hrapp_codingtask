package intense.hr.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Entity
@Table(name="job_candidates")
public class JobCandidate {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="full_name")
	private String fullName;

	@Column(name="date_of_birth")
	@JsonFormat(shape=JsonFormat.Shape.STRING,
				pattern="dd/MM/yyyy")
	@JsonDeserialize(using=LocalDateDeserializer.class)
	@JsonSerialize(using=LocalDateSerializer.class)
	private LocalDate dateOfBirth;

	@Column(name="contact_number")
	private String contactNumber;

	@Column(unique=true,
			nullable=false)
	private String email;

	@ManyToMany(fetch=FetchType.LAZY,
				cascade=CascadeType.MERGE)
	@JoinTable(name="jobcandidate_skill",
	joinColumns = @JoinColumn(name="jobcandidate_id"),
	inverseJoinColumns = @JoinColumn(name="skill_id"))
	private List<Skill> skills = new ArrayList<>();

	public JobCandidate() { }

	// for TestData.java
	public JobCandidate(String fullName, LocalDate dateOfBirth, String contactNumber, String email) {
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.contactNumber = contactNumber;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Skill> getSkils() {
		return skills;
	}

	public void setSkils(List<Skill> skils) {
		this.skills = skils;
	}

	public void addSkill(Skill skill) {
		if (skill == null) {
			throw new IllegalArgumentException("null shouldn't be in list of skills!");
		}
		if (!skills.contains(skill)) {
			skills.add(skill);
			skill.getJobCandidates().add(this);
		}
		else {
			System.out.println("Cannot add already existing skill!");
		}
	}

	public void removeSkill(Skill skill) {
		if (skill == null) {
			throw new IllegalArgumentException("null parameter!");
		}
		if (skills.contains(skill)) {
			skills.remove(skill);
			skill.getJobCandidates().remove(this);
		}
		else {
			System.out.println("Cannot remove non-existing skill!");
		}
	}

}
