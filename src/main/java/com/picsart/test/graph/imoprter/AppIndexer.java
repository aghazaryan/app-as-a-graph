package com.picsart.test.graph.imoprter;

import com.picsart.test.graph.dal.Neo4jDAO;
import com.picsart.test.graph.entities.Navigation;
import com.picsart.test.graph.entities.Screen;

import java.util.List;

/**
 * @author ag on 2019-04-16
 */

public class AppIndexer {
    private Neo4jDAO dao;

    public AppIndexer(Neo4jDAO dao) {
        this.dao = dao;
    }


    public void indexScreen(Screen screen) {
        dao.run(String.format("CREATE (s: screen %s)", screen));
    }

    public void indexNavigation(Navigation navigation) {

        if (!dao.run(String.format("MATCH (t: screen {id: '%s'}) return t", navigation.getTo().getId()))
                .hasNext()) {
            indexScreen(navigation.getTo());
        }

        dao.run(String.format("MATCH (s: screen {id: '%s'}), (t: screen {id: '%s'}) CREATE UNIQUE (s) -[n: BUTTON %s]-> (t)",
                navigation.getSource().getId(),
                navigation.getTo().getId(),
                navigation));
    }

    public void createIndex(String labelProperty) {
        dao.run("CREATE INDEX ON :" + labelProperty);
    }

    public void clear() {
        dao.run("MATCH (n) DETACH DELETE n");
    }
}
