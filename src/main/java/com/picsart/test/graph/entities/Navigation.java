package com.picsart.test.graph.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author ag on 2019-04-16
 */

public class Navigation {
    private String id;
    private NavigationType type;

    @JsonIgnore
    private Screen source;
    @JsonIgnore
    private Screen to;

    public Navigation(String id, NavigationType type, Screen source, Screen to) {
        this.id = id;
        this.type = type;
        this.source = source;
        this.to = to;
    }

    public Navigation(String id, Screen source, Screen to) {
        this(id, NavigationType.BUTTON, source, to);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NavigationType getType() {
        return type;
    }

    public void setType(NavigationType type) {
        this.type = type;
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
                ",type:'" + type +
                "'}";
    }
}
