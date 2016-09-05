import java.awt.Image;
import java.awt.Toolkit;


public class Person {
	//GUI 관련
	private Toolkit tk;
	private Image img[] = new Image[2];
	private int x = 400;
	private int y;
	private int destX = 390;
	//그 외
	private int Id;
	private int weight;
	private int destFloor; // this means destination floor.
	private int nowFloor; // this means know floor.
	private int party = 0; // this means the number of parties(companies) behind him.
	private int upDown; // this variable represents that this person's state ( 1 : up or -1 : down )
	private int selectedEle; // this variable point the elevator selected by this person.
	private boolean isIncluded = false;
	private boolean isArrived = false;
	private boolean isUpdate = false;
	private boolean ride = false;

	public Person(int Id, int destF, int nowF, int ty) {
		//GUI 변수
		y = ty;
		tk = Toolkit.getDefaultToolkit();

		int count = (int)(Math.random()*1000)%9 + 1; // 
		if(count%2 == 0) {
			count++;
		}
		img[0] = tk.createImage("img/pa" + count + ".png");
		img[1] = tk.createImage("img/pa" + (count+1)+ ".png");

		//나머지 변수();
		this.party=this.getCountPerson(count);
		this.Id = Id;
		this.weight = 68;
		this.destFloor = destF;
		this.nowFloor = nowF;
	}
	public int getCountPerson(int tem)
	{
		if(tem==7)
			return 2;
		else if(tem ==9)
			return 4;
		else
			return 1;
		/*else if(tem==)
			return 2;
			*/
		
		
	}
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getDestFloor() {
		return destFloor;
	}

	public void setDestFloor(int destFloor) {
		this.destFloor = destFloor;
	}

	public int getNowFloor() {
		return nowFloor;
	}

	public void setNowFloor(int nowFloor) {
		this.nowFloor = nowFloor;
	}

	public int getParty() {
		return party;
	}
	public int getUpDown() {
		return upDown;
	}

	public void setUpDown(int upDown) {
		this.upDown = upDown;
	}

	public int getSelectedEle() {
		return selectedEle;
	}

	public void setSelectedEle(int selectedEle) {
		this.selectedEle = selectedEle;
	}
	
	public boolean isIncluded() {
		return isIncluded;
	}

	public void setIncluded(boolean isIncluded) {
		this.isIncluded = isIncluded;
	}

	public Toolkit getTk() {
		return tk;
	}

	public void setTk(Toolkit tk) {
		this.tk = tk;
	}

	public Image getImg(int i) {
		return img[i];
	}

	public void setImg(Image[] img) {
		this.img = img;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDestX() {
		return destX;
	}

	public void setDestX(int destX) {
		this.destX = destX;
	}

	public boolean isArrived() {
		return isArrived;
	}

	public void setArrived(boolean isArrived) {
		this.isArrived = isArrived;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	public boolean getRide() {
		return ride;
	}
	public void setRide(boolean ride) {
		this.ride = ride;
	}
	
}
