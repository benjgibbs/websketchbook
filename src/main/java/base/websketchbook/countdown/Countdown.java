package base.websketchbook.countdown;

import static spark.Spark.get;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.base.Throwables;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import freemarker.template.Configuration;

public class Countdown {
	
	private static final String TEMPLATE = "src/main/resources/spark/template/freemarker";

	final private FreeMarkerEngine fme = new FreeMarkerEngine();
	
	final private Solver solver; 
	
	public Countdown() throws IOException {
		solver = createSolver();
		
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_21);
        cfg.setURLEscapingCharset("UTF-8");
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE));
        fme.setConfiguration(cfg);
		
		get("/countdown", (req,res) -> {
			String letters = req.queryParams("letters");
			List<String> words = new ArrayList<>();
			if(letters != null){
				letters = letters.toLowerCase();
				List<Character> letterList = new ArrayList<>();
				for(int i = 0; i < letters.length(); i++){
					char c = letters.charAt(i);
					if(c >= 'a' && c <= 'z') {
						letterList.add(c);
					}
				}
				solver.solve(letterList, words);
			}
			
			words.sort( (a,b) -> b.length()-a.length());
			List<String> withLengths = words.stream()
					.filter( w -> w.length() > 3)
					.map(w -> w.toLowerCase())
					.distinct()
					.map(w -> (w + ": " + w.length() + createUrl(w)))
					.collect(Collectors.toList());
			
			Map<String, Object> model = new HashMap<>();
			
			model.put("wordlist", withLengths);
			return new ModelAndView(model, "countdown.ftl");
		},fme);
	}

	private Solver createSolver() {
		Solver solver = null;
		try {
			solver = new Solver("src/main/resources/words.txt");
		} catch(IOException e){
			Throwables.propagate(e);
		}
		
		return solver;
	}

	private String createUrl(String w) {
		return " <a href=\"http://www.oxforddictionaries.com/definition/english/" + w + "\">oxford</a> " +
			   " <a href=\"http://dictionary.cambridge.org/dictionary/british/" + w + "\">cambridge</a>";
	}

}
