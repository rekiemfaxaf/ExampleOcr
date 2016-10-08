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
public class DataGetterHPTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"PS 101 / 101", 101},
                {"PS 72/ 72", 72},
                {"PS 110/ 110", 110},
                {"PS 199 / 199", 199},
                {"P S 1 9 9 / 1 9 9", 199},
                {"PS 19 9 / 19 9", 199},
                {"P S 1 99  / 1 99", 199},
                {null,-1},
                {"",-1}});
    }

    private final String mDataFromOCR;
    private final Integer mExpectedHP;
    private DataGetter data;

    public DataGetterHPTest(String dataFromOCR, Integer expectedHP){
        mDataFromOCR = dataFromOCR;
        mExpectedHP = expectedHP;
    }

    @Before
    public void setUp(){
        data =  new DataGetter();
    }

    @Test
    public void getHpDataParametrized() {
        Integer hpActual = data.getHp(mDataFromOCR);
        assertEquals(mExpectedHP, hpActual);
    }
}
