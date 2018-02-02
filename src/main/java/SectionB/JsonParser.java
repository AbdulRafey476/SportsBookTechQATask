package SectionB;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class JsonParser {

    public static Map<String, String> jsonParse(String jsonNode1, String jsonNode2, String filePath) {

        JSONParser parser = new JSONParser();
        Map<String, String> jsonList = new HashMap<String, String>();

        try {

            Object object = parser.parse(new FileReader(filePath));

            JSONArray jasonArray = (JSONArray) object;

            for (int i = 0; i < jasonArray.size(); i++) {

                String jsonString = jasonArray.get(i).toString();
                JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonString);
                jsonList.put((String) jsonObject.get(jsonNode1), (String) jsonObject.get(jsonNode2));
            }

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonList;
    }
}

