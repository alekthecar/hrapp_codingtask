package intense.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import intense.hr.model.JobCandidate;

@Repository
public interface JobCandidateRepository extends JpaRepository<JobCandidate, Long> {

	List<JobCandidate> findByFullNameContaining(String name);

}
