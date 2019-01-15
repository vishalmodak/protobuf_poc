package com.lending.proto;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.protobuf.format.JsonFormat;
import com.jayway.jsonpath.JsonPath;

import junit.framework.TestCase;

public class LoanTest {
    
    JsonFormat jsonFormat = new JsonFormat();
    
    @Test
    public void buildLoan() {
        String fileName = "data/2015CA169772974.json";
        Loan.Builder loanBuilder = Loan.newBuilder();
        try {
            Path path = Paths.get(fileName);
            byte[] bytes = Files.readAllBytes(path);
            String loanJson = new ObjectMapper().writeValueAsString(JsonPath.read(new String(bytes), "$.loan"));
            System.out.println("JSON: " + new String(loanJson));
            jsonFormat.merge(new ByteArrayInputStream(loanJson.getBytes()), loanBuilder);
        } catch (Exception e) {
            e.printStackTrace();
            TestCase.fail(e.getMessage());
        }
        Loan loan = loanBuilder.build();
        System.out.println("Loan: " + loan.toString());
        
        TestCase.assertNotNull(loan);
        TestCase.assertEquals(loan.getLoanNumber(), "2015CA169772974");
    }

}
