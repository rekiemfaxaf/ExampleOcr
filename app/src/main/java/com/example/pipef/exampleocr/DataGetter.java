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
            Matcher m = r.matcher(dataFromOCR);
            String pc = null;
            String onlynumber = null;
            if (m.find()) {
                System.out.println("Found value: " + m.group(0));
                pc = m.group(0);
            } else {
                return -1;
            }
            onlynumber = pc.replace("pc", "");
            return Integer.parseInt(onlynumber);
        } else{
            return -1;
        }
    }

    public Integer getHp(String dataFromOCR) {
        if (dataFromOCR != null) {
            Pattern r = Pattern.compile(Constants.HP_REGEX.toString());
            Matcher m = r.matcher(dataFromOCR);
            String pc = null;
            String[] parts = null;
            if (m.find()) {
                System.out.println("Found value: " + m.group(0));
                pc = m.group(0);
            } else {
                return -1;
            }
            parts = pc.split(" ");
            return Integer.parseInt(parts[1]);
        } else{
            return -1;
        }
    }


}
