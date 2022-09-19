package com.dxc.createagent.dao;

import com.dxc.createagent.entity.Account;

public interface AccountDAO {
	public Account findAccount(String userName);
}
