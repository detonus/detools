package com.detoworks.repository;

import com.detoworks.model.*;
import org.springframework.data.repository.CrudRepository;

public interface RegExpDao extends CrudRepository<Fiddle, FiddleKey> {

	Iterable<Fiddle> findById(Long id);

}
