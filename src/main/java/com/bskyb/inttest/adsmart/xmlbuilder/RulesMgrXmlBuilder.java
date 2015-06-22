package com.bskyb.inttest.adsmart.xmlbuilder;

import com.bskyb.inttest.adsmart.utils.Methods;
import org.w3c.dom.Document;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by I&T Lab User on 10/12/2014.
 */
public class RulesMgrXmlBuilder {

//Fields under #Schedule
    boolean tagWindow = true;   //optional
    boolean tagAvail = false;
    private boolean tagInactivityTime = false; //optional


    //Fields under #Window
    long availStartValue;
    DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");
    DateFormat dateFormat3 = new SimpleDateFormat("ddMMyyyy");
    DateFormat returnDateFormat = new SimpleDateFormat("dd-MMM-yy HH.mm.ss.SSS");
    //Default is the current dataTime
    Date referenceDateTime = new Date();
    Date initialWindowStartValueDateTime;

    long startAfterMilliSeconds = 120000;
    private int primaryDeviceInactivity = 65535;
    private int secondaryDeviceInactivity = 65535;
    private String networkName;

    //Driving Data variables
    List <AvailIdCampaignIdMap> availIdCampaignIdMapDATA;

    //Default constructor
    public RulesMgrXmlBuilder(List<AvailIdCampaignIdMap> availIdCampaignIdMapDATA){
        this.availIdCampaignIdMapDATA=availIdCampaignIdMapDATA;
        if(availIdCampaignIdMapDATA.size()>0)
            this.setTagAvail(true);
    }
    public RulesMgrXmlBuilder(List<AvailIdCampaignIdMap> availIdCampaignIdMapDATA, int primaryDeviceInactivity, int secondaryDeviceInactivity){
        this.availIdCampaignIdMapDATA=availIdCampaignIdMapDATA;
        if(availIdCampaignIdMapDATA.size()>0)
            this.setTagAvail(true);
        this.primaryDeviceInactivity = primaryDeviceInactivity;
        this.secondaryDeviceInactivity = secondaryDeviceInactivity;
        this.setTagInactivityTime(true);
    }

    public void setPrimaryDeviceInactivity(int primaryDeviceInactivity) {
        this.primaryDeviceInactivity = primaryDeviceInactivity;
        this.setTagInactivityTime(true);
    }
    public void setSecondaryDeviceInactivity(int secondaryDeviceInactivity) {
        this.secondaryDeviceInactivity = secondaryDeviceInactivity;
        this.setTagInactivityTime(true);
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public void setTagAvail(boolean tagAvail) {
        this.tagAvail = tagAvail;
    }
    public void setTagInactivityTime(boolean tagInactivityTime){
        this.tagInactivityTime = tagInactivityTime;
    }
    public boolean isTagAvail() {
        return tagAvail;
    }

    public void setTagWindow(boolean tagWindow) {
        this.tagWindow = tagWindow;
    }

    public void createXML(String fileOut, Date referenceDateTime ){
        //if dateString format is ddMMyyy & time will be considered as 00.00.00
        this.referenceDateTime=referenceDateTime;
        createXML(fileOut);
    }
    public void createXML(String fileOut){
        try{

            long longReferenceDateTime = Methods.getLongDateTime(referenceDateTime);
            initialWindowStartValueDateTime = new Date(longReferenceDateTime+ startAfterMilliSeconds);
            long windowStartValue;

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements (Schedule)
            Document doc = docBuilder.newDocument();

            XmlTagSchedule xmlTagSchedule = new XmlTagSchedule(longReferenceDateTime);
            xmlTagSchedule.setNetworkName(networkName);
            doc = xmlTagSchedule.setXmlTagSchedule(null,doc);

                  for(int i=0; i < availIdCampaignIdMapDATA.size() && tagWindow;i++) {

                      referenceDateTime.setTime(longReferenceDateTime + startAfterMilliSeconds + (i * availIdCampaignIdMapDATA.get(0).getCampaign(0).duration * 1000));
                      availStartValue = Methods.getLongDateTime(referenceDateTime);
                      //referenceDateTime.setTime(longReferenceDateTime + startAfterMilliSeconds + (i * availIdCampaignIdMapDATA.get(0).getCampaign(0).duration * 1000)); //- 1080000);
                      //windowStartValue = Methods.getLongDateTime(referenceDateTime);

                      //#Window
                      XmlTagWindow xmlTagWindow = new XmlTagWindow(availStartValue);
                      doc = xmlTagWindow.setXmlTagWindow(xmlTagSchedule.getElementId(), doc);

                      //#Avail
                      if(tagAvail) {
                          XmlTagAvail xmlTagAvail = new XmlTagAvail(availStartValue,availIdCampaignIdMapDATA.get(i).getCampaign(0).duration);
                          xmlTagAvail.availId = availIdCampaignIdMapDATA.get(i).getAvailId();
                          xmlTagAvail.breakId=availIdCampaignIdMapDATA.get(i).getBreakId();
                          doc = xmlTagAvail.setXmlTagAvail(xmlTagWindow.getElementId(),doc);

                          //Spot & Spot Campaign
                          XmlTagSpot xmlTagSpot = new XmlTagSpot();
                          xmlTagSpot.setAvilIdCampaigns(availIdCampaignIdMapDATA.get(i));
                          doc = xmlTagSpot.setXmlTagSpot(xmlTagAvail.getElementId(),doc);

                          //inactivityTime
                          if(tagInactivityTime){
                              XmlTagInactivityTime xmlTagInactivityTime = new XmlTagInactivityTime();
                              xmlTagInactivityTime.setPrimaryDevice(primaryDeviceInactivity);
                              xmlTagInactivityTime.setSecondaryDevice(secondaryDeviceInactivity);
                              doc = xmlTagInactivityTime.setXmlTagInactivityTime(xmlTagAvail.getElementId(), doc);
                          }


                      }

                  }
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            //see the output on the screen
            //StreamResult result = new StreamResult(System.out);

            StreamResult result = new StreamResult(new File(fileOut));

            transformer.transform(source, result);


        }catch(Exception e) {

            System.out.println("Exception in RulesMgrXmlBuilder.createXML: " + e);

        }


    }

    public String getAvailStartValue() {
            return returnDateFormat.format(initialWindowStartValueDateTime);
    }
    public void setReturnDateFormat(String format){
        this.returnDateFormat=new SimpleDateFormat(format);
    }
}
