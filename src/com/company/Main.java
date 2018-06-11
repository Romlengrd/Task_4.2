package com.company;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        String filename = "src\\com\\company\\file1.txt";
        String filename1 = "src\\com\\company\\file2.txt";
        try (FileReader in = new FileReader(filename)) {
            BufferedReader br = new BufferedReader(in);

            String string;
            String s2 = "";
            while ((string = br.readLine()) != null) {
                string += br.readLine();
                s2 += string;
            }

            String[] javaWords = {"for", "Scanner", "println", "while", "if", "Integer", "double"};
            String[] javaWordsOut = new String[javaWords.length];
            int[] counterJava = new int[javaWords.length];

            for (int i = 0; i < javaWords.length; i++) {

                if (s2.contains(javaWords[i])) {
                    System.out.println("есть " + javaWords[i]);
                    javaWordsOut[i] = javaWords[i];

                    Pattern p = Pattern.compile(javaWords[i]);
                    Matcher m = p.matcher(s2);
                    int counter = 0;
                    while (m.find()) {
                        counter++;
                    }
                    if (counter != 0) {
                        counterJava[i] = counter;
                    }
                    System.out.println(counterJava[i]);
                }

            }

            String wordOutByteString = String.join(",", javaWordsOut);
            String counteOutString = "";
            StringBuilder b = new StringBuilder();
            for (int i = 0; i < counterJava.length; i++) {
                b.append(counterJava[i] + " ");
            }
            String lineSeparator = System.getProperty("line.separator");
            counteOutString = lineSeparator + b.toString();

            try (FileWriter out = new FileWriter(filename1)) {
                out.write(wordOutByteString);
                out.write(counteOutString);
            }
        }
            catch (FileNotFoundException e)

            {
                e.printStackTrace();
            } catch (IOException e)

            {
                e.printStackTrace();
            }
    }
}