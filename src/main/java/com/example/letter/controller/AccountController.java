package com.example.letter.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.letter.domain.Account;
import com.example.letter.domain.AccountForm;
import com.example.letter.service.AccountService;
import com.example.letter.validation.SignUpValidator;

@Controller
public class AccountController {
	@Autowired
	AccountService accountService;
	
	@Autowired
	SignUpValidator signUpValidator;
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@PostMapping("/login")
	public String login(@RequestParam String loginId, @RequestParam String password, Model model) {
		logger.info(loginId + "," + password);
		
		if(loginId.equals("") || password.equals("")) {
			model.addAttribute("fieldEmpty", "아이디와 비밀번호를 입력하세요.");
			return "login";
		}
		
		Account account = accountService.findByLoginIdAndPassword(loginId, password);
		
		if(account != null) {
			logger.info(account.getNickname());
			return "index";
		}
		
		model.addAttribute("loginError", "아이디 혹은 비밀번호가 틀립니다.");
		return "login";
	}
	
	@GetMapping("/signup")
	public String signupForm(Model model) {
		
		model.addAttribute("accountForm", new AccountForm());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signup(@ModelAttribute AccountForm accountForm, BindingResult result) {
		logger.info(accountForm.getAccount().getLoginId());
		logger.info(accountForm.getAccount().getPassword());
		logger.info(accountForm.getAccount().getNickname());
		logger.info(accountForm.getPasswordCheck());
		
		signUpValidator.validate(accountForm, result);
		
		if(result.hasErrors()) return "signup";
		
		accountService.saveAccount(accountForm.getAccount());
		
		
		return "redirect:/";
	}
}
