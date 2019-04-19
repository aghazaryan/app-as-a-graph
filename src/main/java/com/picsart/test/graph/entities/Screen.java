package com.picsart.test.graph.entities;

/**
 * @author ag on 2019-04-16
 */

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
    }
}