package com.example.insuranceprototype.Service;

import com.example.insuranceprototype.Entity.DumpRecord;
import com.example.insuranceprototype.Repository.DumpRecordRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;

@Service
public class DumpRecordService {

    @Autowired
    private DumpRecordRepository dumpRepo;



    public void addDumpRecord(Long serviceId, String methodName, Object dumpValue) throws IOException {

        DumpRecord dump = new DumpRecord();
        dump.setServiceId(serviceId);
        dump.setMethodName(methodName);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        StringWriter stringEmp = new StringWriter();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.writeValue(stringEmp, dumpValue);
        dump.setDumpValue(stringEmp.toString());
        dumpRepo.save(dump);
    }


}
