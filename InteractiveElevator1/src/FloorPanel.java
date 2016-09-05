import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FloorPanel extends JPanel {
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image tmpImg[] = new Image[12];

	public FloorPanel() {
		setLayout(null);
		for (int i=0; i <= 11; i++) {
			tmpImg[11 - i] = tk.createImage("img/f" + i + ".png");
		}
		
		setBounds(0, 0, 60, 665);
	}
	
	public void paintComponent(Graphics g){
		g.setColor(new Color(0, 0, 0)); // 왼쪽 끝에 그림같다
		g.fillRect(0, 0, 60, 665);

		for (int i=0; i<12; i++) {
			g.drawImage(tmpImg[i], 0, (i * 55)+5, this);
		}
	}
}
