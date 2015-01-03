package base.websketchbook.reddit;

import static spark.Spark.get;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Strings;

import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import base.websketchbook.reddit.JsonParser.Link;
import base.websketchbook.reddit.JsonParser.LinkData;
import base.websketchbook.reddit.JsonParser.SubReddit;

public class RedditApiTest {

    final private FreeMarkerEngine fme = new FreeMarkerEngine();
    final private String UserAgent = "apitest/1.0 by u/bengibbs";

    public RedditApiTest() throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_21);
        cfg.setURLEscapingCharset("UTF-8");
        cfg.setDirectoryForTemplateLoading(new File("src/main/resources/spark/template/freemarker"));
        fme.setConfiguration(cfg);

        get("/reddit", (req, res) -> {
            Map<String, Object> attribs = new HashMap<>();
            String prevLink = "/reddit?";
            String nextLink = "/reddit?";
            String subStr = "aww";
            List<LinkData> linkData = new ArrayList<>();

            try {
                Object sub = req.queryParams("sub");
                if (sub != null) {
                    subStr = (String) sub;
                    prevLink += "sub=" + subStr;
                    nextLink += "sub=" + subStr;
                }

                Object filter = req.queryParams("filter");
                Object before = req.queryParams("filter");
                Object after = req.queryParams("after");

                attribs.put("subreddit", subStr);

                SubReddit sr = getReddit(subStr, (String) before, (String) after);

                Link[] children = sr.data.children;
                for (Link link : children) {
                    if (filter != null && !link.data.domain.equals(filter)) {
                        continue;
                    }
                    linkData.add(link.data);
                }

                if (children.length > 0) {
                    prevLink = "/reddit?sub=" + subStr + "&before=" + children[0].data.name;
                    nextLink = "/reddit?sub=" + subStr + "&after=" + children[children.length - 1].data.name;
                }

            } catch (Exception e) {
                System.err.println(String.format("Error processing template: %s", e));
                attribs.put("error", e);
            } finally {
                attribs.put("listoflinks", linkData);
                attribs.put("prevLink", prevLink);
                attribs.put("nextLink", nextLink);
            }
            return new ModelAndView(attribs, "reddit.ftl");
        }, fme);
    }

    @VisibleForTesting
    SubReddit getReddit(String subreddit, String before, String after) throws Exception {
        String url = createUrl(subreddit, before, after);
        URL reddit = new URL(url);
        URLConnection connection = reddit.openConnection();
        connection.setRequestProperty("User-Agent", UserAgent);
        InputStreamReader isr = new InputStreamReader(connection.getInputStream());
        SubReddit fromJson = JsonParser.read(isr);
        isr.close();
        return fromJson;
    }

    private String createUrl(String subreddit, String before, String after) {
        String url = "http://www.reddit.com/r/" + subreddit + "/top.json?limit=100";
        if (!Strings.isNullOrEmpty(before)) {
            url += "&before=" + before;
        } else if (!Strings.isNullOrEmpty(after)) {
            url += "&after=" + after;
        }
        return url;
    }

    @VisibleForTesting
    SubReddit getFileReddit(String subreddit) throws Exception {
        InputStream stream = this.getClass().getResourceAsStream("/aww.json");
        InputStreamReader isr = new InputStreamReader(stream);
        SubReddit fromJson = JsonParser.read(isr);
        return fromJson;
    }

}
