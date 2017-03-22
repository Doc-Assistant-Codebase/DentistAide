package aide.dentists.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import aide.dentists.model.ConsultationRecord;
import aide.dentists.model.ConsultationRecordRepository;
import aide.dentists.model.MedicineMasterData;
import aide.dentists.model.MedicineMasterDataRepository;
import aide.dentists.model.PatientInfo;
import aide.dentists.model.PatientInfoRepository;
import aide.dentists.model.Prescription;
import aide.dentists.model.PrescriptionRepository;

import aide.dentists.model.WrapperForMedicinePatientInfo;

/**
 * This is the basic controller class for Dentist Aide.
 * @author Vivek Mittal 
 *
 */
@Controller
public class IndexController {


	@Autowired
	PrescriptionRepository prescriptionRepository;
	
	@Autowired
	MedicineMasterDataRepository medicineMasterDataRepository;
	
	@Autowired
	PatientInfoRepository patientInfoRepository;

	@Autowired
	ConsultationRecordRepository consultationRecordRepository;
	
	@GetMapping("/home")
	public String home(Map<String, Object> model){
		
		List<Prescription> prescriptions = (ArrayList<Prescription>) this.prescriptionRepository.findAll();
		model.put("prescriptions", prescriptions);
		
		List<MedicineMasterData> medMasterDataList = (ArrayList<MedicineMasterData>) this.medicineMasterDataRepository.findAll();
		model.put("masterMedicinesList", medMasterDataList);
		
		HashMap<Integer, MedicineMasterData> mmdTabMap = new HashMap<Integer, MedicineMasterData>();
		HashMap<Integer, MedicineMasterData> mmdCapMap = new HashMap<Integer, MedicineMasterData>();
		HashMap<Integer, MedicineMasterData> mmdSypMap = new HashMap<Integer, MedicineMasterData>();
		HashMap<Integer, MedicineMasterData> mmdGelMap = new HashMap<Integer, MedicineMasterData>();
		HashMap<Integer, MedicineMasterData> mmdSuspMap = new HashMap<Integer, MedicineMasterData>();
		HashMap<Integer, MedicineMasterData> mmdGarglesMap = new HashMap<Integer, MedicineMasterData>();
		HashMap<Integer, MedicineMasterData> mmdMouthwashMap = new HashMap<Integer, MedicineMasterData>();
		
		
		
		for(MedicineMasterData mmd: medMasterDataList){
			if("tab".equalsIgnoreCase(mmd.getDrugForm())){
				mmdTabMap.put(mmd.getId(), mmd);
			}else if("cap".equalsIgnoreCase(mmd.getDrugForm())){
				mmdCapMap.put(mmd.getId(), mmd);
			}else if("syp".equalsIgnoreCase(mmd.getDrugForm())){
				mmdSypMap.put(mmd.getId(), mmd);
			}else if("gel".equalsIgnoreCase(mmd.getDrugForm())){
				mmdGelMap.put(mmd.getId(), mmd);
			}else if("susp".equalsIgnoreCase(mmd.getDrugForm())){
				mmdSuspMap.put(mmd.getId(), mmd);
			}else if("gargles".equalsIgnoreCase(mmd.getDrugForm())){
				mmdGarglesMap.put(mmd.getId(), mmd);
			}else if("mouthwash".equalsIgnoreCase(mmd.getDrugForm())){
				mmdMouthwashMap.put(mmd.getId(), mmd);
			}
		}
		
		model.put("masterMedicinesGelMap", mmdTabMap);
		model.put("masterMedicinesCapMap", mmdCapMap);
		model.put("masterMedicinesSypMap", mmdSypMap);
		model.put("masterMedicinesGelMap", mmdGelMap);
		model.put("masterMedicinesSuspMap", mmdSuspMap);
		model.put("masterMedicinesGarglesMap", mmdGarglesMap);
		model.put("masterMedicinesMouthwashMap", mmdMouthwashMap);
		

		List<MedicineMasterData> medData = new ArrayList<MedicineMasterData>();
		model.put("medData", medData);
		
		List<PatientInfo> patientsList = new ArrayList<PatientInfo>();
		model.put("patientsList", patientsList);
		
		return "startPrescription";
	}
	
	@RequestMapping(value="addPrescription", method=RequestMethod.POST)
	public String addPrescription(@ModelAttribute Prescription prescription){
		prescriptionRepository.save(prescription);
		return "redirect:home";
		
	}
	

	@RequestMapping(value="searchPrescription", method=RequestMethod.POST)
	public String searchPrescription(Model model, @RequestParam String medName){
		
		model.addAttribute("prescriptions", prescriptionRepository.findByMedName(medName.toString()));
		model.addAttribute("medName", medName);
		
		return "home";
	}
	
	@RequestMapping(value="searchMedicine", method=RequestMethod.POST)
	public String searchMedicine(Model model, @RequestParam String tradeName){
		
		model.addAttribute("masterMedicinesList", medicineMasterDataRepository.findByTradeName(tradeName.toString()));
		model.addAttribute("tradeName", tradeName);
		
		return "searchAndAssign";
	}
	
	@RequestMapping(value="registerPatient", method=RequestMethod.POST)
	public String addPatient(Model model, @ModelAttribute PatientInfo patientInfo){
		patientInfoRepository.save(patientInfo);
		model.addAttribute("retrievedPatientInfo", patientInfo);
		
		
		List<PatientInfo> retrievedPatientInfo = new ArrayList<PatientInfo>();
		retrievedPatientInfo.add(patientInfo);
		model.addAttribute("retrievedPatientInfOJSON", new Gson().toJson(retrievedPatientInfo));

		return "searchAndAssignNew";
		
	}
	
	@RequestMapping(value="searchPatient", method=RequestMethod.POST)
	public String searchPatient(Model model, @RequestParam String searchBy, @RequestParam String searchValue){
		
		if(searchBy != null){
			List<PatientInfo> retrievedPatientInfo = getPatientInformation(searchBy, searchValue);

			if(retrievedPatientInfo==null ||  (retrievedPatientInfo!=null && retrievedPatientInfo.isEmpty())){
				
				return "resgisterPatient";
			}
			
			model.addAttribute("retrievedPatientInfo", retrievedPatientInfo);
			
			for(PatientInfo info: retrievedPatientInfo){
				info.setPrescription(null);
			}
			model.addAttribute("retrievedPatientInfOJSON", new Gson().toJson(retrievedPatientInfo));

			if(retrievedPatientInfo!=null && retrievedPatientInfo.size()>1){
				return "selectPatient";
			}
		}
		
		return "searchAndAssignNew";
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
	
	@GetMapping("/newPatientFlow")
	public String newPatientFlow(Map<String, Object> model){
		List<PatientInfo> patientsList = new ArrayList<PatientInfo>();
		model.put("patientsList", patientsList);
		
		return "resgisterPatient";
	}
	
	@RequestMapping(value = "/postSelectedData", method = RequestMethod.POST)
	public ResponseEntity<WrapperForMedicinePatientInfo> postSelectedData(@RequestBody WrapperForMedicinePatientInfo medDataList) {
		List<PatientInfo> patientInfo = patientInfoRepository.findById(medDataList.getPatientInfo().get(0).getId());
		//PatientInfo patientInfo = medDataList.getPatientInfo().get(0);
		List<Prescription> prescriptions = new ArrayList<Prescription>();
		Date prescriptionDate = new Date();
		
		ConsultationRecord consultationRecord = new ConsultationRecord(medDataList.getPatientInfo().get(0).getId(), prescriptionDate);
		consultationRecordRepository.save(consultationRecord);
		
		for (MedicineMasterData medData : medDataList.getMedDataList()) {
			System.out.println("id : " + medData.getId() + " Drug Form :" + medData.getDrugForm() + " Trade Name : "
					+ medData.getTradeName() + " Strength : " + medData.getStrength() + " Frequency : " + medData.getFrquency()
					+ " Duration: " + medData.getDuration() + " Special Instructions: "+medData.getSpecialInstructions());
			
			Prescription prescription = new Prescription();
			prescription.setPrescriptionNumber(consultationRecord.getId());
			prescription.setMedName(medData.getTradeName());
			prescription.setMedStrength(medData.getStrength());
			prescription.setMedType(medData.getDrugForm());
			prescription.setNotes(medData.getSpecialInstructions());
			prescription.setPatientInfo(patientInfo.get(0));
			prescription.setPrescriptionDate(prescriptionDate);
			prescriptions.add(prescription);
		}
		
		prescriptionRepository.save(prescriptions);
		
		medDataList.setPrescriptionNumber(String.valueOf(consultationRecord.getId()));
		
		return new ResponseEntity<WrapperForMedicinePatientInfo>(medDataList, HttpStatus.OK);

	}

	@RequestMapping(value="/fetchPrescriptions", method=RequestMethod.POST)
	public String fetchPrescriptions(Model model, @RequestParam("patientHiddenId") String patientHiddenId){
		
		List<PatientInfo> patientInfo = patientInfoRepository.findById(Integer.parseInt(patientHiddenId));
		for(PatientInfo info: patientInfo){
			info.setPrescription(null);
		}
		model.addAttribute("prescriptionForPatientJSON", new Gson().toJson(patientInfo));
		
		return "prescriptionHistory";
	}
}
