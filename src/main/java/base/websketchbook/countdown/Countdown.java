package base.websketchbook.countdown;

import static spark.Spark.get;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Lists;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import freemarker.template.Configuration;

public class Countdown {
	
	final private FreeMarkerEngine fme = new FreeMarkerEngine();
	
	public Countdown() throws IOException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_21);
        cfg.setURLEscapingCharset("UTF-8");
        cfg.setDirectoryForTemplateLoading(new File("src/main/resources/spark/template/freemarker"));
        fme.setConfiguration(cfg);
		
		get("/countdown", (req,res) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("wordList", Lists.newArrayList("a","b","c"));
			return new ModelAndView(model, "countdown.ftl");
		},fme);
	}

}
