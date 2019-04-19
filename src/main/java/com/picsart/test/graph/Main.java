package com.picsart.test.graph;

import com.picsart.test.graph.entities.Navigation;
import com.picsart.test.graph.entities.Screen;
import com.picsart.test.graph.indexer.AppIndexer;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;

import java.util.Arrays;
import java.util.List;

/**
 * @author ag on 2019-04-16
 */

public class Main {

    public static void main(String[] args) {

        int id = 0;
        Screen profile = new Screen.Builder().build(id++ + ":profile");
        Screen saveButton = new Screen.Builder().build(id++ + ":saveButton");
        Screen threeDots = new Screen.Builder().build(id++ + ":threeDots");
        Screen profliePic = new Screen.Builder().build(id++ + ":profliePic");
        Screen editProfile = new Screen.Builder().build(id++ + ":editProfile");
        Screen followers = new Screen.Builder().build(id++ + ":followers");
        Screen followings = new Screen.Builder().build(id++ + ":followings");
        Screen stickersSeeAll = new Screen.Builder().build(id++ + ":stickersSeeAll");
        Screen sticker = new Screen.Builder().build(id++ + ":sticker");
        Screen photo = new Screen.Builder().build(id++ + ":photo");
        Screen privateStickers = new Screen.Builder().build(id++ + ":privateStickers");
        Screen privatePhotos = new Screen.Builder().build(id++ + ":privatePhotos");
        Screen reposts = new Screen.Builder().build(id++ + ":reposts");
        Screen public_ = new Screen.Builder().build(id++ + ":public_");
        Screen add = new Screen.Builder().build(id++ + ":add");
        Screen discoverArtist = new Screen.Builder().build(id++ + ":discoverArtist");
        Screen uploadPhoto = new Screen.Builder().build(id++ + ":uploadPhoto");
        Screen settings = new Screen.Builder().build(id++ + ":settings");
        Screen logout = new Screen.Builder().build(id++ + ":logout");
        Screen inviteFrineds = new Screen.Builder().build(id++ + ":inviteFrineds");
        Screen user = new Screen.Builder().build(id++ + ":user");
        Screen findPeopleToFollow = new Screen.Builder().build(id++ + ":FindPeopleToFollow");
        Screen stickerTag = new Screen.Builder().build(id++ + ":stickerTag");
        Screen apply = new Screen.Builder().build(id++ + ":apply");
        Screen photoTag = new Screen.Builder().build(id + ":photoTag");

        id = 0;

        List<Navigation> navigations = Arrays.asList(
                new Navigation("" + id++, profile, saveButton),
                new Navigation("" + id++, profile, threeDots),
                new Navigation("" + id++, profile, profliePic),
                new Navigation("" + id++, profile, editProfile),
                new Navigation("" + id++, profile, followers),
                new Navigation("" + id++, profile, followings),
                new Navigation("" + id++, profile, stickersSeeAll),
                new Navigation("" + id++, profile, sticker),
                new Navigation("" + id++, profile, photo),
                new Navigation("" + id++, saveButton, privateStickers),
                new Navigation("" + id++, saveButton, privatePhotos),
                new Navigation("" + id++, saveButton, reposts),
                new Navigation("" + id++, saveButton, public_),
                new Navigation("" + id++, saveButton, add),
                new Navigation("" + id++, threeDots, discoverArtist),
                new Navigation("" + id++, threeDots, uploadPhoto),
                new Navigation("" + id++, threeDots, settings),
                new Navigation("" + id++, threeDots, logout),
                new Navigation("" + id++, followers, inviteFrineds),
                new Navigation("" + id++, followers, user),
                new Navigation("" + id++, followings, findPeopleToFollow),
                new Navigation("" + id++, followings, user),
                new Navigation("" + id++, stickersSeeAll, sticker),
                new Navigation("" + id++, sticker, stickerTag),
                new Navigation("" + id++, sticker, apply),
                new Navigation("" + id, photo, photoTag));


        AppIndexer appIndexer = new AppIndexer(GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "neo4j2")));
        appIndexer.indexScreen(profile);
        appIndexer.createIndex("screen(id)");
        navigations.forEach(appIndexer::indexNavigation);

        appIndexer.close();
    }
}
