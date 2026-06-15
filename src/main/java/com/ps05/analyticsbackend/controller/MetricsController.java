package com.ps05.analyticsbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ps05.analyticsbackend.entity.MetricsSummary;
import com.ps05.analyticsbackend.repository.MetricsRepository;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class MetricsController {

    @Autowired
    private MetricsRepository metricsRepository;

    // READ ALL + SEARCH

    @GetMapping("/api/metrics")
    public List<MetricsSummary> getMetrics(
            @RequestParam(required = false) String search) {

        if (search != null && !search.isEmpty()) {
            return metricsRepository
                    .findByMetricNameContainingIgnoreCase(search);
        }

        return metricsRepository.findAll();
    }

    // CREATE

    @PostMapping("/api/metrics")
    public MetricsSummary addMetric(
            @RequestBody MetricsSummary metric) {

        return metricsRepository.save(metric);
    }

    // READ BY ID

    @GetMapping("/api/metrics/{id}")
    public MetricsSummary getMetricById(
            @PathVariable Long id) {

        return metricsRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Metric Not Found"));
    }

    // UPDATE

    @PutMapping("/api/metrics/{id}")
    public MetricsSummary updateMetric(
            @PathVariable Long id,
            @RequestBody MetricsSummary updatedMetric) {

        MetricsSummary metric = metricsRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Metric Not Found"));

        metric.setMetricName(updatedMetric.getMetricName());
        metric.setMetricValue(updatedMetric.getMetricValue());

        return metricsRepository.save(metric);
    }

    // DELETE

    @DeleteMapping("/api/metrics/{id}")
    public String deleteMetric(
            @PathVariable Long id) {

        metricsRepository.deleteById(id);

        return "Metric Deleted Successfully";
    }
}