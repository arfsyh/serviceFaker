package com.faker.servicefaker.controller;

import com.faker.servicefaker.config.DataSourceContextHolder;
import com.faker.servicefaker.dto.DatatestDto;
import com.faker.servicefaker.entity.Datatest;
import com.faker.servicefaker.repository.DatatestRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("api/datatest")
public class DatatestController {
    private DatatestRepository datatestRepository;

    @Transactional
    @PostMapping
    public ResponseEntity<String> createDatatest(@RequestHeader("env") String env, @RequestBody DatatestDto datatestDto) {
        Map<String, String> datatestMap = datatestDto.getDatatest();
        for(Map.Entry<String, String> entry : datatestMap.entrySet()){
            Datatest datatestEntity = new Datatest();
            datatestEntity.setScenarioName(datatestDto.getScenarioName());
            datatestEntity.setService(datatestDto.getService());
            datatestEntity.setAction(datatestDto.getAction());
            datatestEntity.setTaskid(datatestDto.getTaskId());
            datatestEntity.setEnv(datatestDto.getEnv());
            datatestEntity.setRegression(datatestDto.getRegression());
            datatestEntity.setTester(datatestDto.getTester());
            datatestEntity.setProjectId(datatestDto.getProjectId());
            datatestEntity.setScenarioId(datatestDto.getScenarioId());
            datatestEntity.setExecute(datatestDto.getExecute());
            datatestEntity.setTransport(datatestDto.getTransport());
            datatestEntity.setParamName(entry.getKey());
            datatestEntity.setParamValue(String.valueOf(entry.getValue())); // Assuming paramValue is String
            datatestRepository.save(datatestEntity);
        }
        return ResponseEntity.ok("Datatest successfully saved.");
    }

}
