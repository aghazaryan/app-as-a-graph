package com.picsart.test.graph.imoprter;

import com.picsart.test.graph.entities.Navigation;
import com.picsart.test.graph.entities.Screen;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.StatementResult;

/**
 * @author ag on 2019-04-16
 */

public class AppIndexer {
    private Driver driver;

    public AppIndexer(Driver driver) {
        this.driver = driver;
    }

    public StatementResult run(String command) {
        System.out.println(command);
        try (Session session = driver.session()) {
            return session.run(command);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void indexScreen(Screen screen) {
        run(String.format("CREATE (s: screen %s)", screen));
    }

    public void indexNavigation(Navigation navigation) {

        if (!run(String.format("MATCH (t: screen {id: '%s'}) return t", navigation.getTo().getId()))
                .hasNext()) {
            indexScreen(navigation.getTo());
        }

        run(String.format("MATCH (s: screen {id: '%s'}), (t: screen {id: '%s'}) CREATE UNIQUE (s) -[n: BUTTON %s]-> (t)",
                navigation.getSource().getId(),
                navigation.getTo().getId(),
                navigation));
    }

    public void close() {
        driver.close();
    }

    public void createIndex(String labelProperty) {
        run("CREATE INDEX ON :" + labelProperty);
    }

    public void clear() {
        run("MATCH (n) DETACH DELETE n");
    }
}
