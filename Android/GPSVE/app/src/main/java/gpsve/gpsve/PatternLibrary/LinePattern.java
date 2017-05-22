package gpsve.gpsve.PatternLibrary;

import gpsve.gpsve.Interface.PatternLibraryInterface;
import processing.core.PApplet;

/**
 * Created by Petter Månsson on 2017-05-19.
 */

public class LinePattern implements PatternLibraryInterface{
    private boolean visible;
    private int[] currentLine = new int[128], previousLine= new int[128], drawLine= new int[128];
    private float lineThickness;
    private Line[] line = new Line[8];
    private PApplet parent;
    private int colorChoose = 1;

    public LinePattern(PApplet parent){
        this.parent = parent;

        for(int i = 0; i<line.length; i++){
            line[i] = new Line(parent,colorChoose);
            if(colorChoose==1){
                colorChoose=2;
            }else{
                colorChoose=1;
            }
        }

    }


    @Override
    public void update(byte[] fft, byte[] wave) {

        for (int i = 0; i < currentLine.length; i++) {
            currentLine[i] = 0;
        }

        for (int i = 0; i < fft.length; i++) {
            if (fft[i] < 0) {
                int k = fft[i];
                k *= -1;
                fft[i] = (byte) k;
            }
            if (fft[i] >= 0 && fft[i] < 16) {
                currentLine[0] += fft[i];
            }
            if (fft[i] >= 16 && fft[i] < 32) {
                currentLine[1] += fft[i];
            }
            if (fft[i] >= 32 && fft[i] < 48) {
                currentLine[2] += fft[i];
            }
            if (fft[i] >= 48 && fft[i] < 64) {
                currentLine[3] += fft[i];
            }
            if (fft[i] >= 64 && fft[i] < 80) {
                currentLine[4] += fft[i];
            }
            if (fft[i] >= 80 && fft[i] < 96) {
                currentLine[5] += fft[i];
            }
            if (fft[i] >= 96 && fft[i] < 112) {
                currentLine[6] += fft[i];
            }
            if (fft[i] >= 112) {
                currentLine[7] += fft[i];
            }
        }

            for (int i = 0; i < currentLine.length; i++) {
                if (currentLine[i] > previousLine[i]) {
                    drawLine[i] = currentLine[i];
                    previousLine[i] = currentLine[i];
                } else {
                    previousLine[i] = decay(previousLine[i], 5);
                    drawLine[i] = previousLine[i];
                }
            }

    }

    public int decay(int drawLine, int lineDecay){
        if(drawLine >= 0) {
            drawLine = drawLine - lineDecay;
        }
        if (drawLine <0) {
            drawLine = 0;
        }
        return drawLine;
    }


    @Override
    public void show() {
        if(visible){
            lineThickness = (float) (parent.width * 0.075);
            float linePos1 = (float) 0.0625, linePos2 = (float) 0.9375;
            for(int i = 0;  i<line.length; i++){
                line[i].show(linePos1,linePos2,drawLine[i],currentLine[i],previousLine[i],lineThickness);
                linePos1 += 0.125;
                linePos2 -= 0.125;
            }
        }
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;

    }
}
