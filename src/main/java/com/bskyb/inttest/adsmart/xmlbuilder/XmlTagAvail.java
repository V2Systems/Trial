package com.bskyb.inttest.adsmart.xmlbuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by I&T Lab User on 12/12/2014.
 */
public class XmlTagAvail {

    Element xmlElement;
    long availStart;
    String availId;
    int durationSeconds;
    int breakId;
    long startAfter = 120000;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    public XmlTagAvail(long availStart, int durationSeconds){
        this.availStart = availStart;
        this.durationSeconds = durationSeconds;

    }

    private List<XmlTagCampaign> availIdId_campaigns;

    public void setAvailId(String availId) {
        this.availId = availId;
    }

    public void setBreakId(int breakId) {
        this.breakId = breakId;
    }

    public void setAvailIdId_campaigns(List<XmlTagCampaign> availIdId_campaigns) {
        this.availIdId_campaigns = availIdId_campaigns;
    }

    public int getBreakId() {
        return breakId;
    }

    public List<XmlTagCampaign> getAvailIdId_campaigns() {
        return availIdId_campaigns;
    }

    public XmlTagCampaign getAvailIdId_campaign(int index){
    return this.availIdId_campaigns.get(index);
    }

    public String getAvailId() {
        return availId;
    }

    public void createAvailability(String availId, int breakId, List<XmlTagCampaign> availIdId_campaigns){
        this.setAvailId(availId);
        this.setBreakId(breakId);
        this.setAvailIdId_campaigns(availIdId_campaigns);
    }

    public void createAvailability(String availId, List<XmlTagCampaign> availIdId_campaigns){
        //Default Break Id
        this.setBreakId(1);
        createAvailability(availId, breakId, availIdId_campaigns);
    }


    public Document setXmlTagAvail(Element parentTag, Document doc){

        this.xmlElement = doc.createElement("Avail");
        xmlElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        xmlElement.setAttribute("framesPerSecond", "25Frames");
        xmlElement.setAttribute("availStart", dateFormat.format(availStart));
        xmlElement.setAttribute("availId", availId);
        xmlElement.setAttribute("eventKey", availId);
        xmlElement.setAttribute("durationSeconds", Integer.toString(durationSeconds));
        xmlElement.setAttribute("durationFrames", "0");
        xmlElement.setAttribute("breakId", Integer.toString(breakId) );
        xmlElement.setAttribute("availInWindow", "1");
        xmlElement.setAttribute("xsi:type", "ns2:NDSAvailType");

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
