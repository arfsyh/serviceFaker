package com.faker.servicefaker.controller;

import com.faker.servicefaker.dto.PackageDto;
import com.faker.servicefaker.service.PackageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/package")
public class PackageController {
    private PackageService packageService;

    @PostMapping
    public ResponseEntity<PackageDto> createPackage(@RequestHeader("env") String env,@RequestBody PackageDto packageDto){
        PackageDto savedPackage = packageService.createPackage(packageDto, env);
        return new ResponseEntity<>(savedPackage, HttpStatus.CREATED);
    }

    @GetMapping("{serviceId}")
    public ResponseEntity<PackageDto> getPackageDetail(@RequestHeader("env") String env,@PathVariable String serviceId){
        PackageDto p = packageService.findById(serviceId, env);
        return ResponseEntity.ok(p);

    }
}
