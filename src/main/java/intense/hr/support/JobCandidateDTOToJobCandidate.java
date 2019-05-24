package intense.hr.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import intense.hr.model.JobCandidate;
import intense.hr.repository.JobCandidateRepository;
import intense.hr.web.dto.JobCandidateDTO;

@Component
public class JobCandidateDTOToJobCandidate implements Converter<JobCandidateDTO, JobCandidate> {

	@Autowired
	private JobCandidateRepository jobCandidateRepository;

	@Override
	public JobCandidate convert(JobCandidateDTO jobCandidateDTO) {
		JobCandidate jobCandidate = null;
		if (jobCandidateDTO.getId() == null) {
			jobCandidate = new JobCandidate();
		}
		else {
			jobCandidate = jobCandidateRepository.findOne(jobCandidateDTO.getId());
			if (jobCandidate == null) {
				throw new IllegalStateException("Editing non-existant job candidate");
			}
		}
		jobCandidate.setFullName(jobCandidateDTO.getFullName());
		jobCandidate.setDateOfBirth(jobCandidateDTO.getDateOfBirth());
		jobCandidate.setContactNumber(jobCandidateDTO.getContactNumber());
		jobCandidate.setEmail(jobCandidateDTO.getEmail());

		return jobCandidate;
	}

	public List<JobCandidate> convert(List<JobCandidateDTO> jobCandidateDTOs) {
		List<JobCandidate> jobCandidates = new ArrayList<>();
		for (JobCandidateDTO jobCandidateDTO : jobCandidateDTOs) {
			jobCandidates.add(convert(jobCandidateDTO));
		}
		return jobCandidates;
	}

}
