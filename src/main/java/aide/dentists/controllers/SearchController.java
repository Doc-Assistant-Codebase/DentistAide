package aide.dentists.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aide.dentists.model.MedicineMasterData;
import aide.dentists.model.MedicineMasterDataRepository;
import aide.dentists.model.PatientInfo;
import aide.dentists.model.PatientInfoRepository;
import aide.dentists.model.PatientPrescriptionHistory;
import aide.dentists.model.Prescription;
import aide.dentists.model.PrescriptionRepository;

/**
 * This is the rest controller class for Dentist Aide.
 * @author Vivek Mittal 
 *
 */
@RestController
public class SearchController {

	@Autowired
	MedicineMasterDataRepository medicineMasterDataRepository;

	@Autowired
	PatientInfoRepository patientInfoRepository;

	@Autowired
	PrescriptionRepository prescriptionRepository;
	
	
    @PostMapping("/searchMedicineByTradeName")
	public ResponseEntity<List<MedicineMasterData>> getMedicineByTradeName(@RequestParam String tradeName){
    	System.out.println("Inside getMedicineByTradeName ");
    	List<MedicineMasterData> medList = medicineMasterDataRepository.findByTradeName(tradeName.toString());
    	
		System.out.println("returing json from getMedicineByTradeName "+medList.size());
		return new ResponseEntity<List<MedicineMasterData>>(medList, HttpStatus.OK);
		
	}
    
    @PostMapping("/searchMedicineByDrugFormJson")
	public ResponseEntity<List<MedicineMasterData>> getMedicineByDrugForm(@RequestParam String drugForm){
    	System.out.println("Inside getMedicineByDrugForm ");
    	List<MedicineMasterData> medList = medicineMasterDataRepository.findByDrugForm(drugForm.toString());
    	System.out.println("returing json from getMedicineByDrugForm "+medList.size());
		return new ResponseEntity<List<MedicineMasterData>>(medList, HttpStatus.OK);
		
	}
    

	@RequestMapping(value="/searchPatientJSON", method=RequestMethod.POST)
	public ResponseEntity<List<PatientInfo>> searchPatientsToReturnJson(Model model, @RequestParam String searchBy, @RequestParam String searchValue){
		
		if(searchBy != null){
			List<PatientInfo> retrievedPatientInfo = getPatientInformation(searchBy, searchValue);
			
			if(retrievedPatientInfo!=null){
				return new ResponseEntity<List<PatientInfo>>(retrievedPatientInfo, HttpStatus.OK);
			}
		}
		
		return null;
	}
	
	@ModelAttribute("retrievedPatientInfo")
	private List<PatientInfo> getPatientInformation(String searchBy, String searchValue){
		if(searchBy != null){
			switch(searchBy){
			case "firstName":
				return patientInfoRepository.findByFirstName(searchValue.toString());
				
	
			case "lastName":
				return patientInfoRepository.findByLastName(searchValue.toString());
				
			case "email":
				return patientInfoRepository.findByEmail(searchValue.toString());
				
	
			case "mobileNumber":
				return patientInfoRepository.findByMobileNumber(searchValue.toString());
			}
		}
		return new ArrayList<PatientInfo>();
	}
	
	@PostMapping("/searchPrescriptionsForPatientId")
	public ResponseEntity<List<PatientPrescriptionHistory>> searchPrescriptionsForPatientId(@RequestParam String patientId){
    	System.out.println("Inside searchPrescriptionsForPatientId ");
    	List<Prescription> presList = prescriptionRepository.findByPatientInfoId(Integer.parseInt(patientId));
    	List<PatientPrescriptionHistory> prescriptionHistory = new ArrayList<PatientPrescriptionHistory>();
    	for(Prescription prescription: presList){
    		prescriptionHistory.add(new PatientPrescriptionHistory(prescription.getMedName(), prescription.getMedType()
    				, prescription.getMedStrength(), prescription.getNotes(), prescription.getId(), prescription.getPrescriptionDate(), prescription.getPrescriptionNumber()));
    	}
    	System.out.println("returing json from searchPrescriptionsForPatientId "+prescriptionHistory.size());
		return new ResponseEntity<List<PatientPrescriptionHistory>>(prescriptionHistory, HttpStatus.OK);
		
	}
    
	@PostMapping("/searchPrescriptionsForPrescNumber")
	public ResponseEntity<List<PatientPrescriptionHistory>> searchPrescriptionsForPrescNumber(@RequestParam String prescriptionNumber){
    	System.out.println("Inside searchPrescriptionsForPrescNumber ");
    	List<Prescription> presList = prescriptionRepository.findByPrescriptionNumber(Integer.parseInt(prescriptionNumber));
    	System.out.println("returing json from searchPrescriptionsForPrescNumber "+presList.size());
    	List<PatientPrescriptionHistory> prescriptionHistory = new ArrayList<PatientPrescriptionHistory>();
    	for(Prescription prescription: presList){
    		prescriptionHistory.add(new PatientPrescriptionHistory(prescription.getMedName(), prescription.getMedType()
    				, prescription.getMedStrength(), prescription.getNotes(), prescription.getId(), prescription.getPrescriptionDate(), prescription.getPrescriptionNumber()));
    	}
    	System.out.println("returing json from searchPrescriptionsForPatientId "+prescriptionHistory.size());
    	
    	return new ResponseEntity<List<PatientPrescriptionHistory>>(prescriptionHistory, HttpStatus.OK);
		
	}
	
}
