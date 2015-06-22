package com.bskyb.inttest.adsmart.windows;

/**
 * Created by I&T Lab User on 28/01/2015.
 */
public class Winexe {
    String psCommand = "C:\\Users\\I&T Lab User\\Downloads\\PSTools\\psexec.exe";


    public void runCommand(){
        try{

            Process p = Runtime.getRuntime().exec("cmd.exe");

            System.out.println(p.getInputStream());

        }catch(Exception e){
            System.out.println("Error running Windows Command");
        }

    }


}
