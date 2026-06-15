package com.ps05.analyticsbackend.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps05.analyticsbackend.mongodb.EventLog;
import com.ps05.analyticsbackend.mongorepository.EventLogRepository;

@RestController
public class EventLogController {

    @Autowired
    private EventLogRepository eventLogRepository;

    @GetMapping("/api/logs/add")
    public String addLog() {

        String[] events = {
            "High CPU Usage",
            "Memory Threshold Exceeded",
            "Disk Space Warning",
            "API Traffic Spike",
            "User Login Detected"
        };

        int random = (int)(Math.random() * events.length);

        EventLog log = new EventLog();

        log.setEventName(events[random]);
        log.setEventType("WARNING");
        log.setTimestamp(LocalDateTime.now().toString());

        eventLogRepository.save(log);

        return "Log Saved Successfully";
    }

    @GetMapping("/api/logs")
    public List<EventLog> getLogs(
            @RequestParam(required = false) String search) {

        if (search != null && !search.isEmpty()) {

            return eventLogRepository
                    .findByEventNameContainingIgnoreCase(search);
        }

        return eventLogRepository.findAll();
    }
}