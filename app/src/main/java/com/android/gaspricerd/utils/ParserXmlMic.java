package com.android.gaspricerd.utils;

import android.util.Log;

import com.android.gaspricerd.model.Combustible;
import com.android.gaspricerd.model.RssFeed;
import com.android.gaspricerd.model.RssItem;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that parse the xml from the MIC.
 */
public class ParserXmlMic {
    private static final String ITEM = "item"; /* parent node */
    private static final String TITLE = "title";
    private static final String PUB_DATE = "pubDate";
    private static final String DESCRIPTION = "description";

    /**
     * This function reader the xml data in the inputStream format and return a
     * RssFeed with all the data need.
     *
     * @param inputStream InputStream with the xml.
     * @return RssFeeMic
     * @throws XmlPullParserException
     * @throws IOException
     */
    public void parser(InputStream inputStream) throws XmlPullParserException, IOException {
        XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
        parserFactory.setNamespaceAware(true);

        XmlPullParser parser = parserFactory.newPullParser();
        parser.setInput(inputStream, null);

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_TAG && parser.getName().equals(ITEM)) {
                readItem(parser);
                break;
            }

            eventType = parser.next();
        }
    }


    private RssFeed readItem(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<RssItem> items = new ArrayList<>();
        String title = "", publicationDate = "";

        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() != XmlPullParser.START_TAG) continue;

            String name = parser.getName();
            if (name != null) {
                switch (name){
                    case TITLE:
                        title = parser.nextText().trim();
                        break;

                    case PUB_DATE:
                        publicationDate = parser.nextText().trim();
                        break;

                    case DESCRIPTION:
                        items = readDescriptionItem(parser.nextText());
                        break;
                }
            }
        }

       return new RssFeed(title, publicationDate, items);
    }

    private ArrayList<RssItem> readDescriptionItem(String text) throws XmlPullParserException, IOException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);

        XmlPullParser xpp = factory.newPullParser();
        xpp.setInput(new StringReader(text));

        ArrayList<RssItem> rssItemList = new ArrayList<>();

        StringBuilder temp = new StringBuilder();
        //Get all the String in the xml.
        while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
            if (xpp.getEventType() == XmlPullParser.TEXT) {
                temp.append(xpp.getText().trim()).append("-");
            }
            xpp.next();
        }

        if (temp.length() > 0) {
            temp = new StringBuilder(temp.toString().replaceAll("--", "-")
                    .replaceAll("RD$", ""));

            String[] split = temp.toString().split("-");

            for (int i = 0; i < split.length; i += 2) {
                String title = split[i].replace(":", "");
                String price = split[i + 1];

                RssItem rssItem = new RssItem();
                rssItem.setPrice(price);
                rssItem.setDescription(title);

                rssItemList.add(rssItem);
            }
        }

        return rssItemList;
    }
}
