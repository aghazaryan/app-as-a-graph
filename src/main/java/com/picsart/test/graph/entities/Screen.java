package com.picsart.test.graph.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

/**
 * @author ag on 2019-04-16
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Screen {

    private String id;
    private String name;
    private String imgUrl;

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "{" +
                "id:'" + id + '\'' +
                ", name:'" + name + '\'' +
                ", imgUrl:'" + imgUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Screen screen = (Screen) o;
        return Objects.equals(id, screen.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class Builder {
        private Screen screen = new Screen();

        public Builder setId(String id) {
            screen.setId(id);
            return this;
        }

        public Builder setName(String name) {
            screen.setName(name);
            return this;
        }

        public Builder setImgUrl(String url) {
            screen.setImgUrl(url);
            return this;
        }

        public Screen build() {
            return screen;
        }

        public Screen build(String str) {
            //id:name:url
            String[] split = str.split(":");
            screen.setId(split[0]);
            screen.setName(split[1]);
            screen.setImgUrl(split.length == 2 ? "some-url" : split[2]);
            return screen;
        }
    }
}
