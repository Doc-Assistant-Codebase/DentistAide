package aide.dentists.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * This is an DTO class for trasmitting consulation records to UI
 * @author Vivek Mittal 
 *
 */
@XmlRootElement
public class PatientPrescriptionHistory implements Serializable{
	
	private String medName;
	private String medType;
	private String medStrength;
	private String notes;
	private Integer id;
	private Date prescriptionDate;
	private Integer prescriptionNumber;
	
	public String getMedName() {
		return medName;
	}
	public void setMedName(String medName) {
		this.medName = medName;
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
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getPrescriptionDate() {
		return prescriptionDate;
	}
	public void setPrescriptionDate(Date prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}
	public Integer getPrescriptionNumber() {
		return prescriptionNumber;
	}
	public void setPrescriptionNumber(Integer prescriptionNumber) {
		this.prescriptionNumber = prescriptionNumber;
	}
	
	public PatientPrescriptionHistory(){
		
	}
	public PatientPrescriptionHistory(String medName, String medType, String medStrength, String notes, Integer id,
			Date prescriptionDate, Integer prescriptionNumber) {
		super();
		this.medName = medName;
		this.medType = medType;
		this.medStrength = medStrength;
		this.notes = notes;
		this.id = id;
		this.prescriptionDate = prescriptionDate;
		this.prescriptionNumber = prescriptionNumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((medName == null) ? 0 : medName.hashCode());
		result = prime * result + ((medStrength == null) ? 0 : medStrength.hashCode());
		result = prime * result + ((medType == null) ? 0 : medType.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((prescriptionDate == null) ? 0 : prescriptionDate.hashCode());
		result = prime * result + ((prescriptionNumber == null) ? 0 : prescriptionNumber.hashCode());
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
		PatientPrescriptionHistory other = (PatientPrescriptionHistory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (medName == null) {
			if (other.medName != null)
				return false;
		} else if (!medName.equals(other.medName))
			return false;
		if (medStrength == null) {
			if (other.medStrength != null)
				return false;
		} else if (!medStrength.equals(other.medStrength))
			return false;
		if (medType == null) {
			if (other.medType != null)
				return false;
		} else if (!medType.equals(other.medType))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (prescriptionDate == null) {
			if (other.prescriptionDate != null)
				return false;
		} else if (!prescriptionDate.equals(other.prescriptionDate))
			return false;
		if (prescriptionNumber == null) {
			if (other.prescriptionNumber != null)
				return false;
		} else if (!prescriptionNumber.equals(other.prescriptionNumber))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PatientPrescriptionHistory [medName=" + medName + ", medType=" + medType + ", medStrength="
				+ medStrength + ", notes=" + notes + ", id=" + id + ", prescriptionDate=" + prescriptionDate
				+ ", prescriptionNumber=" + prescriptionNumber + "]";
	}
	
	
	

}
