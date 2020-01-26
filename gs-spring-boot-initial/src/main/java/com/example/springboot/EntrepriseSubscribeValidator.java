package com.example.springboot;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.springboot.model.Entreprise;

public class EntrepriseSubscribeValidator implements Validator {

	@Override public boolean supports(Class<?> cls) {
		return Entreprise.class.equals(cls);
	}
	
	@Override public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "nbrSalaries", "nbrSalaries.empty", "Le nombre de salariés est obligatoire.");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "raisonSocial", "raisonSocial.empty", "Le raison social est obligatoire.");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "email","email.empty","Un email est obligatoire.");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "mdp","mdp.empty","Un mot de passe est obligatoire.");
	}
}
