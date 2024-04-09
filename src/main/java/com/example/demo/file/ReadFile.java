package com.example.demo.file;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile {
    private static CharSequence  detectCharset(Path path) throws Exception {
        return Files
                .probeContentType(path)
                .split(";")[1]
                .split("=")[1]
                .trim();
    }
    public static void main(String[] args) {
        try {
            Path currentPath = Paths.get("filename.txt");
            File myObj = new File(String.valueOf(currentPath.toAbsolutePath()));
            CharSequence charset = detectCharset(currentPath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}