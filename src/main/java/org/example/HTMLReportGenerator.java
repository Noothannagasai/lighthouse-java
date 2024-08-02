//package org.example;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.TimeZone;
//
//public class HTMLReportGenerator {
//    private static String generateHtmlReport(JSONObject jsonReport) throws JSONException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//        String timestamp = dateFormat.format(new Date());
//
//        // Extract data from JSON
//        Double performanceScore = Double.valueOf(jsonReport.getJSONObject("lighthouseResult")
//                .getJSONObject("categories")
//                .getJSONObject("performance")
//                .get("score").toString());
//
//        double accessibilityScore = Double.valueOf(jsonReport.getJSONObject("lighthouseResult")
//                .getJSONObject("categories")
//                .getJSONObject("accessibility")
//                .get("score").toString());
//        double bestPracticesScore = Double.valueOf(jsonReport.getJSONObject("lighthouseResult")
//                .getJSONObject("categories")
//                .getJSONObject("best-practices")
//                .get("score").toString());
//        double seoScore = Double.valueOf(jsonReport.getJSONObject("lighthouseResult")
//                .getJSONObject("categories")
//                .getJSONObject("seo")
//                .get("score").toString());
//        String url = urlToTest;
//        String strategy = strategyused;
//
//        JSONArray performanceDetails = jsonReport.getJSONArray("performance");
//        JSONArray accessibilityDetails = jsonReport.getJSONArray("accessibilityDetails");
//        JSONArray bestPracticesDetails = jsonReport.getJSONArray("bestPracticesDetails");
//        JSONArray seoDetails = jsonReport.getJSONArray("seoDetails");
//
//        return "<!DOCTYPE html>\n" +
//                "<html lang=\"en\">\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
//                "    <title>PageSpeed Insights Report</title>\n" +
//                "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">\n" +
//                "    <style>\n" +
//                "        body { font-family: Arial, sans-serif; background-color: #f4f4f4; color: #333; margin: 0; padding: 20px; }\n" +
//                "        .container { max-width: 900px; margin: auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); }\n" +
//                "        h1 { color: #333; }\n" +
//                "        .charts-container { display: flex; justify-content: space-around; margin-top: 20px; }\n" +
//                "        .charts-container canvas { width: 200px; height: 200px; }\n" +
//                "        .details-container { margin-top: 20px; }\n" +
//                "        .details-container section { margin-bottom: 20px; }\n" +
//                "        .details-container h2 { color: #333; }\n" +
//                "        .details-container table { width: 100%; border-collapse: collapse; margin-top: 10px; }\n" +
//                "        .details-container th, .details-container td { padding: 10px; text-align: left; border-bottom: 1px solid #ddd; }\n" +
//                "        .details-container th { background-color: #f8f8f8; }\n" +
//                "        .details-container tr:nth-child(even) { background-color: #f9f9f9; }\n" +
//                "        .score { font-weight: bold; }\n" +
//                "        .score-performance { color: #4caf50; }\n" +
//                "        .score-accessibility { color: #2196f3; }\n" +
//                "        .score-best-practices { color: #ff9800; }\n" +
//                "        .score-seo { color: #f44336; }\n" +
//                "    </style>\n" +
//                "    <script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "    <div class=\"container\">\n" +
//                "        <h1>PageSpeed Insights Report</h1>\n" +
//                "        <p><strong>URL Tested:</strong> " + url + "</p>\n" +
//                "        <p><strong>Strategy Used:</strong> " + strategy + "</p>\n" +
//                "        <p><strong>Report generated at:</strong> " + timestamp + "</p>\n" +
//                "        <div class=\"charts-container\">\n" +
//                "            <div>\n" +
//                "                <h3>Performance</h3>\n" +
//                "                <canvas id=\"performanceChart\"></canvas>\n" +
//                "            </div>\n" +
//                "            <div>\n" +
//                "                <h3>Accessibility</h3>\n" +
//                "                <canvas id=\"accessibilityChart\"></canvas>\n" +
//                "            </div>\n" +
//                "            <div>\n" +
//                "                <h3>Best Practices</h3>\n" +
//                "                <canvas id=\"bestPracticesChart\"></canvas>\n" +
//                "            </div>\n" +
//                "            <div>\n" +
//                "                <h3>SEO</h3>\n" +
//                "                <canvas id=\"seoChart\"></canvas>\n" +
//                "            </div>\n" +
//                "        </div>\n" +
//                "        <div class=\"details-container\">\n" +
//                "            <section id=\"performanceSection\">\n" +
//                "                <h2>Performance Details</h2>\n" +
//                "                <table class=\"table\">\n" +
//                "                    <tr><th>Metric</th><th>Value</th></tr>\n" +
//                "                    " + generateDetailsRows(performanceDetails) + "\n" +
//                "                </table>\n" +
//                "            </section>\n" +
//                "            <section id=\"accessibilitySection\">\n" +
//                "                <h2>Accessibility Details</h2>\n" +
//                "                <table class=\"table\">\n" +
//                "                    <tr><th>Metric</th><th>Value</th></tr>\n" +
//                "                    " + generateDetailsRows(accessibilityDetails) + "\n" +
//                "                </table>\n" +
//                "            </section>\n" +
//                "            <section id=\"bestPracticesSection\">\n" +
//                "                <h2>Best Practices Details</h2>\n" +
//                "                <table class=\"table\">\n" +
//                "                    <tr><th>Metric</th><th>Value</th></tr>\n" +
//                "                    " + generateDetailsRows(bestPracticesDetails) + "\n" +
//                "                </table>\n" +
//                "            </section>\n" +
//                "            <section id=\"seoSection\">\n" +
//                "                <h2>SEO Details</h2>\n" +
//                "                <table class=\"table\">\n" +
//                "                    <tr><th>Metric</th><th>Value</th></tr>\n" +
//                "                    " + generateDetailsRows(seoDetails) + "\n" +
//                "                </table>\n" +
//                "            </section>\n" +
//                "        </div>\n" +
//                "    </div>\n" +
//                "    <script>\n" +
//                "        function createPieChart(ctx, label, score, color, sectionId) {\n" +
//                "            new Chart(ctx, {\n" +
//                "                type: 'pie',\n" +
//                "                data: {\n" +
//                "                    labels: [label, 'Remaining'],\n" +
//                "                    datasets: [{\n" +
//                "                        data: [score, 100 - score],\n" +
//                "                        backgroundColor: [color, '#e0e0e0'],\n" +
//                "                        borderColor: '#fff',\n" +
//                "                        borderWidth: 1\n" +
//                "                    }]\n" +
//                "                },\n" +
//                "                options: {\n" +
//                "                    responsive: true,\n" +
//                "                    plugins: {\n" +
//                "                        legend: {\n" +
//                "                            position: 'bottom',\n" +
//                "                        },\n" +
//                "                        tooltip: {\n" +
//                "                            callbacks: {\n" +
//                "                                label: function(tooltipItem) {\n" +
//                "                                    return tooltipItem.label + ': ' + tooltipItem.raw.toFixed(2) + '%';\n" +
//                "                                }\n" +
//                "                            }\n" +
//                "                        }\n" +
//                "                    }\n" +
//                "                },\n" +
//                "                plugins: [{\n" +
//                "                    afterRender: function(chart) {\n" +
//                "                        chart.canvas.addEventListener('click', function() {\n" +
//                "                            document.getElementById(sectionId).scrollIntoView({ behavior: 'smooth' });\n" +
//                "                        });\n" +
//                "                    }\n" +
//                "                }]\n" +
//                "            });\n" +
//                "        }\n" +
//                "        createPieChart(document.getElementById('performanceChart').getContext('2d'), 'Performance', " + (performanceScore * 100) + ", '#4caf50', 'performanceSection');\n" +
//                "        createPieChart(document.getElementById('accessibilityChart').getContext('2d'), 'Accessibility', " + (accessibilityScore * 100) + ", '#2196f3', 'accessibilitySection');\n" +
//                "        createPieChart(document.getElementById('bestPracticesChart').getContext('2d'), 'Best Practices', " + (bestPracticesScore * 100) + ", '#ff9800', 'bestPracticesSection');\n" +
//                "        createPieChart(document.getElementById('seoChart').getContext('2d'), 'SEO', " + (seoScore * 100) + ", '#f44336', 'seoSection');\n" +
//                "    </script>\n" +
//                "</body>\n" +
//                "</html>";
//    }
//
//    private static String generateDetailsRows(JSONArray details) throws JSONException {
//        StringBuilder rows = new StringBuilder();
//        for (int i = 0; i < details.length(); i++) {
//            JSONObject detail = details.getJSONObject(i);
//            rows.append("<tr><td>").append(detail.getString("metric")).append("</td><td>").append(detail.getString("value")).append("</td></tr>");
//        }
//        return rows.toString();
//    }
//}














///////////////////////////////////////////////////////////////////////
//    private static String generateHtmlReport(double performanceScore, double accessibilityScore,
//                                             double bestPracticesScore, double seoScore, String url, String strategy) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//        String timestamp = dateFormat.format(new Date());
//
//        // Generating HTML content with styling and charts
//        return "<!DOCTYPE html>\n" +
//                "<html>\n" +
//                "<head>\n" +
//                "<meta charset=\"UTF-8\">\n" +
//                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
//                "<title>PageSpeed Insights Report</title>\n" +
//                "<style>\n" +
//                "body { font-family: Arial, sans-serif; background-color: #f4f4f4; color: #333; margin: 0; padding: 20px; }\n" +
//                "h1 { color: #333; }\n" +
//                ".container { max-width: 900px; margin: auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); }\n" +
//                ".charts-container { display: flex; justify-content: space-around; margin-top: 20px; }\n" +
//                ".charts-container canvas { width: 200px; height: 200px; }\n" +
//                "table { width: 100%; border-collapse: collapse; margin-top: 20px; }\n" +
//                "table th, table td { padding: 10px; text-align: left; border-bottom: 1px solid #ddd; }\n" +
//                "table th { background-color: #f8f8f8; }\n" +
//                "table tr:nth-child(even) { background-color: #f9f9f9; }\n" +
//                ".score { font-weight: bold; }\n" +
//                ".score-performance { color: #4caf50; }\n" +
//                ".score-accessibility { color: #2196f3; }\n" +
//                ".score-best-practices { color: #ff9800; }\n" +
//                ".score-seo { color: #f44336; }\n" +
//                "</style>\n" +
//                "<script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "<div class=\"container\">\n" +
//                "<h1>PageSpeed Insights Report</h1>\n" +
//                "<p><strong>URL Tested:</strong> " + url + "</p>\n" +
//                "<p><strong>Strategy Used:</strong> " + strategy + "</p>\n" +
//                "<p><strong>Report generated at:</strong> " + timestamp + "</p>\n" +
//                "<h2>Scores</h2>\n" +
//                "<div class=\"charts-container\">\n" +
//                "<div><h3>Performance</h3><canvas id=\"performanceChart\"></canvas></div>\n" +
//                "<div><h3>Accessibility</h3><canvas id=\"accessibilityChart\"></canvas></div>\n" +
//                "<div><h3>Best Practices</h3><canvas id=\"bestPracticesChart\"></canvas></div>\n" +
//                "<div><h3>SEO</h3><canvas id=\"seoChart\"></canvas></div>\n" +
//                "</div>\n" +
//                "<h2>Details</h2>\n" +
//                "<table>\n" +
//                "<tr><th>Category</th><th>Score</th></tr>\n" +
//                "<tr><td>Performance</td><td class=\"score score-performance\">" + String.format("%.2f", performanceScore * 100) + "%</td></tr>\n" +
//                "<tr><td>Accessibility</td><td class=\"score score-accessibility\">" + String.format("%.2f", accessibilityScore * 100) + "%</td></tr>\n" +
//                "<tr><td>Best Practices</td><td class=\"score score-best-practices\">" + String.format("%.2f", bestPracticesScore * 100) + "%</td></tr>\n" +
//                "<tr><td>SEO</td><td class=\"score score-seo\">" + String.format("%.2f", seoScore * 100) + "%</td></tr>\n" +
//                "</table>\n" +
//                "<script>\n" +
//                "function createPieChart(ctx, label, score, color) {\n" +
//                "    new Chart(ctx, {\n" +
//                "        type: 'pie',\n" +
//                "        data: {\n" +
//                "            labels: [label, 'Remaining'],\n" +
//                "            datasets: [{\n" +
//                "                data: [score, 100 - score],\n" +
//                "                backgroundColor: [color, '#e0e0e0'],\n" +
//                "                borderColor: '#fff',\n" +
//                "                borderWidth: 1\n" +
//                "            }]\n" +
//                "        },\n" +
//                "        options: {\n" +
//                "            responsive: true,\n" +
//                "            plugins: {\n" +
//                "                legend: {\n" +
//                "                    position: 'bottom',\n" +
//                "                },\n" +
//                "                tooltip: {\n" +
//                "                    callbacks: {\n" +
//                "                        label: function(tooltipItem) {\n" +
//                "                            return tooltipItem.label + ': ' + tooltipItem.raw.toFixed(2) + '%';\n" +
//                "                        }\n" +
//                "                    }\n" +
//                "                }\n" +
//                "            }\n" +
//                "        }\n" +
//                "    });\n" +
//                "}\n" +
//                "createPieChart(document.getElementById('performanceChart').getContext('2d'), 'Performance', " + (performanceScore * 100) + ", '#4caf50');\n" +
//                "createPieChart(document.getElementById('accessibilityChart').getContext('2d'), 'Accessibility', " + (accessibilityScore * 100) + ", '#2196f3');\n" +
//                "createPieChart(document.getElementById('bestPracticesChart').getContext('2d'), 'Best Practices', " + (bestPracticesScore * 100) + ", '#ff9800');\n" +
//                "createPieChart(document.getElementById('seoChart').getContext('2d'), 'SEO', " + (seoScore * 100) + ", '#f44336');\n" +
//                "</script>\n" +
//                "</div>\n" +
//                "</body>\n" +
//                "</html>";
//    }
