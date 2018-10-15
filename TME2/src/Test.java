
import graphic.Window;
import java.awt.Point;

public class Test{
	public static void main(String[] args) {
		Window w = new Window(400, 400, "Rectangle");
		Point p1 = new Point(200, 200);
		Point p2 = new Point(100, 100);
		Point p3 = new Point(100, 300);
		DessineLigne c1 = new DessineLigne(p1, p2, w);
		DessineLigne c2 = new DessineLigne(p2, p3, w);
		DessineLigne c3 = new DessineLigne(p3, p1, w);
		c1.start();
		c2.start();
		c3.start();
	}
}