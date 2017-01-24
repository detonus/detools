package com.detoworks.service;

import com.detoworks.model.RegExp;
import com.detoworks.model.RegExpKey;
import com.detoworks.repository.RegExpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Banach on 2017-01-24.
 */
@Service
public class RegExpService {

    @Autowired
    private RegExpRepository regExpRepo;

    private AtomicLong idGen;
    private ConcurrentHashMap<Long, AtomicInteger> subidGen;

    //setting atomic id & subid from db
    @PostConstruct
    public void init() {
        RegExp regExp = regExpRepo.findTopByOrderByIdDesc();
        if (regExp != null) {
            idGen = new AtomicLong(regExp.getId());
        } else {
            idGen = new AtomicLong(0);
        }
        System.out.println("idGen: " + idGen.get());
    }

    public RegExp findOne(RegExpKey regExpKey) {
        return regExpRepo.findOne(regExpKey);
    }

    public void save(RegExp regExp) {
        //generating id & subid logic

        regExp.setModify(new Date());
        regExpRepo.save(regExp);
    }
}
