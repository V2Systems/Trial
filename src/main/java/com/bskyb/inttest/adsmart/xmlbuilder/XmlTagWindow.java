package com.bskyb.inttest.adsmart.xmlbuilder;

import com.bskyb.inttest.adsmart.utils.Methods;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by I&T Lab User on 17/12/2014.
 */
public class XmlTagWindow {
    Element xmlElement;
    long windowStart;
    long windowDuration = 200000; //2hrs=02000000 //hhmmssMsMs
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    boolean tagAvail = false;   //optional

    public XmlTagWindow(long longReferenceDateTime ){
        windowStart = longReferenceDateTime - (Methods.getLongSeconds(windowDuration)/2);
    }

    public Document setXmlTagWindow(Element parentTag, Document doc){
        this.xmlElement = doc.createElement("Window");
        xmlElement.setAttribute("windowDuration",String.format("%08d",windowDuration));
        xmlElement.setAttribute("windowStart", dateFormat.format(windowStart));

        if(parentTag!=null)
            parentTag.appendChild(xmlElement);
        else
            doc.appendChild(xmlElement);

        return doc;
    }

    public void setWindowStartValue(long windowStartValue) {
        this.windowStart = windowStartValue;
    }
    public void setWindowDuration(long windowDurationValue) {
        this.windowStart = windowDurationValue;
    }
    public Element getElementId(){
        return this.xmlElement;
    }
}
