import java.util.Scanner;
/**
 * Reservation Ŭ������ test�ϴ� ���α׷�.
 * @author ������
 *
 */
public class TestReservation {

	public static void main(String[] args){

		//command = (r)eserve, c(ancel), f(ind), p(rint), or q(uit).
		String command;	//����� �Է¹��� ����
		Scanner in = new Scanner(System.in);
		
		//������ ������ ������Ʈ.
		Reservation reservation = new Reservation();

		boolean done = true;
		while(done){
			System.out.println("Enter a command: (r)eserve, c(ancel), f(ind), p(rint), or q(uit): ");
			command = in.next();

			if(command.equals("r")){
				reservation.reserve();
			}
			else if(command.equals("c")){
				reservation.cancel();
			}
			else if(command.equals("f")){
				reservation.find();
			}
			else if(command.equals("p")){
				reservation.print();
			}
			else if(command.equals("q")){
				done = reservation.quit(done);
			}
			else{
				System.out.println("�߸��� ��ɾ� �Է�.");
			}
		}
		in.close();
	}
}

