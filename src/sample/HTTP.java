package sample;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class HTTP {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String AUTHENTICATION = "OrpLanM2WUiEppeeg6CW";

    public static int index;

    public static String output = "";

    String[] result;

    public static String[][] resultArray;

    public static JSONArray docs;


    public static void parseJSON(Object file) {
        // In java JSONObject is used to create JSON object
        JSONObject json = (JSONObject) file;


        System.out.println(json);
        docs = (JSONArray) json.get("docs");
        System.out.println(docs);

        if (index <= -1){
            System.out.println(docs.size());
            for (int i = 0; i < docs.size(); i++) {
                JSONObject o = (JSONObject) docs.get(i);
                String objString = o.toString();
                String[] result = objString.split("[:,{}]");
                System.out.println("result :"+Arrays.toString(result));
                String[][] resultArray = new String[docs.size()][result.length];
                for(int j = 0; j< result.length; j++){
                    resultArray[i][j] = result[j];
                }
                //resultArray[i] = result;
                System.out.println(Arrays.toString(resultArray[i]));


                HTTP.resultArray = resultArray;
                //System.out.println(Arrays.toString(HTTP.resultArray[i]));
                if(i>0) {
                    System.out.println(Arrays.toString(resultArray[i-1]));
                    //System.out.println(Arrays.toString(HTTP.resultArray[i-1]));
                }
            }
            System.out.println(Arrays.toString(HTTP.resultArray[0]));
        }
        else {
            JSONObject o = (JSONObject) docs.get(index);
            String objString = o.toString();
            String[] result = objString.split("[:,{}]");
            System.out.println("result :"+result);
            String[][] resultArray = new String[docs.size()][result.length];
            resultArray[index] = result;

            HTTP.resultArray = resultArray;
        }
    }


    public static void sendGET(String url) throws IOException {
        URL obj = new URL("https://the-one-api.dev/v2/"+url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Authorization", "Bearer " + AUTHENTICATION);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            output = response.toString();
            Object file = JSONValue.parse(output);

            parseJSON(file);

        } else {
            System.out.println("GET request not worked");
        }

    }
}
