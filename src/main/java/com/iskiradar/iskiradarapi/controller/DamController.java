package com.iskiradar.iskiradarapi.controller;

import com.iskiradar.iskiradarapi.dto.DamData;
import com.iskiradar.iskiradarapi.dto.OverviewData; // Yeni DTO'yu import ediyoruz
import com.iskiradar.iskiradarapi.service.DamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dams")
public class DamController {

    private final DamService damService;

    @Autowired
    public DamController(DamService damService) {
        this.damService = damService;
    }

    // Ana baraj listesini döndüren endpoint
    @GetMapping(produces = "application/json; charset=UTF-8")
    public ResponseEntity<List<DamData>> getAllDams() {
        List<DamData> dams = damService.getDamData();
        return ResponseEntity.ok(dams);
    }

    // YENİ EKLENEN ENDPOINT: Genel bakış verisini döndürür
    @GetMapping(value = "/overview", produces = "application/json; charset=UTF-8")
    public ResponseEntity<OverviewData> getOverview() {
        OverviewData overviewData = damService.getOverviewData();
        return ResponseEntity.ok(overviewData);
    }
}