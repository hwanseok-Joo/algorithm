
import java.util.ArrayList;
public class T extends Thread {
	static int speed = 30;
	ArrayList<Person> per;
	Elevator[] elev;
	MyPanel mp;
	public T(ArrayList<Person> per, Elevator[] elev, MyPanel mp){
		this.per = per;
		this.elev = elev;
		this.mp = mp;
	}
	/*
	 * This run method use for elevator thread.
	 * if one elevator selected, move through this method. 
	 * through this method, we can check the current elevator info. 
	 */
	//class -> main  thread->run
	public void run(){
		while(true){
			try {
				Thread.sleep(speed);

				for (int i = 0; i < per.size(); i++) {
					if((per.get(i).getDestX() == per.get(i).getX()) && (per.get(i).isIncluded() == false) && per.get(i).getX() != 390) {
						per.get(i).setArrived(true);
						per.get(i).setIncluded(true);
					}
					if(per.get(i).getDestX() < per.get(i).getX())
						per.get(i).setX(per.get(i).getX() - 1);
					if(per.get(i).getX() ==  -50)
						per.remove(i);
				}
				for(int i=0; i<elev.length; i++) {
					if(elev[i].isStop()) {
						elev[i].setStopCount(elev[i].getStopCount() + 1);
						if(elev[i].getStopCount() == 60) {
							elev[i].setStop(false);
							Elevator.findNext(elev[i],MyFrame.d); // 이부분에서 100은 쓸데없는 값이다.
							elev[i].setStopCount(0);
						}
						continue;
					}
					if(elev[i].getY() % 55 == 2 ) { // 측정위치를 지날 때 마다 현재층정보만 업데이트 해준다.
						if(elev[i].isWait() == true) {
							if(elev[i].waitingListOfUpdown(elev[i].getNowFloor(), elev[i].getUpDown()) == Elevator.getRide(elev[i], elev[i].getNowFloor(), per)) {
								elev[i].setWait(false);
								Elevator.ride(elev[i],elev[i].getNowFloor(), per);
								Elevator.findNext(elev[i],MyFrame.d); // 이부분에서 100은 쓸데없는 값이다.
								continue;
							}
						}
						if(elev[i].getUpDown() > 0) {
							if(elev[i].isUpdate() == false) {
								elev[i].setNowFloor(12 - (elev[i].getY() - 2) / 55 );
								System.out.println("$$$$$$$$$$층도착 " + i + "번 엘베가 현재 " + elev[i].getNowFloor() + "층 ----- " + elev[i].getUpDown());
								if(Elevator.iswillRide(elev[i], elev[i].getNowFloor(), per)) 
									elev[i].setWait(true);
								elev[i].setUpdate(true);			
								if(elev[i].getDestY() == elev[i].getY()) {
									elev[i].setStop(true);
									Elevator.getoff(elev[i],elev[i].getNowFloor(), per);
									if(elev[i].waitingListOfUpdown(elev[i].getNowFloor(), elev[i].getUpDown()) == Elevator.getRide(elev[i], elev[i].getNowFloor(), per)) {
										Elevator.ride(elev[i],elev[i].getNowFloor(), per);
									}
									else {
										elev[i].setWait(true);
									}
									
									
								}
							}
						}
						else if(elev[i].getUpDown() < 0) {
							if(elev[i].isUpdate() == false) {
								elev[i].setNowFloor(12 - (elev[i].getY() - 2) / 55 );
								System.out.println("$$$$$$$$$$층도착 " + i + "번 엘베가 현재 " + elev[i].getNowFloor());
								if(Elevator.iswillRide(elev[i], elev[i].getNowFloor(), per)) 
									elev[i].setWait(true);
								elev[i].setUpdate(true);
								if(elev[i].getDestY() == elev[i].getY()) {
									elev[i].setStop(true);
									if(elev[i].waitingListOfUpdown(elev[i].getNowFloor(), elev[i].getUpDown()) == Elevator.getRide(elev[i], elev[i].getNowFloor(), per)) {
										Elevator.ride(elev[i],elev[i].getNowFloor(), per);
									}
									else {
										elev[i].setWait(true);
									}
									Elevator.getoff(elev[i],elev[i].getNowFloor(), per);
								}
							}
						}
					}
					if(elev[i].isRealStop() == true) {
						Elevator.findNext(elev[i],MyFrame.d);
					}
					if(elev[i].getDestY() < elev[i].getY()){
						elev[i].setUpdate(false);
						elev[i].setY(elev[i].getY() - 1);
					}
					else if(elev[i].getDestY() > elev[i].getY()){
						elev[i].setUpdate(false);
						elev[i].setY(elev[i].getY() + 1);
					}
				}
				mp.repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 

		}
	}
}