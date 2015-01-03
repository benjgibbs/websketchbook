package base.websketchbook.countdown;

import static spark.Spark.get;

public class Countdown {
	
	public Countdown() {
		get("/countdown", (req,res) -> "countdown");
	}

}
