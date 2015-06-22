package com.bskyb.inttest.adsmart.utils;

/**
 * Created by I&T Lab User on 25/02/2015.
 */
public class KeyValuePair {
    String key;
    int value;

    public KeyValuePair(){
        this.key = "";
        this.value= 0;
    }
    public KeyValuePair(String key, int value){
        this.key = key;
        this.value= value;
    }
    public int getValue(){
        return this.value;
    }
    public String getKey(){
        return this.key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
