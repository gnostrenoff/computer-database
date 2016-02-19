package com.gnostrenoff.cdb.controllers.utils;

import com.gnostrenoff.cdb.controllers.exceptions.InvalidComputerException;
import com.gnostrenoff.cdb.dto.ComputerDto;

public class ComputerDtoValidator {

	public static void validate(ComputerDto computer) {
		String name = computer.getName();
		if (name == null || name.isEmpty()) {
			throw new InvalidComputerException("computer has no name");
		}
	}

}
