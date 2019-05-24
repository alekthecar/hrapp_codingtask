package intense.hr;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import intense.hr.model.JobCandidate;
import intense.hr.model.Skill;
import intense.hr.service.JobCandidateService;
import intense.hr.service.SkillService;

@Component
public class TestData {

	@Autowired
	private JobCandidateService jobCandidateService;

	@Autowired
	private SkillService skillService;

	@PostConstruct
	public void init() {

		JobCandidate alek = new JobCandidate("Antić,Aleksandar",
											 LocalDate.of(1983, 10, 11),
											 "+381642632979",
											 "alek@uns.ac.rs");

		JobCandidate njegos = new JobCandidate("Petrović,Radivoje",
											   LocalDate.of(1813, 11, 13),
											   "+381647653467",
											   "theRealNjegos@gmail.com");

		JobCandidate bBad = new JobCandidate("Heisenberg,Werner",
											 LocalDate.of(1901, 12, 5),
											 "+381644937637",
											 "heisenberg@yahoo.com");

		JobCandidate smartGuy = new JobCandidate("von Neumann,John",
												 LocalDate.of(1903, 12, 28),
												 "+381648638626",
												 "dontBotherMe@xyz.com");

		Skill eng = new Skill("English");
		Skill rus = new Skill("Russian");
		Skill cs = new Skill("Computer science");
		Skill alg = new Skill("Algebra");
		Skill phy = new Skill("Physics");

		skillService.save(eng);
		skillService.save(rus);
		skillService.save(cs);
		skillService.save(alg);
		skillService.save(phy);

		alek.addSkill(eng);
		alek.addSkill(phy);

		njegos.addSkill(eng);
		njegos.addSkill(rus);

		bBad.addSkill(phy);
		bBad.addSkill(alg);
		bBad.addSkill(eng);

		smartGuy.addSkill(eng);
		smartGuy.addSkill(rus);
		smartGuy.addSkill(cs);
		smartGuy.addSkill(alg);
		smartGuy.addSkill(phy);

		jobCandidateService.save(alek);
		jobCandidateService.save(njegos);
		jobCandidateService.save(bBad);
		jobCandidateService.save(smartGuy);
	}

}
