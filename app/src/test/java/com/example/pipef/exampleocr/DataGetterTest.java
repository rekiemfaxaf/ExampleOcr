package com.example.pipef.exampleocr;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by pipef on 07-10-2016.
 */

public class DataGetterTest {
    private DataGetter data;
    private String dataFromOCR = "Q ’3 Q €11 730/6“ 10:12\n" +
            "pc1448 {:7\n" +
            "‘ .1\n" +
            "€ I\n" +
            "x. , ;\n" +
            "9\n" +
            "A rca n I n e\n" +
            "PS 110/ 110\n" +
            "Fuego 187.04 kg 1.94 m\n" +
            "Tipo Peso Altura\n" +
            "H 187485 9 68\n" +
            "POLVOESTELARES CARAMELOS GROWLITHE\n" +
            "w H2200 E 2\n" +
            "Mordisco ®\n" +
            "Sinicstro\n" +
            "Terratemblor 35";
    @Before
    public void init(){
        data =  new DataGetter();
    }

    @Test
    public void getCpDataOkReturnCp(){
        //prepare
        Integer cpExpected = 1448;
        Integer cpActual = data.getCp(dataFromOCR);
        assertEquals(cpExpected, cpActual);
    }

    @Test
    public void getpDataOkReturnErrorNumber(){
        //prepare
        String dateEmpty = null;
        Integer cpExpected = -1;
        Integer cpActual = data.getCp(dateEmpty);
        assertEquals(cpExpected, cpActual);
    }

    @Test
    public void getHpDataOkReturnCp(){
        //prepare
        Integer cpExpected = 110;
        Integer cpActual = data.getHp(dataFromOCR);
        assertEquals(cpExpected, cpActual);
    }

    @Test
    public void getHpDataOkReturnErrorNumber(){
        //prepare
        String dateEmpty = null;
        Integer cpExpected = -1;
        Integer cpActual = data.getHp(dateEmpty);
        assertEquals(cpExpected, cpActual);
    }
}
