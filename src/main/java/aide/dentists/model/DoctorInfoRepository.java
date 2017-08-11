package aide.dentists.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DoctorInfoRepository extends CrudRepository<DoctorInfo, Integer>{

	public List<DoctorInfo> findByFirstName(String firstName);
	
	public List<DoctorInfo> findByLastName(String lastName);
	
	public List<DoctorInfo> findByMobileNumber(String mobileNumber);
	
	public List<DoctorInfo> findByEmail(String email);

	public List<DoctorInfo> findById(Integer id);
	
	public List<DoctorInfo> findByEmailAndPassword(String email, String password);
	
}
