package dna.test.medcenter.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class MedTest implements Serializable {

	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private LocalDate testDate;
	private double testResult;
	private String symptom;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Patient patient;
	
	public MedTest() {
		
	}
	
	public MedTest(LocalDate testDate, double testResult, String symptom) {
		super();
		this.testDate = testDate;
		this.testResult = testResult;
		this.symptom = symptom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getTestDate() {
		return testDate;
	}

	public void setTestDate(LocalDate testDate) {
		this.testDate = testDate;
	}

	public double getTestResult() {
		return testResult;
	}

	public void setTestResult(double testResult) {
		this.testResult = testResult;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
		
}