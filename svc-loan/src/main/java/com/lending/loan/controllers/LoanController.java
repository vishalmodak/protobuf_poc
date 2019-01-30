package com.lending.loan.controllers;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.googlecode.protobuf.format.JsonFormat;
import com.lending.loan.kafka.LoanRepository;
import com.lending.proto.Loan;
import com.lending.proto.PaymentList;

import io.micrometer.core.annotation.Timed;

@RestController
public class LoanController {

    private static final Logger log = LoggerFactory.getLogger(LoanController.class);

    @Autowired
    private LoanRepository loanRepository;
    
    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.payments.uri}")
    private String paymentServiceURI;
    
    private static MediaType protobufMediaType = MediaType.valueOf("application/x-protobuf");
    private static MediaType jsonMediaType = MediaType.valueOf("application/json");

    @RequestMapping(value="/loan", method=RequestMethod.GET, produces={"text/plain"})
    public String index(@PathVariable String loanNumber) {
        return "Welcome to Loan Service...!!";
    }

    @Timed(percentiles = {0.90, 0.95, 0.99}, histogram = true)
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

    @Timed(percentiles = {0.90, 0.95, 0.99}, histogram = true)
    @RequestMapping(value="/loan/payments/{loanNumber}", method=RequestMethod.GET, produces={"application/json","application/x-protobuf"})
    public PaymentList lookupPayments(@PathVariable String loanNumber, @RequestParam(name = "dup", required = false) Integer dupCount, 
            @RequestHeader HttpHeaders headers) {
        String getPaymentsURI = paymentServiceURI + "/v1/payments/{loanNumber}";
        if (dupCount != null && dupCount > 1) {
            getPaymentsURI += "?dup=" + dupCount;
        }

        HttpHeaders outheaders = new HttpHeaders();
        if (headers.getAccept().contains(protobufMediaType)) {
            outheaders.setAccept(Arrays.asList(protobufMediaType));
        } else if (headers.getAccept().contains(jsonMediaType)) {
            outheaders.setAccept(Arrays.asList(jsonMediaType));
        }

        final HttpEntity<String> entity = new HttpEntity<String>(outheaders);

        Map<String, String> params = new HashMap<>();
        params.put("loanNumber", loanNumber);

        ResponseEntity<PaymentList> response = restTemplate.exchange(getPaymentsURI, HttpMethod.GET, entity, PaymentList.class, params);
//        log.info("Loan: " + response.getBody());

        return response.getBody();
    }
}
