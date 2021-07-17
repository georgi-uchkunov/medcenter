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

@Entity
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
	
}