import java.io.IOException;
import java.io.Reader;


public class DecryptingReader extends Reader
{
	private Reader reader;
	/**
	 * 
	 */
	public DecryptingReader(Reader reader) 
	{
		this.reader = reader;
	}
	/**
	 * 
	 */
	public void close() throws IOException 
	{
		reader.close();
	}
	/**
	 * 
	 */
	public int read(char[] a, int x, int y) throws IOException 
	{
		return reader.read(a, x, y);
	}
	/**
	 * 
	 */
	public int read(char[] a) throws IOException 
	{
		int count = 0;
		int b = 0;
		while (b != -1 && count < a.length) 
		{
			b = reader.read();
			if (b >= (int) 'A' && b <= (int) 'Z') 
			{
				b = (b - (int) 'A' - 3) % 26 + (int) 'A';
			}
			a[count++] = (char) b;
		}
		if (count != a.length) 
		{
			count = -1;
		}
		
		return count;
	}
}
