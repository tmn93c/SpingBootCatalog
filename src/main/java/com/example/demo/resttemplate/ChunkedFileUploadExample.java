package com.example.demo.resttemplate;

import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;


public class ChunkedFileUploadExample {

    private static final int CHUNK_SIZE = 1024 * 1024; // 1MB

    public static void main(String[] args) throws IOException {
        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
//        Path filePath = getAbsolutePath("putty.exe");

        // Path to the file to be uploaded
        File file = new File("putty.exe");
        String userDir = System.getProperty("user.dir");
        String chunkFolder = userDir + "\\" + simpleUUid()+ "\\";
        createFolder(chunkFolder);
        createChunks(file.getAbsolutePath(), chunkFolder);
        mergeChunks(chunkFolder, chunkFolder + "\\putty.exe");
        // Calculate the total number of chunks
        int totalChunks = (int) Math.ceil((double) file.length() / CHUNK_SIZE);

        // Iterate over chunks
        for (int chunkNumber = 0; chunkNumber < totalChunks; chunkNumber++) {
            // Read the chunk from the file
//            byte[] chunkData = readChunk(file.toPath(), chunkNumber);

            // Create the request body as a MultiValueMap
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//            body.add("fileChunk", new FileSystemResource(chunkData, "chunk.bin"));
            body.add("chunkNumber", String.valueOf(chunkNumber));
            body.add("totalChunks", String.valueOf(totalChunks));

            // Set the request headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // Create the RequestCallback to set the request body and headers
            RequestCallback requestCallback = request -> {
                request.getHeaders().addAll(headers);
                HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
//                request.setEntity(entity);
            };

            // Create the ResponseExtractor to handle the response
            ResponseExtractor<ResponseEntity<String>> responseExtractor = restTemplate.responseEntityExtractor(String.class);

            // Send the request
            ResponseEntity<String> response = restTemplate.execute("http://example.com/upload", HttpMethod.POST, requestCallback, responseExtractor);

            // Handle the response
            System.out.println("Response status: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody());
        }
    }

    private static String simpleUUid(){
        return UUID.randomUUID().toString();
    }

    private static void createChunks(String sourceFilePath, String chunkOutputPath) {
        try (FileInputStream fis = new FileInputStream(sourceFilePath)) {
            byte[] buffer = new byte[CHUNK_SIZE];
            int bytesRead;
            int chunkIndex = 0;

            while ((bytesRead = fis.read(buffer)) != -1) {
                String chunkFilename = chunkOutputPath + "chunk_" + chunkIndex + ".dat";
                try (FileOutputStream fos = new FileOutputStream(chunkFilename)) {
                    fos.write(buffer, 0, bytesRead);
                }
                chunkIndex++;
            }
            System.out.println("Chunk files created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFolder(String folderPath) {
        File folder = new File(folderPath);

        if (!folder.exists()) {
            boolean success = folder.mkdir();

            if (success) {
                System.out.println("Chunk folder created successfully.");
            } else {
                System.err.println("Failed to create chunk folder.");
            }
        } else {
            System.out.println("Chunk folder already exists.");
        }
    }

    private static void mergeChunks(String chunkFolderPath, String outputFile) {
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            File folder = new File(chunkFolderPath);
            File[] chunkFiles = folder.listFiles();

            if (chunkFiles != null) {
                // Sort the chunk files by name to ensure correct order
                Arrays.sort(chunkFiles);

                for (File chunkFile : chunkFiles) {
                    try (FileInputStream fis = new FileInputStream(chunkFile)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = fis.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }
                    }
                }

                System.out.println("Chunks merged successfully.");
            } else {
                System.err.println("No chunk files found in the directory.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}