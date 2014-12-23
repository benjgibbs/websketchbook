package base.websketchbook;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;
import spark.Spark;

import com.google.common.base.Strings;

public class Main {
    public static void main(String[] args) {
	ProcessBuilder process = new ProcessBuilder();
	String portStr = process.environment().get("PORT");
	int port = 4567;
	if(!Strings.isNullOrEmpty(portStr)){
	    port = Integer.parseInt(portStr);
	}
	Spark.port(port);
	staticFileLocation("/public");
	get("/hello", (req, res) -> "Hello World");
    }
}
