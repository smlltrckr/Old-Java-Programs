import java.io.IOException;
import java.io.Writer;


public class EncryptingWriter  extends Writer
{
	Writer writer;
	/**
	 * 
	 * @param w
	 */
	public EncryptingWriter(Writer writer) 
	{
		this.writer = writer;
	}
	/**
	 * 
	 */
	public void close() throws IOException 
	{
		writer.close();
	}
	/**
	 * 
	 */
	public void flush() throws IOException 
	{
		writer.flush();
	}
	/**
	 * 
	 */
	public void write(char[] a) throws IOException 
	{
		for (char c : a) 
		{
			if ((int) c >= (int) 'A' && (int) c <= (int) 'Z') 
			{
				int result = ((int) c - (int) 'A' + 3) % 26 + (int) 'A';
				writer.write(result);
			} 
			else 
			{
				writer.write((int) c);
			}
		}
	}
	/**
	 * 
	 */
	public void write(char[] a, int x, int y) throws IOException 
	{
		writer.write(a, x, y);
	}
}
