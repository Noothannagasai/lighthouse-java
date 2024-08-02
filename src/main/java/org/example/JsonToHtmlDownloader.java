package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonToHtmlDownloader {

    public static void main(String[] args) {
        String apiUrl = "https://googlechrome.github.io/lighthouse/viewer/";
        String jsonFilePath = "C:\\Users\\nagas\\IdeaProjects\\lighthouse\\LH.json"; // Path to your JSON file
        String outputHtmlFilePath = "output.html"; // Path where the HTML will be saved

        try {
            // Read JSON from file
            String jsonInput = readJsonFromFile(jsonFilePath);

            // Send JSON to the API
            String htmlResponse = sendJsonToApi(apiUrl, jsonInput);

            // Save HTML response to a file
            saveHtmlToFile(htmlResponse, outputHtmlFilePath);

            System.out.println("HTML report saved as '" + outputHtmlFilePath + "'");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readJsonFromFile(String filePath) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        StringBuilder jsonInput = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            jsonInput.append(line);
        }
        br.close();
        return jsonInput.toString();
    }

    private static String sendJsonToApi(String apiUrl, String jsonInput) throws Exception {
        // Create URL and HttpURLConnection
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "text/html");
        conn.setDoOutput(true);

        // Send JSON data
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
        writer.write(jsonInput);
        writer.flush();
        writer.close();

        // Check the response code
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        // Read the response
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder htmlResponse = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            htmlResponse.append(line);
        }
        br.close();
        conn.disconnect();

        return htmlResponse.toString();
    }

    private static void saveHtmlToFile(String htmlResponse, String filePath) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(htmlResponse);
        writer.close();
    }
}
