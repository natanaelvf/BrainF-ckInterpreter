package brainf;

import java.util.Scanner;
public class BFJavaRun {

	public static byte[] array = new byte[6000];
	public static int index = array.length/2;

	public static void main(String[] args) {
		for (int a = 0; a < 8; a++)
			array[index]++;

		while (array[index] != 0) {

			index++;

			array[index]++;
			array[index]++;
			array[index]++;
			array[index]++;

			while (array[index] != 0) {

				index++;

				array[index]++;
				array[index]++;

				index++;

				array[index]++;
				array[index]++;
				array[index]++;

				index++;

				array[index]++;
				array[index]++;
				array[index]++;

				index++;

				array[index]++;

				index--;
				index--;
				index--;
				index--;

				array[index]--;

			}

			index++;

			array[index]++;

			index++;

			array[index]++;

			index++;

			array[index]--;

			index++;
			index++;

			array[index]++;

			while (array[index] != 0) {

				index--;

			}

			index--;

			array[index]--;

		}

		index++;
		index++;

		System.out.print((char)array[index]);

		index++;

		array[index]--;
		array[index]--;
		array[index]--;

		System.out.print((char)array[index]);

		for (int a = 0; a < 7; a++)
			array[index]++;

		System.out.print((char)array[index]);
		System.out.print((char)array[index]);

		array[index]++;
		array[index]++;
		array[index]++;

		System.out.print((char)array[index]);

		index++;
		index++;

		System.out.print((char)array[index]);

		index--;

		array[index]--;

		System.out.print((char)array[index]);

		index--;

		System.out.print((char)array[index]);

		array[index]++;
		array[index]++;
		array[index]++;

		System.out.print((char)array[index]);

		for (int a = 0; a < 6; a++)
			array[index]--;

		System.out.print((char)array[index]);

		for (int a = 0; a < 8; a++)
			array[index]--;

		System.out.print((char)array[index]);

		index++;
		index++;

		array[index]++;

		System.out.print((char)array[index]);

		index++;

		array[index]++;
		array[index]++;

		System.out.print((char)array[index]);
	}
}