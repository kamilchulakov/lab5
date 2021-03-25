package test_inputdata;

import input_exceptions.LessThanException;
import input_exceptions.MoreThanException;
import logic.InputData;
import org.junit.Test;

import static org.junit.Assert.fail;

public class TestInputDataCoordinateY {
    InputData inputData = new InputData(false);
    @Test
    public void testMaxLong() {
        try {
            inputData.setCoordinateY(Long.MAX_VALUE);
            fail();
        } catch (MoreThanException ignored) {
        } catch (LessThanException e) {
            fail();
        }
    }
    @Test
    public void testValidInteger() {
        try {
            inputData.setCoordinateY(5);
        } catch (Exception e) {
            fail();
        }
    }
    @Test
    public void testMinFloat() {
        try {
            inputData.setCoordinateY(Float.MIN_VALUE);
        } catch (MoreThanException | LessThanException e) {
            fail();
        }
    }
    @Test
    public void testInvalidInteger() {
        try {
            inputData.setCoordinateY(557);
            fail();
        } catch (MoreThanException ignored) {
        } catch (LessThanException e) {
            fail();
        }
    }
    @Test
    public void testMaxFloat() {
        try {
            inputData.setCoordinateY(Float.MAX_VALUE);
            fail();
        } catch (MoreThanException ignored) {
        } catch (LessThanException e) {
            fail();
        }
    }
}
