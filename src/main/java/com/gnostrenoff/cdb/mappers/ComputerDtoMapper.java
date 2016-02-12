package com.gnostrenoff.cdb.mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.gnostrenoff.cdb.dto.ComputerDto;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;

public class ComputerDtoMapper {
	
	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	public static Computer toComputer(ComputerDto dto){
		Computer computer = new Computer();		
		computer.setName(dto.getName());
		LocalDateTime introduced = LocalDateTime.parse(dto.getIntroduced(), FORMATTER);
		computer.setIntroduced(introduced);
		LocalDateTime discontinued = LocalDateTime.parse(dto.getDiscontinued(), FORMATTER);
		computer.setDiscontinued(discontinued);
		Company company = new Company();
		company.setName(dto.getCompany());
		computer.setCompany(company);
		
		return computer;
	}
	
	public static ComputerDto toDto(Computer computer){
		
		ComputerDto computerDto = new ComputerDto();
		computerDto.setName(computer.getName());
		computerDto.setIntroduced(computer.getIntroduced().format(FORMATTER));
		computerDto.setDiscontinued(computer.getDiscontinued().format(FORMATTER));

		computerDto.setCompany(computer.getCompany().getName());
		
		return computerDto;
	}
	
	


}
