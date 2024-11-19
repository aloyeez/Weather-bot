import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather {
    public String getAddress(String city) throws IOException, ParseException {
        String address = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=e27595b05f00953dcf40d00fefb80e3d&units=metric&lang=ua";

        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int code = connection.getResponseCode();
        System.out.println(code);
        if (code == 200) {
            InputStreamReader in = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String data = "";
            while (br.ready()) {
                data += br.readLine();
            }
            System.out.println(data);
            FileWriter fw = new FileWriter("data.json");
            fw.write(data);

            fw.close();

            // process received information
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(data);
            JSONObject main = (JSONObject) object.get("main");
            var temp = main.get("temp");
            return (temp.toString());


        }
        return "Oops!";
    }
}
