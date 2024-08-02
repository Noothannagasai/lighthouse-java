package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PageSpeedInsightsExample {

    public static void main(String[] args) throws IOException {
        String urlToTest = "https://www.mastercard.co.in/en-in/";  // Replace with the URL you want to test
        
        // Constructing the API URL
        String apiUrl = "https://www.googleapis.com/pagespeedonline/v5/runPagespeed?url=" +
                urlToTest +
                "&strategy=mobile";

        // Creating a URL object
        URL url = new URL(apiUrl);

        // Creating an HttpURLConnection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Reading the response
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
//        reader.close();


        // Parsing the JSON response
        String jsonResponse = response.toString();
        System.out.println("JSON Response:\n" + jsonResponse);

        // You can now parse the JSON response to extract specific metrics
        // Example: extracting performance score
        double performanceScore = extractPerformanceScore(jsonResponse);
        System.out.println("Performance Score: " + performanceScore);
    }

    // Method to extract performance score from JSON response
    private static double extractPerformanceScore(String jsonResponse) {
        // You should use a JSON parsing library like Jackson or Gson for production code
        // For simplicity, let's assume the JSON structure
        // Example using simple substring search (not recommended for production)
        int index = jsonResponse.indexOf("\"performance_score\":");
        if (index != -1) {
            int startIndex = index + "\"performance_score\":".length();
            int endIndex = jsonResponse.indexOf(",", startIndex);
            if (endIndex != -1) {
                String scoreStr = jsonResponse.substring(startIndex, endIndex).trim();
                return Double.parseDouble(scoreStr);
            }
        }
        return -1.0;  // Error handling: return -1 if score not found
    }
}
