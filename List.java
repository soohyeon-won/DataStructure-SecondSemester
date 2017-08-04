/**
 * 연결리스트를 관리하는 클래스
 * @author 원수현
 *
 */
class List {

	// List클래스의 필드 head
	private Node head ;

	// 이너 클래스
	private static class Node {
		private String name; // 고객 이름
		private String seatNum; // 좌석번호
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

	//head가 null을 가리키게 함.
	public void reset(){
		head = null;
	}

	// 노드의 마지막에 삽입.
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

	// 연결 리스트 출력
	public void printEvery() {
		Node scan=head;
		while (scan != null) {
			System.out.println(scan.name + "	" + scan.seatNum);
			scan = scan.next;
		}
		System.out.println();
	}

	// 입력받은 데이터가 있는 노드를 지워주는 메소드.
	// 입력받은 값 지우기
	public void remove(String data) {
		Node prev = null;
		Node curr = head;

		if (head == null) { // index = 0 일 때
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

	// 이름을 받아서 좌석의 번호를 반환하는 메소드.
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


	// 고객 이름이 주어지면 고객이 예약한 좌석을 출력한다.
	// 예약 하지 않았으면 예약하지 않은 고객이라는 메세지를 출력한다.
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

	// 남은 좌석 수를 반환 해 주는 메소드.
	public int available() {
		int availSeat = 20;	//총 좌석 개수. row*col(4*5)
		Node scan;
		for (scan = head; scan != null; scan = scan.next) {
			availSeat = availSeat - 1;
		}
		return availSeat;
	}
}
