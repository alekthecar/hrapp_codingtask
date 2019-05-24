package intense.hr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import intense.hr.model.Skill;
import intense.hr.repository.SkillRepository;
import intense.hr.service.SkillService;

@Service
public class JpaSkillService implements SkillService {

	@Autowired
	private SkillRepository skillRepository;

	@Override
	public Skill save(Skill skill) {
		return skillRepository.save(skill);
	}

	@Override
	public Skill findOne(Integer id) {
		return skillRepository.findOne(id);
	}

	@Override
	public Skill findByName(String name) {
		return skillRepository.findByName(name);
	}

	@Override
	public List<Skill> findByNameIn(List<String> skillNames) {
		for (String skillName : skillNames) {
			Skill skillWithGivenName = findByName(skillName);
			if (skillWithGivenName == null) {
				return null; // skillNames contains nonexistent skill
			}
		}
		return skillRepository.findByNameIn(skillNames);
	}

}
