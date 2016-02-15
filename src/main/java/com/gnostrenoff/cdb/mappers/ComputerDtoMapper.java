package com.gnostrenoff.cdb.mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.gnostrenoff.cdb.dto.ComputerDto;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;

public class ComputerDtoMapper {
	
	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	public static Computer toComputer(ComputerDto dto){
		Computer computer = new Computer();		
		computer.setName(dto.getName());
		computer.setId(dto.getId());
		
		String strIntroduced = dto.getIntroduced();
		if(strIntroduced != null && !strIntroduced.isEmpty()){
			LocalDateTime introduced = LocalDateTime.parse(strIntroduced, FORMATTER);
			computer.setIntroduced(introduced);
		}
		String strDiscontinued = dto.getDiscontinued();
		if(strDiscontinued != null && !strDiscontinued.isEmpty()){
			LocalDateTime discontinued = LocalDateTime.parse(strDiscontinued, FORMATTER);
			computer.setDiscontinued(discontinued);
		}

		if(dto.getCompanyId() != 0){
			Company company = new Company();
			company.setName(dto.getCompanyName());
			company.setId(dto.getCompanyId());
			computer.setCompany(company);
		}
	
		return computer;
	}
	
	public static ComputerDto toDto(Computer computer){
		
		ComputerDto computerDto = new ComputerDto();
		computerDto.setName(computer.getName());
		computerDto.setId(computer.getId());
		
		if(computer.getIntroduced() != null)
			computerDto.setIntroduced(computer.getIntroduced().format(FORMATTER));
		if(computer.getDiscontinued() != null)
			computerDto.setDiscontinued(computer.getDiscontinued().format(FORMATTER));

		Company company = computer.getCompany();
		if(company != null){
			computerDto.setCompanyName(company.getName());
			computerDto.setCompanyId(company.getId());
		}
		
		return computerDto;
	}
	
	public static List<Computer> toComputerList(List<ComputerDto> dtoList){
		
		List<Computer> computerList = new ArrayList<>();
		
		for(ComputerDto dto : dtoList){
			computerList.add(toComputer(dto));
		}
		
		return computerList;
	}
	
	public static List<ComputerDto> toDtoList(List<Computer> computerList){
		
		List<ComputerDto> dtoList = new ArrayList<>();
		
		for(Computer computer : computerList){
			dtoList.add(toDto(computer));
		}
		
		return dtoList;
	}


}
