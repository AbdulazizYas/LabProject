import java.util.ArrayList;

public class Trie {
  private TrieNode root;
  private int size;

  public Trie() {
    root = new TrieNode();
    size = 0;
  }

  // NOT TESTED
  public boolean contains(String word) {
    TrieNode p = root;

    int wordLen = word.length();

    for (int i = 0; i < wordLen; i++) {
      char ch = word.charAt(i);
      p = p.getChilds()[p.IndexOf(ch)];

      if (p == null)
        return false;
    }
    return p.isWord();
  }

  public boolean isPrefix(String prefix) {
    TrieNode p = root;

    int wordLen = prefix.length();

    for (int i = 0; i < wordLen; i++) {
      char ch = prefix.charAt(i);
      p = p.getChilds()[p.IndexOf(ch)];

      if (p == null)
        return false;
    }
    return true;
  }

  public void insert(String word) {
    if (word == null || !word.matches("^[A-Z]*$"))
      return;

    TrieNode p = root;

    for (char ch : word.toCharArray()) {
      if (p.getChilds()[p.IndexOf(ch)] != null) {
        p = p.getChilds()[p.IndexOf(ch)];
      } else {
        TrieNode nw = new TrieNode();
        size++;
        nw.setData(String.valueOf(ch));
        p.getChilds()[p.IndexOf(ch)] = nw;
        p = p.getChilds()[p.IndexOf(ch)];
      }
    }

    p.setIsWord(true);
  }

  // helper method
  private boolean delete(TrieNode p, String word, int index) {

    if (index == word.length()) {
      if (p.isLeaf()) {
        size--;
        return true;
      } else {
        p.setIsWord(false);
        return false;
      }
    }

    TrieNode node = p.getChilds()[p.IndexOf(word.charAt(index))];

    boolean shouldDeleted = delete(node, word, index + 1);

    if (shouldDeleted) {

      p.getChilds()[p.IndexOf(word.charAt(index))] = null;

      if (p.isLeaf() && !p.isWord()) {
        size--;
        return true;
      } else {
        return false;
      }
    }

    return false;
  }

  public void delete(String word) {
    if (contains(word))
      delete(root, word, 0);
  }

  public boolean isEmpty() {
    for (int i = 0; i < root.getChilds().length; i++) {
      if (root.getChilds()[i] != null) {
        return false;
      }
    }
    return true;
  }

  public void clear() {
    this.root = new TrieNode();
    size = 0;
  }

  public String[] allWordsPrefix(String prefix) {
    TrieNode p = root;

    int wordLen = prefix.length();

    for (int i = 0; i < wordLen; i++) {
      char ch = prefix.charAt(i);
      p = p.getChilds()[p.IndexOf(ch)];

      if (p == null)
        return new String[0];
    }
    return allWordsPrefix(prefix, p, new ArrayList<String>());
  }

  public String[] allWordsPrefix(String prefix, TrieNode n, ArrayList<String> words) {
    if (n == null) {
      String[] arr = new String[words.size()];
      arr = words.toArray(arr);
      return arr;
    }

    if (n.isWord())
      words.add(prefix);

    for (TrieNode node : n.getChilds())
      if (node != null)
        allWordsPrefix(prefix + (char) ('A' + node.IndexOf(node.getData().charAt(0))), node, words);

    String[] arr = new String[words.size()];
    arr = words.toArray(arr);
    return arr;
  }

  public int size() {
    return this.size;
  }
}
