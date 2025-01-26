package org.javaguru.travel.insurance.rest;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

@Component
class JsonFileReader {
    String readJsonFromFile(String filePath) throws FileNotFoundException {
        try {
            File file = ResourceUtils.getFile("classpath:" + filePath);
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }
}
