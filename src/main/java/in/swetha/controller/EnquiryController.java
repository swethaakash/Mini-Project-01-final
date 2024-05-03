package in.swetha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.swetha.binding.SearchCriteria;
import in.swetha.entity.StudentEnq;
import in.swetha.service.EnquiryService;

@Controller
public class EnquiryController {
	
	@Autowired
	private EnquiryService enqService;
	
	
	@GetMapping("/enquiry")
	public String enqPage(Model model) {
		model.addAttribute("enq", new StudentEnq());
		return"addEnqView";
		
	}
	
	@PostMapping("/addEnquiry")
	public String addEnquiry(StudentEnq se, Model model) {
		boolean addEnq= enqService.addEnq(se);
		if (addEnq) {
			model.addAttribute("smsg", "added enquiry");
		} else {
			model.addAttribute("errMsg", "add enquiry failed");
		}
		return "addEnqView";
	}
	
	@GetMapping("/enquiries")
	public String viewEnquiries(Model model) {
		List<StudentEnq> enquiriesList =  enqService.getEnquiries(null,null);
		model.addAttribute("enquiries", enquiriesList);
		return "displayEnqView";
		
	}
	
	
	@PostMapping("/filter-enquiries")
	public String filterEnquiries(SearchCriteria sc, Model model) {
		List<StudentEnq> enquiriesList =  enqService.getEnquiries(null,null);
		model.addAttribute("enquiries", enquiriesList);
		return "displayEnqView";
		
		
	}
	
	
	

}
