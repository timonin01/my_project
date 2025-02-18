package javaguru.travel.insurance.rest;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class JsonFileReader {

    public String readJsonFromFile(String filePath) throws IOException {
        File file = ResourceUtils.getFile("classpath:" + filePath);
        return new String(Files.readAllBytes(file.toPath()));
    }
}