package dna.test.medcenter.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dna.test.medcenter.models.Patient;
import dna.test.medcenter.models.Role;

/**
 * Repository for the {@link Role} model
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findByCode(final String code);

}