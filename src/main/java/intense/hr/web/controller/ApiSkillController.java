package intense.hr.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import intense.hr.model.Skill;
import intense.hr.service.SkillService;
import intense.hr.support.SkillDTOToSkill;
import intense.hr.support.SkillToSkillDTO;
import intense.hr.web.dto.SkillDTO;

@Controller
@RequestMapping(value="/api/skills")
public class ApiSkillController {

	@Autowired
	private SkillService skillService;

	@Autowired
	private SkillDTOToSkill toSkill;

	@Autowired
	private SkillToSkillDTO toSkillDTO;

	@RequestMapping(method=RequestMethod.POST,
					consumes="application/json")
	public ResponseEntity<SkillDTO> addSkill(@Validated @RequestBody SkillDTO newSkillDTO) {
		Skill newSkill = toSkill.convert(newSkillDTO);
		Skill savedSkill = skillService.save(newSkill);
		return new ResponseEntity<>(toSkillDTO.convert(savedSkill), HttpStatus.CREATED);
	}

}
