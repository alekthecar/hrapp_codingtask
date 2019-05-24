package intense.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import intense.hr.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {

	Skill findByName(String name);

	List<Skill> findByNameIn(List<String> skills);
}
