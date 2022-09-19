package com.dxc.createagent.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dxc.createagent.dao.AgentDAO;
import com.dxc.createagent.entity.Agent;
import com.dxc.createagent.model.AgentInfor;
import com.dxc.createagent.validator.AgentInforValidator;

@Controller
@Transactional
@EnableWebMvc
public class AdminController {
	private List<String> lsAccType = Arrays.asList("Agent", "Broker", "Coinsurer", "Reinsurer");
	private List<String> lsStatus = Arrays.asList("Active", "Terminated");
	private static final Log log = LogFactory.getLog(AdminController.class);
	
	@Autowired
	private AgentDAO agentDao;
	
	@Autowired
	private AgentInforValidator agentInforValidator;
	
	@InitBinder
	public void myInitBinder (WebDataBinder binder) {
		Object target = binder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target: " + target);
		
		if (target.getClass()==AgentInfor.class) {
			binder.setValidator(agentInforValidator);
		}
	}
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping("/add_form")
	public String showForm (Model model) {
		AgentInfor agentInfo = new AgentInfor();
		model.addAttribute("agentForm", agentInfo);
		model.addAttribute("lsAccType", lsAccType);
		model.addAttribute("lsStatus", lsStatus);
		return "add_form";
	}
	
	@RequestMapping(value = {"/add_form"}, method = RequestMethod.POST)
	@Transactional(propagation = Propagation.NEVER)
	public String addAgent(Model  model, @ModelAttribute("agentForm") @Validated AgentInfor agentInfor, 
			BindingResult result, final RedirectAttributes redirectAttribute) {
		model.addAttribute("lsAccType", lsAccType);
		model.addAttribute("lsStatus", lsStatus);
		if(result.hasErrors()) {
			log.error("ADD AGENT FAIL!");
			return "add_form";
		}
		
		Agent agent = new Agent(agentInfor.getCompanyCode(), agentInfor.getCompanyName(), 
				agentInfor.getAccountName(), agentInfor.getAccountType(), 
				agentInfor.getLicenceNumber(), agentInfor.getAccountStatus());
		
		System.out.println(agent);
		try {
			agentDao.addAgent(agent);
			log.error("ADD AGENT SUCESSFULLY!");
		} catch (Exception e) {
			String message = e.getMessage();
			model.addAttribute("message", message);
			log.error("ADD AGENT FAIL!");
			return "add_form";
		}
		return "redirect:/result_add";
	}
	@RequestMapping("/result_add")
	private String showResult(Model model) {
		return "result_add";
	}
	
	@RequestMapping("/edit_agent")
	public String showEditForm (Model model, @RequestParam(value = "agentCode") String agentCode) {
		AgentInfor agentInfo = new AgentInfor();
		model.addAttribute("agentForm", agentInfo);
		Agent agent = agentDao.findAgentByCode(agentCode);
		System.out.println(agent);
		model.addAttribute("agentInfor", agent);
		model.addAttribute("lsAccType", lsAccType);
		model.addAttribute("lsStatus", lsStatus);
		return "edit_agent";
	}
	@RequestMapping(value = { "/edit_agent" }, method = RequestMethod.POST)
	@Transactional(propagation = Propagation.NEVER)
	public String editAgent(Model  model, @RequestParam(value = "agentCode") String agentCode, 
			@ModelAttribute("agentForm") @Validated AgentInfor agentInfor, 
			BindingResult result, final RedirectAttributes redirectAttribute) {
		model.addAttribute("lsAccType", lsAccType);
		model.addAttribute("lsStatus", lsStatus);
		if(result.hasErrors()) {
			log.error("==========MODIFY AGENT FAIL!==========");
			return "edit_agent";
		}
		Agent agent = new Agent(agentInfor.getCompanyCode(), agentInfor.getCompanyName(), 
				agentInfor.getAccountName(), agentInfor.getAccountType(), 
				agentInfor.getLicenceNumber(), agentInfor.getAccountStatus());
		agent.setAgentCode(agentCode);
		System.out.println("See Agent before update: " + agent);
		try {
			agentDao.updateAgentInfor(agent);
			log.error("==========MODIFY AGENT SUCESSFULLY!==========");
		} catch (Exception e) {
			String message = e.getMessage();
			model.addAttribute("message", message);
			log.error("==========MODIFY AGENT FAIL!==========");
			return "edit_agent";
		}
		return "redirect:/result_modify";
	}
	
	@RequestMapping("/result_modify")
	private String showResultModify(Model model) {
		return "result_modify";
	}
	
}
