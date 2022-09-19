package com.dxc.createagent.dao.implement;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.createagent.dao.AgentDAO;
import com.dxc.createagent.entity.Agent;

@Transactional
public class AgentDAOImpl implements AgentDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Agent> listAgent() {
		List<Agent> list = new ArrayList<Agent>();
		Session session = sessionFactory.getCurrentSession();
		list = session.createQuery("from Agent", Agent.class).getResultList();
		session.flush();
		return list;
	}

	public void addAgent(Agent agent) {
		Session session = sessionFactory.getCurrentSession();

		//Set string ID
		String code;
		if (countAgent()<10) {
			code = "AG00000" + countAgent();
		} else if (countAgent()<100) {
			code = "AG0000" + countAgent();
		}else if (countAgent()<1000) {
			code = "AG000" + countAgent();
		}else if (countAgent()<10000) {
			code = "AG00" + countAgent();
		}else {
			code = "AG0" + countAgent();
		}
		agent.setAgentCode(code);;
		try {
			session.persist(agent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Agent findAgentByCode(String code) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria crit = session.createCriteria(Agent.class);
		crit.add(Restrictions.eq("agentCode", code));
		return (Agent) crit.uniqueResult();
	}

	public int countAgent() {
		int number = listAgent().size() + 1;
		return number;
	}

	public void updateAgentInfor(Agent agent) {
		Session session = sessionFactory.getCurrentSession();
		session.load(Agent.class, agent.getAgentCode());
		session.update(agent);
		System.out.println("Update sucessful!");
	}

	public Agent findAgentByLicenceNumber(String licenceNumber) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria crit = session.createCriteria(Agent.class);
		crit.add(Restrictions.eq("licenceNumber", licenceNumber));
		return (Agent) crit.uniqueResult();
	}

	public List<Agent> listAgentByCompany(String companyCode, String accountType) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM Agents WHERE company_code = :companyCode and account_type = :accountType";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Agent.class);
		query.setParameter("companyCode", companyCode);
		query.setParameter("accountType", accountType);
		List<Agent> results = query.list();
		return results;
	}

}
