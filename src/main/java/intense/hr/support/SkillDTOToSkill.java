package intense.hr.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import intense.hr.model.Skill;
import intense.hr.repository.SkillRepository;
import intense.hr.web.dto.SkillDTO;

@Component
public class SkillDTOToSkill implements Converter<SkillDTO, Skill> {

	@Autowired
	private SkillRepository skillRepository;

	@Override
	public Skill convert(SkillDTO skillDTO) {
		Skill skill = null;
		if (skillDTO.getId() == null) {
			skill = new Skill();
		}
		else {
			skill = skillRepository.findOne(skillDTO.getId());
			if (skill == null) {
				throw new IllegalStateException("Editing non-existant skill");
			}
		}
		skill.setName(skillDTO.getName());

		return skill;
	}

	public List<Skill> convert(List<SkillDTO> skillDTOs) {
		List<Skill> skills = new ArrayList<>();
		for (SkillDTO skillDTO : skillDTOs) {
			skills.add(convert(skillDTO));
		}
		return skills;
	}
}
