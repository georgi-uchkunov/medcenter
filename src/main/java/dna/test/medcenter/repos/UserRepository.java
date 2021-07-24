package dna.test.medcenter.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dna.test.medcenter.models.Patient;
import dna.test.medcenter.models.User;

/**
 * Repository for the {@link User} model
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsernameAndPassword(final String username, final String password);
}