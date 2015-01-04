package base.websketchbook.reddit;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

public class UserAgentCheck {

    @Test
    public void test() throws Exception {
        String url = "http://whatismyuseragent.com";
        URL test = new URL(url);
        URLConnection connection = test.openConnection();
        connection.setRequestProperty("User-Agent", "apitest/1.0 by u/bengibbs");
        InputStreamReader isr = new InputStreamReader(connection.getInputStream());
        
        char[] buff = new char[1024];
        StringBuilder builder = new StringBuilder();
        while(isr.read(buff) != -1){
            builder.append(buff);
        }
        String str = builder.toString();
        System.out.println(str);
    }

}
