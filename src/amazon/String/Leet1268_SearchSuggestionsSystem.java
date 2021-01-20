package amazon.String;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leet1268_SearchSuggestionsSystem {
    class TrieNode {
        int index;
        List<String> products;
        TrieNode[] children;
        public TrieNode(int index) {
            this.index = index;
            children = new TrieNode[26];
            products = new ArrayList<>();
        }
    }
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        /*
        Assume: string only contains lowercase letters
        Tier
            construTrie
                    root
                    /
                   m sorted list ["mobile","mouse","moneypot","monitor","mousepad"]
                   /
                   o
                  / | \
                  b n  u ["mouse","mousepad"]
                  ....  \
                         s ["mouse","mousepad"]
            get result from the trie
        */
        List<List<String>> res = new ArrayList<>();
        TrieNode root  = new TrieNode(-1);
        constructTrie(root, products);
        searchFromTrie(root, searchWord, res);
        return res;
    }

    private void searchFromTrie(TrieNode root, String searchWord, List<List<String>> res) {
        char[] arr = searchWord.toCharArray();
        TrieNode top = root;
        for (int i = 0; i < arr.length; i++) {
            int index = arr[i] - 'a';
            if (top != null) { // 注意不是top.children[index] != null
                top = top.children[index];
            }
            res.add(top == null ? new ArrayList<>() :  top.products);
        }
    }

    private void constructTrie(TrieNode root, String[] products) {
        for (String s : products) {
            TrieNode top = root;
            char[] arr = s.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                int index = arr[i] - 'a';
                if (top.children[index] == null) {
                    top.children[index] = new TrieNode(index);
                }
                top = top.children[index];
                top.products.add(s);
                Collections.sort(top.products);
                if (top.products.size() > 3) {
                    top.products.remove(top.products.size() - 1);
                }
            }
        }
    }
}
