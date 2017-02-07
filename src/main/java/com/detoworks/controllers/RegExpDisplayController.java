package com.detoworks.controllers;

import com.detoworks.error.RegExpNotFoundException;
import com.detoworks.model.RegExp;
import com.detoworks.model.RegExpKey;
import com.detoworks.repository.RegExpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/regexp/disp")
@ControllerAdvice
public class RegExpDisplayController {

	private RegExpRepository regExpRepo;

	@Autowired
	public void setRegExpRepo(RegExpRepository regExpRepo) {
		this.regExpRepo = regExpRepo;
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<RegExp>> getAll() throws Exception {
		List<RegExp> list = StreamSupport.stream(regExpRepo.findAll().spliterator(), true).collect(Collectors.toList());
		if (list.size() == 0) {
			throw new RegExpNotFoundException("No keys in database");
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id:[0-9][0-9]*}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<RegExp>> lookupById(@PathVariable("id") long id) throws Exception {
		List<RegExp> list = StreamSupport.stream(regExpRepo.findById(id).spliterator(), true).collect(Collectors.toList());
		if (list.size() == 0) {
			throw new RegExpNotFoundException("No keys for " + id + " has been found.");
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id:[0-9][0-9]*}/{subid:[0-9][0-9]*}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<RegExp> lookupByKey(@PathVariable("id") long id, @PathVariable("subid") int subid) throws Exception {
		RegExp regExp = regExpRepo.findOne(new RegExpKey(id, subid));
		if (regExp == null) {
			throw new RegExpNotFoundException("No keys for " + id + "/" + subid + " has been found.");
		}
		return new ResponseEntity(regExp, HttpStatus.OK);
	}

	@ExceptionHandler(RegExpNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Zasób nie istnieje")
	public void handleNotFound() {
	}
}
