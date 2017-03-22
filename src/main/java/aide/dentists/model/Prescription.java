package aide.dentists.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * This is an entity class for prescription records
 * @author Vivek Mittal 
 *
 */
@Entity
@Configuration
@ConfigurationProperties("oracle")
public class Prescription {

	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_prescription")
	 @SequenceGenerator(name="seq_prescription", initialValue=1, allocationSize=1)
	 private Integer id;

	 @Column(name="medName")
	 private String medName;

	 public String getMedName() {
		return medName;
	}


	public void setMedName(String medName) {
		this.medName = medName;
	}


	public Prescription(String medName, String notes, String medType, String medStrength) {
		super();
		this.medName = medName;
		this.notes = notes;
		this.medType = medType;
		this.medStrength = medStrength;
	}


	public Prescription(String medName, String notes, String medType, String medStrength, PatientInfo patientInfo, Date prescriptionDate, Integer prescriptionNumber) {
		super();
		this.medName = medName;
		this.notes = notes;
		this.medType = medType;
		this.medStrength = medStrength;
		this.patientInfo = patientInfo;
		this.prescriptionDate = prescriptionDate;
		this.prescriptionNumber = prescriptionNumber;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public String getMedType() {
		return medType;
	}


	public void setMedType(String medType) {
		this.medType = medType;
	}


	public String getMedStrength() {
		return medStrength;
	}


	public void setMedStrength(String medStrength) {
		this.medStrength = medStrength;
	}

	@Column(name="notes")
	 private String notes;

	 @Column(name="medType")
	 private String medType;

	 @Column(name="medStrength")
	 private String medStrength;
	 
	 public Prescription() {}


	 public Integer getId() {
	   return id;
	 }

	 public void setId(Integer id) {
	   this.id = id;
	 }
	 
	@ManyToOne(cascade = CascadeType.ALL)
	private PatientInfo patientInfo;

	public PatientInfo getPatientInfo() {
		return patientInfo;
	}


	public void setPatientInfo(PatientInfo patientInfo) {
		this.patientInfo = patientInfo;
	}

	@Column(name="prescription_date")
	private Date prescriptionDate = new Date();

	public Date getPrescriptionDate() {
		return prescriptionDate;
	}


	public void setPrescriptionDate(Date prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	} 
	
	@Column(name="prescriptionNumber")	
	private Integer prescriptionNumber;

	public Integer getPrescriptionNumber() {
		return prescriptionNumber;
	}


	public void setPrescriptionNumber(Integer prescriptionNumber) {
		this.prescriptionNumber = prescriptionNumber;
	}
	

}
