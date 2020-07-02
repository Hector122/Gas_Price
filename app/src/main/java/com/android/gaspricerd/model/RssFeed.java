package com.android.gaspricerd.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "rss")
public class RssFeed {
    @Element(name = "title")
    private String title;
    @Element(name = "pubDate")
    private String pubDate;
    @Element(name = "description")
    private String description;

    public String getTitle() {
        return title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getDescription() {
        return description;
    }
}
