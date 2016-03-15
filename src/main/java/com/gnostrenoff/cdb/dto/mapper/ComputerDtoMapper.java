package com.gnostrenoff.cdb.dto.mapper;

import com.gnostrenoff.cdb.dto.ComputerDto;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is providing a static method to convert a dto to a computer object, and a computer
 * into dto.
 *
 * @author excilys
 */
@Component
public class ComputerDtoMapper {

  /** The message source. */
  @Autowired
  private MessageSource messageSource;

  /**
   * converts a dto in computer.
   *
   * @param dto
   *          the dto
   * @return computer
   */
  public Computer toComputer(ComputerDto dto) {
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
        messageSource.getMessage("util.dateFormat", null, LocaleContextHolder.getLocale()));
    
    Computer computer = new Computer();
    computer.setName(dto.getName());
    computer.setId(dto.getId());

    String strIntroduced = dto.getIntroduced();
    if (strIntroduced != null && !strIntroduced.isEmpty()) {
      LocalDate introduced = LocalDate.parse(strIntroduced, formatter);
      computer.setIntroduced(introduced);
    }
    String strDiscontinued = dto.getDiscontinued();
    if (strDiscontinued != null && !strDiscontinued.isEmpty()) {
      LocalDate discontinued = LocalDate.parse(strDiscontinued, formatter);
      computer.setDiscontinued(discontinued);
    }

    if (dto.getCompanyId() != 0) {
      Company company = new Company();
      company.setName(dto.getCompanyName());
      company.setId(dto.getCompanyId());
      computer.setCompany(company);
    }

    return computer;
  }

  /**
   * converts a computer in dto.
   *
   * @param computer
   *          the computer
   * @return dto
   */
  public ComputerDto toDto(Computer computer) {
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
        messageSource.getMessage("util.dateFormat", null, LocaleContextHolder.getLocale()));

    ComputerDto computerDto = new ComputerDto();
    computerDto.setName(computer.getName());
    computerDto.setId(computer.getId());

    if (computer.getIntroduced() != null) {
      computerDto.setIntroduced(computer.getIntroduced().format(formatter));
    }
    if (computer.getDiscontinued() != null) {
      computerDto.setDiscontinued(computer.getDiscontinued().format(formatter));
    }

    Company company = computer.getCompany();
    if (company != null) {
      computerDto.setCompanyName(company.getName());
      computerDto.setCompanyId(company.getId());
    }

    return computerDto;
  }

  /**
   * convert a list of dto into a list of computer.
   *
   * @param dtoList
   *          list of dtos
   * @return computerList list of computers
   */
  public List<Computer> toComputerList(List<ComputerDto> dtoList) {

    List<Computer> computerList = new ArrayList<>();

    dtoList.forEach(dto -> computerList.add(toComputer(dto)));

    return computerList;
  }

  /**
   * convert a list of computer into a list of dto.
   *
   * @param computerList
   *          list of computers
   * @return dtoList of dtos
   */
  public List<ComputerDto> toDtoList(List<Computer> computerList) {

    List<ComputerDto> dtoList = new ArrayList<>();

    computerList.forEach(computer -> dtoList.add(toDto(computer)));

    return dtoList;
  }
}
