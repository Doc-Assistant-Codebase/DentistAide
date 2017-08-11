package aide.dentists.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * This is an entity class for patient information records
 * @author Vivek Mittal 
 *
 */
@Entity
@Configuration
@ConfigurationProperties("oracle")
public class PatientInfo {

	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_patient")
	 @SequenceGenerator(name="seq_patient", initialValue=1, allocationSize=1)
	private Integer id;
	 

	 @Column(name="firstName")
	 private String firstName;

	 @Column(name="lastName")
	 private String lastName;

	 @Column(name="mobileNumber")
	 private String mobileNumber;

	 @Column(name="address")
	 private String address;

	 @Column(name="email")
	 private String email;
	
	 @Column(name="billNumber")
	 private String billNumber;

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public PatientInfo(){
		 
	 }
	 
	public PatientInfo(String firstName, String lastName, String mobileNumber, String address, String email,
			String billNumber, List<Prescription> prescription) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.email = email;
		this.billNumber = billNumber;
		this.prescription = prescription;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = StringUtils.isNotBlank(firstName)?StringUtils.lowerCase(firstName):null;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = StringUtils.isNotBlank(lastName)?StringUtils.lowerCase(lastName):null;;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = StringUtils.isNotBlank(address)?StringUtils.lowerCase(address):null;;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = StringUtils.isNotBlank(email)?StringUtils.lowerCase(email):null;;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
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
		PatientInfo other = (PatientInfo) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (mobileNumber == null) {
			if (other.mobileNumber != null)
				return false;
		} else if (!mobileNumber.equals(other.mobileNumber))
			return false;
		return true;
	}
	
	@OneToMany(mappedBy="patientInfo")
	private List<Prescription> prescription;

	public List<Prescription> getPrescription() {
		return prescription;
	}

	public void setPrescription(List<Prescription> prescription) {
		this.prescription = prescription;
	}

	

}
