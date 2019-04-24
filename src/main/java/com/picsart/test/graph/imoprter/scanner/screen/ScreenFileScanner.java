package com.picsart.test.graph.imoprter.scanner.screen;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.picsart.test.graph.entities.Screen;
import com.picsart.test.graph.tools.FileReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ag on 2019-04-24
 */

public class ScreenFileScanner implements ScreenScanner<String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Screen scan(String info) {
        try {
            return objectMapper.readValue(info, Screen.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Screen> scanScreens(String path) throws Exception {
        return FileReader.readTextFile(path)
                .map(this::scan)
                .collect(Collectors.toList());
    }
}
