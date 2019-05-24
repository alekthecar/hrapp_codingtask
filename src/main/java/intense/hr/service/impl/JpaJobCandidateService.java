package intense.hr.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import intense.hr.model.JobCandidate;
import intense.hr.model.Skill;
import intense.hr.repository.JobCandidateRepository;
import intense.hr.service.JobCandidateService;

@Service
public class JpaJobCandidateService implements JobCandidateService {

	@Autowired
	private JobCandidateRepository jobCandidateRepository;

	@Override
	public JobCandidate save(JobCandidate jobCandidate) {
		return jobCandidateRepository.save(jobCandidate);
	}

	@Override
	public JobCandidate findOne(Long id) {
		return jobCandidateRepository.findOne(id);
	}

	@Override
	public List<JobCandidate> findAll() {
		return jobCandidateRepository.findAll();
	}

	@Override
	public List<JobCandidate> findByFullNameContaining(String name) {
		return jobCandidateRepository.findByFullNameContaining(name);
	}

	@Override
	public List<JobCandidate> findByListOfSkills(List<JobCandidate> jobCandidates,
												 List<Skill> requiredSkills) {
		return jobCandidates.stream()
							.filter(jc -> jc.getSkils().containsAll(requiredSkills))
							.collect(Collectors.toList());
	}

	@Override
	public void delete(JobCandidate jobCandidate) {
		jobCandidateRepository.delete(jobCandidate);
	}

}
