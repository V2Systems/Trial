package com.bskyb.inttest.adsmart.xmlbuilder;

import org.w3c.dom.Element;

/**
 * Created by I&T Lab User on 12/12/2014.
 */
public class XmlTagCampaign {
    Element xmlElement;
    private String campaignId;
    private int clashGroup=-1;
    private int clashCount=-1;
    private int duration = 30; //default

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public void setClashCount(int clashCount) {
        this.clashCount = clashCount;
    }

    public void setClashGroup(int clashGroup) {
        this.clashGroup = clashGroup;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public int getClashCount() {
        return clashCount;
    }

    public int getClashGroup() {
        return clashGroup;
    }

    public int getDuration() {
        return duration;
    }

    public void createCampaign(String campaignId,int clashGroup, int clashCount,int duration ){

        setCampaignId(campaignId);
        setClashCount(clashGroup);
        setClashCount(clashCount);
        setDuration(duration);
    }

    public void createCampaign(String campaignId,int clashGroup, int clashCount ){
        this.setDuration(30);
        createCampaign(campaignId, clashGroup, clashCount, duration );

    }


    public void createCampaign(String campaignId ){
        this.setClashGroup(0);
        this.setClashCount(0);
        this.setDuration(30);
        createCampaign(campaignId, clashGroup, clashCount, duration );

    }
    public void createCampaign(String campaignId, int duration ){
        this.setClashGroup(0);
        this.setClashCount(0);
        createCampaign(campaignId, clashGroup, clashCount, duration );

    }
    public XmlTagCampaign(String campaignId, int duration, int clashGroup, int clashCount){
        this.campaignId= campaignId;
        this.duration=duration;
        this.clashGroup=clashGroup;
        this.clashCount=clashCount;
    }

    public XmlTagCampaign(){

    }
    public Element getElementId(){
        return this.xmlElement;
    }
}
