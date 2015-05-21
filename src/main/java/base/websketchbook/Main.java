package base.websketchbook;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

import java.io.IOException;

import spark.Spark;
import base.websketchbook.countdown.Countdown;
import base.websketchbook.reddit.RedditApiTest;
import base.websketchbook.whoisthat.WhoIsThat;

import com.google.common.base.Strings;

public class Main {
	
	static Countdown countdown = null;
	static RedditApiTest reddit = null;
	static WhoIsThat whoIsThat = null;
	
	public static void main(String[] args) throws IOException {
		setPort();
		staticFileLocation("/public");
		get("/hello", (req, res) -> "Hello World");
		countdown = new Countdown();
		reddit = new RedditApiTest();
		whoIsThat = new WhoIsThat();
		
	}

	private static void setPort() {
		ProcessBuilder process = new ProcessBuilder();
		String portStr = process.environment().get("PORT");
		int port = 4567;
		if (!Strings.isNullOrEmpty(portStr)) {
			port = Integer.parseInt(portStr);
		}
		Spark.port(port);
	}
}
