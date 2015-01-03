package base.websketchbook.reddit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import base.websketchbook.reddit.JsonParser.Link;
import base.websketchbook.reddit.JsonParser.SubReddit;

public class JsonTest {

	@Test
	public void test() {
		InputStream stream = this.getClass().getResourceAsStream("/aww.json");
		InputStreamReader isr = new InputStreamReader(stream);
		SubReddit fromJson = JsonParser.read(isr);
		
		assertThat(fromJson.data, notNullValue());
		assertThat(fromJson.data.children, notNullValue());
		int i = 0;
		for(Link ld : fromJson.data.children) {
			assertThat(ld.data.url, notNullValue());
			assertThat(ld.data.thumbnail, notNullValue());
			assertThat(ld.data.created_utc, notNullValue());
			System.out.println(++i + ": " + ld.data.created_utc  + " " + ld.data.url);
		}
	}
	
	@Test
	public void testGettingAUrlFromReddit() throws Exception {
		RedditApiTest api = new RedditApiTest();
		SubReddit fromJson = api.getReddit("aww");
		int i = 0;
		for(Link ld : fromJson.data.children) {
			assertThat(ld.data.url, notNullValue());
			assertThat(ld.data.thumbnail, notNullValue());
			assertThat(ld.data.created_utc, notNullValue());
			System.out.println(++i + ": " + ld.data.created_utc  + " " + ld.data.url);
		}
		assertThat(i, equalTo(100));
		
	}
	

}
