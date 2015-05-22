package base.websketchbook.countdown;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

public class SolverTest {

	private Solver solver;
	
	@Before
	public void setup() throws IOException {
		solver = new Solver("src/test/resources/testwords");
	}
	
	@Test
	public void test() {
		List<String> words = new ArrayList<>();
		solver.solve(Lists.newArrayList('c','t','x','a'), words);
		assertThat(words).contains("cat");
	}
	
	@Test 
	public void checkFullWordsCanBeLoaded() throws IOException {
		List<String> words = new ArrayList<>();
		solver = new Solver("/usr/share/dict/words");
		solver.solve(Lists.newArrayList('c','t','x','a'), words);
		assertThat(words).contains("cat","tax","a", "at");
	}

}
