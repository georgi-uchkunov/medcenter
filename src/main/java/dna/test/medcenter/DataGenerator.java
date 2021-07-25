package dna.test.medcenter;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dna.test.medcenter.models.MedTest;
import dna.test.medcenter.models.Patient;
import dna.test.medcenter.models.Role;
import dna.test.medcenter.models.User;
import dna.test.medcenter.repos.PatientRepository;
import dna.test.medcenter.repos.RoleRepository;
import dna.test.medcenter.repos.TestRepository;
import dna.test.medcenter.repos.UserRepository;

/**
 * Generates example data for all entities: {@link Role}, {@link User},
 * {@link Patient} and {@link MedTest} Loads on startup and its key method runs
 * only in case the database has no {@link User} table yet - meaning in most
 * cases where the app is ran for the first time and the database is empty
 */
@Component
public class DataGenerator {
	private RoleRepository roleRepository;
	private UserRepository userRepository;
	private PatientRepository patientRepository;
	private TestRepository testRepository;

	@Autowired
	public DataGenerator(UserRepository userRepository, RoleRepository roleRepository,
			PatientRepository patientRepository, TestRepository testRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.patientRepository = patientRepository;
		this.testRepository = testRepository;
	}

	/**
	 * Key method, which handles when to generate the example data and combines the
	 * methods specialized for the different entities
	 */
	@PostConstruct
	public void loadExampleInfo() {
		if (userRepository.count() == 0) {
			loadMedicalPhysician();
			loadUsersWithPatientRole();
			loadPatients();
			loadMedTests();
		}
	}

	/**
	 * Generates the "MED_PHYS" {@link Role} and attaches it to a {@link User}
	 * slated to have higher access and privileges The user has username and
	 * password: "doctor01"
	 */
	public void loadMedicalPhysician() {
		User medPhysician = new User("doctor01@test.com", "doctor01", "doctor01", "John", "Smith",
				LocalDate.of(1987, 07, 17), "Male", "Main Street 81");
		Role medPhysicianRole = new Role();
		medPhysicianRole.setCode("MED_PHYS");
		medPhysician.addRole(roleRepository.save(medPhysicianRole));
		userRepository.save(medPhysician);
	}

	/**
	 * Generates the "PATIENT" {@link Role} and attaches it to three example
	 * {@link User} with generic access and privileges
	 */
	public void loadUsersWithPatientRole() {
		User examplePatientOne = new User("test1@test.com", "AliceS", "test1", "Alice", "Smith",
				LocalDate.parse("1983-11-15"), "female", "Main Str 17");
		User examplePatientTwo = new User("test2@test.com", "BobJ", "test2", "Bob", "Jones",
				LocalDate.parse("1992-08-07"), "male", "Second Str 89");
		User examplePatientThree = new User("test3@test.com", "ClarkN", "test3", "Clark", "North",
				LocalDate.parse("1998-05-16"), "male", "Silver Str 117");
		Role patientRole = new Role();
		patientRole.setCode("PATIENT");
		roleRepository.save(patientRole);
		examplePatientOne.addRole(patientRole);
		examplePatientTwo.addRole(patientRole);
		examplePatientThree.addRole(patientRole);
		userRepository.save(examplePatientOne);
		userRepository.save(examplePatientTwo);
		userRepository.save(examplePatientThree);

	}

	/**
	 * Generates three {@link Patient} matching to the three example {@link User}
	 * All three have DNAs matching the ones in the task example
	 */
	public void loadPatients() {
		Patient examplePatientA = new Patient("test1@test.com", "ATGCGGTATC", "Alice Smith", "359881234567",
				LocalDate.parse("1983-11-15"), "female", "Main Str 17");
		Patient examplePatientB = new Patient("test2@test.com", "ATGTA", "Bob Jones", "359881234568",
				LocalDate.parse("1992-08-07"), "male", "Second Str 89");
		Patient examplePatientC = new Patient("test3@test.com", "ATGCGTC", "Clark North", "359881234569",
				LocalDate.parse("1998-05-16"), "male", "Silver Str 117");
		patientRepository.save(examplePatientA);
		patientRepository.save(examplePatientB);
		patientRepository.save(examplePatientC);
	}

	/**
	 * Generates two {@link MedTest} for each of the three example {@link Patient}
	 * All {@link MedTest} have a result value of 10 so they can stand out and be
	 * used as a quick example of the
	 * {@link dna.test.medcenter.rest#getGeneticDisorderProbability()}
	 */
	public void loadMedTests() {
		MedTest exampleTestOne = new MedTest(LocalDate.of(2021, 06, 21), 10, "Blurred vision");
		exampleTestOne.setPatient(patientRepository.findByPatientNameAndEmail("Alice Smith", "test1@test.com"));
		MedTest exampleTestTwo = new MedTest(LocalDate.of(2021, 07, 01), 10, "Loss of hearing");
		exampleTestTwo.setPatient(patientRepository.findByPatientNameAndEmail("Alice Smith", "test1@test.com"));
		MedTest exampleTestThree = new MedTest(LocalDate.of(2021, 05, 10), 10, "Constant sneezing");
		exampleTestThree.setPatient(patientRepository.findByPatientNameAndEmail("Bob Jones", "test2@test.com"));
		MedTest exampleTestFour = new MedTest(LocalDate.of(2021, 07, 17), 10, "Rapid hiccups");
		exampleTestFour.setPatient(patientRepository.findByPatientNameAndEmail("Bob Jones", "test2@test.com"));
		MedTest exampleTestFive = new MedTest(LocalDate.of(2021, 05, 18), 10, "Insomnia");
		exampleTestFive.setPatient(patientRepository.findByPatientNameAndEmail("Clark North", "test3@test.com"));
		MedTest exampleTestSix = new MedTest(LocalDate.of(2021, 07, 03), 10, "Narcolepsy");
		exampleTestSix.setPatient(patientRepository.findByPatientNameAndEmail("Clark North", "test3@test.com"));
		testRepository.save(exampleTestOne);
		testRepository.save(exampleTestTwo);
		testRepository.save(exampleTestThree);
		testRepository.save(exampleTestFour);
		testRepository.save(exampleTestFive);
		testRepository.save(exampleTestSix);

	}

}