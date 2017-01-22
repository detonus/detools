package com.detoworks.controllers;

import java.util.List;

import com.detoworks.model.Fiddle;
import com.detoworks.model.FiddleKey;
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
	public Iterable<Fiddle> getAll() throws Exception {
		return dao.findAll();
	}

	@RequestMapping(value = "/{id:[0-9][0-9]*}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public Iterable<Fiddle> lookupById(@PathVariable("id") long id) throws Exception {
		return dao.findById(id);
	}

	@RequestMapping(value = "/{id:[0-9][0-9]*}/{sub:[0-9][0-9]*}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public Fiddle lookupByKey(@PathVariable("id") long id, @PathVariable("id") int subid) throws Exception {
		return dao.findOne(new FiddleKey(id, subid));
	}

}
