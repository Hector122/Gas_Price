package com.android.gaspricerd.rss;

import java.util.ArrayList;

/**
 * Class that hold the rss
 */
public class RssFeed {
    private String title;
    private String pubDate;
    private ArrayList<RssItem> items;

    /**
     * Constructor
     *
     * @param title   header title.
     * @param pubDate Date of the publication.
     * @param items   item to show.
     */
    public RssFeed(String title, String pubDate, ArrayList<RssItem> items) {

        this.title = title;
        this.pubDate = pubDate;
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public ArrayList<RssItem> getItems() {
        return items;
    }
}
