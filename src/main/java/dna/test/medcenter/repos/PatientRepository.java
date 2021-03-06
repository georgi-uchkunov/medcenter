package dna.test.medcenter.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dna.test.medcenter.models.Patient;
import dna.test.medcenter.models.User;

/**
 * Repository for the {@link Patient} model
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	Patient findByPatientNameAndEmail(final String patientName, final String email);

	Patient findByPatientNameAndEmailAndPhoneNumber(final String patientName, final String email, String phoneNumber);

}