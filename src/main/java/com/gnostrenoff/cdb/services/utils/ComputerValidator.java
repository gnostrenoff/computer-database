package com.gnostrenoff.cdb.services.utils;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.services.exceptions.ComputerValidatorException;
import com.gnostrenoff.cdb.services.impl.ComputerServiceImpl;

public class ComputerValidator{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImpl.class);
	
	public static void validate(Computer computer){
		
		String name = computer.getName();
		LocalDate introduced = computer.getIntroduced();
		LocalDate discontinued = computer.getDiscontinued();
		
		if(name == null || name.equals("")){
			LOGGER.error("no name found in computer");
			throw new ComputerValidatorException("no name found in computer");
		}
		if(introduced != null && discontinued != null){
			if(!computer.getDiscontinued().isAfter(computer.getIntroduced())){
				LOGGER.error("invalid dates order");
				throw new ComputerValidatorException("invalid dates order");
			}
		}
		if(introduced == null && discontinued != null){
			LOGGER.error("no introduced date");
			throw new ComputerValidatorException("no introduced date");
		}
	}

}
