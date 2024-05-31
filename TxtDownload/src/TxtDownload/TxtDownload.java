package TxtDownload;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TxtDownload {
	public static void main(String[] args) {
		String webpageUrl = "https://en.wikipedia.org/wiki/Google";
		String filepath = "dw_pdf.pdf";

		try (BufferedInputStream in = new BufferedInputStream(new URL(webpageUrl).openStream());
				FileOutputStream f = new FileOutputStream(filepath)) {
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				f.write(dataBuffer, 0, bytesRead);
			}
			System.out.println("PDF file downloaded successfully!");

		} catch (Exception e) {
			System.out.println("Error occurred while downloading or reading the webpage.");
		}

		try (Scanner scanner = new Scanner(new File(filepath))) {

			int lines = 0;
			int words = 0;
			int characters = 0;
			int alphabets = 0;
			int numbers = 0;
			int specialChars = 0;
			int alphanumericWords = 0;

			while (scanner.hasNextLine()) {
				String currentLine = scanner.nextLine();
				lines++;
				characters += currentLine.length();

				for (char c : currentLine.toCharArray()) {
					if (Character.isLetter(c)) {
						alphabets++;
					} else if (Character.isDigit(c)) {
						numbers++;
					} else if (!Character.isWhitespace(c)) {
						specialChars++;
					}
				}

				String wordArray[] = currentLine.split("\\s+");
				words += wordArray.length;

				for (String word : wordArray) {
					if (Pattern.matches("[a-zA-Z0-9]+", word)) {
						alphanumericWords++;
					}
				}
			}

			scanner.close();

			System.out.println("\nNumber of lines: " + lines);
			System.out.println("Number of words: " + words);
			System.out.println("Number of characters: " + characters);
			System.out.println("Number of alphabets: " + alphabets);
			System.out.println("Number of numbers: " + numbers);
			System.out.println("Number of special characters: " + specialChars);
			System.out.println("Number of alphanumeric words: " + alphanumericWords);

		} catch (Exception e) {
			System.out.println("Error occurred while downloading or reading the webpage.");
		}
	}

}