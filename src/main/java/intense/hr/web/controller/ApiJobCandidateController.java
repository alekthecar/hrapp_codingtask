package intense.hr.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import intense.hr.model.JobCandidate;
import intense.hr.model.Skill;
import intense.hr.service.JobCandidateService;
import intense.hr.service.SkillService;
import intense.hr.support.JobCandidateDTOToJobCandidate;
import intense.hr.support.JobCandidateToJobCandidateDTO;
import intense.hr.web.dto.JobCandidateDTO;

@Controller
@RequestMapping(value="/api/jobCandidates")
public class ApiJobCandidateController {

	@Autowired
	private JobCandidateService jobCandidateService;

	@Autowired
	private SkillService skillService;

	@Autowired
	private JobCandidateDTOToJobCandidate toJC;

	@Autowired
	private JobCandidateToJobCandidateDTO toJCDTO;

	@RequestMapping(method=RequestMethod.POST,
			consumes="application/json")
	public ResponseEntity<JobCandidateDTO>
	addJobCandidate(@Validated @RequestBody JobCandidateDTO newJobCandidateDTO) {
		JobCandidate newJobCandidate = toJC.convert(newJobCandidateDTO);
		JobCandidate savedJobCandidate = jobCandidateService.save(newJobCandidate);
		return new ResponseEntity<>(toJCDTO.convert(savedJobCandidate), HttpStatus.CREATED);
	}

	@RequestMapping(method=RequestMethod.GET,
					value="/{id}")
	public ResponseEntity<JobCandidateDTO> getJobCandidate(@PathVariable Long id) {
		JobCandidate jobCandidate = jobCandidateService.findOne(id);
		if (jobCandidate == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toJCDTO.convert(jobCandidate), HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<JobCandidateDTO>>
	getJobCandidates(@RequestParam(required=false) String nameContains,
					 @RequestParam(required=false) List<String> skills) {
		List<JobCandidate> jobCandidates;
		if (nameContains == null) { // no name filter
			jobCandidates = jobCandidateService.findAll();
		}
		else { // filter by name
			jobCandidates = jobCandidateService.findByFullNameContaining(nameContains);
		}
		return filterBySkills(jobCandidates, skills);
	}

	private ResponseEntity<List<JobCandidateDTO>>
	filterBySkills(List<JobCandidate> jc, List<String> skills) {
		if (jc == null || jc.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (skills == null) { // no skills filter
			return new ResponseEntity<>(toJCDTO.convert(jc), HttpStatus.OK);
		}
		else { // filter by skills
			List<Skill> requiredSkills = skillService.findByNameIn(skills);
			if (requiredSkills == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			List<JobCandidate> jcWithRequiredSkills =
					jobCandidateService.findByListOfSkills(jc, requiredSkills);
			return new ResponseEntity<>(toJCDTO.convert(jcWithRequiredSkills), HttpStatus.OK);
		}
	}

	@RequestMapping(method=RequestMethod.PUT,
					value="/{id}/addSkill")
	public ResponseEntity<Void> addSkill(@PathVariable Long id,
										 @RequestParam(value="name", required=true) String name) {
		JobCandidate jobCandidate = jobCandidateService.findOne(id);
		Skill newSkill = skillService.findByName(name);
		if (jobCandidate == null || newSkill == null) { // cannot add nonexistent skill
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		jobCandidate.addSkill(newSkill);
		jobCandidateService.save(jobCandidate);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.PUT,
					value="/{id}/removeSkill")
	public ResponseEntity<Void> removeSkill(@PathVariable Long id,
											@RequestParam(value="name", required=true) String name) {
		JobCandidate jobCandidate = jobCandidateService.findOne(id);
		Skill toBeRemovedSkill = skillService.findByName(name);
		if (jobCandidate == null || toBeRemovedSkill == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		jobCandidate.removeSkill(toBeRemovedSkill);
		jobCandidateService.save(jobCandidate);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.DELETE,
					value="/{id}")
	public ResponseEntity<Void> deleteJobCandidate(@PathVariable Long id) {
		JobCandidate toBeRemovedJobCandidate = jobCandidateService.findOne(id);
		if (toBeRemovedJobCandidate == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		jobCandidateService.delete(toBeRemovedJobCandidate);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
