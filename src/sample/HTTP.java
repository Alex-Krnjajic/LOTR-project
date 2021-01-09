package sample;

import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    public static Object file;

    private static int responseCode;


    public static void parseJSON() {
        // In java JSONObject is used to create JSON object
        JSONObject json = (JSONObject) file;


        System.out.println(json);
        docs = (JSONArray) json.get("docs");
        resultArray = new String[docs.size()][50];
        System.out.println(docs);

        if (index <= -1){
            System.out.println(docs.size());
            for (int i = 0; i < docs.size(); i++) {
                JSONObject o = (JSONObject) docs.get(i);
                String objString = o.toString();
                System.out.println(objString);
                String[] result = objString.split("[:,{}]");
                resultArray[i] = result;
                System.out.println(Arrays.toString(resultArray[i]));
            }

        }
        else {
            JSONObject o = (JSONObject) docs.get(index);
            String objString = o.toString();
            String[] result = objString.split("(?!\\B\"[^\"]*)[:=,{}](?![^\"]*\"\\B)");
            System.out.println("result :"+result);
            resultArray[index] = result;
        }
    }


    public static int getResponseCode() {
        return responseCode;
    }

    public static void sendGET(String url) throws IOException, ParseException {

        Gson gson = new Gson();

        JSONParser parser = new JSONParser();

        URL obj = new URL("https://the-one-api.dev/v2/"+url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Authorization", "Bearer " + AUTHENTICATION);
        responseCode = con.getResponseCode();
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

            Object gsonResult = gson.fromJson(output,Object.class);

            System.out.println(gsonResult);


            file = parser.parse(output);

            parseJSON();

        } else {
            System.out.println("GET request not worked");
        }

    }
}
