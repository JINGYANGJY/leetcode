package String;

public class Leet1698_NumberofDistinctSubstringsinaString {
    static class TrieNode {
        int index;
        TrieNode[] children;
        public TrieNode(int index) {
            this.index = index;
            this.children = new TrieNode[26];
        }
    }
    public int countDistinct(String s) {
        /*
        Assume only contians lowercase letters
        enumerate all substrings
            use set to dedup
        Time: O(n^3)

        --------------------------------
        optimization:
            all posssible length substring
                dedup - robinkarp
                Time: O(n^2)

            Trie - no generate substring
                dedup - set
                Time: O(n^2)
        */
        TrieNode root = new TrieNode(-1);
        int[] res = new int[1];
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            constructTrie(arr, i, res, root);
        }
        return res[0];
    }

    private void constructTrie(char[] arr, int index, int[] res, TrieNode root) {
        TrieNode top = root;
        for (int i = index; i < arr.length; i++) {
            int j = arr[i] - 'a';
            if (top.children[j] == null) {
                top.children[j] = new TrieNode(j);
                res[0] += 1;
            }
            top = top.children[j];
        }
    }
}
