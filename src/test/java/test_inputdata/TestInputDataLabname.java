package test_inputdata;

import input_exceptions.CancelException;
import input_exceptions.LessThanException;
import input_exceptions.MoreThanException;
import logic.InputData;
import org.junit.Test;

import static org.junit.Assert.fail;

public class TestInputDataLabname {
    InputData inputData = new InputData(false);
    @Test
    public void testNull() {
        try {
            inputData.setLabName(null);
            fail();
        } catch (Exception ignored) {};
    }
    @Test
    public void testCancel() {
        try {
            inputData.setLabName("");
            fail();
        } catch (CancelException ignored) {
        } catch (Exception e) {
            fail();
        }
    }
}
