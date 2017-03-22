package aide.dentists.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
/**
 * 
 * This is an interface for consultation records table (basic CRUD operations)
 * @author Vivek Mittal 
 *
 */
public interface ConsultationRecordRepository extends CrudRepository<ConsultationRecord, Integer>{

	public List<ConsultationRecord> findById(Integer id);
	
	public List<ConsultationRecord> findByPatientId(Integer patientId);

	public List<ConsultationRecord> findByPatientIdAndConsultationDate(Integer patientId, Date consultationDate);
}

