package gpsve.gpsve;

import android.app.Activity;

import org.junit.Test;

import java.util.Random;

import gpsve.gpsve.Controllers.PatternController;
import gpsve.gpsve.Controllers.SoundConverter;
import gpsve.gpsve.Patterns.PatternOskar;
import processing.core.PApplet;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Oskar on 2017-05-10.
 */

public class TestPatternOskar {

    private PatternController testController = new PatternController(new Activity(),new SoundConverter(2));



    private PatternOskar testOskar = new PatternOskar(testController);

    @SuppressWarnings("deprecation")
    @Test
    public void testRGB() {

        int[] testBLUERED = testOskar.waveLengthToRGB(400);
        assertTrue(testBLUERED[0] != 0);
        assertTrue(testBLUERED[1] == 0);
        assertTrue(testBLUERED[2] != 0);
        int[] testBLUEGREEN = testOskar.waveLengthToRGB(460);
        assertTrue(testBLUEGREEN[0] == 0);
        assertTrue(testBLUEGREEN[1] != 0);
        assertTrue(testBLUEGREEN[2] != 0);
        int[] testGREENBLUE = testOskar.waveLengthToRGB(500);
        assertTrue(testGREENBLUE[0] == 0);
        assertTrue(testGREENBLUE[1] != 0);
        assertTrue(testGREENBLUE[2] != 0);
        int[] testGREENRED = testOskar.waveLengthToRGB(540);
        assertTrue(testGREENRED[0] != 0);
        assertTrue(testGREENRED[1] != 0);
        assertTrue(testGREENRED[2] == 0);
        int[] testREDGREEN = testOskar.waveLengthToRGB(600);
        assertTrue(testREDGREEN[0] != 0);
        assertTrue(testREDGREEN[1] != 0);
        assertTrue(testREDGREEN[2] == 0);
        int[] testRED = testOskar.waveLengthToRGB(740);
        assertTrue(testRED[0] != 0);
        assertTrue(testRED[1] == 0);
        assertTrue(testRED[2] == 0);
        int[] testHIGH = testOskar.waveLengthToRGB(900);
        assertTrue(testHIGH[0] == 0);
        assertTrue(testHIGH[1] == 0);
        assertTrue(testHIGH[2] == 0);
        int[] testLOW = testOskar.waveLengthToRGB(100);
        assertTrue(testLOW[0] == 0);
        assertTrue(testLOW[1] == 0);
        assertTrue(testLOW[2] == 0);

        byte[] testArray = new byte[128];
        new Random().nextBytes(testArray);
        testController.setPattern(testOskar);
        testController.settings();
        testController.setup();
        testOskar.updatePattern(testArray, testArray);
        assertTrue(testOskar.okToDraw());

    }
}
