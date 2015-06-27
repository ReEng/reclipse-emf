
public class TwitterNotification extends Notification 
{
	private String account = "";
	private String passwort = "";
	
	public TwitterNotification(Blog b,String account, String passwort)
	{
		this.account = account;
		this.passwort = passwort;
		b.addNotification(this);
	}
	
	public void update(/*Content c*/)
	{
		
	}	
}
