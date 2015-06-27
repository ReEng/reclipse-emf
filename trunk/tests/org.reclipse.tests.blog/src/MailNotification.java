
public class MailNotification extends Notification 
{
	private String mailAdress = "";
	
	public MailNotification(Blog b, String mailAdress)
	{
		this.mailAdress = mailAdress;
		b.addNotification(this);
	}
	
	public void update(/*Content c*/)
	{		
	}
}
