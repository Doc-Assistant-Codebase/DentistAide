package aide.dentists.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
/**
 * 
 * This is an interface for master medicine data records table (basic CRUD operations)
 * @author Vivek Mittal 
 *
 */
public interface MedicineMasterDataRepository extends CrudRepository<MedicineMasterData, Integer>{

	public List<MedicineMasterData> findByTradeName(String tradeName);
	
	public List<MedicineMasterData> findByDrugForm(String drugForm);
}

