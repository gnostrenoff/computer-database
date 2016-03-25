package com.gnostrenoff.cdb.controller;

import com.gnostrenoff.cdb.dto.ComputerDto;
import com.gnostrenoff.cdb.dto.PageDto;
import com.gnostrenoff.cdb.dto.mapper.ComputerDtoMapper;
import com.gnostrenoff.cdb.dto.mapper.PageDtoMapper;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.service.ComputerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class ComputerRestController.
 */
@RestController
@RequestMapping(value = "/api/computer")
public class ComputerRestController {

    /**
     * The computer service.
     */
    @Autowired
    private ComputerService computerService;

    /**
     * The computer dto mapper.
     */
    @Autowired
    private ComputerDtoMapper computerDtoMapper;

    @Autowired
    private PageDtoMapper pageDtoMapper;

    /**
     * Gets the computer matching with given id.
     *
     * @param id the id
     * @return the response entity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    //@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ComputerDto> get(@PathVariable long id) {
        return new ResponseEntity<>(computerDtoMapper.toDto(computerService.get(id)), HttpStatus.OK);
    }

    /**
     * Gets the list of computers.
     *
     * @return the response entity
     */
    @RequestMapping(value = "/all/{index}", method = RequestMethod.GET)
    //@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<PageDto> get(@PathVariable int index) {
        if (index <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        PageRequest pageRequest = new PageRequest(index - 1, 10, Direction.ASC, "name");
        Page<Computer> page = computerService.getList(pageRequest, null);
        PageDto pageDto = pageDtoMapper.toPageDto(page, null);
        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    /**
     * Creates the the computer given.
     *
     * @param computerDto the computer dto
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.POST)
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ComputerDto> create(@RequestBody ComputerDto computerDto) {
        computerService.create(computerDtoMapper.toComputer(computerDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Update the the computer given.
     *
     * @param id          the id
     * @param computerDto the computer dto
     * @return the response entity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ComputerDto> update(@PathVariable long id, @RequestBody ComputerDto computerDto) {
        computerService.update(computerDtoMapper.toComputer(computerDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Deletes the computer matching with given id.
     *
     * @param id the id
     * @return the response entity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ComputerDto> delete(@PathVariable long id) {
        computerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
