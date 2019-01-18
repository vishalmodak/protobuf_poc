package com.lending.loan.controllers;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.googlecode.protobuf.format.JsonFormat;
import com.lending.loan.kafka.LoanRepository;
import com.lending.proto.Loan;
import com.lending.proto.PaymentList;

@RestController
public class LoanController {
    
    private static final Logger log = LoggerFactory.getLogger(LoanController.class);
    
    @Autowired
    private LoanRepository loanRepository;
    
    @RequestMapping(value="/loan", method=RequestMethod.GET, produces={"text/plain"})
    public String index(@PathVariable String loanNumber) {
        return "Welcome to Loan Service...!!";
    }

    @RequestMapping(value="/loan/{loanNumber}", method=RequestMethod.GET, produces={"application/x-protobuf", "application/json"})
    @ResponseBody
    public Loan lookupLoan(@PathVariable String loanNumber) {
        String loanStr = loanRepository.lookup(loanNumber);
        log.info(loanStr);
        Loan.Builder loanBuilder = Loan.newBuilder();
        try {
            new JsonFormat().merge(new ByteArrayInputStream(loanStr.getBytes()), loanBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Loan loan = loanBuilder.build();
        return loan;
    }
    
    @RequestMapping(value="/loan/payments/{loanNumber}", method=RequestMethod.GET, produces={"application/json","application/x-protobuf"})
    public PaymentList lookupPayments(@PathVariable String loanNumber) {
        String paymentURI = "http://localhost:3000/v1/payments/{loanNumber}";
        
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new ProtobufHttpMessageConverter());
        
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList((MediaType.valueOf("application/x-protobuf"))));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        
        Map<String, String> params = new HashMap<>();
        params.put("loanNumber", loanNumber);
        
        ResponseEntity<PaymentList> response = restTemplate.exchange(paymentURI, HttpMethod.GET, entity, PaymentList.class, params);
        log.info("Loan: " + response.getBody());
        
        return response.getBody();
    }
}
