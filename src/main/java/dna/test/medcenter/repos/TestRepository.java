package dna.test.medcenter.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dna.test.medcenter.models.MedTest;

/**
 * Repository for the {@link MedTest} model
 */
@Repository
public interface TestRepository extends JpaRepository<MedTest, Integer> {

}