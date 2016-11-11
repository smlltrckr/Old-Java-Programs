import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;


public class Decorator 
{
	public static void main(String args[]) throws IOException
	{
		char[] charArray = {'G', 'F', ' ', 'S', 'R', 'V', 'B'};
		System.out.print("Original : ");
		System.out.println(charArray);
		
		CharArrayWriter charWriter = new CharArrayWriter();
		EncryptingWriter writer = new EncryptingWriter(charWriter);
		
		System.out.print("Encrypted: ");
		
		writer.write(charArray);
		
		System.out.println(charWriter.toCharArray());
		
		CharArrayReader cReader = new CharArrayReader(charWriter.toCharArray());
		DecryptingReader reader = new DecryptingReader(cReader);
		
		char[] result = new char[7];
		reader.read(result);
		
		System.out.print("Decrypted: ");
		System.out.println(result);
		
		writer.flush();
		writer.close();
		reader.close();
	}
}
