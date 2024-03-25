package com.example.foodtruckdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/foodtrucks")
public class FoodTruckController {
    @Autowired
    private com.example.foodtruckdemo.service.FoodTruckService foodTruckService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Map>> getAllFoodTrucks(){
        try{
            List<Map> foodTruck = foodTruckService.getAllFoodTrucks();
            return ResponseEntity.ok().header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST").body(foodTruck);
//            return new ResponseEntity<>(foodTruck, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    // 添加其他API端点（如果需要）
}