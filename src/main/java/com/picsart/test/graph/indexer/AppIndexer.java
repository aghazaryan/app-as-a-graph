package com.picsart.test.graph.indexer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.picsart.test.graph.entities.Navigation;
import com.picsart.test.graph.entities.Screen;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;

/**
 * @author ag on 2019-04-16
 */

public class AppIndexer {
    private Driver driver;

    public AppIndexer(Driver driver) {
        this.driver = driver;
    }

    private void run(String command) {
        System.out.println(command);
        try (Session session = driver.session()) {
            session.run(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void indexScreen(Screen screen) {
        run(String.format("CREATE (s: screen %s)", screen));
    }

    public void indexNavigation(Navigation navigation) {
        run(String.format("MATCH (s: screen {id: '%s'}) CREATE (s) -[n: %s %s]- (t: screen %s)",
                navigation.getSource().getId(),
                navigation.getType(), navigation,
                navigation.getTo()));
    }

    public void close() {
        driver.close();
    }
}
