package com.detoworks.repository;

import com.detoworks.model.*;
import org.springframework.data.repository.CrudRepository;

public interface RegExpRepository extends CrudRepository<RegExp, RegExpKey> {

	Iterable<RegExp> findById(Long id);

	RegExp findTopByOrderByIdDesc();

}
