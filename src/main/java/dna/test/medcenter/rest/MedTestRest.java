package dna.test.medcenter.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dna.test.medcenter.models.MedTest;
import dna.test.medcenter.models.Patient;
import dna.test.medcenter.models.Role;
import dna.test.medcenter.models.User;
import dna.test.medcenter.repos.PatientRepository;
import dna.test.medcenter.repos.RoleRepository;
import dna.test.medcenter.repos.TestRepository;
import dna.test.medcenter.repos.UserRepository;

/**
 * RestController, which uses functionalities from the {@link TestRepository}
 * and {@link PatientRepository} related to their respective entities. Receives
 * AJAX requests from the client, directed based on GetMapping and PostMapping.
 * 
 * Handles creating medical tests, redoing them, returning all medical tests or
 * a specific one based on Id, returning the tests only for a specific patient
 * and returning tests based on searching for patient names and/or phone numbers
 */
@RestController
public class MedTestRest {

	private TestRepository testRepository;
	private PatientRepository patientRepository;

	@Autowired
	public MedTestRest(final TestRepository testRepository, final PatientRepository patientRepository) {
		this.testRepository = testRepository;
		this.patientRepository = patientRepository;
	}

	/**
	 * Returns all {@link MedTest}
	 * 
	 * @param pageable
	 * @return all DNA tests
	 */
	@GetMapping("/getAllMedTests")
	public Page<MedTest> getAllMedTests(Pageable pageable) {
		return testRepository.findAll(pageable);
	}

	/**
	 * Returns a specific {@link MedTest} based on Id
	 * 
	 * @param id
	 * @return the DNA test matching the id
	 */
	@GetMapping(value = "/getSelectedMedTestById")
	public MedTest getSelectedMedTestById(@RequestParam(name = "id") int id) {
		List<MedTest> medTests = testRepository.findAll();
		for (int i = 0; i < medTests.size(); i++) {
			MedTest currentMedTest = medTests.get(i);
			if (currentMedTest.getId() == id) {
				return currentMedTest;
			}

		}
		return null;
	}

	/**
	 * Returns all {@link MedTest} relating to a specific {@link User} with the
	 * "PATIENT" {@link Role} who is currently in session
	 * 
	 * @param session
	 * @return all DNA tests of the current user logged in as patient
	 */
	@GetMapping("/getPatientMedTests")
	public ResponseEntity<List<MedTest>> getPatientMedTests(HttpSession session) {
		final List<MedTest> medTests = new ArrayList<>();
		final User user = (User) session.getAttribute("currentUser");
		if (null == user) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(medTests);
		} else {
			String userEmail = user.getEmail();
			String userName = user.getFirstName() + " " + user.getLastName();
			List<Patient> patients = patientRepository.findAll();
			for (int i = 0; i < patients.size(); i++) {
				Patient currentPatient = patients.get(i);
				if (currentPatient.getEmail().equalsIgnoreCase(userEmail)
						&& currentPatient.getPatientName().equalsIgnoreCase(userName)) {
					medTests.addAll(currentPatient.getMedicalTests());

				}
			}
		}
		return ResponseEntity.ok(medTests);

	}

	/**
	 * Returns multiple specific {@link MedTest} where the search term fully or
	 * partially matches their patient names or phone numbers
	 * 
	 * @param searchTerm
	 * @return specific DNA tests based on search terms
	 */
	@GetMapping("/getSpecificTests")
	public ResponseEntity<List<MedTest>> getSpecificTests(String searchTerm) {
		final List<MedTest> specificTests = new ArrayList<>();
		final List<MedTest> allTests = testRepository.findAll();
		for (int i = 0; i < allTests.size(); i++) {
			MedTest currentTest = allTests.get(i);
			String[] nameParts = currentTest.getPatient().getPatientName().split(" ");
			String firstName = nameParts[0];
			String lastName = nameParts[1];
			if (searchTerm.equalsIgnoreCase(currentTest.getPatient().getPatientName())
					|| searchTerm.equalsIgnoreCase(currentTest.getPatient().getPhoneNumber())
					|| currentTest.getPatient().getPatientName().contains(searchTerm)
					|| currentTest.getPatient().getPhoneNumber().contains(searchTerm)
					|| searchTerm.equalsIgnoreCase(firstName) || searchTerm.equalsIgnoreCase(lastName)) {
				specificTests.add(currentTest);
			}
		}

		return ResponseEntity.ok(specificTests);
	}

	/**
	 * Creates a new {@link MedTest} and assigns it to a specific {@link Patient}
	 * based on matching parameters. If such a {@link Patient} does not exist yet,
	 * they are created.
	 * 
	 * @param email, patientName, address, gender, phoneNumber, dateOfBirthString,
	 *               dna, symptom
	 * @return a new DNA test
	 */
	@PostMapping("/performDNATest")
	public MedTest performDNATest(@RequestParam(name = "email") String email,
			@RequestParam(name = "patientName") String patientName, @RequestParam(name = "address") String address,
			@RequestParam(name = "gender") String gender, @RequestParam(name = "phoneNumber") String phoneNumber,
			@RequestParam(name = "dateOfBirthString") String dateOfBirthString, @RequestParam(name = "dna") String dna,
			@RequestParam(name = "symptom") String symptom) {
		LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString);
		LocalDate testDate = LocalDate.now();
		Patient existingPatient = patientRepository.findByPatientNameAndEmailAndPhoneNumber(patientName, email,
				phoneNumber);
		if (existingPatient == null) {
			Patient newPatient = new Patient(email, dna, patientName, phoneNumber, dateOfBirth, gender, address);
			patientRepository.saveAndFlush(newPatient);
			double testResult = getGeneticDisorderProbability(dna);
			MedTest newTest = new MedTest(testDate, testResult, symptom);
			newTest.setPatient(
					patientRepository.findByPatientNameAndEmailAndPhoneNumber(patientName, email, phoneNumber));
			testRepository.saveAndFlush(newTest);
			return newTest;

		} else {
			double testResult = getGeneticDisorderProbability(dna);
			MedTest newTest = new MedTest(testDate, testResult, symptom);
			newTest.setPatient(existingPatient);
			testRepository.saveAndFlush(newTest);
			return newTest;
		}

	}

	/**
	 * Calculates the likelihood of human to have genetic disorder based on the DNA.
	 *
	 * @param dna – human dna to be checked
	 * @return a number between 0 and 1 with the probability for disorder
	 */
	public double getGeneticDisorderProbability(String dna) {

		/**
		 * Reverse the DNA sequence into a new String
		 */
		String reversedDna = new StringBuilder(dna).reverse().toString();

		/**
		 * Transform the DNA String into a char array
		 */
		char[] dnaArray = new char[dna.length()];
		for (int i = 0; i < dna.length(); i++) {
			dnaArray[i] = dna.charAt(i);
		}

		/**
		 * Transform the reversed DNA String into a char array
		 */
		char[] reversedDnaArray = new char[reversedDna.length()];
		for (int i = 0; i < reversedDna.length(); i++) {
			reversedDnaArray[i] = reversedDna.charAt(i);
		}

		/**
		 * Make a new ArrayList into which to save characters from the reversed DNA,
		 * which match the prefix of the DNA. The method compares all the characters
		 * from the reversed array to the normal array's first character until there is
		 * a match. Then the matched character from the reversed array is saved into the
		 * array list. The matching and saving continues over from the next character in
		 * the reversed sequence, but now it compares to the second DNA character and so
		 * on.
		 */
		ArrayList<Character> prefixArrayList = new ArrayList<Character>();

		int j = 0;

		for (int i = 0; i < reversedDnaArray.length; i++) {
			char currentChar = reversedDnaArray[i];
			if (currentChar == dnaArray[j]) {
				prefixArrayList.add(currentChar);
				j++;
			}
		}

		/**
		 * Turn the array list into a String
		 */
		String prefixArrayListString = getPrefixArrayListString(prefixArrayList);
		prefixArrayListString = prefixArrayListString.substring(4);

		/**
		 * Compare the String to the DNA String and cut off characters from the end
		 * until a genuine prefix match
		 */
		for (int i = 0; i < 255; i++) {
			if (!reversedDna.contains(prefixArrayListString)) {
				prefixArrayListString = StringUtils.chop(prefixArrayListString);
			} else {
				break;
			}
		}

		/**
		 * Compare the DNA and matching prefix lengths to get the disorder probability.
		 * Prefix length is set to 0 in case it was 1, as a single match is not enough.
		 */
		double dnaLength = dna.length();
		double prefixLength = prefixArrayListString.length();
		if (prefixArrayListString.length() == 1) {
			prefixLength = 0;
		}
		double geneticDisorderProbability = prefixLength / dnaLength;

		return geneticDisorderProbability;
	}

	/**
	 * Makes a single string out of the matching symbols in the proper and reversed
	 * dna sequences
	 * 
	 * @param prefixArrayList - the array list of matching symbols
	 * @return a string of the matching symbols
	 */
	private String getPrefixArrayListString(ArrayList<Character> prefixArrayList) {
		String prefixArrayListString = null;
		for (int i = 0; i < prefixArrayList.size(); i++) {
			char currentChar = prefixArrayList.get(i);
			prefixArrayListString = prefixArrayListString + currentChar;
		}
		return prefixArrayListString;

	}

	/**
	 * Redoes the calculation of a probable genetic disorder, updates the test date
	 * and edits an existing {@link MedTest}
	 * 
	 * @param testId
	 * @return a test with (possibly) updated result and an updated test date
	 */
	@PostMapping(value = "/redoDNATest")
	public MedTest redoDnaTest(@RequestParam(name = "testId") int testId) {
		Optional<MedTest> testForRedo = testRepository.findById(testId);

		if (testForRedo.isPresent()) {
			MedTest requiredMedTest = testForRedo.get();
			String requiredMedTestDna = requiredMedTest.getPatient().getDna();
			double newResult = getGeneticDisorderProbability(requiredMedTestDna);
			requiredMedTest.setTestResult(newResult);
			requiredMedTest.setTestDate(LocalDate.now());
			testRepository.saveAndFlush(requiredMedTest);
		}
		return null;

	}

}