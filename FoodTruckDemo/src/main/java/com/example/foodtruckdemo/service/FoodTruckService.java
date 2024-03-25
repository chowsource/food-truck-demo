package com.example.foodtruckdemo.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FoodTruckService {
    private List<Map> foodTrucks = new ArrayList<>();

    @PostConstruct
    public void initialize() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Mobile_Food_Facility_Permit.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            boolean isFirstHeader = true;
            String headerLine = null;
            String[] headers = null;
            int headerLen = 0;
            while ((line = reader.readLine()) != null) {
                if(isFirstHeader){
                    headerLine = line;
                    headerLine = headerLine.replace(" ","_").replace("(","").replace(")","");
                    headers = headerLine.split(",");
                    headerLen = headers.length;
                    isFirstHeader = false;
                    continue;
                }
                ArrayList<String> parts = new ArrayList<String>();
                int startIndex = 0;
                int endIndex = 0;
                boolean matchQuotes = true;
                int len = line.length();
                for(int i = 0; i < len; i++){
                    char symb = line.charAt(i);
                    if(symb == '"'){
                        matchQuotes = !matchQuotes;
                    }
                    if(!matchQuotes){
                        continue;
                    }
                    if(line.charAt(i) == ','){
                        endIndex = i;
                        String itemValue = line.substring(startIndex,endIndex);
                        parts.add(itemValue);
                        startIndex = endIndex + 1;
                    }
                    if(i == len - 1){
                        String itemValue = line.substring(startIndex);
                        parts.add(itemValue);
                    }
                }

                Map item = new HashMap<>();
                for (int i = 0; i < headerLen; i++) {
                    String key = headers[i];
                    String value = parts.get(i);
                    item.put(key,value);
                    foodTrucks.add(item);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Map> getAllFoodTrucks() {
        return foodTrucks;
    }

}
