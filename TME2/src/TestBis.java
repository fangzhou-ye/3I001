import java.awt.Point;
import graphic.Window;

public class TestBis {
	public static void main(String[] args) {
		Window w = new Window(400, 400, "Rectangle");
		Point p1 = new Point(200, 200);
		Point p2 = new Point(100, 100);
		Point p3 = new Point(100, 300);
		new Thread(new DessineLigneBis(p1, p2, w)).start();
		new Thread(new DessineLigneBis(p2, p3, w)).start();
		new Thread(new DessineLigneBis(p3, p1, w)).start();
	}
}
