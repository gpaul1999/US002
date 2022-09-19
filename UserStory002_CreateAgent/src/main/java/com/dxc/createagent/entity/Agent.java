package com.dxc.createagent.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Agents")
public class Agent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "agent_code", updatable = false, length = 8, nullable = false)
	private String agentCode;
	@Column(name = "company_code", length = 8, nullable = false)
	private String companyCode;
	@Column(name = "company_name", length = 60, nullable = false)
	private String companyName;
	@Column(name = "account_name", length = 60, nullable = false)
	private String accountName;
	@Column(name = "account_type", length = 30, nullable = false)
	private String accountType;
	@Column(name = "licence_number", length = 20, nullable = false)
	private String licenceNumber;
	@Column(name = "account_status", length = 30, nullable = false)
	private String accountStatus;
	
	
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getLicenceNumber() {
		return licenceNumber;
	}
	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}
	
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((licenceNumber == null) ? 0 : licenceNumber.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agent other = (Agent) obj;
		if (licenceNumber == null) {
			if (other.licenceNumber != null)
				return false;
		} else if (!licenceNumber.equals(other.licenceNumber))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Agent [agentCode=" + agentCode + ", companyCode=" + companyCode + ", companyName=" + companyName
				+ ", accountName=" + accountName + ", accountType=" + accountType + ", licenceNumber=" + licenceNumber
				+ ", accountStatus=" + accountStatus + "]";
	}
	public Agent(String companyCode, String companyName, String accountName, String accountType, String licenceNumber,
			String accountStatus) {
		super();
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.accountName = accountName;
		this.accountType = accountType;
		this.licenceNumber = licenceNumber;
		this.accountStatus = accountStatus;
	}
	public Agent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
