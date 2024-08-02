package org.example;

import com.google.gson.*;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
public class App 
{
        public static void main(String[] args) {
//            String apiKey = "YOUR_API_KEY";
            String urlToCheck = "https://www.mastercard.co.in/en-in";
            try {
//                URL url = new URL("https://www.googleapis.com/pagespeedonline/v5/runPagespeed?url=" +
//                        urlToCheck + "&key=" + apiKey);
                URL url = new URL("https://www.googleapis.com/pagespeedonline/v5/runPagespeed?url=" +
                        urlToCheck);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                rd.close();

                String json = result.toString();
                System.out.println("PageSpeed Insights JSON Response:");
                System.out.println(json);

                PageSpeedInsightsReport report = parsePerformanceScore(json);
                System.out.println("Performance Score: " + report.getPerformanceScore());
                System.out.println("Largest Contentful Paint: " + report.getLcp());
                System.out.println("Total Blocking Time: " + report.getTbt());
                System.out.println("Cumulative Layout Shift: " + report.getCls());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    private static PageSpeedInsightsReport parsePerformanceScore(String json) {
    JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
    JsonObject lighthouseResult = jsonObject.getAsJsonObject("lighthouseResult");
    JsonObject categories = lighthouseResult.getAsJsonObject("categories");
    JsonObject performance = categories.getAsJsonObject("performance");

    double score = performance.get("score").getAsDouble();
    double lcp = getAuditNumericValue(lighthouseResult, "largest-contentful-paint");
    double tbt = getAuditNumericValue(lighthouseResult, "total-blocking-time");
    double cls = getAuditNumericValue(lighthouseResult, "cumulative-layout-shift");

    return new PageSpeedInsightsReport(score, lcp, tbt, cls);
}

    private static double getAuditNumericValue(JsonObject lighthouseResult, String auditId) {
        JsonObject audits = lighthouseResult.getAsJsonObject("audits");
        JsonObject audit = audits.getAsJsonObject(auditId);
        String numericValue = String.valueOf(audit.get("numericValue"));
        return Double.parseDouble(numericValue);
    }

    static class PageSpeedInsightsReport {
        private double performanceScore;
        private double lcp;
        private double tbt;
        private double cls;

        public PageSpeedInsightsReport(double performanceScore, double lcp, double tbt, double cls) {
            this.performanceScore = performanceScore;
            this.lcp = lcp;
            this.tbt = tbt;
            this.cls = cls;
        }

        public double getPerformanceScore() {
            return performanceScore;
        }

        public double getLcp() {
            return lcp;
        }

        public double getTbt() {
            return tbt;
        }

        public double getCls() {
            return cls;
        }
    }
}


