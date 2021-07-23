package dna.test.medcenter.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dna.test.medcenter.models.MedTest;



@Repository
public interface TestRepository extends JpaRepository<MedTest, Integer> {
	

}