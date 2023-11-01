import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.List;

public class JsonCvsConverter extends FormatConverter{

    public JsonCvsConverter() {
        super(Formato.JSON, Formato.CSV);
    }

    @Override
    public boolean convertAtoB(Archivo a, Archivo b) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(b.getFile()))){
            JSONArray jsonArray = new JSONArray(getJsonData(a));
            JSONObject firstObject = jsonArray.getJSONObject(0);
            String[] header = firstObject.keySet().toArray(new String[0]);
            writer.writeNext(header);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String[] data = new String[header.length];
                for (int j = 0; j < header.length; j++) {
                    data[j] = jsonObject.optString(header[j], "");
                }
                writer.writeNext(data);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean convertBtoA(Archivo b, Archivo a) {
        JSONArray jsonArray = new JSONArray();
        try (CSVReader reader = new CSVReader(new FileReader(b.getFile()))){
            List<String[]> lines = reader.readAll();
            String[] header = lines.get(0);

            for (int i = 1; i < lines.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                String[] data = lines.get(i);
                for (int j = 0; j < header.length; j++) {
                    jsonObject.put(header[j], data[j]);
                }
                jsonArray.put(jsonObject);
            }
            try (FileWriter fileWriter = new FileWriter(a.getFile())) {
                fileWriter.write(jsonArray.toString());
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private String getJsonData(Archivo archivo){;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo.getFile()))) {
            StringBuilder jsonStringBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonStringBuilder.append(line);
            }
            return jsonStringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
