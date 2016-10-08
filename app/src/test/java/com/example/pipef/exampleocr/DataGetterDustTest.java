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
public class DataGetterDustTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"1900", 1900},
                {"3000", 3000},
                {"12 00", 1200},
                {"10", 10},
                {"520", 520},
                {"1 6 4 5", 1645},
                {"16 45", 1645},
                {null,-1},
                {"",-1}});
    }

    private final String mDataFromOCR;
    private final Integer mExpectedDust;
    private DataGetter data;

    public DataGetterDustTest(String dataFromOCR, Integer expectedDust){
        mDataFromOCR = dataFromOCR;
        mExpectedDust = expectedDust;
    }

    @Before
    public void setUp(){
        data =  new DataGetter();
    }

    @Test
    public void getCpDataParametrized() {
        Integer DustActual = data.getDust(mDataFromOCR);
        assertEquals(mExpectedDust, DustActual);
    }
}
