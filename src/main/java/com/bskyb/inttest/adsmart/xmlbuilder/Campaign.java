package com.bskyb.inttest.adsmart.xmlbuilder;

/**
 * Created by I&T Lab User on 18/12/2014.
 */
public class Campaign {
    String campaignId;
    int duration;
    private int [][] clashGroup_clashCount;


    public Campaign(String campaignId,int duration,int [][] clashGroup_clashCount){
        this.campaignId= campaignId;
        this.duration=duration;
        this.clashGroup_clashCount=clashGroup_clashCount;
    }
    public Campaign(String campaignId,int duration){
        this.campaignId= campaignId;
        this.duration=duration;
        this.clashGroup_clashCount=null;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setClashGroup_clashCount(int [][] clashGroup_clashCount) {
        this.clashGroup_clashCount = clashGroup_clashCount;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public int [][] getClashGroup_clashCount() {
        return this.clashGroup_clashCount;
    }
    public int  getClashGroup(int index) {
        if(clashGroup_clashCount.length>index)
            return this.clashGroup_clashCount[index][0];
        else
            return -1;
    }
    public int getClashCount(int index) {
        if(clashGroup_clashCount.length>index)
            return this.clashGroup_clashCount[index][1];
        else
            return -1;
    }
    public int  sizeClashGroup_clashCount() {
        if(clashGroup_clashCount!=null)
            return clashGroup_clashCount.length;
        else
            return 0;
    }
}
