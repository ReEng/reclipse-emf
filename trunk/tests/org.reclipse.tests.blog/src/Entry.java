
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class Entry extends Content
{
	private static int uniqueId = 0;
	
	
	private List<Comment> comments = new Vector<Comment>();
	
	private int id = 0;
	
	private String text = "";
	private String subject = "";
	
	public Entry(String subject, String text)
	{
		this.id = Entry.uniqueId++;
		this.subject = subject;
		this.text = text;
	}
	
	public void addComment(Comment c)
	{
		this.comments.add(c);
	}
	
	public String getAuthor()
	{
		return "Owner";
	}
	
	public List<Comment> getComments()
	{
		return this.comments;
	}
	
	public String getContent()
	{
		return this.text;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public String render()
	{
		return this.id + " " + this.subject + "\n" + 
				", by " + this.getAuthor() + "\n" + 
				this.text + "\n";
	}
	
	public void setSubject(String subject)
	{
		this.subject = subject;
		this.setUpdated();
	}
	
	public void setText(String text)
	{
		this.text = text;
		this.setUpdated();
	}
	
	private void setUpdated()
	{
		this.updateDate = new Date();
	}

}
