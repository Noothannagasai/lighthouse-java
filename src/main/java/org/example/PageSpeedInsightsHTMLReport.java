package org.example;

import java.io.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.utilities.ExcelUtility;
import org.utilities.fetchJsonResponse;
import org.utilities.generateHtmlReport;


public class PageSpeedInsightsHTMLReport {
    static ExcelUtility utility;
    public static void main(String[] args) throws IOException {
        generateHtmlReport report = new generateHtmlReport();
        fetchJsonResponse response = new fetchJsonResponse();
        utility = new ExcelUtility("testdata.xlsx","data");

        for (int i = 1; i <= utility.getlastRowNum("data"); i++) {
            String urlToTest =  utility.getData("data",i,"URL");
            String strategyused =  utility.getData("data",i,"Strategy");
            String browser =  utility.getData("data",i,"Browser");
            String userAgent =null;
            if(browser.equalsIgnoreCase("chrome"))
                userAgent = "Chrome/91.0.4472.124 ";
            else if (browser.equalsIgnoreCase("edge"))
                userAgent = "Mozilla/5.0 (Edg/91.0.864.59";
            else if (browser.equalsIgnoreCase("firefox"))
                userAgent = "Mozilla/5.0 (Firefox/89.0";

            String apiUrl = "https://www.googleapis.com/pagespeedonline/v5/runPagespeed?url=" +
                    urlToTest+"&strategy=" +strategyused+ "&category=performance&category=accessibility&category=best-practices&category=seo";

            String jsonResponse = response.fetchJsonResponse(apiUrl,userAgent);
            JsonObject json = JsonParser.parseString(jsonResponse).getAsJsonObject();

            double performanceScore = json.getAsJsonObject("lighthouseResult")
                    .getAsJsonObject("categories")
                    .getAsJsonObject("performance")
                    .get("score")
                    .getAsDouble();

            double accessibilityScore = json.getAsJsonObject("lighthouseResult")
                    .getAsJsonObject("categories")
                    .getAsJsonObject("accessibility")
                    .get("score")
                    .getAsDouble();

            double bestPracticesScore = json.getAsJsonObject("lighthouseResult")
                    .getAsJsonObject("categories")
                    .getAsJsonObject("best-practices")
                    .get("score")
                    .getAsDouble();

            double seoScore = json.getAsJsonObject("lighthouseResult")
                    .getAsJsonObject("categories")
                    .getAsJsonObject("seo")
                    .get("score")
                    .getAsDouble();

            String htmlReport = report.generateHtmlReport(performanceScore, accessibilityScore, bestPracticesScore, seoScore, urlToTest, strategyused);
            setStatus(performanceScore, accessibilityScore, bestPracticesScore, seoScore,i);
            report.saveHtmlReport(htmlReport,i);
        }
        utility.closeSheet();
    }
    public static void setStatus(double performanceScore,double accessibilityScore,double bestPracticesScore,double seoScore,int i) throws IOException {
       if(performanceScore>0.7 && accessibilityScore>0.7 && bestPracticesScore>0.8 && seoScore>0.8)
            utility.writeStatus("data",i,"Status","Pass");
       else
            utility.writeStatus("data",i,"Status","Fail");
    }
}
