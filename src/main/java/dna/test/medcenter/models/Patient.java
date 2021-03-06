package dna.test.medcenter.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents the patient on which a {@link MedTest} is performed. Separate from
 * the "PATIENT" {@link Role} Creating a new Patient does not create a new
 * {@link User} and vice versa Includes an auto-generated Id, email (which needs
 * to be unique), dna (which needs to be unique), date of birth, name, phone,
 * gender and address Connected to {@link MedTest} in a one to many relation
 * Does not return its list of tests in Json responses to prevent recursion
 */
@Entity
@JsonIgnoreProperties("medicalTests")
public class Patient implements Serializable {

	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "email", nullable = false, unique = true)
	private String email;
	@Column(name = "dna", nullable = false, unique = true)
	private String dna;
	private LocalDate dateOfBirth;
	private String patientName;
	private String phoneNumber;
	private String gender;
	private String address;

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<MedTest> medicalTests;

	public Patient() {

	}

	public Patient(String email, String dna, String patientName, String phoneNumber, LocalDate dateOfBirth,
			String gender, String address) {
		super();
		this.email = email;
		this.dna = dna;
		this.dateOfBirth = dateOfBirth;
		this.patientName = patientName;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<MedTest> getMedicalTests() {
		return medicalTests;
	}

	public void setMedicalTests(List<MedTest> medicalTests) {
		this.medicalTests = medicalTests;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((patientName == null) ? 0 : patientName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (patientName == null) {
			if (other.patientName != null)
				return false;
		} else if (!patientName.equals(other.patientName))
			return false;
		return true;
	}

}