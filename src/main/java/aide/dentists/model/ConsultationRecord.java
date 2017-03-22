package aide.dentists.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * This is an entity class for consulation records
 * @author Vivek Mittal 
 *
 */
@Entity
@Configuration
@ConfigurationProperties("oracle")
public class ConsultationRecord {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_consultaion")
	 @SequenceGenerator(name="seq_consultaion", initialValue=1, allocationSize=1)
	 private Integer id;
	
	 private Integer patientId;

	 private Date consultationDate;

	 public Integer getId() {
	   return id;
	 }

	 public void setId(Integer id) {
	   this.id = id;
	 }
	 
	 
	 public ConsultationRecord(){
		 
	 }

	public ConsultationRecord(Integer patientId, Date consultationDate) {
		super();
		this.patientId = patientId;
		this.consultationDate = consultationDate;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Date getConsultationDate() {
		return consultationDate;
	}

	public void setConsultationDate(Date consultationDate) {
		this.consultationDate = consultationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consultationDate == null) ? 0 : consultationDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((patientId == null) ? 0 : patientId.hashCode());
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
		ConsultationRecord other = (ConsultationRecord) obj;
		if (consultationDate == null) {
			if (other.consultationDate != null)
				return false;
		} else if (!consultationDate.equals(other.consultationDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (patientId == null) {
			if (other.patientId != null)
				return false;
		} else if (!patientId.equals(other.patientId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConsultationRecord [id=" + id + ", patientId=" + patientId + ", consultationDate=" + consultationDate
				+ "]";
	}
	 
	 

}
