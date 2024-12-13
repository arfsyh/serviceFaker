package com.faker.servicefaker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "datatest")
public class Datatest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "scenario_name")
    private String scenarioName;

    private String service;

    private String action;

    private String taskid;

    private String env;

    private String regression;

    private String tester;

    @Column(name = "param_name")
    private String paramName;

    @Column(name = "param_value")
    private String paramValue;

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "scenario_id")
    private String scenarioId;

    private Long execute;

    private String transport;


}
