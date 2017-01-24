package com.detoworks.repository;

import com.detoworks.model.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.stream.Stream;

public interface RegExpRepository extends CrudRepository<RegExp, RegExpKey> {

	Iterable<RegExp> findById(Long id);

	RegExp findTopByOrderByIdDesc();

	RegExp findTopByIdOrderBySubidDesc(long id);

	Iterable<RegExp> findAllDistinctBy();

	@Query("SELECT DISTINCT re.id FROM RegExp re")
	Iterable<Long> findAllDistinctIdBy();

}
