package com.picsart.test.graph.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author ag on 2019-04-16
 */

public class Navigation {
    private String id;
    private String name;

    @JsonIgnore
    private Screen source;
    @JsonIgnore
    private Screen to;

    public Navigation(String id, String name, Screen source, Screen to) {
        this.id = id;
        this.name = name;
        this.source = source;
        this.to = to;
    }

    public Navigation(String idName, Screen source, Screen to) {
        this(idName.split(":")[0], idName.split(":")[1], source, to);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Screen getSource() {
        return source;
    }

    public void setSource(Screen source) {
        this.source = source;
    }

    public Screen getTo() {
        return to;
    }

    public void setTo(Screen to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "{" +
                "id:'" + id + '\'' +
                ", name:'" + name + '\'' +
                '}';
    }
}
