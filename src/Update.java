package src;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Update{
    public static String data;
    // Use JsonObject to convert to JSON, and then pull the data from that.

    public static String getData(){
        return data;
    }
    
    public static void points(){
        String API_Key = "key=" + "AIzaSyC3AAzbHKTV3G1Brywmak8uhbhYLaFN9AI";
        final String Grid_Data = "includeGridData=" + "true";
        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(
            URI.create("https://sheets.googleapis.com/v4/spreadsheets/1L88aiK9tt3bM2OZzvztkVLktJeiBv1XYzeIjrTg6vPE?"+API_Key+"&" +Grid_Data))
            .build();

        client
            .sendAsync(request, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(Update::updateData)
            .join();
    }

    private static void updateData(String n){
        data = n;
        System.out.println("Data updated.");
    }

    public static void JSON(){
        
    }
}