package com.detoworks.service;

import com.detoworks.model.RegExp;
import com.detoworks.model.RegExpKey;
import com.detoworks.repository.RegExpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Banach on 2017-01-24.
 */
@Service
@Scope
public class RegExpService {

    @Autowired
    private RegExpRepository regExpRepo;

    private AtomicLong idGen;
    private ConcurrentHashMap<Long, AtomicInteger> subidGen;
    {

    }

    public RegExp findOne(RegExpKey regExpKey) {
        return regExpRepo.findOne(regExpKey);
    }

    public void save(RegExp regExp) {


        regExpRepo.save(regExp);
    }
}
