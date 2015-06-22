package com.bskyb.inttest.adsmart.cucumber.glue.stepdefinitations;

import com.bskyb.inttest.adsmart.customssh.SshClient;
import com.bskyb.inttest.adsmart.utils.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by I&T Lab User on 25/02/2015.
 */
public class AssgGlue {
    //class variables to access static data
    //RulesManagerGlue rulesManagerGlue;


    //Static Variables
    static String assgIP;
    static List<KeyValuePair> lstNetworkLastEventId;

    public int getLastEventId(String networkName, int requestedEvent){
        try {
            if(lstNetworkLastEventId!=null) {
                for (int i = 0; i < lstNetworkLastEventId.size(); i++) {
                    if (lstNetworkLastEventId.get(i).getKey().equalsIgnoreCase(networkName))
                    {
                        int returnVal = lstNetworkLastEventId.get(i).getValue();
                        int value = returnVal + requestedEvent;
                        String key = lstNetworkLastEventId.get(i).getKey();
                        KeyValuePair keyValuePair = new KeyValuePair(key, value);
                        lstNetworkLastEventId.remove(i);
                        lstNetworkLastEventId.add(i,keyValuePair);
                        return returnVal;
                    }

                }
            }
            else
            lstNetworkLastEventId = new ArrayList<KeyValuePair>();
                SshClient.connect(assgIP, 22, "PBU10", "Changeme_14");
                String command = "sh /home/pbu10/getlastevent.sh " + "\"" + networkName.replace(" ", "\\s") + "\"";
                int LastEventId = Integer.parseInt(SshClient.executeCommand(command).trim());
                KeyValuePair keyValuePair = new KeyValuePair(networkName, LastEventId + requestedEvent);
                lstNetworkLastEventId.add(keyValuePair);
                return LastEventId;

        }catch(Exception e){
            Methods.printConditional("Error in method AssgGlue.getLastEventId :" + e);
            return 99999;
        }

    }

    public void setAssgIP(String assgIP) {
        AssgGlue.assgIP = assgIP;
    }
}
