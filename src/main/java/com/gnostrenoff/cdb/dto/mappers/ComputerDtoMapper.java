package com.gnostrenoff.cdb.dto.mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.gnostrenoff.cdb.dto.ComputerDto;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;

/**
 * This class is providing a static method to convert a dto to a computer object, and a computer into dto
 * @author excilys
 */
public class ComputerDtoMapper {
	
	/**
	 * format for dates
	 */
	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	
	/**
	 * converts a dto in computer
	 * @param dto
	 * @return computer
	 */
	public static Computer toComputer(ComputerDto dto){
		Computer computer = new Computer();		
		computer.setName(dto.getName());
		computer.setId(dto.getId());
		
		String strIntroduced = dto.getIntroduced();
		if(strIntroduced != null && !strIntroduced.isEmpty()){
			LocalDate introduced = LocalDate.parse(strIntroduced, FORMATTER);
			computer.setIntroduced(introduced);
		}
		String strDiscontinued = dto.getDiscontinued();
		if(strDiscontinued != null && !strDiscontinued.isEmpty()){
			LocalDate discontinued = LocalDate.parse(strDiscontinued, FORMATTER);
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
	
	/**
	 * converts a computer in dto
	 * @param computer
	 * @return dto
	 */
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
	
	/**
	 * convert a list of dto into a list of computer
	 * @param dtoList list of dtos
	 * @return computerList list of computers
	 */
	public static List<Computer> toComputerList(List<ComputerDto> dtoList){
		
		List<Computer> computerList = new ArrayList<>();
		
		for(ComputerDto dto : dtoList){
			computerList.add(toComputer(dto));
		}
		
		return computerList;
	}
	
	/**
	 * convert a list of computer into a list of dto
	 * @param computerList list of computers
	 * @return dtoList of dtos
	 */
	public static List<ComputerDto> toDtoList(List<Computer> computerList){
		
		List<ComputerDto> dtoList = new ArrayList<>();
		
		for(Computer computer : computerList){
			dtoList.add(toDto(computer));
		}
		
		return dtoList;
	}


}
