package com.bskyb.inttest.adsmart.xmlbuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by I&T Lab User on 12/12/2014.
 */
public class AvailIdCampaignIdMap {

    String availId;
    private String[] campaignIds;
    List<Campaign> campaigns;
    int breakId=1;
    Random randomNumber = new Random();

    public AvailIdCampaignIdMap( String availId, String [] campaignIds){

        this.availId = availId;
        this.campaignIds = campaignIds;

        if(campaignIds!=null)
            {
                this.campaigns = new ArrayList<Campaign>();
                for(int i=0; i<campaignIds.length;i++ )
                {
                    Campaign campaignItem = new Campaign(campaignIds[i], 30);
                    campaigns.add(campaignItem);
                }

            }
    }

    public AvailIdCampaignIdMap( String availId, List<Campaign> campaignIds){

        this.availId = availId;

        if(campaignIds!=null)
            this.campaigns = campaignIds;
    }

    public int getCampaignsCount(){

        return this.campaigns.size();
    }

    public Campaign getCampaign(int index){
        return this.campaigns.get(index);
    }

    public List<Campaign> getCampaigns(){
        return this.campaigns;
    }

    public String getAvailId(){
        return this.availId;
    }

    public int getBreakId(){
        return this.breakId;
    }
}
