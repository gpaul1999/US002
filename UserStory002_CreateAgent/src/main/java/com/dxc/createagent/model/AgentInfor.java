package com.dxc.createagent.model;

public class AgentInfor {
	private String agentCode;
	private String companyCode;
	private String companyName;
	private String accountName;
	private String accountType;
	private String licenceNumber;
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
	public String toString() {
		return "AgentInfor [agentCode=" + agentCode + ", companyCode=" + companyCode + ", companyName=" + companyName
				+ ", accountName=" + accountName + ", accountType=" + accountType + ", licenceNumber=" + licenceNumber
				+ ", accountStatus=" + accountStatus + "]";
	}
	
	
}
