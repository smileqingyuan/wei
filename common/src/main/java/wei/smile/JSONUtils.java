package wei.smile;

import com.alibaba.fastjson.JSONWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JSONUtils {


    public static void main(String[] args) throws IOException {
        JSONWriter writer = new JSONWriter(new FileWriter("hugeArray.json"));
        writer.startArray();
        for (int i = 0; i < 10; ++i) {
            Map<String,String> map= new HashMap<>();
            map.put(""+i,"ww"+i*20);
            writer.writeValue(map);
        }
        writer.endArray();
        writer.close();
    }

}
