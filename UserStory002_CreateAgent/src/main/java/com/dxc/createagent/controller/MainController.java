package com.dxc.createagent.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.dxc.createagent.dao.AgentDAO;
import com.dxc.createagent.entity.Agent;




@Controller
@Transactional
@EnableWebMvc
public class MainController {
	
	
	@Autowired
	private AgentDAO agentDao;
	
	@RequestMapping("/403")
	public String accessDenied() {
		return "/403";
	}
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping({"/agent_list"})
	public String userListHandler(Model model) {
		List<Agent> list = agentDao.listAgent();
		model.addAttribute("listAgent", list);
		return "agent_list";
	}
	
	@RequestMapping(value = { "/agent_info" }, method = RequestMethod.GET)
	public String agentInfor(Model model, @RequestParam(value = "agentCode") String agentCode) {
		Agent agent = agentDao.findAgentByCode(agentCode);
		System.out.println(agent);
		model.addAttribute("agentInfor", agent);
		return "agent_info";
	}
	
	
	
	
}

