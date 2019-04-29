package com.picsart.test.graph.dal;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.StatementResult;

/**
 * @author ag on 2019-04-27
 */

public class Neo4jDAO {

    private Driver driver;

    public Neo4jDAO(Driver driver) {
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

    public void close() {
        driver.close();
    }
}
