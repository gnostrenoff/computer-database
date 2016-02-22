package com.gnostrenoff.cdb.controllers.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gnostrenoff.cdb.controllers.exceptions.InvalidComputerException;
import com.gnostrenoff.cdb.dto.ComputerDto;

public class ComputerDtoValidator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDtoValidator.class);

	public static void validate(ComputerDto computer) {
		
		boolean hasIntroduced = false;
		boolean hasDiscontinued = false;
		
		String name = computer.getName();
		if (name == null || name.isEmpty()) {
			throw new InvalidComputerException("computer has no name");
		}
		
		String introduced = computer.getIntroduced();
		if (introduced != null && !introduced.isEmpty()) {
			computer.setIntroduced(introduced.replace('/', '-'));
			checkDates(computer.getIntroduced());
			hasIntroduced = true;
		}
		String discontinued = computer.getDiscontinued();
		if (discontinued != null && !discontinued.isEmpty()) {
			computer.setDiscontinued(discontinued.replace('/', '-'));
			checkDates(computer.getDiscontinued());
			hasDiscontinued = true;
		}
		
		if(!hasIntroduced && hasDiscontinued){
			LOGGER.error("no introduced date");
			throw new InvalidComputerException("no introduced date");
		}

	}
	
	public static void checkDates(String date){

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    try {
	      dateFormat.parse(date.trim());
	    } catch (ParseException pe) {
	    	LOGGER.error("date invalid");
			throw new InvalidComputerException("date invalid");
	    }
			
	}

}
