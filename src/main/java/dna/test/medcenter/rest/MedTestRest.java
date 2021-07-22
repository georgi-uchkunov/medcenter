package dna.test.medcenter.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import dna.test.medcenter.models.User;
import dna.test.medcenter.repos.PatientRepository;
import dna.test.medcenter.repos.TestRepository;

@RestController
public class MedTestRest {

	private TestRepository testRepository;
	private PatientRepository patientRepository;

	@Autowired
	public MedTestRest(final TestRepository testRepository, final PatientRepository patientRepository) {
		this.testRepository = testRepository;
		this.patientRepository = patientRepository;
	}

	@GetMapping("/getAllMedTests")
	public Page<MedTest> getAllMedTests(Pageable pageable) {
		return testRepository.findAll(pageable);
	}

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
			double testResult = 1;
			/* getGeneticDisorderProbability(String dna); */
			MedTest newTest = new MedTest(testDate, testResult, symptom);
			newTest.setPatient(patientRepository.findByPatientNameAndEmailAndPhoneNumber(patientName, email, phoneNumber));
			testRepository.saveAndFlush(newTest);
			return newTest;

		} else {
			double testResult = 1;
			/* getGeneticDisorderProbability(String dna); */
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
	double getGeneticDisorderProbability(String dna) {
		return 0;
		// to be implemented...
	}

}