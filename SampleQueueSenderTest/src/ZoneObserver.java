

/**
 * Clasa care Observa primirea mesajelor si le preia
 * @author chiso
 *
 */
public class ZoneObserver extends Observer {

	public ZoneObserver(TibcoEMSQueueReceiver subject, MessageViewPanel view) {
		this.subject = subject;
		this.msgView = view;
		//ataseaza Observatorul clasei observate
		this.subject.attach(this);
	}
	
	@Override
	public void update() {
		Util util =new Util();
		String mesaj = util.createMessageForView(subject.getMessage(), subject.getQueueName());
		
		msgView.setZona(subject.getZona());
		msgView.setMessage(mesaj);
		msgView.setContor(msgView.getContor()+1);
		
		//System.out.println(subject.getQueueName() + " " + subject.getMessage() + " " + subject.getZona());
	}
	
	
	

}
