package dna.test.medcenter;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dna.test.medcenter.models.Role;
import dna.test.medcenter.models.User;
import dna.test.medcenter.repos.RoleRepository;
import dna.test.medcenter.repos.UserRepository;

@Component
public class DataGenerator {
	private RoleRepository roleRepository;
	private UserRepository userRepository;

	@Autowired
	public DataGenerator(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@PostConstruct
	public void loadMedicalPhysician() {
		if (userRepository.count() == 0) {
			User medPhysician = new User();
			medPhysician.setFirstName("John");
			medPhysician.setLastName("Smith");
			medPhysician.setAddress("Main Street 81");
			medPhysician.setGender("Male");
			medPhysician.setDateOfBirth(LocalDate.of(1987, 07, 17));
			medPhysician.setEmail("doctor01@test.com");
			medPhysician.setPassword("doctor01");
			medPhysician.setUsername("doctor01");
			Role medPhysicianRole = new Role();
			medPhysicianRole.setCode("MED_PHYS");
			medPhysician.addRole(roleRepository.save(medPhysicianRole));
			userRepository.save(medPhysician);
		}
	}

}