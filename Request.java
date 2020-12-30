import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class Request{
    // Instance Variables
    private static String data;
    private static int maxRows;

    // Accessor Methods
    public static String getData(){
        return data;
    }
    public static int getMaxRows(){
        return maxRows;
    }
    
    // Mutator Methods
    private static void setData(String n){
        data = n;
        System.out.println("Data updated.");
    }
    private static void setMaxRows(){
        maxRows = new JSONObject(data)
            .getJSONArray("sheets")
            .getJSONObject(0)
            .getJSONArray("data")
            .getJSONObject(0)
            .getJSONArray("rowData").length();
    }

    // Request HTTP data from the Google Sheets API
    public static void pull(){
        final String API_Key = "key=" + "AIzaSyC3AAzbHKTV3G1Brywmak8uhbhYLaFN9AI";
        final String Grid_Data = "includeGridData=" + "true";

        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(
            URI.create("https://sheets.googleapis.com/v4/spreadsheets/1L88aiK9tt3bM2OZzvztkVLktJeiBv1XYzeIjrTg6vPE?"+API_Key+"&" +Grid_Data))
            .build();

        client
            .sendAsync(request, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(Request::setData)
            .join();
        
        setMaxRows();
    }
    
    // Create a member datatype from extracted data
    public static Member member(int row){
        Member member = new Member();
        HashMap<String, HashMap<String, String>> info = extractMember(row);

        info.forEach((key, map) -> {
            if(key.equals("General")){
                String last = map.get("Last Name");
                String first = map.get("First Name");

                member.setLast(last);
                member.setFirst(first);
            } else {
                // Create a month datatype for each hashmap
                Month month = new Month(key);
                map.forEach((name, points) -> {
                    Event event = new Event(name, points);
                    month.addEvent(event);
                });
                member.addMonth(month);
            }
        });
        return member;
    }
    
    // Returns only the necessary data from the Google Sheets HTTP request
    private static HashMap<String, HashMap<String, String>> extractMember(int memRow){
        // JSONArray containing all important data to extract
        JSONArray array = new JSONObject(data).getJSONArray("sheets");

        // Hashmap which holds all of the member info to be returned
        HashMap<String, HashMap<String, String>> memberInfo = new HashMap<String, HashMap<String, String>>();

        // Iterates through all sheets
        for(int i=0; i<array.length(); i++){
            // Creates an events HashMap for each sheet to later append to the memberInfo hashmap
            HashMap<String, String> events = new HashMap<String, String>();

            // Creates a title to later append to the memberInfo hashmap
            String title = array.getJSONObject(i).getJSONObject("properties").getString("title");

            // The data from specifically one sheet
            JSONArray sheet = array
                .getJSONObject(i)
                .getJSONArray("data")
                .getJSONObject(0)
                .getJSONArray("rowData");
            
            // eventRow JSONArray will provide the key values
            JSONArray eventRow = sheet
                .getJSONObject(0)
                .getJSONArray("values");

            // All values for a particular row in one month sheet
            for(int j=0; j<eventRow.length(); j++){
                // The data from specifically one row
                JSONArray memberRow = sheet
                    .getJSONObject(memRow-1)
                    .getJSONArray("values");
            
                // Presetting values in the event that the value doesn't exist
                String header = "Blank Header";
                String value = "Blank";
            
                // Try block for error handling
                try {
                    // Extract value as string if it exists and is a string
                    header = eventRow.getJSONObject(j).getJSONObject("effectiveValue").getString("stringValue");
                    value = memberRow.getJSONObject(j).getJSONObject("effectiveValue").getString("stringValue");
                } catch (Exception e) {

                    try{
                        // Extract value as string if it exists and is an integer
                        Integer temp = memberRow.getJSONObject(j).getJSONObject("effectiveValue").getInt("numberValue");
                        value = temp.toString();
                    } catch (Exception ex){}

                } finally {
                    // Escape lines to get rid of unnecessary key/value pairs
                    if(header.equals("Blank Header") || value.equals("Blank")) continue;
                        else if(header.equals("VLOOKUP NAME")) continue; 
                        else if(!title.equals("General") && (header.equals("First Name") || header.equals("Last Name"))) continue;
                        else if(!title.equals("General") && header.equals(title + " Points")) continue;
                    events.put(header, value);
                }
            }
            // Insert info for new sheet into the HashMap
            memberInfo.put(title, events);
        }
        
        return memberInfo;
    }

}
