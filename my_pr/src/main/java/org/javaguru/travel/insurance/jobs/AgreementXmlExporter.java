package org.javaguru.travel.insurance.jobs;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class AgreementXmlExporter {

    @Value( "${file.export.path}" )
    private String agreementExportPath;

    private static final Logger logger = LoggerFactory.getLogger(AgreementXmlExporter.class);

    private final XmlMapper xmlMapper = new XmlMapper();

    public String convertAgreementToXml(AgreementDTO agreement) throws Exception {
        // Сериализуем объект в XML
        String xml = xmlMapper.writeValueAsString(agreement);
        return xml;
    }

    public void storeXmlToFile(String uuid, String agreementXml){
        try (FileWriter writer = new FileWriter(agreementExportPath)) {
            File file = new File(agreementExportPath + "/agreement-" + uuid + ".xml");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(agreementXml);
            bw.close();
            logger.info("XML has been successfully written to the file: " + agreementXml);
        } catch (IOException e) {
            logger.info("XML is not written to the file: " + uuid);
        }
    }
}
