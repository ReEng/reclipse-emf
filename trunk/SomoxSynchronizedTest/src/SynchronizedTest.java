
public class SynchronizedTest implements ISynchronizable {

	/* (non-Javadoc)
	 * @see ISynchronizable#showMessage(java.lang.String)
	 */
	@Override
	public boolean showMessage(String s){
		
		synchronized(this){
			System.out.println("The message is " + s);
		}
		
		return true;
	}
	
	
}
