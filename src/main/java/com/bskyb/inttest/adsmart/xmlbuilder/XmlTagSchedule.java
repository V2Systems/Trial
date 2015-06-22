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
public class XmlTagSchedule {
    Element xmlElement;
    //Fields under #Schedule
    static String broadcastDate;
    static String begDateTime;
    static String endDateTime;
    static String networkName = "MAH";
    static String zoneName = "BSKYB";
    boolean tagWindow = true;   //optional
    boolean tagProgram = false; //optional
    boolean tagSpot = false;    //optional

    //local variable
    long startAfter = 120000;
    long longDate;

    public XmlTagSchedule(long longReferenceDateTime){
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");
        String windowStart = dateFormat1.format(Methods.getDateTime(longReferenceDateTime));

        begDateTime = dateFormat1.format(Methods.getDateTime(longReferenceDateTime));
        broadcastDate = dateFormat2.format(Methods.getDateTime(longReferenceDateTime+startAfter));
        endDateTime= dateFormat1.format(Methods.getDateTime(longReferenceDateTime+86400000)); //A Day worth of schedules
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public long getLongDate() {
        return longDate;
    }

    public Document setXmlTagSchedule(Element parentTag, Document doc){

        this.xmlElement = doc.createElement("Schedule");
        xmlElement.setAttribute("xmlns","urn:scte:118:version01");
        xmlElement.setAttribute("xmlns:ns2","urn:nds:dynamic:ruleManagerICD:01:01");
        xmlElement.setAttribute("revision","1");
        xmlElement.setAttribute("level","0");  //optional
        xmlElement.setAttribute("schemaVersion","http://www.scte.org/documents/pdf/standards/ANSI_SCTE_118_3_2006.pdf");
        xmlElement.setAttribute("zoneName",zoneName);

        xmlElement.setAttribute("begDateTime", begDateTime);
        xmlElement.setAttribute("broadcastDate", broadcastDate);
        xmlElement.setAttribute("endDateTime", endDateTime);
        xmlElement.setAttribute("networkName", networkName);

        if(parentTag!=null)
            parentTag.appendChild(xmlElement);
        else
            doc.appendChild(xmlElement);

        return doc;
    }
    public Element getElementId(){
        return this.xmlElement;
    }
}
