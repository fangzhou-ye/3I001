import graphic.Window;
import java.awt.Point;

public class DessineLigne extends Thread{
	private Point p_depart;
	private Point p_arrive;
	private Window win;

	public DessineLigne(Point p1, Point p2, Window w){
		p_depart = p1;
		p_arrive = p2;
		win = w;
	}

	public void run(){
		win.plotLine(p_depart, p_arrive);
	}
}