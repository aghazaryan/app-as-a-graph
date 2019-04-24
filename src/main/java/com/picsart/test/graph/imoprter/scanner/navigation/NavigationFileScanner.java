package com.picsart.test.graph.imoprter.scanner.navigation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.picsart.test.graph.entities.Navigation;
import com.picsart.test.graph.entities.Screen;
import com.picsart.test.graph.tools.FileReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ag on 2019-04-24
 */

public class NavigationFileScanner implements NavigationScanner<String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private List<Screen> screens;

    public NavigationFileScanner(List<Screen> screens) {
        this.screens = screens;
    }

    @Override
    public Navigation scan(String info) {
        try {
            JsonNode jsonNode = objectMapper.readTree(info);
            String id = jsonNode.get("id").asText();
            String sourceId = jsonNode.get("source").asText();
            String toId = jsonNode.get("to").asText();

            Screen source = screens.stream().filter(s -> s.getId().equals(sourceId)).findFirst().get();
            Screen to = screens.stream().filter(s -> s.getId().equals(toId)).findFirst().get();

            return new Navigation(id, to.getName(), source, to);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Navigation> scanScreens(String path) throws Exception {
        return FileReader.readTextFile(path)
                .map(this::scan)
                .collect(Collectors.toList());
    }
}
