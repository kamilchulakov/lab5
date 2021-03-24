package test_inputdata;

import input_exceptions.LessThanException;
import input_exceptions.MoreThanException;
import logic.InputData;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestInputDataCoordinateX {
    InputData inputData = new InputData();
    @Test
    public void testMaxLong() {
        try {
            inputData.setCoordinateX(Long.MAX_VALUE);
            fail();
        } catch (MoreThanException ignored) {
        } catch (LessThanException e) {
            fail();
        }
    }
    @Test
    public void testValidInteger() {
        try {
            inputData.setCoordinateX(5);
        } catch (Exception e) {
            fail();
        }
    }
    @Test
    public void testMinFloat() {
        try {
            inputData.setCoordinateX(Float.MIN_VALUE);
        } catch (MoreThanException | LessThanException e) {
            fail();
        }
    }
    @Test
    public void testInvalidInteger() {
        try {
            inputData.setCoordinateX(555);
            fail();
        } catch (MoreThanException ignored) {
        } catch (LessThanException e) {
            fail();
        }
    }
    @Test
    public void testMaxFloat() {
        try {
            inputData.setCoordinateX(Float.MAX_VALUE);
            fail();
        } catch (MoreThanException ignored) {
        } catch (LessThanException e) {
            fail();
        }
    }

}
