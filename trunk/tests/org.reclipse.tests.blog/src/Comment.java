
public class Comment extends Content
{
	private String author = "";
	private String text = "";
	
	
	public Comment(String author, String text)
	{
		this.author = author;
		this.text = text;
	}
	
	public String getAuthor()
	{
		return this.author;
	}
	
	public String getContent()
	{
		return this.text;
	}
	
	public String render()
	{
		return this.author + " wrote at " + this.getCreationDate() + ":\n" + this.text + "\n";
	}
}
