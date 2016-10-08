package com.example.pipef.exampleocr;

import com.example.pipef.exampleocr.contants.Constants;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pipef on 07-10-2016.
 */

public class DataGetter {

    public Integer getCp(String dataFromOCR) {
        if (dataFromOCR != null) {
            Pattern r = Pattern.compile(Constants.CP_REGEX.toString());
            String lowerAndTrimedData = dataFromOCR.toLowerCase().trim().replace(" ","");
            System.out.println("lowerAndTrimedData value: " + lowerAndTrimedData);
            Matcher m = r.matcher(lowerAndTrimedData);
            String CpValue = null;
            String onlynumber = null;
            if (m.find()) {
                System.out.println("Found value: " + m.group(0));
                CpValue = m.group(0);
            } else {
                return -1;
            }
            onlynumber = CpValue.replace("pc", "");
            return Integer.parseInt(onlynumber);
        } else{
            return -1;
        }
    }

    public Integer getHp(String dataFromOCR) {
        if (dataFromOCR != null) {
            Pattern r = Pattern.compile(Constants.HP_REGEX.toString().trim());
            String lowerAndTrimedData = dataFromOCR.toLowerCase().trim().replace(" ","");
            System.out.println("lowerAndTrimedData value: " + lowerAndTrimedData);
            Matcher m = r.matcher(lowerAndTrimedData);
            String HpValue = null;
            String[] parts = null;
            if (m.find()) {
                System.out.println("Found value: " + m.group(0));
                HpValue = m.group(0);
            } else {
                return -1;
            }
            String onlynumber = null;
            onlynumber = HpValue.replace("ps", "");
            return Integer.parseInt(onlynumber);
        } else{
            return -1;
        }
    }

    public Integer getDust(String dataFromOCR) {
        if (dataFromOCR != null) {
            Pattern r = Pattern.compile(Constants.DUST_REGEX.toString().trim());
            String lowerAndTrimedData = dataFromOCR.toLowerCase().trim().replace(" ","");
            System.out.println("lowerAndTrimedData value: " + lowerAndTrimedData);
            Matcher m = r.matcher(lowerAndTrimedData);
            String dustValue = null;
            if (m.find()) {
                System.out.println("Found value: " + m.group(0));
                dustValue = m.group(0);
            } else {
                return -1;
            }
            return Integer.parseInt(dustValue);
        } else{
            return -1;
        }
    }

    public String getName(String dataFromOCR) {
        if (dataFromOCR != null) {
            Pattern r = Pattern.compile(Constants.NAME_REGEX.toString().trim());
            String lowerAndTrimedData = dataFromOCR.toLowerCase().trim().replace(" ","");
            System.out.println("lowerAndTrimedData value: " + lowerAndTrimedData);
            Matcher m = r.matcher(lowerAndTrimedData);
            String nameValue = null;
            if (m.find()) {
                System.out.println("Found value: " + m.group(0));
                nameValue = m.group(0);
            } else {
                return "";
            }
            return nameValue;
        } else{
            return "";
        }
    }

}
