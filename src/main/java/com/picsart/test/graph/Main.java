package com.picsart.test.graph;

import com.picsart.test.graph.entities.Navigation;
import com.picsart.test.graph.entities.Screen;
import com.picsart.test.graph.imoprter.AppIndexer;
import com.picsart.test.graph.imoprter.scanner.navigation.NavigationFileScanner;
import com.picsart.test.graph.imoprter.scanner.screen.ScreenFileScanner;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;

import java.util.List;

/**
 * @author ag on 2019-04-16
 */

public class Main {

    public static void main(String[] args) throws Exception {

        List<Screen> screens = new ScreenFileScanner().scanScreens("./src/main/resources/screens");
        List<Navigation> navigations = new NavigationFileScanner(screens).scanScreens("./src/main/resources/navigations");

        AppIndexer appIndexer = new AppIndexer(GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "neo4j2")));
        appIndexer.clear();
        appIndexer.createIndex("screen(id)");
        appIndexer.createIndex("BUTTON(id)");
        appIndexer.indexScreen(screens.get(0));
        navigations.forEach(appIndexer::indexNavigation);

        appIndexer.close();
    }
}
