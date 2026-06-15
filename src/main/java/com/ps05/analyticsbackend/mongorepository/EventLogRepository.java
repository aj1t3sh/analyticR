package com.ps05.analyticsbackend.mongorepository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ps05.analyticsbackend.mongodb.EventLog;

public interface EventLogRepository extends MongoRepository<EventLog, String> {

    List<EventLog> findByEventNameContainingIgnoreCase(String eventName);

}