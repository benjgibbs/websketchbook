package base.websketchbook.reddit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
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
		public String domain;
		public String thumbnail;
		public Date created_utc;
		public int ups;
		public String url;
		public String title;

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
