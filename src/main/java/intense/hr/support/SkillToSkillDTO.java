package intense.hr.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import intense.hr.model.Skill;
import intense.hr.web.dto.SkillDTO;

@Component
public class SkillToSkillDTO implements Converter<Skill, SkillDTO> {

	@Override
	public SkillDTO convert(Skill skill) {
		SkillDTO skillDTO = new SkillDTO();
		skillDTO.setId(skill.getId());
		skillDTO.setName(skill.getName());
		return skillDTO;
	}

	public List<SkillDTO> convert(List<Skill> skills) {
		List<SkillDTO> skillDTOs = new ArrayList<>();
		for (Skill skill : skills) {
			skillDTOs.add(convert(skill));
		}
		return skillDTOs;
	}
}
