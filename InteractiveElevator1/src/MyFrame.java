import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class MyFrame implements ActionListener{
	private static int now = -1;
	private Font fo = new Font ("AR JULIAN",Font.BOLD,18);
	private Font fo2 = new Font ("Consolas",Font.PLAIN,15);
	private Font congetsionFont = new Font("AR JULIAN", Font.BOLD, 14);
	
	// background
	private Toolkit tk;
	private final int BGWIDTH = 900; 
	private final int BGHEIGHT = 735;
	private final int CEILING = 150;
	private final int GROUND = 626;
	
	public static int SEC = 100; // 추가
	ArrayList<Integer> numPerson = new ArrayList<Integer>();
	
	//추가
	private Timer test;
	private JButton testButton = new JButton();
	private JButton slowButton = new JButton();
	//private JButton testButton2 = new JButton();
	private JLabel testLabel = new JLabel();
	private JPanel testPanel = new JPanel();
	private JButton[] changeBtn = new JButton[2];
	

	private Image bgImg = Toolkit.getDefaultToolkit().getImage("img/sky.png");
	
	// 추가
	public static int time = 0; // check the moring & lunch & night ^^
	public static int e1=3;
	public static int e2=2;
	public static int e3=1;
	public static int e4=0;
	
	ImageIcon icon = new ImageIcon("img/sky.jpg");
	ImageIcon fast = new ImageIcon("img/fast.png");
	ImageIcon slow = new ImageIcon("img/slow.png");
	//추가
	
	ImageIcon busy = new ImageIcon("img/busy.png");
	ImageIcon normal1 = new ImageIcon("img/normal1.png");
	ImageIcon morning = new ImageIcon("img/morning.png");
	ImageIcon lunch = new ImageIcon("img/lunch.png");
	ImageIcon night = new ImageIcon("img/night.png");
	ImageIcon normal2 = new ImageIcon("img/normal2.png");
	
	Image img[] = new Image[2];	// 추가
	Toolkit tk2 = Toolkit.getDefaultToolkit(); // 추가
	private BufferedImage image;

	public static int personCount = 0;
	public int wait_location[] = {120, 200, 280, 360};
	
	private int db_lock1 = 0;
	private int db_lock2 = 0;
	private int db_lock3 = 0;
	private int db_lock4 = 0;
	private int db_lock5 = 0;
	
	
	JFrame mainFrame = new JFrame("Select Elevator");
	//JFrame subFrame = new JFrame("Floor Button");
	//JFrame thirdFrame = new JFrame("Speed Control");
	JTextArea floorArea = new JTextArea(5, 20);
	JTextArea first = new JTextArea(5, 20);
	JTextArea second = new JTextArea(5, 20);
	JLabel timeLabel = new JLabel();

	JPanel jp;
	JPanel floorPanel = new JPanel();
	JPanel textPanel = new JPanel();
	JPanel backgroundPanel = new JPanel();
	JPanel timeImage;

	JPanel doorPanel = new JPanel();
	JPanel selectButton = new JPanel();
	JPanel showBest1 = new JPanel();
	JPanel showBest2 = new JPanel();
	JPanel showFloor = new JPanel();
	JPanel speedTable = new JPanel();
	JPanel time2 = new JPanel();
	JPanel TimeImage = new JPanel();
	
	JPanel sun = new Picture();
	JPanel ChangeTable = new JPanel();

	JPanel pink = new JPanel();

	private JButton[] recoBtn = new JButton[2];   
	private JButton[] floorBtn = new JButton[12];
	private JButton[] selectBtn = new JButton[12];
	private JButton[] speedBtn = new JButton[5];
	public static int d;
	
	private int morning_setting = 1;
	private int lunch_setting = 1;
	private int night1_setting = 1;
	private int night2_setting = 1;
	//추가	

	public static int[] person_arr = new int[12];
	private int auto =0;
	private int check =1;
	private int clockInterval , clockTick;
	private int clockTimeS = 0;  	//time in seconds
	private int clockTimeM = 0;
	private int clockTimeH = 0;
	private int clockTemp = 0;
	private String clockTimeStringS="0";
	private String clockTimeStringM="0";
	private String clockTimeStringH="0";
	private Font myClockFont;
	int testTemp;
	int testHour, testMinute, testSecond;
	//
		
	public ArrayList<Person> drawPer = new ArrayList<Person>();
	public Elevator[] elev = new Elevator[4];

	public Person tmpPerson;

	public MyFrame(){
		mainFrame.setBounds(132, 5, 1043 + 10, 660 + 38);//실제 윈도우에서 이 frame의 상대적인 위치랑, 너비 높이
		mainFrame.addWindowListener(new WindowDestroyer());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(null);
		

		//mainFrame
		jp = new MyPanel(drawPer, elev);
		pink.setBounds(600, 330, 400, 230);
		pink.setBackground(new Color(252, 195, 100)); //오른쪽 아래 프레임
		//new Color(253, 219, 205)

		// 오른쪽 프레임
		Container contentPane = mainFrame.getContentPane();
		//contentPane.setBackground(new Color(250, 233, 100));	// 원본
		contentPane.setBackground(new Color(168, 78, 25));	// 수정		
		
		contentPane.add(jp);
		contentPane.add(TimeImage);
		contentPane.add(sun);	// 추가2
		//contentPane.setBackground(new Color(232, 100, 100)); // 번호표 배경색
		//contentPane.add(pink);

		//floor
		mainFrame.add(new FloorPanel());
		

		//door 
		doorPanel.setLayout(new GridLayout(12,1));
		ImageIcon doorIcon = new ImageIcon ("img/door1.png");
		//11floo
		for(int i=11; i>=10; i--) {
			floorBtn[i] = new JButton();
			floorBtn[i].setActionCommand(Integer.toString(i+1) +"");
			floorBtn[i].setIcon(doorIcon); //각 층마다 문짝 달기
			floorBtn[i].setBorder(null);
			floorBtn[i].setBackground(new Color(179, 235, 244));//11층12층 색깔
			doorPanel.add(floorBtn[i]);
			floorBtn[i].addActionListener(this);
		}
		//10floor
		for(int i=9; i>=8; i--) {
			floorBtn[i] = new JButton();
			floorBtn[i].setActionCommand(Integer.toString(i+1) +"");
			floorBtn[i].setIcon(doorIcon);
			floorBtn[i].setBorder(null);
			floorBtn[i].setBackground(new Color(179, 235, 244));
			doorPanel.add(floorBtn[i]);
			floorBtn[i].addActionListener(this);
		}
		for(int i=7; i>=6; i--) {
			floorBtn[i] = new JButton();
			floorBtn[i].setActionCommand(Integer.toString(i+1) +"");
			floorBtn[i].setIcon(doorIcon);
			floorBtn[i].setBorder(null);
			floorBtn[i].setBackground(new Color(179, 235, 244));
			doorPanel.add(floorBtn[i]);
			floorBtn[i].addActionListener(this);
		}
		for(int i=5; i>=4; i--) {
			floorBtn[i] = new JButton();
			floorBtn[i].setActionCommand(Integer.toString(i+1) +"");
			floorBtn[i].setIcon(doorIcon);
			floorBtn[i].setBorder(null);
			floorBtn[i].setBackground(new Color(179, 235, 244));
			doorPanel.add(floorBtn[i]);
			floorBtn[i].addActionListener(this);
		}
		for(int i=3; i>=2; i--) {
			floorBtn[i] = new JButton();
			floorBtn[i].setActionCommand(Integer.toString(i+1) +"");
			floorBtn[i].setIcon(doorIcon);
			floorBtn[i].setBorder(null);
			floorBtn[i].setBackground(new Color(179, 235, 244));
			doorPanel.add(floorBtn[i]);
			floorBtn[i].addActionListener(this);
		}
		for(int i=1; i>=0; i--) {
			floorBtn[i] = new JButton();
			floorBtn[i].setActionCommand(Integer.toString(i+1) +"");
			floorBtn[i].setIcon(doorIcon);
			floorBtn[i].setBorder(null);
			floorBtn[i].setBackground(new Color(179, 235, 244));
			doorPanel.add(floorBtn[i]);
			floorBtn[i].addActionListener(this);
		}

		doorPanel.setBounds(510, 0, 56, 660);
		contentPane.add(doorPanel);
		
		// 추가2
		changeBtn[0] = new JButton(busy);
		changeBtn[0].setActionCommand("mode1");
		changeBtn[0].setBackground(new Color(168, 78, 25));
		changeBtn[0].addActionListener(this);
		changeBtn[1] = new JButton(normal1);
		changeBtn[1].setActionCommand("mode2");
		changeBtn[1].setBackground(new Color(168, 78, 25));
		changeBtn[1].addActionListener(this);
		
		// 버튼정렬
		
		changeBtn[0].setHorizontalAlignment(SwingConstants.CENTER);
		changeBtn[0].setVerticalAlignment(SwingConstants.CENTER);
		changeBtn[1].setHorizontalAlignment(SwingConstants.CENTER);
		changeBtn[1].setVerticalAlignment(SwingConstants.CENTER);
		
		//버튼 디자인 정리
		
		changeBtn[0].setBorderPainted(false); 	
		changeBtn[0].setFocusPainted(false);	 
		changeBtn[0].setContentAreaFilled(false);	
		changeBtn[1].setBorderPainted(false); 	
		changeBtn[1].setFocusPainted(false);	 
		changeBtn[1].setContentAreaFilled(false);
		
		ChangeTable.setLayout(new GridLayout(1,2));
		ChangeTable.setBackground(new Color(168, 78, 25));
		ChangeTable.add(changeBtn[0]);
		ChangeTable.add(changeBtn[1]);
		ChangeTable.setBounds(590, 320,200, 70);
		contentPane.add(ChangeTable); 
		
		
		JLabel label = new JLabel(new ImageIcon("img/sky.png"));
		JLabel label2 = new JLabel(new ImageIcon("img/background2.png"));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setSize(350, 100);
		

		textPanel.setLayout(new GridLayout(1,1));
		textPanel.setBounds(780,560,260,80);
		textPanel.add(label);
		//textPanel.setFont(fo);
		//textPanel.setForeground(Color.black); // 위에 나오는 글자색!
		
		backgroundPanel.setBackground(new Color(168, 78, 25));
		backgroundPanel.setLayout(new GridLayout(1, 1));
		backgroundPanel.setBounds(560, 560, 230, 100);
		backgroundPanel.add(label2);
		
		contentPane.add(backgroundPanel);
		contentPane.add(textPanel);
		//mainFrame

		//thirdFrame 컨제스쳔 컨트롤 ^^
		speedBtn[0] = new JButton(morning);
		speedBtn[0].setActionCommand("mor");
		speedBtn[0].setBackground(new Color(168, 78, 25));
		speedBtn[0].addActionListener(this);
		speedBtn[1] = new JButton(lunch);
		speedBtn[1].setActionCommand("lun");
		speedBtn[1].setBackground(new Color(168, 78, 25));
		speedBtn[1].addActionListener(this);
		speedBtn[2] = new JButton(night);
		speedBtn[2].setActionCommand("nig");
		speedBtn[2].setBackground(new Color(168, 78, 25));
		speedBtn[2].addActionListener(this);
		speedBtn[3] = new JButton(normal2);
		speedBtn[3].setActionCommand("nor");
		speedBtn[3].setText("normal");
		speedBtn[3].setBackground(new Color(168, 78, 25));
		speedBtn[3].addActionListener(this);
		
		// 버튼정렬
		
		speedBtn[0].setHorizontalAlignment(SwingConstants.CENTER);
		speedBtn[0].setVerticalAlignment(SwingConstants.CENTER);
		speedBtn[1].setHorizontalAlignment(SwingConstants.CENTER);
		speedBtn[1].setVerticalAlignment(SwingConstants.CENTER);
		speedBtn[2].setHorizontalAlignment(SwingConstants.CENTER);
		speedBtn[2].setVerticalAlignment(SwingConstants.CENTER);
		speedBtn[3].setHorizontalAlignment(SwingConstants.CENTER);
		speedBtn[3].setVerticalAlignment(SwingConstants.CENTER);
				
		//버튼 디자인 정리
				
		speedBtn[0].setBorderPainted(false); 	
		speedBtn[0].setFocusPainted(false);	 
		speedBtn[0].setContentAreaFilled(false);	
		speedBtn[1].setBorderPainted(false); 	
		speedBtn[1].setFocusPainted(false);	 
		speedBtn[1].setContentAreaFilled(false);	
		speedBtn[2].setBorderPainted(false); 	
		speedBtn[2].setFocusPainted(false);	 
		speedBtn[2].setContentAreaFilled(false);	
		speedBtn[3].setBorderPainted(false); 	
		speedBtn[3].setFocusPainted(false);	 
		speedBtn[3].setContentAreaFilled(false);	
		
		
		speedTable.setLayout(new GridLayout(2,2));
		speedTable.setBackground(new Color(168, 78, 25));
		speedTable.add(speedBtn[0]);
		speedTable.add(speedBtn[1]);
		speedTable.add(speedBtn[2]);
		speedTable.add(speedBtn[3]);
		//speedTable.add(speedBtn[4]);
		//Container st = thirdFrame.getContentPane();
					
		speedTable.setBounds(580, 400, 220, 152);
		contentPane.add(speedTable);  

		//subFrame
		/*Container cp = subFrame.getContentPane();
      cp.setBackground(new Color(250, 233, 246));*/

		floorArea.setFont(fo);
		floorArea.setForeground(Color.black); // 위에 나오는 글자색!
		
		floorArea.setEditable(false);
		first.setEditable(false);
		second.setEditable(false);

		showFloor.setBounds(605, 13, 225, 95);
		showFloor.setBackground(new Color(0, 0, 0));
		//floorArea.setBackground(new Color(245, 238, 246));//층수 보여주는곳 
		showFloor.add(floorArea);
		contentPane.add(showFloor);

		showBest1.setBackground(new Color(0, 0, 0));
		showBest1.add(first);
		showBest1.add(second);
		first.setFont(fo2);
		second.setFont(fo2);
		first.setForeground(new Color(255, 0, 0));
		second.setForeground(new Color(0, 0, 255));

		showBest1.setBounds(605, 108, 160, 200);
		contentPane.add(showBest1);
		//Select 1, 2
		showBest2.setBackground(new Color(250, 233, 246));

		showBest2.setLayout(new GridLayout(2,1));

		recoBtn[0] = new JButton();
		recoBtn[0].setBorder(null);
		//recoBtn[0].setBackground(new Color(252, 236, 207));
		recoBtn[0].setBackground(new Color(255, 180, 180));
		showBest2.add(recoBtn[0]);
		recoBtn[0].addActionListener(this);

		recoBtn[1] = new JButton();
		recoBtn[1].setBorder(null);
		//recoBtn[1].setBackground(new Color(253, 219, 205));
		recoBtn[1].setBackground(new Color(144, 228, 255));
		
		showBest2.add(recoBtn[1]);
		recoBtn[1].addActionListener(this);

		showBest2.setBounds(778, 108, 50, 200);
		contentPane.add(showBest2);
		//
		selectButton.setLayout(new GridLayout(6,2));
		ImageIcon buttonIcon[] = new ImageIcon[12];
		for (int i = 0; i < 12; i++) { 
			buttonIcon[i] = new ImageIcon ("img/b" + Integer.toString(i+1) + ".png");
			selectBtn[i] = new JButton();
			selectBtn[i].setActionCommand("D" + Integer.toString(i+1));
			selectBtn[i].setIcon(buttonIcon[i]);
			selectBtn[i].setBorder(null);
			selectBtn[i].setBackground(new Color(0, 0, 0));
			selectButton.add(selectBtn[i]);
			selectBtn[i].addActionListener(this);
		}
		selectButton.setBounds(853, 13, 150, 298);
		contentPane.add(selectButton);

				
				Calendar now = Calendar.getInstance();
				testHour = now.get(Calendar.HOUR_OF_DAY);
				testMinute = now.get(Calendar.MINUTE);
				testSecond = now.get(Calendar.SECOND);
				testTemp = 3600 * testHour + 60 * testMinute + testSecond;
				clockInterval = 1;
				clockTick = testTemp * 10;
				clockTimeS = clockTick / 20;
				
				
				clockTimeStringS = new Double(clockTimeS).toString();
				myClockFont = new Font("italic", Font.ITALIC, 20);
				testLabel = new JLabel();
				testLabel.setFont(myClockFont);
				testLabel.setForeground(Color.WHITE);
				testLabel.setText("Timer");
				testButton = new JButton(fast);
				slowButton = new JButton(slow);
				//testButton2 = new JButton(start);
				
				 //버튼 정렬
				testButton.setHorizontalAlignment(SwingConstants.CENTER);
				testButton.setVerticalAlignment(SwingConstants.CENTER);
				slowButton.setHorizontalAlignment(SwingConstants.CENTER);
				slowButton.setVerticalAlignment(SwingConstants.CENTER);
				//testButton2.setHorizontalAlignment(SwingConstants.CENTER);
				//testButton2.setVerticalAlignment(SwingConstants.CENTER);
				

				//버튼 디자인 정리
				testButton.setBorderPainted(false); 	// 버튼 경계선 제거
				testButton.setFocusPainted(false);	 //포커스(선택했던 버튼 표시) 제거
				testButton.setContentAreaFilled(false);	//버튼영역 배경 제거
				slowButton.setBorderPainted(false); 	// 버튼 경계선 제거
				slowButton.setFocusPainted(false);	 //포커스(선택했던 버튼 표시) 제거
				slowButton.setContentAreaFilled(false);	//버튼영역 배경 제거
				//testButton2.setBorderPainted(false); 	
				//testButton2.setFocusPainted(false);	 
				//testButton2.setContentAreaFilled(false);	

				test = new Timer(SEC, new ActionListener(){
			         @SuppressWarnings("static-access")
			         public void actionPerformed(ActionEvent e) {
			            // TODO Auto-generated method stub
			            clockTick += clockInterval;
			            clockTimeS = clockTick / 10;

			            clockTimeM = clockTimeS /60;
			            clockTimeH = clockTimeS /3600;
			            
			            clockTimeStringS = new Integer(clockTimeS % 60).toString();
			            clockTimeStringM = new Integer(clockTimeM % 60).toString();
			            clockTimeStringH = new Integer(clockTimeH % 24).toString();
			            Picture.runa(clockTimeH % 24, clockTimeM % 60);
			            //((TimeImage) TimeImage).runa(clockTimeH % 24);
			              // ((TimeImage) TimeImage).moving(clockTimeH % 24);
			       //     move(clockTimeH % 24);
			            /////////////////////////////////////////////////////
			               if(clockTimeH % 24 < 10 && clockTimeH % 24 >= 0)
			                  clockTimeStringH = "0" + clockTimeStringH;
			               if(clockTimeS % 60 < 10 && clockTimeS % 60 >= 0)
			                  clockTimeStringS ="0" + clockTimeStringS;
			               if(clockTimeM % 60 < 10 && clockTimeM % 60 >= 0)
			                  clockTimeStringM ="0" + clockTimeStringM;
			              
			                  testLabel.setText(""+clockTimeStringH+":"+  clockTimeStringM+":"+clockTimeStringS + "");
			                  /*
			                  if(clockTimeStringH.equals("12") && db_lock1 ==0)
			                  {
			                	
			              		
			                	  
			                	  updateTime(clockTimeStringH);
			                	  db_lock1 = 1;
			                	  
			                	  db_lock5 = 0;
			                  }
			                  
			                  else if(clockTimeStringH.equals("17") && db_lock2 ==0)
			                  {
			                	  
				              		
			                	  updateTime(clockTimeStringH);
			                	  db_lock2 = 1;
			                  }
			                  
			                  else if(clockTimeStringH.equals("23") && db_lock3 ==0)
			                  {
			                	 
			                	  
			                	  updateTime(clockTimeStringH);
			                	  db_lock3 = 1;
			                  }
			                  
			             
			                  else if(clockTimeStringH.equals("00") && db_lock5 ==0)
			                  {
			      
			                	  
			                	  updateTime(clockTimeStringH);
			                	  db_lock5 = 1;
			                	  
			                	  db_lock1 = 0;
			                	  db_lock2 = 0;
			                	  db_lock3 = 0;
			                  }*/
			         }
			      });
	
	testPanel.setLayout(new GridLayout(2,2));
	testPanel.setBackground(new Color(168, 78, 25));
	testPanel.setBounds(820, 330, 200, 120);
	test.start();
	//testPanel.add(testButton2);
	testPanel.add(slowButton);
	testPanel.add(testButton);
	testPanel.add(testLabel);
	contentPane.add(testPanel);
	slowButton.addActionListener(this);
	slowButton.setActionCommand("slow");
	testButton.addActionListener(this);
	testButton.setActionCommand("test");
				
		selectDisable(false);
		bestDisable(false);
		mainFrame.setVisible(true);
	}
	public static void main(String[] args) {
		new MyFrame();
	}
/*	
	public void paintComponent(Graphics g){
		g.setColor(new Color(250, 233, 246)); // 왼쪽 끝에 그림같다
			g.drawImage(img[0], 761,421, (ImageObserver) this);
	}
*/	
	public void updateTime(String hour)
	{
		
				
				// 媛�媛곸쓽 �붾퉬 insert��max person count��痢��섎� 援ы빐��蹂�닔濡���옣
				
				if(hour.equals("12"))
				{
					Database db = new Database();
					db.DBInsertion(12, person_arr);
					System.out.println("Max Floor : " + db.getMaxFloor());
					System.out.println("Max Person : " + db.getMaxPerson());
					
					morning_setting = db.getMaxFloor();
					
					for(int i=0; i<person_arr.length; i++)
					{
						person_arr[i] = 0;
					}
				}
				if(hour.equals("17"))
				{
					Database db = new Database();
					db.DBInsertion(17, person_arr);
					System.out.println("Max Floor : " + db.getMaxFloor());
					System.out.println("Max Person : " + db.getMaxPerson());
					
					lunch_setting = db.getMaxFloor();
					
					for(int i=0; i<person_arr.length; i++)
					{
						person_arr[i] = 0;
					}
				}
				
				if(hour.equals("23"))
				{
					Database db = new Database();
					db.DBInsertion(23, person_arr);
					System.out.println("Max Floor : " + db.getMaxFloor());
					System.out.println("Max Person : " + db.getMaxPerson());
					
					night1_setting = db.getMaxFloor();
					
					for(int i=0; i<person_arr.length; i++)
					{
						person_arr[i] = 0;
					}
				}
					
				//db clear
				if(hour.equals("00"))
				{
					Database db = new Database();
					db.DBDeletion();
					
				}
				
				//Thread sleeping for 1 sec


	}


	public void selectDisable2(){
	      for(int i = 0; i < 12; i++) 
	            selectBtn[i].setEnabled(false);
	}
	
	public void selectDisable(boolean a)
	   {
	      if(check == -1)
	      {
	         floorArea.setFont(congetsionFont);
	         floorArea.setText("\n   You can't go between 2F ~ 4F.");
	         for(int i = 0; i < 12; i++)
	         {
	            if(i == now - 1)
	            {
	               selectBtn[i].setEnabled(false);
	               now = -1;
	            }
	         /*   if(i >= 1 && i <= 3)
	               selectBtn[i].setEnabled(false);*/
	            else
	               selectBtn[i].setEnabled(true);
	         }
	      }
	      else
	      {
	         for(int i = 0; i < 12; i++) 
	         {
	            if(i == now-1) {
	               selectBtn[i].setEnabled(false);
	               now = -1;
	            }
	            else
	               selectBtn[i].setEnabled(a);
	         }
	      }
	   }
	
	public void floorDisable(boolean a) {
		for(int i = 0; i < 12; i++) {
			floorBtn[i].setEnabled(a);
		}
	}
	public void bestDisable(boolean a) {
		for(int i = 0; i < 2; i++) {
			recoBtn[i].setEnabled(a);
		}
	}
	/*
	 * This method suggest the best, and second best elevator.
	 * when the user push elevator destination floor button, 
	 * we can calculate primary idle elevator position and secondary idle elevator position.
	 * we suggest the elevator number using this position.
	 */
	public void suggestionEle(Person P, int except,int count)
	{
		int current=P.getY();
		int minY=Integer.MAX_VALUE;
		int minEle=0,tem=0;
		int all=0;

		for(int i=0; i<4; i++)  
		{
			if (i == except)
				continue;
			else if (except != -1 && elev[except].getLeftWeight() == 0  &&  elev[i].getLeftWeight() == 0){
				all=0;
				for(int k=0; k<4; k++){
					if(elev[k].getLeftWeight() != 0)
						all=1;
				}
				if(all == 1)  //if best elevator is full, we can recommend the second best elevator. 
				continue;
			
			}

			if(P.getUpDown()==elev[i].getUpDown())
			{
				if(elev[i].getUpDown()==1)//UP
				{
					if(current-elev[i].getY() <= 0)//if elevator exist same floor or under floor.
					{
						tem = ab(current-elev[i].getY());
						System.out.println("tem: " + tem);
						if( minY >= tem){
							if(minY == tem){
								if(elev[minEle].getLeftWeight() > elev[i].getLeftWeight())
									continue;
							}
							minY=tem;
							minEle=i;
						}
					}
					else            //if elevator exist upper floor.
					{
						tem= ab(elev[i].maxFloorY(elev[i])-elev[i].getY()) + ab(elev[i].maxFloorY(elev[i])-current);
						if( minY >= tem){
							if(minY == tem){
								if(elev[minEle].getLeftWeight() > elev[i].getLeftWeight())
									continue;
							}
							minY=tem;
							minEle=i;
						}
					}
				}
				else if(elev[i].getUpDown()==-1)   //DOWN
				{
					if(current-elev[i].getY() <= 0)   //if elevator exist same floor or under floor.
					{
						tem = ab(elev[i].getY()-current);
						if( minY >= tem)
						{
							if(minY == tem){
								if(elev[minEle].getLeftWeight() > elev[i].getLeftWeight())
									continue;
							}
							minY=tem;
							minEle=i;
						}
					}
					else             //if elevator exist upper floor.
					{
						tem= ab(elev[i].getY()-elev[i].minFloorY(elev[i])) + ab(current-elev[i].minFloorY(elev[i]));
						if( minY >= tem){
							if(minY == tem){
								if(elev[minEle].getLeftWeight() > elev[i].getLeftWeight())
									continue;
							}
							minY=tem;
							minEle=i;
						}
					}
				}
			}
			else if (elev[i].getUpDown() != 0) 
			{
				if(elev[i].getUpDown()==1)//elevator->UP request->DOWN
				{
					tem = ab(elev[i].maxFloorY(elev[i])-elev[i].getY()) + ab(elev[i].maxFloorY(elev[i])-current); // 자기목표층갓다가 걔 위치감
					if( minY >= tem){
						if(minY == tem){
							if(elev[minEle].getLeftWeight() > elev[i].getLeftWeight())
								continue;
						}
						minY=tem;
						minEle=i;
					}
				}
				else //elevator->DOWN request->UP
				{

					tem = ab(elev[i].minFloorY(elev[i])-elev[i].getY()) + ab(elev[i].minFloorY(elev[i])-current); // 자기목표층갓다가 걔 위치감
					if( minY >= tem){
						if(minY == tem){
							if(elev[minEle].getLeftWeight() > elev[i].getLeftWeight())
								continue;
						}
						minY=tem;
						minEle=i;
					}
				}
			}
			else if (elev[i].getUpDown()== 0)// elevator stop.
			{
				tem = ab(elev[i].getY()-current);
				if( minY >= tem)
				{
					if(minY == tem){
						if(elev[minEle].getLeftWeight() > elev[i].getLeftWeight())
							continue;
					}
					minY=tem;
					minEle=i;
				}
			}
		}
		
		recoBtn[count].setActionCommand(Integer.toString(minEle)+"D");
		//show the primary elevator info on the recommend board.
		if(count==0){
			first.setText("\n     Elevator "+(minEle+1)+ "\n  "
					+ "   "+ elev[minEle].getNowFloor()+" floor");
			if(elev[minEle].getLeftWeight()<68)
				first.append("\n     "+"full");
			else
				first.append("\n     "+elev[minEle].getLeftWeight()/68+" persons ");
			d=minEle;
		}
		//show the secondary elevator info on the recommend board.
		else if(count==1){
			second.setText("\n     Elevator "+(minEle+1)+ "\n    "
					+ " "+ elev[minEle].getNowFloor()+" floor");
			if(elev[minEle].getLeftWeight()<68)
				second.append("\n     "+"full");
			else
				second.append("\n     "+elev[minEle].getLeftWeight()/68+" persons ");
		}
		//return minEle;
		if(count==0)
	suggestionEle(P,minEle,++count);
	}
	
	public int ab(int x){
		if (x<0)
			return (-1)*x;
		return x;
	}
	
	public int checkTime(){
	      int result, hh, mm;
	      String[] time;
	      String[] time2;
	      long now = System.currentTimeMillis();
	      Date date = new Date(now);
	      SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
	      String strNow = sdfNow.format(date); 
	      
	      
	      time = strNow.split(" ");  //time[0]=date   time[1]=time
	      time2 = time[1].split(":");  //time2[0]=hour  time2[1]=minute  time2[2]=second
	      
	      hh = Integer.parseInt(time2[0]);
	      mm = Integer.parseInt(time2[1]);
	   
	      if(hh == 3 || hh == 11 || hh == 18)
	         if(mm >= 00 || mm <= 59)
	            return -1;
	      return 1;
	   }
	/*
	 *This method is action method. 
	 *when the push the buttons on the main frame.
	 *each button act the different action. 
	 *if you push the start floor button, person create on the floor and wait until push 
	 *the destination floor button.
	 *if you push the destination floor button, destination floor info show on the board.
	 *and recommend the two elevators.
	 *if you push the recommend button, each elevator move to user and move to destiantion floor.
	 *if you push the morning,lunch,dinner,normal button, elevators set on the appropriate floor and fixed.
	 *if you push the  mode button(busy, normal), elevator mode change.      
	 */
	public void actionPerformed(ActionEvent e) {
		   if (e.getActionCommand().equals("1"))
		      {
		         now = 1;
		         check = checkTime();
		         floorArea.setText("\n                  	Floor 1");
		         tmpPerson = new Person(personCount, 1, 1, 607);
		         drawPer.add(tmpPerson);
		         floorDisable(false);
		         selectDisable(true);
		      }
		      else if (e.getActionCommand().equals("2"))
		      {
		         now = 2;
		         check = checkTime();
		         if(check == -1)   //congestion time
		         {
		            floorArea.setFont(congetsionFont);
		            floorArea.setText("\n        It's congestion time! \n        You have to walk !");
		         }
		         else
		         {
		            floorArea.setText("\n           	 Floor 2");
		            tmpPerson = new Person(personCount, 2, 2, 552) ;
		            drawPer.add(tmpPerson);
		            floorDisable(false);
		            selectDisable(true);
		         }
		      }
		      else if (e.getActionCommand().equals("3"))
		      {
		         now = 3;
		         check = checkTime();
		         if(check == -1)   //congestion time
		         {
		            floorArea.setFont(congetsionFont);
		            floorArea.setText("\n        It's congestion time! \n        You have to walk !");
		         }
		         else
		         {
		            floorArea.setText("\n            	Floor 3");
		            tmpPerson = new Person(personCount,3,3,497);
		            drawPer.add(tmpPerson);
		            floorDisable(false);
		            selectDisable(true);
		         }

		      }
		      else if (e.getActionCommand().equals("4"))
		      {
		         now = 4;
		         check = checkTime();
		         if(check == -1)   //congestion time
		         {
		            floorArea.setFont(congetsionFont);
		            floorArea.setText("\n        It's congestion time! \n        You have to walk !");
		         }
		         else
		         {
		            tmpPerson = new Person(personCount,4,4,442);
		            floorArea.setText("\n          	  Floor 4");
		            drawPer.add(tmpPerson);
		            floorDisable(false);
		            selectDisable(true);
		         }

		      }
		      else if (e.getActionCommand().equals("5"))
		      {
		         now = 5;
		         check = checkTime();
		         tmpPerson = new Person(personCount,5,5,387);
		         floorArea.setText("\n           	 Floor 5");
		         drawPer.add(tmpPerson);
		         floorDisable(false);
		         selectDisable(true);
		      }
		      else if (e.getActionCommand().equals("6"))
		      {
		         now = 6;
		         check = checkTime();
		         tmpPerson = new Person(personCount,6,6,332);
		         floorArea.setText("\n          	  Floor 6");
		         drawPer.add(tmpPerson);
		         floorDisable(false);
		         selectDisable(true);
		      }
		      else if (e.getActionCommand().equals("7"))
		      {
		         now = 7;
		         check = checkTime();
		         tmpPerson = new Person(personCount,7,7,277);
		         floorArea.setText("\n          	  Floor 7");
		         drawPer.add(tmpPerson);
		         floorDisable(false);
		         selectDisable(true);
		      }
		      else if (e.getActionCommand().equals("8"))
		      {
		         now = 8;
		         check = checkTime();
		         tmpPerson = new Person(personCount,8,8,222);
		         floorArea.setText("\n         	   Floor 8");
		         drawPer.add(tmpPerson);
		         floorDisable(false);
		         selectDisable(true);
		      }
		      else if (e.getActionCommand().equals("9"))
		      {
		         now = 9;
		         check = checkTime();
		         tmpPerson = new Person(personCount,9,9,167);
		         floorArea.setText("\n          	  Floor 9");
		         drawPer.add(tmpPerson);
		         floorDisable(false);
		         selectDisable(true);
		      }
		      else if (e.getActionCommand().equals("10"))
		      {
		         now = 10;
		         check = checkTime();
		         tmpPerson = new Person(personCount,10,10,112);
		         floorArea.setText("\n         	   Floor 10");
		         drawPer.add(tmpPerson);
		         floorDisable(false);
		         selectDisable(true);
		      }
		      else if (e.getActionCommand().equals("11"))
		      {
		         now = 11;
		         check = checkTime();
		         tmpPerson = new Person(personCount,11,11,57);
		         floorArea.setText("\n          	  Floor 11");
		         drawPer.add(tmpPerson);
		         floorDisable(false);
		         selectDisable(true);
		      }
		      else if (e.getActionCommand().equals("12"))
		      {
		         now = 12;
		         check = checkTime();
		         tmpPerson = new Person(personCount,12,12,2);
		         floorArea.setText("\n          	  Floor 12");
		         drawPer.add(tmpPerson);
		         floorDisable(false);
		         selectDisable(true);
		      }
	      else if(e.getActionCommand().equals("test")){
	          if(test.isRunning())
	          {
	             test.stop();
	             clockInterval = clockInterval * 7;
	             test.start();
	          }
	       }
	      else if(e.getActionCommand().equals("slow")){
				if(test.isRunning())
				{
					test.stop();
					if( clockInterval <=1 )
						clockInterval =1;
					else
						clockInterval =clockInterval / 7;

					test.start();
				}
	       }	 
		else if (e.getActionCommand().charAt(0)=='D'&&auto ==1){
			tmpPerson.setDestFloor(Integer.parseInt(e.getActionCommand().substring(1)));
		
			if(tmpPerson.getDestFloor() > tmpPerson.getNowFloor())
				tmpPerson.setUpDown(1);
			else
				tmpPerson.setUpDown(-1);
			suggestionEle(tmpPerson,-1,0); // 우선순위 엘베 선택해주기
			selectDisable(false);
			bestDisable(true);
			System.out.println(e.getActionCommand().charAt(0)+"\n\n\n\n");
			System.out.println(e.getActionCommand().charAt(1)+"\n");
	
			
			//우선순위 엘베 움직이게 만들기
			
			if(elev[d].getLeftWeight() >= (68*tmpPerson.getParty()))
			{
				tmpPerson.setRide(true);
				elev[d].setLeftWeight(elev[d].getLeftWeight()-68*tmpPerson.getParty());
			}
			tmpPerson.setSelectedEle(d);
			tmpPerson.setDestX(wait_location[d]);
			elev[d].waitingListAt(((607 - tmpPerson.getY()) / 55) + 1).add(tmpPerson);
			personCount++;

			floorDisable(true); // 지속적으로 사람 생성할수있게 하는것
			//time =1;
			Elevator.findNext(elev[d],d);
			System.out.println(e.getActionCommand().charAt(1)+"\n");
			
			System.out.println(e.getActionCommand().charAt(1)+"HHHHH\n");
		}
		else if (e.getActionCommand().charAt(0)=='D'&&auto==0)
		{
			tmpPerson.setDestFloor(Integer.parseInt(e.getActionCommand().substring(1)));
			if(checkTime() == -1 && (tmpPerson.getDestFloor()== 2 || tmpPerson.getDestFloor()==3|| tmpPerson.getDestFloor()==4))
			{
				drawPer.remove(tmpPerson);
				floorDisable(true);
	             selectDisable2();
	             return;
	          }
	          if(tmpPerson.getDestFloor() > tmpPerson.getNowFloor())
	             tmpPerson.setUpDown(1);
	          else
	             tmpPerson.setUpDown(-1);
	          suggestionEle(tmpPerson,-1,0);
	          selectDisable(false);
	          bestDisable(true);
	       }
	     else if (e.getActionCommand().charAt(1)=='D'&&auto==0)
	     {
	         d=Integer.parseInt(""+e.getActionCommand().charAt(0));      
	         if(elev[d].getLeftWeight() >= (68 * tmpPerson.getParty()))
	         {
	            tmpPerson.setRide(true);
	            elev[d].setLeftWeight(elev[d].getLeftWeight()-68*tmpPerson.getParty());
	         }
	         tmpPerson.setSelectedEle(d);
	         tmpPerson.setDestX(wait_location[d]);
	         elev[d].waitingListAt(((607 - tmpPerson.getY()) / 55) + 1).add(tmpPerson);
	         personCount++;
	         first.setText("");
	         second.setText("");
	         bestDisable(false);
	         floorDisable(true);
	         Elevator.findNext(elev[d],d);
	      }
		else if (e.getActionCommand().charAt(1)=='D'){
			int d=3;
			Elevator.goTo(elev[d],1);// 엘베 이니셜 위치로 돌아오게 만들기.
			int a=2;
			Elevator.goTo(elev[a],1);
		//d=Integer.parseInt(""+e.getActionCommand().charAt(0)); 
		System.out.println(e.getActionCommand().charAt(0)+"\n\n\n\n");
		System.out.println(e.getActionCommand().charAt(1)+"\n\n\n");    
	//	System.out.println(e.getActionCommand().charAt(0)+"\n\n\n\n\n\n\n\n");
	
}
		else if (e.getActionCommand().equals("mode1")) {
				auto =1;
				floorArea.setText("\n                      BUSY MODE");
		
		}
		else if (e.getActionCommand().equals("mode2")) {
			auto =0;
			floorArea.setText("\n                    NORMAL MODE");
	
		}
		else if (e.getActionCommand().charAt(0)=='m') {
			time =1;
		
			
			Elevator.goTo(elev[e1],1);// 엘베 이니셜 위치로 돌아오게 만들기.
			
			Elevator.goTo(elev[e2],1);
			
			Elevator.goTo(elev[e3],1);
			
			Elevator.goTo(elev[e4],1);
			
		}
		else if (e.getActionCommand().equals("lun")) {
			time=2;
			
			
			Elevator.goTo(elev[e1],1);// 엘베 이니셜 위치로 돌아오게 만들기.
		
			Elevator.goTo(elev[e2],4);
		
			Elevator.goTo(elev[e3],8);
			
			Elevator.goTo(elev[e4],12);
			
		}
		else if (e.getActionCommand().equals("nig")) {
			time =3;
		
			Elevator.goTo(elev[e1],8);// 엘베 이니셜 위치로 돌아오게 만들기.
		
			Elevator.goTo(elev[e2],8);
		
			Elevator.goTo(elev[e3],8);
			
			Elevator.goTo(elev[e4],8);
		}
		else if (e.getActionCommand().equals("nor")) {
			time= 0;
			System.out.println(e.getActionCommand().charAt(0)+"\n\n\n\n");
			System.out.println(e.getActionCommand().charAt(1)+"\n\n\n");
	
		}
		else {
			System.out.println("Error in button interface.");
		}
	

	}
}