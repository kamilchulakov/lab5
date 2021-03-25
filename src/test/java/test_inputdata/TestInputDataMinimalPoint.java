package test_inputdata;

import input_exceptions.CancelException;
import input_exceptions.LessThanException;
import input_exceptions.MoreThanException;
import logic.InputData;

import static org.junit.Assert.fail;
import org.junit.Test;

public class TestInputDataMinimalPoint {
    InputData inputData = new InputData(false);
    @Test
    public void testNull() {
        try {
            inputData.setMinimalPoint(null);
            fail();
        } catch (CancelException ignored) {
        } catch (Exception e) {
            fail();
        }
    }
    @Test
    public void testEmpty() {
        try {
            inputData.setMinimalPoint("");
            fail();
        } catch (NumberFormatException ignored) {
        } catch (Exception e) {
            fail();
        }
    }
    @Test
    public void testInvalid() {
        try {
            inputData.setMinimalPoint("                    ");
            fail();
        } catch (NumberFormatException ignored) {
        } catch (Exception e) {
            fail();
        }
    }
    @Test
    public void testMinLong() {
        try {
            inputData.setMinimalPoint(String.valueOf(Long.MIN_VALUE));
            fail();
        } catch (LessThanException ignored) {
        } catch (Exception e) {
            fail();
        }
    }
}
