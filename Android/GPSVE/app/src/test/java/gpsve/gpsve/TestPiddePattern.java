package gpsve.gpsve;



import org.junit.Test;

import gpsve.gpsve.Patterns.PatternPidde;
import processing.core.PApplet;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Petter on 2017-04-11.
 */

public class TestPiddePattern {

    private PatternPidde testPidde = new PatternPidde(new PApplet());


    /**
     * Test method obsolote since code has been altered.
     */
    @Test
    public void soundByteArrayTest(){

        byte[] bytes = new byte[]{-1,0,1,-32,32,-64,64,-96,96};
//        testPidde.setSoundBytes(bytes);
        assertTrue(testPidde.getLine1()!=0 && testPidde.getLine1()== 2);
        assertTrue(testPidde.getLine2()!=0 && testPidde.getLine2() == 64);
        assertTrue(testPidde.getLine3()!=0 && testPidde.getLine3() == 128);
        assertTrue(testPidde.getLine4()!=0 && testPidde.getLine4() == 192);

    }

    @Test
    public void updatePatternTest(){
        byte[] bytes = new byte[]{-1,0,1,-16,16,-32,32,-48,48,-64,64,-80,80,-96,96,-112,112};
        testPidde.updatePattern(bytes,bytes);
        assertTrue(testPidde.getCurrentLineAtPos(0)!=0 && testPidde.getCurrentLineAtPos(0)==2);
        assertTrue(testPidde.getCurrentLineAtPos(1)!=0 && testPidde.getCurrentLineAtPos(1)==32);
        assertTrue(testPidde.getCurrentLineAtPos(2)!=0 && testPidde.getCurrentLineAtPos(2)==64);
        assertTrue(testPidde.getCurrentLineAtPos(3)!=0 && testPidde.getCurrentLineAtPos(3)==96);
        assertTrue(testPidde.getCurrentLineAtPos(4)!=0 && testPidde.getCurrentLineAtPos(4)==128);
        assertTrue(testPidde.getCurrentLineAtPos(5)!=0 && testPidde.getCurrentLineAtPos(5)==160);
        assertTrue(testPidde.getCurrentLineAtPos(6)!=0 && testPidde.getCurrentLineAtPos(6)==192);
        assertTrue(testPidde.getCurrentLineAtPos(7)!=0 && testPidde.getCurrentLineAtPos(7)==224);
      }





}
