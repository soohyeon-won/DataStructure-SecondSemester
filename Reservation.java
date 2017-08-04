import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * 비행기를 예약하고 취소, 예약 내용을 관리하는 프로그램
 * @author 원수현
 *
 */
public class Reservation {

	Scanner in = new Scanner(System.in);
	final int row = 5;
	final int col = 4;
	String seat[][] = new String[row][col]; // 좌석을 저장할 2차원배열
	List client = new List();				// 예약한 고객의 정보를 저장할 리스트
	String[] splitSeatNum;					// 입력 받은 좌석번호를 두개로 쪼개어 저장할 배열
	ArrayList<String> alpaList = new ArrayList<>();	// 고객의 이름을 알파벳 순으로 저장할 ArrayList

	// 빈생성자
	Reservation() {

	}

	// 배열을 출력하는 메소드
	public void layout() {
		int count = 1;

		System.out.println();
		System.out.println("Seat Layout");
		System.out.println("-----------");

		for (int i = 0; i < row; i++) {
			System.out.print(count + " ");
			for (int j = 0; j < col; j++) {
				if (this.seat[i][j] == null) {
					if (j == 0)
						System.out.print("A");
					else if (j == 1)
						System.out.print("B");
					else if (j == 2)
						System.out.print("C");
					else
						System.out.print("D");
				} else if (this.seat[i][j].equalsIgnoreCase("*"))
					this.seat[i][j] = "*";

				// 기본적으로 null 값이 배열에 들어있어서 null 이면 출력 안함
				if (seat[i][j] != null) {
					System.out.print(this.seat[i][j]);
					System.out.print(" ");
				} else
					System.out.print(" ");
			}
			System.out.println();
			count++;
		}
	}


	// 비행기를 예약하는 메소드
	public void reserve() {

		layout(); // 좌석을 보여줌
		System.out.println("Enter a customer name and desired seat number: ");

		String name = in.next();
		String seatNum = in.next();

		this.splitSeatNum = seatNum.split(""); // 콘솔에서 입력받은 것을 나눔.
		int number = Integer.parseInt(splitSeatNum[0]) - 1; // 2A의 2 배열에 저장하기 위해 -1
		int alpa = changeAlpa(splitSeatNum[1]); // 2A의 A부분 changeAlpa메소드가 A를 0으로 바꿔준다.

		// 유효한 좌석번호 일 때만 실행.
		if(number<5 && number>=0 && alpa>=0 && alpa<=3){
			// 고객이 입력한 좌석이 이미 예약 되어있을 때
			if(this.seat[number][alpa]=="*"){
				System.out.println("This seat is not available!");
			}
			//예약이 가능 할 때.
			else{
				alpaList.add(name + " " +seatNum);		//arrayList에 저장.
				Collections.sort(alpaList, String.CASE_INSENSITIVE_ORDER);	//알파벳 순서로정렬.
				this.seat[number][alpa] = "*";		
				//고객의 정보를 연결리스트에 저장. (임시)
				client.insertLast(name, seatNum);
			}
		}
		else{
			System.out.println("This seat is not available!");
		}	
	}

	// 좌석의 알파벳(열)을 받아서 숫자로 반환하는 메소드
	public int changeAlpa(String seatStr) {
		int change;
		// 대소문자 전부 체크
		if (seatStr.equals("A") || seatStr.equals("a"))
			change = 0;
		else if (seatStr.equals("B") || seatStr.equals("b"))
			change = 1;
		else if (seatStr.equals("C") || seatStr.equals("c"))
			change = 2;
		else
			change = 3;
		return change;
	}

	// 고객의 이름을 받아 예약을 취소하는 메소드
	public void cancel() {
		System.out.println("Enter a customer name");
		String name = in.next();
		String seatNum = client.getSeatNum(name);
		// 고객의 이름으로 예약된 좌석이 없을 때.
		if (seatNum == null) {
			System.out.println("No such customer: " + name);
		} 
		// 고객 이름 발견 -> 예약 취소
		else {
			// 좌석 배열을 원래대로 되돌린다.
			this.splitSeatNum = seatNum.split(""); // 콘솔에서 입력받은 것을 나눔.
			int number = Integer.parseInt(splitSeatNum[0]) - 1; // 2A의 2 배열에 저장하기 위해 -1
			int alpa = changeAlpa(splitSeatNum[1]); // 2A의 A부분 changeAlpa가 A를 0으로 바꿔준다.
			if (alpa == 0)
				this.seat[number][alpa] = "A";
			else if (alpa == 1)
				this.seat[number][alpa] = "B";
			else if (alpa == 2)
				this.seat[number][alpa] = "C";
			else
				this.seat[number][alpa] = "D";
			System.out.println("Customer name:" + name + ", reservation canceled");
		}
		//연결리스트와 arrayList에서 모두 삭제.
		client.remove(name);
		alpaList.remove(name + " " + seatNum);

	}

	// 예약된 좌석을 확인시켜주는 메소드
	public void find() {
		System.out.println("Enter a cutomer name: ");
		String name = in.next();
		System.out.println("Seat number: ");
		client.customerFind(name);
	}

	// 모든 예약 정보를 출력해주는 메소드
	public void print() {

		String[] name;
		String str;

		/** 연결리스트에 이름 순으로 저장하는 부분 **/
		client.reset();	//head=null을 해줌.(reserve에서 임시로 저장한부분 초기화)
		for (int i = 0; i < alpaList.size(); i++) {
			str = alpaList.get(i);	//arrayList에 있는 문자열을 가져옴.
			name = str.split(" ");	//문자열을 쪼개어서 name 배열에 저장.
			client.insertLast(name[0], name[1]);	//이름순으로 정렬되어 리스트에 저장된다.
		}

		layout();
		int availSeat = client.available();	//예약 가능한 좌석.
		System.out.println("Reservation information");
		System.out.println("Name   Seat number");
		System.out.println("----   -----------");
		client.printEvery();
		System.out.println("Available number of seats: " + availSeat);
	}

	//프로그램을 종료하는 메소드.
	public boolean quit(boolean done){
		done = false;
		System.out.println("End of commands!");
		return done;
	}
}