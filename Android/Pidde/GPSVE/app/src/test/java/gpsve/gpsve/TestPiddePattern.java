package gpsve.gpsve;

;

import org.junit.Test;

import processing.core.PApplet;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Petter on 2017-04-11.
 */

public class TestPiddePattern {

    private PiddePattern testPidde = new PiddePattern(new PApplet());
//
//    public TestPiddePattern(PiddePattern testPidde){
//
//        this.testPidde = testPidde;
//    }

    @Test
    public void soundByteArrayTest(){

        byte[] bytes = new byte[]{-1,0,1,-32,32,-64,64,-96,96};
        testPidde.setSoundBytes(bytes);
        assertTrue(testPidde.getLine1()!=0 && testPidde.getLine1()== 2);
        assertTrue(testPidde.getLine2()!=0 && testPidde.getLine2() == 64);
        assertTrue(testPidde.getLine3()!=0 && testPidde.getLine3() == 128);
        assertTrue(testPidde.getLine4()!=0 && testPidde.getLine4() == 192);

    }

//    public static void main(String[] args){
//        PiddePattern pidde = new PiddePattern(new PApplet());
//        TestPiddePattern testPidde = new TestPiddePattern(pidde);
//        testPidde.soundByteArrayTest();
//    }

}
