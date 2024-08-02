package org.example;

public class Testingcode {
    //    private static String generateHtmlReport(double performanceScore, double accessibilityScore,
//                                             double bestPracticesScore, double seoScore, String url, String strategy) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//        String timestamp = dateFormat.format(new Date());
//
//        // URL or Base64 string for the image
//        String logoUrl = "..//download.png"; // Update this with the correct path or Base64 string
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
//                "        .logo { position: absolute; top: 20px; right: 20px; width: 100px; }\n" +
//                "        h1 { color: #333; }\n" +
//                "        .charts-container { display: flex; justify-content: space-around; margin-top: 20px; }\n" +
//                "        .charts-container canvas { width: 200px; height: 200px; }\n" +
//                "        table { width: 100%; border-collapse: collapse; margin-top: 20px; }\n" +
//                "        table th, table td { padding: 10px; text-align: left; border-bottom: 1px solid #ddd; }\n" +
//                "        table th { background-color: #f8f8f8; }\n" +
//                "        table tr:nth-child(even) { background-color: #f9f9f9; }\n" +
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
//                "        <img src=\"" + logoUrl + "\" alt=\"Logo\" class=\"logo\" />\n" +
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
//                "        <h2>Details</h2>\n" +
//                "        <table class=\"table\">\n" +
//                "            <tr><th>Category</th><th>Score</th></tr>\n" +
//                "            <tr><td>Performance</td><td class=\"score score-performance\">" + String.format("%.2f", performanceScore * 100) + "%</td></tr>\n" +
//                "            <tr><td>Accessibility</td><td class=\"score score-accessibility\">" + String.format("%.2f", accessibilityScore * 100) + "%</td></tr>\n" +
//                "            <tr><td>Best Practices</td><td class=\"score score-best-practices\">" + String.format("%.2f", bestPracticesScore * 100) + "%</td></tr>\n" +
//                "            <tr><td>SEO</td><td class=\"score score-seo\">" + String.format("%.2f", seoScore * 100) + "%</td></tr>\n" +
//                "        </table>\n" +
//                "    </div>\n" +
//                "    <script>\n" +
//                "        function createPieChart(ctx, label, score, color) {\n" +
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
//                "                }\n" +
//                "            });\n" +
//                "        }\n" +
//                "        createPieChart(document.getElementById('performanceChart').getContext('2d'), 'Performance', " + (performanceScore * 100) + ", '#4caf50');\n" +
//                "        createPieChart(document.getElementById('accessibilityChart').getContext('2d'), 'Accessibility', " + (accessibilityScore * 100) + ", '#2196f3');\n" +
//                "        createPieChart(document.getElementById('bestPracticesChart').getContext('2d'), 'Best Practices', " + (bestPracticesScore * 100) + ", '#ff9800');\n" +
//                "        createPieChart(document.getElementById('seoChart').getContext('2d'), 'SEO', " + (seoScore * 100) + ", '#f44336');\n" +
//                "    </script>\n" +
//                "</body>\n" +
//                "</html>";
//    }
}





//////////////////////////////////////////////////////////


//    private static String generateHtmlReport(double performanceScore, double accessibilityScore,
//                                             double bestPracticesScore, double seoScore, String url, String strategy) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//        String timestamp = dateFormat.format(new Date());
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
//                "        table { width: 100%; border-collapse: collapse; margin-top: 20px; }\n" +
//                "        table th, table td { padding: 10px; text-align: left; border-bottom: 1px solid #ddd; }\n" +
//                "        table th { background-color: #f8f8f8; }\n" +
//                "        table tr:nth-child(even) { background-color: #f9f9f9; }\n" +
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
//                "        <h2>Details</h2>\n" +
//                "        <table class=\"table\">\n" +
//                "            <tr><th>Category</th><th>Score</th></tr>\n" +
//                "            <tr><td>Performance</td><td class=\"score score-performance\">" + String.format("%.2f", performanceScore * 100) + "%</td></tr>\n" +
//                "            <tr><td>Accessibility</td><td class=\"score score-accessibility\">" + String.format("%.2f", accessibilityScore * 100) + "%</td></tr>\n" +
//                "            <tr><td>Best Practices</td><td class=\"score score-best-practices\">" + String.format("%.2f", bestPracticesScore * 100) + "%</td></tr>\n" +
//                "            <tr><td>SEO</td><td class=\"score score-seo\">" + String.format("%.2f", seoScore * 100) + "%</td></tr>\n" +
//                "        </table>\n" +
//                "    </div>\n" +
//                "    <script>\n" +
//                "        function createPieChart(ctx, label, score, color) {\n" +
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
//                "                }\n" +
//                "            });\n" +
//                "        }\n" +
//                "        createPieChart(document.getElementById('performanceChart').getContext('2d'), 'Performance', " + (performanceScore * 100) + ", '#4caf50');\n" +
//                "        createPieChart(document.getElementById('accessibilityChart').getContext('2d'), 'Accessibility', " + (accessibilityScore * 100) + ", '#2196f3');\n" +
//                "        createPieChart(document.getElementById('bestPracticesChart').getContext('2d'), 'Best Practices', " + (bestPracticesScore * 100) + ", '#ff9800');\n" +
//                "        createPieChart(document.getElementById('seoChart').getContext('2d'), 'SEO', " + (seoScore * 100) + ", '#f44336');\n" +
//                "    </script>\n" +
//                "</body>\n" +
//                "</html>";
//    }




