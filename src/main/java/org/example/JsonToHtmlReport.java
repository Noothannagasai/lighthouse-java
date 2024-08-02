package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class JsonToHtmlReport {
    public static void main(String[] args) {
        String jsonFilePath = "C:/Users/nagas/Downloads/LH.json"; // Path to the JSON file
        String htmlFilePath = "C:/Users/nagas/Downloads/report.html";  // Path to save the HTML file

        try (FileReader reader = new FileReader(jsonFilePath)) {
            Gson gson = new Gson();
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

            String htmlContent = generateHtml(jsonObject);

            try (FileWriter writer = new FileWriter(htmlFilePath)) {
                writer.write(htmlContent);
            }

            System.out.println("HTML report generated successfully at: " + htmlFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateHtml(JsonObject jsonObject) {
        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder.append("<html>");
        htmlBuilder.append("<head>");
        htmlBuilder.append("<title>Performance Report</title>");
        htmlBuilder.append("<style>");
        htmlBuilder.append("body { font-family: Arial, sans-serif; background-color: #f9f9f9; color: #333; }");
        htmlBuilder.append(".container { width: 80%; margin: auto; padding: 20px; background-color: #fff; border-radius: 8px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); }");
        htmlBuilder.append(".header { text-align: center; margin-bottom: 40px; }");
        htmlBuilder.append(".section { margin: 20px 0; }");
        htmlBuilder.append(".section-title { font-size: 20px; font-weight: bold; border-bottom: 2px solid #ddd; padding-bottom: 5px; }");
        htmlBuilder.append(".data-item { margin: 5px 0; padding-left: 10px; }");
        htmlBuilder.append(".data-item span { font-weight: bold; }");
        htmlBuilder.append(".nested { margin-left: 20px; border-left: 2px solid #eee; padding-left: 10px; }");
        htmlBuilder.append("</style>");
        htmlBuilder.append("</head>");
        htmlBuilder.append("<body>");
        htmlBuilder.append("<div class=\"container\">");
        htmlBuilder.append("<div class=\"header\"><h1>Performance Report</h1></div>");
        htmlBuilder.append("<div class=\"content\">");

        generateHtmlFromJsonObject(jsonObject, htmlBuilder);

        htmlBuilder.append("</div>");
        htmlBuilder.append("</div>");
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");

        return htmlBuilder.toString();
    }

    private static void generateHtmlFromJsonObject(JsonObject jsonObject, StringBuilder htmlBuilder) {
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            JsonElement value = entry.getValue();

            htmlBuilder.append("<div class=\"section\">");
            htmlBuilder.append("<div class=\"section-title\">").append(key).append("</div>");

            if (value.isJsonObject()) {
                htmlBuilder.append("<div class=\"nested\">");
                generateHtmlFromJsonObject(value.getAsJsonObject(), htmlBuilder);
                htmlBuilder.append("</div>");
            } else if (value.isJsonArray()) {
                htmlBuilder.append("<div class=\"nested\">");
                generateHtmlFromJsonArray(value.getAsJsonArray(), htmlBuilder);
                htmlBuilder.append("</div>");
            } else if (value.isJsonNull()) {
                htmlBuilder.append("<div class=\"data-item\"><span>").append(key).append(": </span>").append("null").append("</div>");
            } else {
                htmlBuilder.append("<div class=\"data-item\"><span>").append(key).append(": </span>").append(value.getAsString()).append("</div>");
            }

            htmlBuilder.append("</div>");
        }
    }

    private static void generateHtmlFromJsonArray(JsonArray jsonArray, StringBuilder htmlBuilder) {
        for (JsonElement element : jsonArray) {
            if (element.isJsonObject()) {
                generateHtmlFromJsonObject(element.getAsJsonObject(), htmlBuilder);
            } else if (element.isJsonArray()) {
                generateHtmlFromJsonArray(element.getAsJsonArray(), htmlBuilder);
            } else if (element.isJsonNull()) {
                htmlBuilder.append("<div class=\"data-item\">null</div>");
            } else {
                htmlBuilder.append("<div class=\"data-item\">").append(element.getAsString()).append("</div>");
            }
        }
    }
}
