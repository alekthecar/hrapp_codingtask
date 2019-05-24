package intense.hr.service;

import java.util.List;

import intense.hr.model.JobCandidate;
import intense.hr.model.Skill;

public interface JobCandidateService {

	JobCandidate save(JobCandidate jobCandidate);

	JobCandidate findOne(Long id);

	List<JobCandidate> findAll();

	List<JobCandidate> findByFullNameContaining(String name);

	List<JobCandidate> findByListOfSkills(List<JobCandidate> jobCandidates, List<Skill> requiredSkills);

	void delete(JobCandidate jobCandidate);

}
