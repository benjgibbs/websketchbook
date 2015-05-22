package base.websketchbook.countdown;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.collect.Lists;

public class Solver {

	class Node {
		boolean isWord;
		String word;
		char letter;
		Map<Character, Node> children = new HashMap<>();

		@Override
		public String toString() {
			ToStringHelper helper = MoreObjects.toStringHelper(this);
			helper.add("letter", letter);
			helper.add("isWord", isWord);
			helper.add("children", children);
			return helper.toString();
		}
	}

	Node root = new Node();

	public Solver(String dictionary) throws IOException {
		Path path = Paths.get(dictionary);
		for (String word : Files.readAllLines(path)) {
			final Stack<Node> stack = new Stack<Node>();
			stack.push(root);
			word.chars().forEach(i -> {
				char c = (char) i;
				Node current = stack.peek();
				Node next = current.children.get(c);
				if (next == null) {
					next = new Node();
					next.letter = c;
					current.children.put(c, next);
				}
				stack.push(next);
			});
			stack.peek().isWord = true;
			stack.peek().word = word;
		}
	}

	public void solve(List<Character> letters, List<String> words) {
		solve(letters, root, words);
	}

	private void solve(List<Character> letters, Node node, List<String> words) {
		if (node.word != null) {
			words.add(node.word);
		}

		if (letters.size() == 0) {
			return;
		}

		for (Character c : letters) {
			Node child = node.children.get(c);
			if(child != null) {
				List<Character> subList = Lists.newArrayList(letters);
				subList.remove(c);
				solve(subList, child, words);
			}
		}
	}
}
