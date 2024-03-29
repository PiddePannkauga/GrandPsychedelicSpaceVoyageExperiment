package gpsve.gpsve.PatternLibrary;

import gpsve.gpsve.Interface.PatternLibraryInterface;
import processing.core.PApplet;

/**
 * Created by Nils Lindkvist on 2017-05-16.
 */

public class BackgroundAlien implements PatternLibraryInterface {
    private PApplet parent;
    private byte[] fft, wave, fftlarge, wavelarge;
    private boolean visible = false;

    static private double Gamma = 0.95;
    static private double IntensityMax = 255;

    public BackgroundAlien(PApplet parent) {
        this.parent = parent;
    }

    @Override
    public void update(byte[] fft, byte[] wave) {
        this.fft = fft;
        this.wave = wave;
        fftlarge = fft;
        wavelarge = wave;
        for (int i = 0; i< fft.length; i++) {
            fftlarge[i] = (byte) (fft[i] *60);
            wavelarge[i] = (byte) (wave[i] *60);
        }
    }

    @Override
    public void show() {
        if(visible) {
            double background = ((fft[15]+128)*(74/51))+380;
            int [] bc = waveLengthToRGB(background);
            parent.background(bc[0],bc[1],bc[2]);

            parent.pushStyle();

            for (int i = 0; i < fft.length/4; i++) {
                double shift = (5*i)+300;

                double wavelength = fftlarge[i] + shift;
                int[] wave = waveLengthToRGB(wavelength);

                if (wavelength < 750 && wavelength > 450) {
                    parent.stroke(wave[0],wave[1],wave[2]);
                    parent.fill(wave[0], wave[1], wave[2]);
                    parent.rect(i*(parent.width/128), 0, parent.width/128, parent.height);
                    parent.rect((parent.width)-((i+1)*(parent.width/128)), 0, parent.width/128, parent.height);
                    parent.rect(i*(parent.width/128)+parent.width/2,0,parent.width/128,parent.height);
                    parent.rect((parent.width)-((i+1)*(parent.width/128))-parent.width/2, 0,parent.width/128,parent.height);

                } else {

                    parent.rect(i*(parent.width/128), 0, parent.width/128, parent.height);
                    parent.rect((parent.width)-((i+1)*(parent.width/128)), 0, parent.width/128, parent.height);
                    parent.rect(i*(parent.width/128)+parent.width/2,0,parent.width/128,parent.height);
                    parent.rect((parent.width)-((i+1)*(parent.width/128))-parent.width/2, 0,parent.width/128,parent.height);

                }
            }
           parent.popStyle();
        }
    }

    public static int[] waveLengthToRGB(double Wavelength){
        double factor;
        double Red,Green,Blue;

        if((Wavelength >= 380) && (Wavelength<440)){
            Red = -(Wavelength - 440) / (440 - 380);
            Green = 0.0;
            Blue = 1.0;
        }else if((Wavelength >= 440) && (Wavelength<490)){
            Red = 0.0;
            Green = (Wavelength - 440) / (490 - 440);
            Blue = 1.0;
        }else if((Wavelength >= 490) && (Wavelength<510)){
            Red = 0.0;
            Green = 1.0;
            Blue = -(Wavelength - 510) / (510 - 490);
        }else if((Wavelength >= 510) && (Wavelength<580)){
            Red = (Wavelength - 510) / (580 - 510);
            Green = 1.0;
            Blue = 0.0;
        }else if((Wavelength >= 580) && (Wavelength<645)){
            Red = 1.0;
            Green = -(Wavelength - 645) / (645 - 580);
            Blue = 0.0;
        }else if((Wavelength >= 645) && (Wavelength<781)){
            Red = 1.0;
            Green = 0.0;
            Blue = 0.0;
        }else{
            Red = 0.0;
            Green = 0.0;
            Blue = 0.0;
        }


        if((Wavelength >= 380) && (Wavelength<420)){
            factor = 0.3 + 0.4*(Wavelength - 380) / (420 - 380);
        }else if((Wavelength >= 420) && (Wavelength<701)){
            factor = 1.0;
        }else if((Wavelength >= 701) && (Wavelength<781)){
            factor = 0.3 + 0.4*(780 - Wavelength) / (780 - 700);
        }else{
            factor = 0.0;
        }

        int[] rgb = new int[3];

        rgb[0] = Red==0.0 ? 0 : (int) Math.round(IntensityMax * Math.pow(Red * factor, Gamma));
        rgb[1] = Green==0.0 ? 0 : (int) Math.round(IntensityMax * Math.pow(Green * factor, Gamma));
        rgb[2] = Blue==0.0 ? 0 : (int) Math.round(IntensityMax * Math.pow(Blue * factor, Gamma));

        return rgb;
    }


    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
