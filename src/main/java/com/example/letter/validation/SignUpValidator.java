package com.example.letter.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.letter.dao.AccountRepository;
import com.example.letter.domain.Account;
import com.example.letter.domain.AccountForm;

@Component
public class SignUpValidator implements Validator{
	@Autowired
	AccountRepository repo;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		AccountForm accountForm = (AccountForm) target;
		Account account = accountForm.getAccount();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.loginId", "required", "아이디를 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.password", "required", "비밀번호를 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordCheck", "required", "비밀번호 확인을 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.nickname", "required", "닉네임를 입력하세요.");
		
		if(errors.hasErrors()) return;
	
		Account existAccount = repo.findByLoginId(account.getLoginId());
		
		if(existAccount != null) {
			errors.rejectValue("account.loginId", "DuplicateId","중복되는 아이디 입니다.");
		}
		
		if(!account.getPassword().equals(accountForm.getPasswordCheck())) {
			errors.rejectValue("passwordCheck", "PasswordNotMatch", "비밀번호가 일치하지 않습니다.");
		}
	}

}
