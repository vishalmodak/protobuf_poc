package com.lending.loan.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.protobuf.Any;
import com.lending.proto.Business;
import com.lending.proto.Person;
import com.lending.proto.Prospect;

@RestController
public class ProspectController {
    private static final Logger log = LoggerFactory.getLogger(ProspectController.class);

    @RequestMapping(value="/prospect/{firstName}", method=RequestMethod.GET, produces={"application/x-protobuf", "application/json"})
    public Prospect lookupProspect(@PathVariable String firstName) {
        Person person = Person.newBuilder().setFirstName("Michael").setLastName("Jordan").build();
        Prospect prospect = Prospect.newBuilder()
                .setPerson(person)
                .setBusiness(
                        Business.newBuilder().setName("Nocode Inc").setFein("123456").build())
                .setAnyProspect(Any.pack(person))
                .build();
        
        return prospect;
    }
}
