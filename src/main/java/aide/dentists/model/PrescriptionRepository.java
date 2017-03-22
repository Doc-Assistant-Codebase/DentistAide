package aide.dentists.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
/**
 * 
 * This is an interface for prescription history records table (basic CRUD operations)
 * @author Vivek Mittal 
 *
 */
public interface PrescriptionRepository extends CrudRepository<Prescription, Integer>{

	public List<Prescription> findByMedName(String medName);
	
	public List<Prescription> findByPatientInfoId(Integer id);
	
	public List<Prescription> findByPrescriptionNumber(Integer id);
}
