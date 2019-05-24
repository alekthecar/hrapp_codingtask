package intense.hr.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="skills")
public class Skill {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(unique=true,
			nullable=false)
	private String name;

	@ManyToMany(fetch=FetchType.LAZY,
				cascade=CascadeType.MERGE)
	@JoinTable(name="jobcandidate_skill",
	joinColumns = @JoinColumn(name="skill_id"),
	inverseJoinColumns = @JoinColumn(name="jobcandidate_id"))
	private List<JobCandidate> jobCandidates = new ArrayList<>();

	public Skill() { }

	public Skill(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<JobCandidate> getJobCandidates() {
		return jobCandidates;
	}

	public void setJobCandidates(List<JobCandidate> jobCandidates) {
		this.jobCandidates = jobCandidates;
	}

}
