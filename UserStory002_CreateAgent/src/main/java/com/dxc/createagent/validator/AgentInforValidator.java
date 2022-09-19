package com.dxc.createagent.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dxc.createagent.dao.AgentDAO;
import com.dxc.createagent.entity.Agent;
import com.dxc.createagent.model.AgentInfor;

@Component
public class AgentInforValidator implements Validator{
	
	@Autowired
	private AgentDAO agentDao;
	
	public boolean supports(Class<?> clazz) {
		return clazz == AgentInfor.class;
	}

	public void validate(Object target, Errors errors) {
		AgentInfor agentInfor = (AgentInfor) target;
		
		
		//Kiểm tra rỗng các phần tử
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyCode", "NotEmpty.agentForm.companyCode");
		ValidationUtils.rejectIfEmpty(errors, "companyName", "NotEmpty.agentForm.companyName");
		ValidationUtils.rejectIfEmpty(errors, "accountName", "NotEmpty.agentForm.accountName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licenceNumber", "NotEmpty.agentForm.licenceNumber");
		
		
		//Kiểm tra sự trùng lặp License Number
		String licence = agentInfor.getLicenceNumber();
		Agent newAgent = agentDao.findAgentByLicenceNumber(licence);
		if (newAgent != null) {
			if (newAgent.getAgentCode().equals(agentInfor.getAgentCode())==false) {
				errors.rejectValue("licenceNumber", "NoDuplicate.agentForm.licenceNumber");
			}
		}
		//Kiểm tra việc chọn loại tài khoản 
		String accountType = agentInfor.getAccountType();
		if (accountType.equalsIgnoreCase("NONE")) {
			errors.rejectValue("accountType", "NotEmpty.agentForm.accountType");
		}
		//Kiểm tra việc chọn trạng thái tài khoản
		String accountStatus = agentInfor.getAccountStatus();
		if (accountStatus.equalsIgnoreCase("NONE")) {
			errors.rejectValue("accountStatus", "NotEmpty.agentForm.accountStatus");
		}
		
		//Kiểm tra xem Agent mới có cùng công ty và loại tài khoản với Agent đã có
		String companyCode = agentInfor.getCompanyCode();
		List<Agent> listAg = agentDao.listAgentByCompany(companyCode, accountType); //Danh sách Agent trong công ty có cùng Account Type
		if (listAg.size()>0) {//Danh sách > 0 -> có người trùng
			if (agentInfor.getAgentCode().equals("")) { //nếu AgentCode rỗng, tức là tạo mới, không thể có người trùng, ngược lại, AgentCode không rỗng, tức là update
				errors.rejectValue("accountType", "NoDuplicate.agentForm.accountType");
			}
		}
	}
	
}
