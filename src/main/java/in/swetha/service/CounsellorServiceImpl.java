package in.swetha.service;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import in.swetha.binding.DashboardResponse;

import in.swetha.entity.Counsellor;
import in.swetha.repo.CounsellorRepo;


@Service
public class CounsellorServiceImpl implements CounsellorService {
	
	@Autowired
	private CounsellorRepo crepo;

	@Override
	public String saveCounsellor(Counsellor c) {
		
		//check duplicates if found don't create
		
		Counsellor obj = crepo.findByEmail(c.getEmail());
		
		if(obj !=null) {
			return "Email is already Registered";
		}
		
		//Saving register details
		Counsellor savedobj= crepo.save(c);
		
		if (savedobj.getCid()!=null) {
			return "Registration Sucessfully Completed";
			
		}
		
		// TODO Auto-generated method stub
		return "Registration Failed";
	}

	@Override
	public Counsellor loginCheck(String email, String pwd) {

		return crepo.findByEmailAndPwd(email, pwd);
	}

	@Override
	public boolean recoverPwd(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DashboardResponse getDashboardInfo(Integer cid) {
		// TODO Auto-generated method stub
		return null;
	}

}
