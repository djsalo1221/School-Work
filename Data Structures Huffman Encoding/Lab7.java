/**
 * @author Daniel Salo
 * @version 8.14.14
 * This is the main class for Huffman encoding/decoding.
 * It take the input file, creates Huffman code for the 
 * input characters based on their frequency, and  
 * Prints the character codes and encoded message.
 * 
 * If the input file contains the encoded message, the program 
 * decodes the message and prints both encoded and decoded message.
 *
 */
 
import java.io.*;
import java.util.*;



public class Lab7 {


	public static void main(String[] args) throws IOException {
		
		// Take input filename from user
		System.out.println("Please enter the data file name:");

		Scanner in = new Scanner(System.in);

		String fileName = in.next();
		 	
		File file = new File (fileName);

		int[] freq = new int[26];

		// List to hold input characters from file
		java.util.List<Character> inputList = new ArrayList<Character>();
		
		// Map to hold character codes and character mapping
		Map<String, Character> codeCharacterMap = new HashMap<String, Character>();

		try {
			FileInputStream fis = new FileInputStream(file);
			char current = 0;

			// Read characters from input file
			while (current != (char)-1) {
				current = (char) fis.read();

				int index = current - 'A';

				if ( index >=0 && index <=25)
				{
					freq[index] = freq[index] + 1;
					inputList.add((char)(index + 'A'));
				}


			}
		} 
		catch (FileNotFoundException e) {
			System.out.println("Invalid name, File not found");
			throw e;
		} catch (IOException e) {
			System.out.println("I/O error on file");
			throw e;
		}
		

		// Build Huffman tree and character codes
		Huffman huffman = new Huffman(freq);
		
		System.out.println("Here is the code of each character:\n");
		System.out.println("Character    Code"); 
		System.out.println("=========================="); 
		for ( int i=0 ; i <26 ; i++)
		{
			char ch = (char)('A' + i);

			if ( freq[i] > 0)
			{
				codeCharacterMap.put(huffman.codeOf(ch), ch);
				System.out.println(ch + "             " + huffman.codeOf(ch));
			}
		}

		System.out.print("\nThe encoded message is ");
		for ( char character : inputList)
		{
			System.out.print(huffman.codeOf(character));
		}
		
		System.out.println();
		
		// If the input file contains encoded message, decode it
		decode(codeCharacterMap, fileName);
		

	}
	

	/**
	 * Reads the encoded message from input file (if input file contains encoded message)
	 * Decodes the input message.
	 * 
	 * @param codeCharacterMap
	 * @param fileName
	 */
	private static void decode ( Map<String, Character> codeCharacterMap, String fileName)
	{
		String encodedString = null;
		FileInputStream fs;
		try {
			fs = new FileInputStream(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(fs));
			// ignore first line of the input file
			br.readLine();
			
			// read encoded message if present
			encodedString = br.readLine();
		} catch (FileNotFoundException e) {
			System.out.println("Invalid name, File not found");
		} catch (IOException e) {
			System.out.println("I/O error on file");
		}
		
		
		if ( encodedString != null)
		{
			System.out.println("\nEncoded Message : " + encodedString);
			
			StringBuffer decodedString = new StringBuffer();
			
			int i=0;
			// Since no character code i prefix of other, thus 
			// comparing the encoded message starting from 
			// length 1 till a character match is found
			while (encodedString.length() != 0)
			{
				i++;
				String testChar = encodedString.substring(0, i);
				if ( codeCharacterMap.containsKey(testChar))
				{
					i=0;
					encodedString = encodedString.substring(testChar.length());
					decodedString.append(codeCharacterMap.get(testChar));
				}
				
			}
			
			System.out.println("Decoded Message : " + decodedString);
			
		}
		
	}

}
