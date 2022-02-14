package core.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class Trie {
 
    private Node root;
 
    public Trie(List<String> words) {
        root = new Node();
        for (String word : words)
            root.insert(word);
 
    }
 
    private boolean find(String prefix, boolean exact) {
        Node current = root;
        
        for (char c : prefix.toCharArray()) {
            current = current.children.get(c);
            if (current == null)
                return false;
        }
        return !exact || current.isWord;
    }
 
    public boolean find(String prefix) {
        return find(prefix, false);
    }
 
    public void suggestHelper(Node root, List<String> list, StringBuffer curr) {
        if (root.isWord) {
            list.add(curr.toString());
        }
 
        if (root.children == null || root.children.isEmpty())
            return;
 
        for (Node child : root.children.values()) {
            suggestHelper(child, list, curr.append(child.c));
            curr.setLength(curr.length() - 1);
        }
    }
 
    public List<String> suggest(String prefix) {
        List<String> list = new ArrayList<>();
        Node lastNode = root;
        StringBuffer curr = new StringBuffer();
        
        for (char c : prefix.toCharArray()) {
            lastNode = lastNode.children.get(c);
            if (lastNode == null)
            	return list;
            
            curr.append(c);
        }
        
        suggestHelper(lastNode, list, curr);
        return list;
    }
    
	class Node {
        Map<Character, Node> children;
        char c;
        boolean isWord;
 
        public Node(char c) {
            this.c = c;
            children = new HashMap<>();
        }
 
        public Node() {
            children = new HashMap<>();
        }
 
        public void insert(String word) {
            if (word == null || word.isEmpty())
                return;
            
            char currChar = word.charAt(0);
            Node child = children.get(currChar);
            
            if (child == null) {
                child = new Node(currChar);
                children.put(currChar, child);
            }
 
            if (word.length() > 1)
                child.insert(word.substring(1));
            else
                child.isWord = true;
        }
 
    }
}
