package com.picsart.test.graph;

import com.picsart.test.graph.dal.Neo4jDAO;
import com.picsart.test.graph.entities.Navigation;
import com.picsart.test.graph.entities.Screen;
import com.picsart.test.graph.imoprter.AppIndexer;
import com.picsart.test.graph.imoprter.scanner.navigation.NavigationFileScanner;
import com.picsart.test.graph.imoprter.scanner.screen.ScreenFileScanner;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;

import java.util.HashSet;
import java.util.List;

/**
 * @author ag on 2019-04-16
 */

public class Main {

    public static void manuallyImportData() throws Exception {
        List<Screen> screens = new ScreenFileScanner().scanScreens("./src/main/resources/screens");
        List<Navigation> navigations = new NavigationFileScanner(screens).scanNavigations("./src/main/resources/navigations");
        importData(screens, navigations);
    }

    public static void automaticllyImportData() throws Exception {
        NavigationFileScanner navigationFileScanner = new NavigationFileScanner("./src/main/resources/navigations_appium");
        List<Screen> screens = navigationFileScanner.getScreens();
        List<Navigation> navigations = navigationFileScanner.scanNavigations();
        System.out.println(navigations.size());
        importData(screens, navigations);
    }

    public static void importData(List<Screen> screens, List<Navigation> navigations) {

        System.out.println("screen set size " + new HashSet<>(screens).size());
        System.out.println("navigation set size " + new HashSet<>(navigations).size());
        Neo4jDAO dao = new Neo4jDAO(GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "neo4j2")));

        AppIndexer appIndexer = new AppIndexer(dao);
        appIndexer.clear();
        appIndexer.createIndex("screen(id)");
        appIndexer.createIndex("BUTTON(id)");
        screens.forEach(appIndexer::indexScreen);
        navigations.forEach(appIndexer::indexNavigation);

        dao.close();
    }

    public static void main(String[] args) throws Exception {
        automaticllyImportData();
    }
}
