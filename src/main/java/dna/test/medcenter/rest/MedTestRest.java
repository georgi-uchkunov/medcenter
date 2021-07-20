package dna.test.medcenter.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

}