package com.nordgeo.seeders;

import com.nordgeo.entity.Tool;
import com.nordgeo.repository.ToolRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class ToolSeeder {

    private final ToolRepository toolRepository;

    public ToolSeeder(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    @EventListener()
    @Order(2)
    public void appReady(ContextRefreshedEvent event) throws ParseException {
        Tool tool1 = new Tool("001n","TS 15 1 R1000","Tachimetr","1111111", new SimpleDateFormat("yyyy-MM-dd").parse("2015-01-01"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-04-01"), true);
        Tool tool2 = new Tool("002n","CS 15","Robot","222222",new SimpleDateFormat("yyyy-MM-dd").parse("2015-08-01"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-01"), true);
        Tool tool3 = new Tool("003n","GS 08","GPS Antena","333333",new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-01"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-01"), true);
        Tool tool4 = new Tool("004n","CS 10","GPS Kontroller","444444", new SimpleDateFormat("yyyy-MM-dd").parse("2017-10-01"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-01"), true);
        Tool tool5 = new Tool("005n","DNA 03","Niwelator","555555", new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-01"), new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-01"), true);


        try{
            toolRepository.save(tool1);
            toolRepository.save(tool2);
            toolRepository.save(tool3);
            toolRepository.save(tool4);
            toolRepository.save(tool5);

        } catch (DataIntegrityViolationException e){
            System.out.println(e.getMessage());
        }

    }
}
