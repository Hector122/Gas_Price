package com.android.gaspricerd;

import com.android.gaspricerd.model.Combustible;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.util.List;

public class Parser {

    public void parseXML(String cdata) throws XmlPullParserException {
        XmlPullParserFactory parserFactory  =  XmlPullParserFactory.newInstance();
        XmlPullParser parser = parserFactory.newPullParser();
       // parser.setInput();

        parserFactory.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
       // parser.


    }

}
