import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyPanel extends JPanel {
	private static int count = 0;
	ArrayList<Person> per;
	Elevator[] elev;
	T t;

	public MyPanel(ArrayList<Person> p, Elevator[] e) {
		this.per = p;
		this.elev = e;

		this.setLayout(null);

		elev[0] = new Elevator(83,0);
		elev[1] = new Elevator(163,1);
		elev[2] = new Elevator(243,2);
		elev[3] = new Elevator(323,3);

		t = new T(per, elev, this);
		t.start();

		this.setBounds(60, 0, 450, 700);// 부모에서 0,0 500너비 500높
	}

	public void paintComponent(Graphics g) {
		g.setColor(new Color(246, 246, 246));
		g.fillRect(0, 0, getWidth(), 110);

		g.setColor(new Color(236, 236, 236));
		g.fillRect(0, 110, getWidth(), 110);

		g.setColor(new Color(226, 226, 226));
		g.fillRect(0, 220, getWidth(), 110);

		g.setColor(new Color(216, 216, 216));
		g.fillRect(0, 330, getWidth(), 110);

		g.setColor(new Color(206, 206, 206));
		g.fillRect(0, 440, getWidth(), 110);

		g.setColor(new Color(196, 196, 196));
		g.fillRect(0, 550, getWidth(), 110);

		// floor
		int floor = 0;
		for (int i = 0; i < 12; i++) {
			floor = floor + 55;
			g.setColor(new Color(76,76,76));
			g.drawLine(0, floor, 530, floor);
			g.setColor(new Color(76, 76, 76));
			g.drawLine(0, floor + 1, 530, floor + 1);
		}

		// Elev 1
		g.setColor(Color.gray);
		g.drawLine(100, 0, 100, 660);
		g.setColor(Color.GRAY);
		g.drawLine(101, 0, 101, 660);
		g.setColor(Color.gray);
		g.drawLine(102, 0, 102, 660);

		// Elev 2
		g.drawLine(180, 0, 180, 660);
		g.setColor(Color.GRAY);
		g.drawLine(181, 0, 181, 660);
		g.setColor(Color.gray);
		g.drawLine(182, 0, 182, 660);

		// Elev 3
		g.drawLine(260, 0, 260, 660);
		g.setColor(Color.GRAY);
		g.drawLine(261, 0, 261, 660);
		g.setColor(Color.gray);
		g.drawLine(262, 0, 262, 660);

		// Elev 4
		g.drawLine(340, 0, 340, 660);
		g.setColor(Color.GRAY);
		g.drawLine(341, 0, 341, 660);
		g.setColor(Color.gray);
		g.drawLine(342, 0, 342, 660);

		for (int i = 0; i < elev.length; i++) {
			if(elev[i].isStop() == true) {
				g.drawImage(elev[i].getImg(1), elev[i].getX(), elev[i].getY(), this); // x, y
			}
			else {
				g.drawImage(elev[i].getImg(0), elev[i].getX(), elev[i].getY(), this); // x, y
			}
		}
		for (int i = 0; i < per.size(); i++) {
			if (count % 6 == 0 || count % 6 == 1)
				g.drawImage(per.get(i).getImg(0), per.get(i).getX(), per.get(i).getY(), this); // x, y
			else
				g.drawImage(per.get(i).getImg(1), per.get(i).getX(), per.get(i).getY(), this);
		}
		count++;
	}
}
