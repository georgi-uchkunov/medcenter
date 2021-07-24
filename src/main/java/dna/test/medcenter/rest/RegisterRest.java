package dna.test.medcenter.rest;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dna.test.medcenter.models.MedTest;
import dna.test.medcenter.models.Role;
import dna.test.medcenter.models.User;
import dna.test.medcenter.repos.PatientRepository;
import dna.test.medcenter.repos.RoleRepository;
import dna.test.medcenter.repos.TestRepository;
import dna.test.medcenter.repos.UserRepository;

/**
 * RestController, which uses functionalities from the {@link UserRepository}
 * and {@link RoleRepository} related to their respective entities. Receives
 * AJAX requests from the client, directed based on GetMapping and PostMapping.
 * 
 * Handles the registration of new users
 */
@RestController
public class RegisterRest {

	private UserRepository userRepository;
	private RoleRepository roleRepository;

	@Autowired
	public RegisterRest(final UserRepository userRepository, final RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	/**
	 * Creates a new {@link User} with the "PATIENT" {@link Role}
	 * 
	 * @param email, username, firstName, lastName, address, gender, password,
	 *               dateOfBirthString
	 * @return newly created user
	 */
	@PostMapping("/registerPatient")
	public User registerPatient(@RequestParam(name = "email") String email,
			@RequestParam(name = "username") String username, @RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName, @RequestParam(name = "address") String address,
			@RequestParam(name = "gender") String gender, @RequestParam(name = "password") String password,
			@RequestParam(name = "dateOfBirthString") String dateOfBirthString) {
		LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString);
		final User newPatient = new User(email, username, password, firstName, lastName, dateOfBirth, gender, address);
		Role patientRole = roleRepository.findByCode("PATIENT");
		newPatient.addRole(roleRepository.save(patientRole));
		return userRepository.saveAndFlush(newPatient);

	}
}