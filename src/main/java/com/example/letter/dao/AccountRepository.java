package com.example.letter.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.letter.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	Account findByLoginIdAndPassword(String loginId, String password);
	
	Account findByLoginId(String loginId);
}
