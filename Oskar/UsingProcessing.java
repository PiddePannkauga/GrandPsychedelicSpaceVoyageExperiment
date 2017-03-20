
import ddf.minim.*;
import processing.core.*;

public class UsingProcessing extends PApplet{
	Minim minim;
	AudioPlayer song;
	float volume;
	int i = 0;
	
	public static void main(String[] args) {
        PApplet.main("UsingProcessing");
    }
	
	public void settings() {
		size(600,600);
	}
	
	public void setup() {
		
		minim = new Minim(this);
		song = minim.loadFile("files/recapping.wav");
		song.play();
	}
	
	public void draw() {
		i++;
		stroke(255);
		background(0);
		ellipse(width/2,height/2, song.mix.get(i)*50, song.mix.get(i)*50);
		
		
	}


}
