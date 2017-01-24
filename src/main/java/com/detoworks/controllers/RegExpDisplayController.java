package com.detoworks.controllers;

import com.detoworks.model.RegExp;
import com.detoworks.model.RegExpKey;
import com.detoworks.repository.RegExpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/regexp/disp")
public class RegExpDisplayController {

	@Autowired
	private RegExpRepository regExpRepo;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<RegExp> getAll() throws Exception {
		List<RegExp> list = StreamSupport.stream(regExpRepo.findAll().spliterator(), true).collect(Collectors.toList());
		return list;
	}

	@RequestMapping(value = "/{id:[0-9][0-9]*}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<RegExp> lookupById(@PathVariable("id") long id) throws Exception {
		List<RegExp> list = StreamSupport.stream(regExpRepo.findById(id).spliterator(), true).collect(Collectors.toList());
		return list;
	}

	@RequestMapping(value = "/{id:[0-9][0-9]*}/{sub:[0-9][0-9]*}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public RegExp lookupByKey(@PathVariable("id") long id, @PathVariable("id") int subid) throws Exception {
		return regExpRepo.findOne(new RegExpKey(id, subid));
	}

}
