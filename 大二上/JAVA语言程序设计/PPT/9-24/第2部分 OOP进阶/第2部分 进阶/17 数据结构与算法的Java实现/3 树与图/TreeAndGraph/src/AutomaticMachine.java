
public class AutomaticMachine {
	//初始状态
	private State curState;

	public AutomaticMachine(){
		//对象创建时，处于created状态
		curState=State.created;
	}
	public void open(){
		if(curState!=State.created){
			return;
		}
		curState=State.opened;
	}
	public void close(){
		if(curState==State.created || curState==State.opened){
			curState=State.closed;
		}
	}

	public State getCurState() {
		return curState;
	}
	public static void main(String[] args) {
		AutomaticMachine machine=new AutomaticMachine();
		System.out.println(machine.getCurState());
		machine.open();
		System.out.println(machine.getCurState());
		machine.close();
		System.out.println(machine.getCurState());
	}
}
/**
 * 自动机的三种状态
 * @author JinXuLiang
 *
 */
enum State{
	created,opened,closed
}