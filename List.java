/**
 * ���Ḯ��Ʈ�� �����ϴ� Ŭ����
 * @author ������
 *
 */
class List {

	// ListŬ������ �ʵ� head
	private Node head ;

	// �̳� Ŭ����
	private static class Node {
		private String name; // �� �̸�
		private String seatNum; // �¼���ȣ
		private Node next;

		public Node(String name, String seatNum, Node next) {
			this.name = name;
			this.seatNum = seatNum;
			this.next = next;
		}

		public Node(String item, String seatNum) {
			this(item, seatNum, null);
		}
	}

	//head�� null�� ����Ű�� ��.
	public void reset(){
		head = null;
	}

	// ����� �������� ����.
	public void insertLast(String item, String seatNum) {

		Node newNode = new Node(item, seatNum);
		Node curr = head;
		if (head == null) {
			head = newNode;
		} else {

			while (curr.next != null) {
				curr = curr.next;
			}
			curr.next = newNode;
		}
	}

	// ���� ����Ʈ ���
	public void printEvery() {
		Node scan=head;
		while (scan != null) {
			System.out.println(scan.name + "	" + scan.seatNum);
			scan = scan.next;
		}
		System.out.println();
	}

	// �Է¹��� �����Ͱ� �ִ� ��带 �����ִ� �޼ҵ�.
	// �Է¹��� �� �����
	public void remove(String data) {
		Node prev = null;
		Node curr = head;

		if (head == null) { // index = 0 �� ��
			return;
		} 
		else {
			while (curr != null) {
				if (curr.name.equals(data)) {
					if (head == curr) {
						head = head.next;
					} 
					else {
						prev.next = curr.next;
					}
					break;
				} 
				else {
					prev = curr;
					curr = curr.next;
				}
			}
		}
	}

	// �̸��� �޾Ƽ� �¼��� ��ȣ�� ��ȯ�ϴ� �޼ҵ�.
	public String getSeatNum(String name) {
		String getSeat = null;
		for (Node scan = head; scan != null; scan = scan.next) {
			if (scan.name.equals(name)) {
				getSeat = scan.seatNum;
				break;
			}
		}
		return getSeat;
	}


	// �� �̸��� �־����� ���� ������ �¼��� ����Ѵ�.
	// ���� ���� �ʾ����� �������� ���� ���̶�� �޼����� ����Ѵ�.
	public void customerFind(String name) {

		boolean find = false;
		for (Node scan = head; scan != null; scan = scan.next) {
			if (scan.name.equals(name)) {
				System.out.println(scan.seatNum);
				find = true;
			}
		}
		if (find != true)
			System.out.println("No such customer: " + name);

	}

	// ���� �¼� ���� ��ȯ �� �ִ� �޼ҵ�.
	public int available() {
		int availSeat = 20;	//�� �¼� ����. row*col(4*5)
		Node scan;
		for (scan = head; scan != null; scan = scan.next) {
			availSeat = availSeat - 1;
		}
		return availSeat;
	}
}
