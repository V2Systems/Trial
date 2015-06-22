package com.bskyb.inttest.adsmart.xmlbuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by I&T Lab User on 18/12/2014.
 */
public class XmlTagInactivityTime {
    Element xmlElement;
    int primaryDevice=65535;
    int secondaryDevice=65535;

    public void setPrimaryDevice(int primaryDevice) {
        if(primaryDevice>-1)
        this.primaryDevice = primaryDevice;
    }

    public void setSecondaryDevice(int secondaryDevice) {
        if(secondaryDevice>-1)
        this.secondaryDevice = secondaryDevice;
    }

    public int getPrimaryDevice() {
        return primaryDevice;
    }

    public int getSecondaryDevice() {
        return secondaryDevice;
    }
    public Element getElementId(){
        return this.xmlElement;
    }

    public Document setXmlTagInactivityTime(Element parentTag, Document doc){
        this.xmlElement = doc.createElement("ns2:inactivityTime");
        xmlElement.setAttribute("primaryDevice",Integer.toString(primaryDevice));
        xmlElement.setAttribute("secondaryDevice", Integer.toString(secondaryDevice));

        if(parentTag!=null)
            parentTag.appendChild(xmlElement);
        else
            doc.appendChild(xmlElement);

        return doc;
    }
}
