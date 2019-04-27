package com.picsart.test.graph.queries;

import com.picsart.test.graph.dal.Neo4jDAO;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.types.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ag on 2019-04-27
 */

public class TestCaseGenerator {

    private Neo4jDAO dao;

    public TestCaseGenerator(Neo4jDAO dao) {
        this.dao = dao;
    }

    public Path shortestPath(String field, String screen1, String screen2) {
        Record record = dao.run(String.format("match p = shortestPath((n:screen {%s:'%s'}) -[*..] -> (m:screen {%s:'%s'})) RETURN p",
                field, screen1, field, screen2)).next();
        return record.get("p").asPath();
    }


    public List<Path> allPath(String field, String screen1, String screen2) {
        return dao.run(String.format("match p = (n:screen {%s:'%s'}) -[*..] -> (m:screen {%s:'%s'}) RETURN p",
                field, screen1, field, screen2))
                .stream()
                .map(r -> r.get("p").asPath())
                .collect(Collectors.toList());

    }

    public Path longestPath(String field, String screen1, String screen2) {
        Record record = dao.run(String.format("match p = (n:screen {%s:'%s'}) -[*..] -> (m:screen {%s:'%s'}) RETURN p ORDER BY length(p) DESC LIMIT 1",
                field, screen1, field, screen2)).next();
        return record.get("p").asPath();
    }

    public static String pathToString(Path path) {
        List<String> names = new ArrayList<>();
        path.nodes().iterator().forEachRemaining(s -> names.add(s.get("name").asString()));
        return String.join(" > ", names);
    }


    public static void main(String[] args) {
        Neo4jDAO dao = new Neo4jDAO(GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "neo4j2")));
        TestCaseGenerator testCaseGenerator = new TestCaseGenerator(dao);
        System.out.println("shortest path");
        System.out.println(pathToString(testCaseGenerator.shortestPath("id", "1", "21")));
        System.out.println("longest path");
        System.out.println(pathToString(testCaseGenerator.longestPath("name", "profile", "apply")));
        System.out.println("all paths");
        System.out.println(testCaseGenerator.allPath("name", "profile", "sticker")
                .stream()
                .map(TestCaseGenerator::pathToString)
                .collect(Collectors.joining("\n")));
        dao.close();

    }
}
