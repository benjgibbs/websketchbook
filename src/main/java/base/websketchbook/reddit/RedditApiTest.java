package base.websketchbook.reddit;

import static spark.Spark.get;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import base.websketchbook.reddit.JsonParser.Link;
import base.websketchbook.reddit.JsonParser.LinkData;
import base.websketchbook.reddit.JsonParser.SubReddit;

public class RedditApiTest {
	
	FreeMarkerEngine fme = new FreeMarkerEngine();

	public RedditApiTest() throws IOException {
		get("/reddit", (req, res) -> {
			Map<String, Object> attribs = new HashMap<>();
			try {
				Object sub = req.queryParams("sub");
				Object filter = req.queryParams("filter");
				String subStr = sub != null ? (String)sub : "aww"; 
				attribs.put("subreddit", subStr);

				SubReddit sr = getReddit(subStr);
				List<LinkData> linkData = new ArrayList<>();
				for (Link link : sr.data.children) {
					if(filter != null && !link.data.domain.equals(filter)){
						continue;
					}
					linkData.add(link.data);
				}
				attribs.put("listoflinks", linkData);

			} catch (Exception e) {
				attribs.put("error", e);
			}

			return new ModelAndView(attribs, "reddit.ftl");
		}, fme);
	}

	SubReddit getReddit(String subreddit) throws Exception {
		URL reddit = new URL("https://www.reddit.com/r/" + subreddit + "/top.json?limit=100");
		InputStreamReader isr = new InputStreamReader(reddit.openStream());
		SubReddit fromJson = JsonParser.read(isr);
		return fromJson;
	}
	
	SubReddit getFileReddit(String subreddit) throws Exception {
		InputStream stream = this.getClass().getResourceAsStream("/aww.json");
		InputStreamReader isr = new InputStreamReader(stream);

		SubReddit fromJson = JsonParser.read(isr);
		return fromJson;
	}

}
