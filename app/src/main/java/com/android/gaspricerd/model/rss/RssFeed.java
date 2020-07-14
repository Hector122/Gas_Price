package com.android.gaspricerd.model.rss;

import java.util.ArrayList;


public class RssFeed {
    private String title;
    private String pubDate;
    private ArrayList<RssItem> items;

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

  public ArrayList<RssItem> getItems(){
        return items;
    }
}
