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
public class DataGetterCPTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"PC528", 528},
                {"PC5 74", 574},
                {"PC1448", 1448},
                {"PC1645", 1645},
                {"PC 1645", 1645},
                {"P C 1 6 4 5", 1645},
                {"PC 16 45", 1645},
                {null,-1},
                {"",-1}});
    }

    private final String mDataFromOCR;
    private final Integer mExpectedCP;
    private DataGetter data;

    public DataGetterCPTest(String dataFromOCR, Integer expectedCP){
        mDataFromOCR = dataFromOCR;
        mExpectedCP = expectedCP;
    }

    @Before
    public void setUp(){
        data =  new DataGetter();
    }

    @Test
    public void getCpDataParametrized() {
        Integer cpActual = data.getCp(mDataFromOCR);
        assertEquals(mExpectedCP, cpActual);
    }
}
