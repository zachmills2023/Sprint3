package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class StockService {
    private static final String API_KEY = "RPNXEEI97ACJ37YI";
    private static final String BASE_URL = "https://www.alphavantage.co/query";

    public void fetchStockData(String symbol)
    {
        String url = BASE_URL + "?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=1min&apikey=" + API_KEY;

        try (CloseableHttpClient httpClient = HttpClients.createDefault())
        {
            HttpGet request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                String result = EntityUtils.toString(entity);
                parseStockData(result, symbol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseStockData(String jsonResponse, String symbol)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonResponse);
            JsonNode timeSeriesNode = rootNode.path("Time Series (1min)");

            if (timeSeriesNode.isMissingNode())
            {
                System.out.println("Error fetching data for symbol: " + symbol);
                return;
            }

            JsonNode latestEntry = timeSeriesNode.elements().next();
            String latestTime = timeSeriesNode.fieldNames().next();
            double latestPrice = latestEntry.path("1. open").asDouble();

            System.out.println("\nLatest data for " + symbol + ":");
            System.out.println("Time: " + latestTime);
            System.out.println("Price: " + latestPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
