public class TrieNode {

  private TrieNode[] childs;
  private String data;
  private boolean isWord;

  public TrieNode() {
    childs = new TrieNode[26];
    data = "";
    isWord = false;

    for (int i = 0; i < childs.length; i++)
      childs[i] = null;
  }

  public int IndexOf(char ch) {
    return (ch - 'A');
  }

  public boolean isLeaf() {
    for (TrieNode n : childs) {
      if (n != null)
        return false;
    }
    return true;
  }

  public TrieNode[] getChilds() {
    return childs;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public boolean isWord() {
    return isWord;
  }

  public void setIsWord(boolean isWord) {
    this.isWord = isWord;
  }

}