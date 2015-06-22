package com.bskyb.inttest.adsmart.xmlbuilder;



import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * Created by I&T Lab User on 17/12/2014.
 */
public class XmlTagSpot {

    Element xmlElement;
    AvailIdCampaignIdMap avilIdCampaigns;
    String length ="00003000";

    public Document setXmlTagSpot(Element parentTag, Document doc ){

        this.xmlElement = doc.createElement("Spot");
        xmlElement.setAttribute("eventType", "LOI");
        xmlElement.setAttribute("length", length);
        xmlElement.setAttribute("lengthFrames", "0");
        xmlElement.setAttribute("positionInAvail", "1");
        xmlElement.setAttribute("spotId", "1");
        xmlElement.setAttribute("trafficId", "1");
        xmlElement.setAttribute("xsi:type", "ns2:SpotRulesType");
        parentTag.appendChild(xmlElement);

        Element ns2_substitutionOptionSlot = doc.createElement("ns2:substitutionOptionSlot");
        xmlElement.appendChild(ns2_substitutionOptionSlot);

        Element ns2_adInSpot = doc.createElement("ns2:adInSpot");
        ns2_substitutionOptionSlot.appendChild(ns2_adInSpot);


        for (int i = 0; i < avilIdCampaigns.getCampaignsCount(); i++) {

            Element ns2_campaignRef = doc.createElement("ns2:campaignRef");
            ns2_adInSpot.appendChild(ns2_campaignRef);
            Element ns2_campaignIdRef = doc.createElement("ns2:campaignIdRef");
            ns2_campaignIdRef.appendChild(doc.createTextNode(avilIdCampaigns.getCampaign(i).getCampaignId()));
            ns2_campaignRef.appendChild(ns2_campaignIdRef);
            //Element ns2_clashData = doc.createElement("ns2:clashData");
            for(int j=0;j<avilIdCampaigns.getCampaign(i).sizeClashGroup_clashCount();j++){
                Element ns2_clashData = doc.createElement("ns2:clashData");
                ns2_clashData.setAttribute("clashGroup", Integer.toString(avilIdCampaigns.getCampaign(i).getClashGroup(j)));
                ns2_clashData.setAttribute("clashCount", Integer.toString(avilIdCampaigns.getCampaign(i).getClashCount(j)));
                //append only if we have valid clashGroup & clashCount
                if(avilIdCampaigns.getCampaign(i).getClashGroup(j)>-1 && avilIdCampaigns.getCampaign(i).getClashCount(j)>-1 )
                    ns2_campaignRef.appendChild(ns2_clashData);
            }
        }

        return doc;
    }

    public void setAvilIdCampaigns(AvailIdCampaignIdMap avilIdCampaigns) {
        this.avilIdCampaigns = avilIdCampaigns;
        this.length = String.format("%08d",avilIdCampaigns.getCampaign(0).duration*100);
    }

    public Element getElementId(){
        return this.xmlElement;
    }
}
