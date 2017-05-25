package gpsve.gpsve;

import org.junit.Test;

import java.util.Random;

import gpsve.gpsve.Controllers.Buffer;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Oskar on 2017-05-17.
 */

public class TestBuffer {
    private Buffer buffer = new Buffer();

    @Test
    public void testB() {
        byte[] testArray = new byte[128];
        new Random().nextBytes(testArray);
        buffer.put(testArray);
        assertTrue(buffer.size() != 0);
        byte[] testR;
        try {
            testR = buffer.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(buffer.size() == 0);

    }
}
