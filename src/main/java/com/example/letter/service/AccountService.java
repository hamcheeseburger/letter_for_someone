package com.example.letter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.letter.dao.AccountRepository;
import com.example.letter.domain.Account;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepo;
	
	public Account findOne(int account_id) {
		return accountRepo.getById(account_id);
	}
	
	public Account findByLoginIdAndPassword(String loginId, String password) {
		return accountRepo.findByLoginIdAndPassword(loginId, password);
	}
	
	public Account saveAccount(Account account) {
		return accountRepo.save(account);
	}
}
