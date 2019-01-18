package com.lending.loan.kafka;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

@Service
public class LoanRepository {
    Map<String, String> loanMap = new HashMap<>();
    
    @PostConstruct
    public void readMockData() {
        try {
            Files.walk(Paths.get("data"))
            .filter(Files::isRegularFile)
            .forEach(file -> { 
                String json;
                try {
                    json = new String(Files.readAllBytes(file));
                    String loanNumber = JsonPath.read(json, "$.loan.loanNumber");
                    String loanJson = new ObjectMapper().writeValueAsString(JsonPath.read(json, "$.loan"));
                    loanMap.put(loanNumber, loanJson);  
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
    
    public String lookup(String loanNumber) {
        return loanMap.get(loanNumber);
    }

}
