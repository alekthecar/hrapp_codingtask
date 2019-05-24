package intense.hr.service;

import java.util.List;

import intense.hr.model.Skill;

public interface SkillService {

	Skill save(Skill skill);

	Skill findOne(Integer id);

	Skill findByName(String name);

	List<Skill> findByNameIn(List<String> skills);
}
