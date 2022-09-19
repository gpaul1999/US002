package com.dxc.createagent.dao;

import java.util.List;

import com.dxc.createagent.entity.Agent;

public interface AgentDAO {
	public List<Agent> listAgent();
	public void addAgent(Agent agent);
	public Agent findAgentByCode(String code);
	public int countAgent();
	public void updateAgentInfor(Agent agent);
	public Agent findAgentByLicenceNumber(String licenceNumber);
	public List<Agent> listAgentByCompany(String companyCode, String accountType);
}
