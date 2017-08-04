import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * ����⸦ �����ϰ� ���, ���� ������ �����ϴ� ���α׷�
 * @author ������
 *
 */
public class Reservation {

	Scanner in = new Scanner(System.in);
	final int row = 5;
	final int col = 4;
	String seat[][] = new String[row][col]; // �¼��� ������ 2�����迭
	List client = new List();				// ������ ���� ������ ������ ����Ʈ
	String[] splitSeatNum;					// �Է� ���� �¼���ȣ�� �ΰ��� �ɰ��� ������ �迭
	ArrayList<String> alpaList = new ArrayList<>();	// ���� �̸��� ���ĺ� ������ ������ ArrayList

	// �������
	Reservation() {

	}

	// �迭�� ����ϴ� �޼ҵ�
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

				// �⺻������ null ���� �迭�� ����־ null �̸� ��� ����
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


	// ����⸦ �����ϴ� �޼ҵ�
	public void reserve() {

		layout(); // �¼��� ������
		System.out.println("Enter a customer name and desired seat number: ");

		String name = in.next();
		String seatNum = in.next();

		this.splitSeatNum = seatNum.split(""); // �ֿܼ��� �Է¹��� ���� ����.
		int number = Integer.parseInt(splitSeatNum[0]) - 1; // 2A�� 2 �迭�� �����ϱ� ���� -1
		int alpa = changeAlpa(splitSeatNum[1]); // 2A�� A�κ� changeAlpa�޼ҵ尡 A�� 0���� �ٲ��ش�.

		// ��ȿ�� �¼���ȣ �� ���� ����.
		if(number<5 && number>=0 && alpa>=0 && alpa<=3){
			// ���� �Է��� �¼��� �̹� ���� �Ǿ����� ��
			if(this.seat[number][alpa]=="*"){
				System.out.println("This seat is not available!");
			}
			//������ ���� �� ��.
			else{
				alpaList.add(name + " " +seatNum);		//arrayList�� ����.
				Collections.sort(alpaList, String.CASE_INSENSITIVE_ORDER);	//���ĺ� ����������.
				this.seat[number][alpa] = "*";		
				//���� ������ ���Ḯ��Ʈ�� ����. (�ӽ�)
				client.insertLast(name, seatNum);
			}
		}
		else{
			System.out.println("This seat is not available!");
		}	
	}

	// �¼��� ���ĺ�(��)�� �޾Ƽ� ���ڷ� ��ȯ�ϴ� �޼ҵ�
	public int changeAlpa(String seatStr) {
		int change;
		// ��ҹ��� ���� üũ
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

	// ���� �̸��� �޾� ������ ����ϴ� �޼ҵ�
	public void cancel() {
		System.out.println("Enter a customer name");
		String name = in.next();
		String seatNum = client.getSeatNum(name);
		// ���� �̸����� ����� �¼��� ���� ��.
		if (seatNum == null) {
			System.out.println("No such customer: " + name);
		} 
		// �� �̸� �߰� -> ���� ���
		else {
			// �¼� �迭�� ������� �ǵ�����.
			this.splitSeatNum = seatNum.split(""); // �ֿܼ��� �Է¹��� ���� ����.
			int number = Integer.parseInt(splitSeatNum[0]) - 1; // 2A�� 2 �迭�� �����ϱ� ���� -1
			int alpa = changeAlpa(splitSeatNum[1]); // 2A�� A�κ� changeAlpa�� A�� 0���� �ٲ��ش�.
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
		//���Ḯ��Ʈ�� arrayList���� ��� ����.
		client.remove(name);
		alpaList.remove(name + " " + seatNum);

	}

	// ����� �¼��� Ȯ�ν����ִ� �޼ҵ�
	public void find() {
		System.out.println("Enter a cutomer name: ");
		String name = in.next();
		System.out.println("Seat number: ");
		client.customerFind(name);
	}

	// ��� ���� ������ ������ִ� �޼ҵ�
	public void print() {

		String[] name;
		String str;

		/** ���Ḯ��Ʈ�� �̸� ������ �����ϴ� �κ� **/
		client.reset();	//head=null�� ����.(reserve���� �ӽ÷� �����Ѻκ� �ʱ�ȭ)
		for (int i = 0; i < alpaList.size(); i++) {
			str = alpaList.get(i);	//arrayList�� �ִ� ���ڿ��� ������.
			name = str.split(" ");	//���ڿ��� �ɰ�� name �迭�� ����.
			client.insertLast(name[0], name[1]);	//�̸������� ���ĵǾ� ����Ʈ�� ����ȴ�.
		}

		layout();
		int availSeat = client.available();	//���� ������ �¼�.
		System.out.println("Reservation information");
		System.out.println("Name   Seat number");
		System.out.println("----   -----------");
		client.printEvery();
		System.out.println("Available number of seats: " + availSeat);
	}

	//���α׷��� �����ϴ� �޼ҵ�.
	public boolean quit(boolean done){
		done = false;
		System.out.println("End of commands!");
		return done;
	}
}