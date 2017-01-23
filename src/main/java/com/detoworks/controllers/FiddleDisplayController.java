package com.detoworks.controllers;

import com.detoworks.model.RegExp;
import com.detoworks.model.RegExpKey;
import com.detoworks.repository.RegExpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disp")
public class FiddleDisplayController {

	@Autowired
	private RegExpDao dao;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public Iterable<RegExp> getAll() throws Exception {
		return dao.findAll();
	}

	@RequestMapping(value = "/{id:[0-9][0-9]*}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public Iterable<RegExp> lookupById(@PathVariable("id") long id) throws Exception {
		return dao.findById(id);
	}

	@RequestMapping(value = "/{id:[0-9][0-9]*}/{sub:[0-9][0-9]*}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public RegExp lookupByKey(@PathVariable("id") long id, @PathVariable("id") int subid) throws Exception {
		return dao.findOne(new RegExpKey(id, subid));
	}

}
