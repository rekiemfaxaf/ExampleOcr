package com.example.pipef.exampleocr;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by pipef on 07-10-2016.
 */
@RunWith(Parameterized.class)
public class DataGetterNameTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"Slowpoke", "slowpoke"},
                {"Slowpo ke", "slowpoke"},
                {"Sl owpoke", "slowpoke"},
                {"Sl owpoke", "slowpoke"},
                {"Slowpo ke", "slowpoke"},
                {"Slowpoke", "slowpoke"},
                {"Slo w poke", "slowpoke"},
                {null,""},
                {"",""}});
    }

    private final String mDataFromOCR;
    private final String mExpectedName;
    private DataGetter data;

    public DataGetterNameTest(String dataFromOCR, String expectedName){
        mDataFromOCR = dataFromOCR;
        mExpectedName = expectedName;
    }

    @Before
    public void setUp(){
        data =  new DataGetter();
    }

    @Test
    public void getCpDataParametrized() {
        String nameActual = data.getName(mDataFromOCR);
        assertEquals(mExpectedName, nameActual);
    }
}
