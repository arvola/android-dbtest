package com.arvola.android.dbtest;

import android.content.Context;
import android.util.Log;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class JsonLoader {

    final Gson gson;

    Context context;

    public JsonLoader(Context context) {
        this.context = context;

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Calendar.class, new CalendarDeserializer());
        gson = builder.create();
    }

    public Gson getGson() {
        return gson;
    }

    public String loadStudents() {
        return loadAssetTextAsString("students.json");
    }

    /**
     * From http://stackoverflow.com/a/23952928
     *
     * @param name Asset file name
     * @return File contents as String
     */
    private String loadAssetTextAsString(String name) {
        BufferedReader in = null;
        try {
            StringBuilder buf = new StringBuilder();
            InputStream is = context.getAssets().open(name);
            in = new BufferedReader(new InputStreamReader(is));

            String str;
            boolean isFirst = true;
            while ( (str = in.readLine()) != null ) {
                if (isFirst)
                    isFirst = false;
                else
                    buf.append('\n');
                buf.append(str);
            }
            return buf.toString();
        } catch (IOException e) {
            Log.e("JsonLoader", "Error opening asset " + name);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    Log.e("JsonLoader", "Error closing asset " + name);
                }
            }
        }

        return null;
    }

    static class CalendarDeserializer implements JsonDeserializer<Calendar> {
        @Override
        public Calendar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            try {
                cal.setTime(sdf.parse(json.getAsString()));// all done
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return cal;
        }
    }
}
