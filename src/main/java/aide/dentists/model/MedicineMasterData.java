package aide.dentists.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * This is an entity class for master medicine data records
 * @author Vivek Mittal 
 *
 */
@Entity
@Configuration
@ConfigurationProperties("oracle")
public class MedicineMasterData {

	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_medicine_master")
	 @SequenceGenerator(name="seq_medicine_master", initialValue=1, allocationSize=1)
	 private Integer id;

	 public Integer getId() {
	   return id;
	 }

	 public void setId(Integer id) {
	   this.id = id;
	 }
	 
	 private String drugForm;
	 private String tradeName;
	 private String strength="";
	 private String frequency="";
	 private String duration="";
	 private String specialInstructions="";

	public String getDrugForm() {
		return drugForm;
	}

	public void setDrugForm(String drugForm) {
		this.drugForm = drugForm;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = (strength!=null)?strength:"";
	}

	public String getFrquency() {
		return frequency;
	}

	public void setFrquency(String frequency) {
		this.frequency = (frequency!=null)?frequency:"";;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = (duration!=null)?duration:"";;;
	}

	public String getSpecialInstructions() {
		return specialInstructions;
	}

	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = (specialInstructions!=null)?specialInstructions:"";;;
	}
	 
	public MedicineMasterData(){
		
	}

	public MedicineMasterData(String drugForm, String tradeName, String strength, String frequency, String duration,
			String specialInstructions) {
		super();
		this.drugForm = drugForm;
		this.tradeName = tradeName;
		this.strength = strength;
		this.frequency = frequency;
		this.duration = duration;
		this.specialInstructions = specialInstructions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((drugForm == null) ? 0 : drugForm.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((frequency == null) ? 0 : frequency.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((specialInstructions == null) ? 0 : specialInstructions.hashCode());
		result = prime * result + ((strength == null) ? 0 : strength.hashCode());
		result = prime * result + ((tradeName == null) ? 0 : tradeName.hashCode());
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
		MedicineMasterData other = (MedicineMasterData) obj;
		if (drugForm == null) {
			if (other.drugForm != null)
				return false;
		} else if (!drugForm.equals(other.drugForm))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (frequency == null) {
			if (other.frequency != null)
				return false;
		} else if (!frequency.equals(other.frequency))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (specialInstructions == null) {
			if (other.specialInstructions != null)
				return false;
		} else if (!specialInstructions.equals(other.specialInstructions))
			return false;
		if (strength == null) {
			if (other.strength != null)
				return false;
		} else if (!strength.equals(other.strength))
			return false;
		if (tradeName == null) {
			if (other.tradeName != null)
				return false;
		} else if (!tradeName.equals(other.tradeName))
			return false;
		return true;
	}
	
	
}
