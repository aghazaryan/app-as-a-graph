package com.picsart.test.graph;

import com.picsart.test.graph.entities.Navigation;
import com.picsart.test.graph.entities.Screen;
import com.picsart.test.graph.indexer.AppIndexer;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;

import static com.picsart.test.graph.entities.NavigationType.BUTTON;

/**
 * @author ag on 2019-04-16
 */

public class Main {

    public static void main(String[] args) {

        Screen home = new Screen.Builder()
                .setId("0")
                .setName("home")
                .setImgUrl("url-home")
                .build();


        Screen a = new Screen.Builder()
                .setId("1")
                .setName("a")
                .setImgUrl("url-a")
                .build();

        Screen b = new Screen.Builder()
                .setId("2")
                .setName("b")
                .setImgUrl("url-b")
                .build();

        Navigation nav1 = new Navigation();
        nav1.setId("1");
        nav1.setSource(home);
        nav1.setTo(a);
        nav1.setType(BUTTON);

        Navigation nav2 = new Navigation();
        nav2.setId("2");
        nav2.setSource(home);
        nav2.setTo(b);
        nav2.setType(BUTTON);


        AppIndexer appIndexer = new AppIndexer( GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "neo4j2")));
        appIndexer.indexScreen(home);
        appIndexer.indexNavigation(nav1);
        appIndexer.indexNavigation(nav2);

        appIndexer.close();
    }
}
