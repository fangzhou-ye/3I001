import java.awt.Point;
import graphic.Window;

public class DessineLigneBis implements Runnable{
	private Point dep, arr;
	private Window win;
	
	public DessineLigneBis(Point dep, Point arr, Window win) {
		this.dep = dep;
		this.arr = arr;
		this.win = win;
	}
	
	@Override
	public void run() {
		win.plotLine(dep, arr);
	}
	
}
