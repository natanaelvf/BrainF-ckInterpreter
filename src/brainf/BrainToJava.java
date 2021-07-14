package brainf;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Stack;

/**
 * Brainf to Java interpreter
 * @author Natanael Ferreira
 */

public class BrainToJava {

	public static byte[] array = new byte[3000];

	public static int index = array.length/2;

	public static final String HEADER = """
			package brainfuck;\r\n
			import java.util.Scanner;
			public class BFJavaRun {\r\n
			\tpublic static byte[] array = new byte[6000];\r
			\tpublic static int index = array.length/2;\r\n
			\tpublic static void main(String[] args) {\r
			""";

	public static final String FILENAME = "./src/brainfuck/BFJavaRun.java";

	public static void main(String[] args) throws IOException {

		String input = readFileAsString("brainfuck.txt");

		String translatedInput = HEADER + interpret(input) + "\t}\r}";

		String output = convert(new Scanner(translatedInput));

		writeToFile(FILENAME, output);
	}

	public static String convert(Scanner sc) {

		StringBuilder sb = new StringBuilder();
		Stack<String> stack = new Stack<>();
		stack.push(sc.nextLine());
		char c = 'a';

		while (sc.hasNextLine()) {
			String nextLine = sc.nextLine();
			if (stack.peek().equals(nextLine)) {
				stack.push(nextLine);
			} else {
				if (stack.size() < 5) {
					while(!stack.isEmpty()) 
						sb.append(stack.pop() + "\r\n");
					stack.push(nextLine);
				} else {
					String tabs = stack.peek().replaceAll("[^\\t]", "");
					sb.append(tabs + "for (int "+c+" = 0; "+c+" < " + stack.size() + "; "+c+"++)\r\n");
					sb.append("\t" + stack.peek() + "\r\n");
					while(!stack.isEmpty()) 
						stack.pop();
					stack.push(nextLine);
				}
			}
		}
		sb.append("}");
		return sb.toString();
	}

	public static String interpret(String file) {
		StringBuilder sb = new StringBuilder();

		int tabsNumber = 2;

		for (int i = 0; i < file.length(); i++) {

			char c = file.charAt(i);

			if(i > 0 && c != file.charAt(i - 1)) 
				sb.append("\r\n");

			for(int j = 0; j < tabsNumber; j++)
				sb.append("\t");

			if (i < file.length() - 1 && c == ']')
				sb.deleteCharAt(sb.length() - 1);

			switch(c) {

			case '>':
				sb.append("index++;\r\n");
				break;

			case '<':
				sb.append("index--;\r\n");
				break;

			case '+':
				sb.append("array[index]++;\r\n");
				break;

			case '-':
				sb.append("array[index]--;\r\n");
				break;

			case '.':
				sb.append("System.out.print((char)array[index]);\r\n");
				break;

			case ',':
				sb.append("array[index] = (byte) (new Scanner(System.in)).next().charAt(0);\r\n");
				break;

			case '[':
				sb.append("while (array[index] != 0) {\r\n");
				tabsNumber++;
				break;				

			case ']':
				tabsNumber--;
				sb.append("}\r\n");
				break;

			default:
				break;
			}
		}
		return sb.toString();
	}

	public static String readFileAsString(String fileName) {
		String text = "";
		try {
			text = new String(Files.readAllBytes(Paths.get(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}

	public static void writeToFile(String fileName, String str) {
		try (FileWriter output = new FileWriter(fileName);) {
			output.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}