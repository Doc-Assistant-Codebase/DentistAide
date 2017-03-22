package aide.dentists.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WrapperForMedicinePatientInfo implements Serializable{
	
	private List<PatientInfo> patientInfo;
	
	public List<PatientInfo> getPatientInfo() {
		return patientInfo;
	}

	public void setPatientInfo(List<PatientInfo> patientInfo) {
		this.patientInfo = patientInfo;
	}

	private List<MedicineMasterData> medDataList;

	private String prescriptionNumber;

	public String getPrescriptionNumber() {
		return prescriptionNumber;
	}

	public void setPrescriptionNumber(String prescriptionNumber) {
		this.prescriptionNumber = prescriptionNumber;
	}

	public List<MedicineMasterData> getMedDataList() {
		return medDataList;
	}

	public void setMedDataList(List<MedicineMasterData> medDataList) {
		this.medDataList = medDataList;
	}
	
	public WrapperForMedicinePatientInfo(){
		
	}

	public WrapperForMedicinePatientInfo(List<PatientInfo> patientInfo, List<MedicineMasterData> medDataList, String prescriptionNumber) {
		super();
		this.patientInfo = patientInfo;
		this.medDataList = medDataList;
		this.prescriptionNumber= prescriptionNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((medDataList == null) ? 0 : medDataList.hashCode());
		result = prime * result + ((patientInfo == null) ? 0 : patientInfo.hashCode());
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
		WrapperForMedicinePatientInfo other = (WrapperForMedicinePatientInfo) obj;
		if (medDataList == null) {
			if (other.medDataList != null)
				return false;
		} else if (!medDataList.equals(other.medDataList))
			return false;
		if (patientInfo == null) {
			if (other.patientInfo != null)
				return false;
		} else if (!patientInfo.equals(other.patientInfo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WrapperForMedicinePatientInfo [patientInfo=" + patientInfo + ", medDataList=" + medDataList
				+ ", prescriptionNumber=" + prescriptionNumber + "]";
	}

	

}
