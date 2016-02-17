package com.gnostrenoff.cdb.services.validator;

import java.time.LocalDate;

import com.gnostrenoff.cdb.exceptions.ComputerValidatorException;
import com.gnostrenoff.cdb.model.Computer;

public class ComputerValidator{
	
	public static void validate(Computer computer){
		
		String name = computer.getName();
		LocalDate introduced = computer.getIntroduced();
		LocalDate discontinued = computer.getDiscontinued();
		
		if(name.equals("")){
			throw new ComputerValidatorException("no name found in computer");
		}
		if(introduced != null && discontinued != null){
			if(!computer.getDiscontinued().isAfter(computer.getIntroduced())){
				throw new ComputerValidatorException("computer cannot be discontinued before being introduced!");
			}
		}
	}

}
