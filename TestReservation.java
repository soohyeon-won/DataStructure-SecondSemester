import java.util.Scanner;
/**
 * Reservation 클래스를 test하는 프로그램.
 * @author 원수현
 *
 */
public class TestReservation {

	public static void main(String[] args){

		//command = (r)eserve, c(ancel), f(ind), p(rint), or q(uit).
		String command;	//명령을 입력받을 변수
		Scanner in = new Scanner(System.in);
		
		//예약을 수행할 오브젝트.
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
				System.out.println("잘못된 명령어 입력.");
			}
		}
		in.close();
	}
}

