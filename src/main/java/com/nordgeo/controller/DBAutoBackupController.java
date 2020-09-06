package com.nordgeo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@EnableScheduling
public class DBAutoBackupController {

    @Scheduled(cron = "0 30 1 * * SAT")
    public void schedule() {

        Logger logger = LoggerFactory.getLogger(this.getClass());
        System.out.println("Backup Started at " + new Date());

        Date backupDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String backupDateStr = format.format(backupDate);

        String fileName = "Daily_DB_Backup"; // default file name
        String folderPath = "C:\\home\\DB-Backup";
        File filePath = new File(folderPath);
        filePath.mkdir(); // create folder if not exist

        String saveFileName = fileName + "_" + backupDateStr + ".sql";
        String savePath = filePath + File.separator + saveFileName;

        String executeCmd = "mysqldump -u root" + " -p antek2012pola2013" + "  nordgeo_db > " + savePath;

        Process runtimeProcess = null;
        int processComplete = 0;

        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
        } catch (IOException e) {
            logger.warn("Backup bazy danych zakończony niepowodzeniem.");
            e.printStackTrace();
        }

        try {
            processComplete = runtimeProcess.waitFor();
        } catch (InterruptedException e) {
            logger.warn("Backup bazy danych zakończony niepowodzeniem.");
            e.printStackTrace();
        }

        if (processComplete == 0)
            logger.info("Backup bazy danych zakończony powodzeniem o: " + new Date());

    }
}