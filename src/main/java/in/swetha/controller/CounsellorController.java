package in.swetha.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.swetha.entity.Counsellor;
import in.swetha.service.CounsellorService;

@Controller // shows this class as a Controller
public class CounsellorController {
	@Autowired  // Connects and talks with CounsellorService class
	private CounsellorService counsellorSvc;
	
	//1.display login view
	@GetMapping("/") // 1.get request API from Frontend
	public String index(Model model) {
		model.addAttribute("counsellor", new Counsellor()); // to map with binding object --> "counsellor" is the Key which can be anything
		return "loginView"; // login view should mapped to binding object
		
	}
	
	
	//5.login method
	@PostMapping("/login")
	public String handleLogin(Counsellor c, Model model) { //input data as a Counsellor Object, Model is for UI
		Counsellor obj =  counsellorSvc.loginCheck(c.getEmail(), c.getPwd()); //calling service class and logincheck method and getting email and password from Counsellor object & saving it into obj
		
		if (obj == null) {
			model.addAttribute("errMsg", "Invalid Credentials");
			return "loginView";
			
		}
		return "redirect:dashboard";
		     
	}
	
	//6. get data for dashboard : method
	@GetMapping("/dashboard")
	public String buildDashboard(Model model) {
		
		//get data for dashboard
		
		return "dashboardView";
		
		
	}
	
	
    //2. display register page
	@GetMapping("/register") //2. get request API from Frontend to see the regitration page
	public String regPage(Model model) {
		
		model.addAttribute("counsellor", new Counsellor()); // to map with binding object --> "counsellor" is the Key which can be anything
		return "registerView"; // login view should mapped to binding object
	}
	
	// 4. register/signUp page
	@PostMapping("/register") //4. register page where counsellor 
	public String handleRegistration(Counsellor c, Model model) {
		 String msg=counsellorSvc.saveCounsellor(c);
		 model.addAttribute("msg", msg); // whatever the msg that we get from service, sending it to Model which shows on UI
		 return"registerView";
	}
	
	// 3. display forgot password page
	@GetMapping("/forgotPwd") //3.display forgotpwd page
	public String recoverPwdPage(Model model) {
		return "forgotPwdView";
	}
	
	//7. recover password method
	
	public String recoverPwd(@RequestParam String email, Model model) { // @request Param to get request using email parameter
		
		boolean status = counsellorSvc.recoverPwd(email); // sending it to service to the recoverpwd method and store the msg into status Object
		
		//If success: success msg
		//else: errormsg
		if (status) {
			model.addAttribute("smsg", "Pwd sent to your email");
			
		} else {
			model.addAttribute("errmsg", "Invalid email");

		}
		
		//fails: return to again forgotpwdview
		return "forgotPwdView";
		
	}
	
	
	
	

}
