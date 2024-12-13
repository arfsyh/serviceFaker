package com.faker.servicefaker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DatatestDto {
    private String scenarioName;
    private String service;
    private String action;
    private String taskId;
    private String env;
    private String regression;
    private String tester;
    private String projectId;
    private String scenarioId;
    private Long execute;
    private String transport;
    private Map<String, String> datatest;
}
