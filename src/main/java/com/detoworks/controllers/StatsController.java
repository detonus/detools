package com.detoworks.controllers;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class StatsController {

    @ResponseBody
    @RequestMapping(value = "/stats", produces = "application/json", method = RequestMethod.GET)
    public Map<String, String> getRuntimeStats() {
        Map<String, String> stats = new LinkedHashMap<>();
        Runtime runtime = Runtime.getRuntime();
        stats.put("Available processors", Integer.toString(runtime.availableProcessors()));
        stats.put("Max memory", FileUtils.byteCountToDisplaySize(runtime.maxMemory()));
        stats.put("Total memory", FileUtils.byteCountToDisplaySize(runtime.totalMemory()));
        stats.put("Used memory", FileUtils.byteCountToDisplaySize(runtime.totalMemory() - runtime.freeMemory()));
        for (MemoryPoolMXBean memoryMXBean : ManagementFactory.getMemoryPoolMXBeans()) {
            if ("Metaspace".equals(memoryMXBean.getName())) {
                stats.put("Used metaspace", FileUtils.byteCountToDisplaySize(memoryMXBean.getUsage().getUsed()));
            }

        }
        return stats;
    }

}
