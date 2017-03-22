package aide.dentists.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
/**
 * 
 * This is an interface for patient information records table (basic CRUD operations)
 * @author Vivek Mittal 
 *
 */
public interface PatientInfoRepository extends CrudRepository<PatientInfo, Integer>{

	public List<PatientInfo> findByFirstName(String firstName);
	
	public List<PatientInfo> findByLastName(String lastName);
	
	public List<PatientInfo> findByMobileNumber(String mobileNumber);
	
	public List<PatientInfo> findByEmail(String email);

	public List<PatientInfo> findById(Integer id);
	
}


