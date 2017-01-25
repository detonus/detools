package com.detoworks.service;

import com.detoworks.configuration.RegExpProps;
import com.detoworks.model.RegExp;
import com.detoworks.model.RegExpKey;
import com.detoworks.repository.RegExpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Banach on 2017-01-24.
 */
@Service
public class RegExpService {

    @Autowired
    private RegExpRepository regExpRepo;

    @Autowired
    private RegExpProps regExpProps;

    private AtomicLong idGen;
    private ConcurrentHashMap<Long, AtomicInteger> subidGen;

    //setting atomic id & subid from db
    @PostConstruct
    public void init() {
        RegExp regExpMax = regExpRepo.findTopByOrderByIdDesc();
        if (regExpMax != null) {
            idGen = new AtomicLong(regExpMax.getId());
        } else {
            idGen = new AtomicLong(0);
        }
        System.out.println("idGen: " + idGen.get());

        subidGen = new ConcurrentHashMap<>();
        if (regExpProps.isLoadDbOnStart()) {
            //all - working:
//        StreamSupport.stream(regExpRepo.findAll().spliterator(), true).forEach((regExp) -> {
//            RegExp regExpMaxSubid = regExpRepo.findTopByIdOrderBySubidDesc(regExp.getId());
//            AtomicInteger maxSubid;
//            if (regExpMaxSubid != null) {
//                maxSubid = new AtomicInteger(regExpMaxSubid.getSubid());
//            } else {
//                maxSubid = new AtomicInteger(0);
//            }
//            subidGen.put(regExp.getId(), maxSubid);
//            System.out.println("idGen: " + regExp.getId() + " -> subidGen: " + maxSubid);
//        });

            //all - working:
//        regExpRepo.findAllDistinctBy().forEach((regExp) -> {
//            RegExp regExpMaxSubid = regExpRepo.findTopByIdOrderBySubidDesc(regExp.getId());
//            AtomicInteger maxSubid;
//            if (regExpMaxSubid != null) {
//                maxSubid = new AtomicInteger(regExpMaxSubid.getSubid());
//            } else {
//                maxSubid = new AtomicInteger(0);
//            }
//            subidGen.put(regExp.getId(), maxSubid);
//            System.out.println("idGen: " + regExp.getId() + " -> subidGen: " + maxSubid);
//        });

            regExpRepo.findAllDistinctIdBy().forEach((id) -> {
                RegExp regExpMaxSubid = regExpRepo.findTopByIdOrderBySubidDesc(id);
                AtomicInteger maxSubid;
                if (regExpMaxSubid != null) {
                    maxSubid = new AtomicInteger(regExpMaxSubid.getSubid());
                } else {
                    maxSubid = new AtomicInteger(0);
                }
                subidGen.put(id, maxSubid);
                System.out.println("idGen: " + id + " -> subidGen: " + maxSubid);
            });
        }

    }

    public RegExp findOne(RegExpKey regExpKey) {
        return regExpRepo.findOne(regExpKey);
    }

    public void save(RegExp regExp) {
        //generating id & subid logic
        //atomic ++ & get next id
        if (regExp.getId() == 0) {
            regExp.setId(idGen.incrementAndGet());
        }
        //synchronion on id->subid map
        if (subidGen.get(regExp.getId()) == null) {
            synchronized (subidGen) {
                if (subidGen.get(regExp.getId()) == null) {
                    AtomicInteger maxSubid;
                    if (!regExpProps.isLoadDbOnStart()) {
                        RegExp regExpMaxSubid = regExpRepo.findTopByIdOrderBySubidDesc(regExp.getId());
                        if (regExpMaxSubid != null) {
                            maxSubid = new AtomicInteger(regExpMaxSubid.getSubid());
                        } else {
                            maxSubid = new AtomicInteger(0);
                        }
                    } else {
                        maxSubid = new AtomicInteger(0);
                    }
                    subidGen.put(regExp.getId(), maxSubid);
                    System.out.println("idGen: " + regExp.getId() + " -> subidGen: " + maxSubid);
                }
            }
        }
        //atomic ++ & get next subid
        if (regExp.getSubid() == 0) {
            regExp.setSubid(subidGen.get(regExp.getId()).incrementAndGet());
        }
        regExp.setModify(new Date());
        regExpRepo.save(regExp);
    }
}
