package base.websketchbook.reddit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class JsonParser {
    static class SubReddit {
        String kind;
        SubData data;
    }

    static class SubData {
        String modhash;
        Link[] children;
    }

    static class Link {
        String kind;
        LinkData data;
    }

    public static class LinkData {
        String domain;
        String thumbnail;
        Date created_utc;
        int ups;
        String url;
        String title;
        String author;
        String name;

        public static DateFormat formater = new SimpleDateFormat();

        public String getThumb() {
            return thumbnail;
        }

        public String getUrl() {
            return url;
        }

        public String getTitle() {
            return title;
        }

        public String getDomain() {
            return domain;
        }

        public String getCreated() {
            return formater.format(created_utc);
        }

        public String getAuthor() {
            return author;
        }
        
        public String getName() {
            return name;
        }
    }

    static class JsonDateDeserializer implements JsonDeserializer<Date> {
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return new Date(json.getAsJsonPrimitive().getAsLong() * 1000);
        }
    }

    static SubReddit read(InputStreamReader isr) {
        BufferedReader br = new BufferedReader(isr);
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDateDeserializer()).create();
        SubReddit fromJson = gson.fromJson(br, JsonParser.SubReddit.class);
        return fromJson;
    }
}
