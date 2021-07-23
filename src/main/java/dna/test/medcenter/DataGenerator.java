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

	@PostConstruct
	public void loadExampleInfo() {
		if (userRepository.count() == 0) {
			loadMedicalPhysician();
			loadUsersWithPatientRole();
			loadPatients();
			loadMedTests();
		}
	}

	public void loadMedicalPhysician() {
		User medPhysician = new User("doctor01@test.com", "doctor01", "doctor01", "John", "Smith",
				LocalDate.of(1987, 07, 17), "Male", "Main Street 81");
		Role medPhysicianRole = new Role();
		medPhysicianRole.setCode("MED_PHYS");
		medPhysician.addRole(roleRepository.save(medPhysicianRole));
		userRepository.save(medPhysician);
	}

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

	public void loadPatients() {
		Patient examplePatientA = new Patient("test1@test.com", "ATGCGGTATC", "Alice Smith", "35988 1234567",
				LocalDate.parse("1983-11-15"), "female", "Main Str 17");
		Patient examplePatientB = new Patient("test2@test.com", "GTCAGTTA", "Bob Jones", "088 7654321",
				LocalDate.parse("1992-08-07"), "male", "Second Str 89");
		Patient examplePatientC = new Patient("test3@test.com", "ACTGACTGA", "Clark North", "088 2143657",
				LocalDate.parse("1998-05-16"), "male", "Silver Str 117");
		patientRepository.save(examplePatientA);
		patientRepository.save(examplePatientB);
		patientRepository.save(examplePatientC);
	}

	public void loadMedTests() {
		MedTest exampleTestOne = new MedTest(LocalDate.of(2021, 06, 21), 0.3, "Blurred vision");
		exampleTestOne.setPatient(patientRepository.findByPatientNameAndEmail("Alice Smith", "test1@test.com"));
		MedTest exampleTestTwo = new MedTest(LocalDate.of(2021, 07, 01), 1, "Loss of hearing");
		exampleTestTwo.setPatient(patientRepository.findByPatientNameAndEmail("Alice Smith", "test1@test.com"));
		MedTest exampleTestThree = new MedTest(LocalDate.of(2021, 05, 10), 0.75, "Constant sneezing");
		exampleTestThree.setPatient(patientRepository.findByPatientNameAndEmail("Bob Jones", "test2@test.com"));
		MedTest exampleTestFour = new MedTest(LocalDate.of(2021, 07, 17), 0.75, "Rapid hiccups");
		exampleTestFour.setPatient(patientRepository.findByPatientNameAndEmail("Bob Jones", "test2@test.com"));
		MedTest exampleTestFive = new MedTest(LocalDate.of(2021, 05, 18), 0, "Insomnia");
		exampleTestFive.setPatient(patientRepository.findByPatientNameAndEmail("Clark North", "test3@test.com"));
		MedTest exampleTestSix = new MedTest(LocalDate.of(2021, 07, 03), 0, "Narcolepsy");
		exampleTestSix.setPatient(patientRepository.findByPatientNameAndEmail("Clark North", "test3@test.com"));
		testRepository.save(exampleTestOne);
		testRepository.save(exampleTestTwo);
		testRepository.save(exampleTestThree);
		testRepository.save(exampleTestFour);
		testRepository.save(exampleTestFive);
		testRepository.save(exampleTestSix);

	}

}