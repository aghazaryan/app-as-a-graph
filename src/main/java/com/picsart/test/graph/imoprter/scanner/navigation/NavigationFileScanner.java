package com.picsart.test.graph.imoprter.scanner.navigation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.picsart.test.graph.entities.Navigation;
import com.picsart.test.graph.entities.Screen;
import com.picsart.test.graph.tools.FileReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ag on 2019-04-24
 */

public class NavigationFileScanner implements NavigationScanner<String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private List<Screen> screens;
    private String path;
    private int autoincrementId = 0;

    public NavigationFileScanner(List<Screen> screens) {
        this.screens = screens;
    }

    public NavigationFileScanner(String path) {
        this.path = path;
        this.screens = scanScreensFromNavigations(path);
        System.out.println("total size = " + screens.size());
    }

    @Override
    public Navigation scan(String info) {
        try {
            JsonNode jsonNode = objectMapper.readTree(info);

            String id = jsonNode.get("id") != null ? jsonNode.get("id").asText() : "" + autoincrementId++;
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

    public List<Navigation> scanNavigations(String path) throws Exception {
        return FileReader.readTextFile(path)
                .map(this::scan)
                .collect(Collectors.toList());
    }

    public List<Navigation> scanNavigations() throws Exception {
        return scanNavigations(path);
    }

    public List<Screen> scanScreensFromNavigations(String path) {
        Set<Screen> screens = new HashSet<>();

        try {
            FileReader.readTextFile(path)
                    .forEach(s -> {
                        try {
                            JsonNode jsonNode = objectMapper.readTree(s);
                            Screen source = new Screen.Builder()
                                    .setId(jsonNode.get("source").asText())
                                    .setName(jsonNode.get("source").asText())
                                    .build();
                            Screen to = new Screen.Builder()
                                    .setId(jsonNode.get("to").asText())
                                    .setName(jsonNode.get("to").asText())
                                    .build();
                            screens.add(source);
                            screens.add(to);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(screens);
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }
}
