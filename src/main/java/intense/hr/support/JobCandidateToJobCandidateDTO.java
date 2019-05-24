package intense.hr.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import intense.hr.model.JobCandidate;
import intense.hr.web.dto.JobCandidateDTO;

@Component
public class JobCandidateToJobCandidateDTO implements Converter<JobCandidate, JobCandidateDTO>{

	@Override
	public JobCandidateDTO convert(JobCandidate jobCandidate) {
		JobCandidateDTO jobCandidateDTO = new JobCandidateDTO();
		jobCandidateDTO.setId(jobCandidate.getId());
		jobCandidateDTO.setFullName(jobCandidate.getFullName());
		jobCandidateDTO.setDateOfBirth(jobCandidate.getDateOfBirth());
		jobCandidateDTO.setContactNumber(jobCandidate.getContactNumber());
		jobCandidateDTO.setEmail(jobCandidate.getEmail());
		return jobCandidateDTO;
	}

	public List<JobCandidateDTO> convert(List<JobCandidate> jobCandidates) {
		List<JobCandidateDTO> jobCandidateDTOs = new ArrayList<>();
		for (JobCandidate jobCandidate : jobCandidates) {
			jobCandidateDTOs.add(convert(jobCandidate));
		}
		return jobCandidateDTOs;
	}

}
