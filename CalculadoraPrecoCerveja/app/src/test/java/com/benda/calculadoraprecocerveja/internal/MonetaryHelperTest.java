package com.benda.calculadoraprecocerveja.internal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class MonetaryHelperTest {

    @Test
    public void formatToMonetary() {
        // arrange
        String value = "175.20";

        // act
        String formatted = MonetaryHelper.formatToMonetary(value);

        // assert
        Assert.assertEquals("R$ 175,20", formatted);
    }

    @Test
    public void cleanFormat() {
        // arrange
        String value = "R$ 175,20";

        // act
        String unformatted = MonetaryHelper.cleanFormat(value);

        // assert
        Assert.assertEquals("175.20", unformatted);
    }
}