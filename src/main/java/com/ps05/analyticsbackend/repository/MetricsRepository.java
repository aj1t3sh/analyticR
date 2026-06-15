package com.ps05.analyticsbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ps05.analyticsbackend.entity.MetricsSummary;

public interface MetricsRepository extends JpaRepository<MetricsSummary, Long> {

    List<MetricsSummary> findByMetricNameContainingIgnoreCase(String metricName);

}