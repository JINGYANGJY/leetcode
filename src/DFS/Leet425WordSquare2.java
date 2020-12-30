package DFS;

import java.util.ArrayList;
import java.util.List;

public class Leet425WordSquare2 {
        //Assume there is only lowercase
        static class TrieNode{
            int index;
            TrieNode[] children;
            String string;
            List<String> startsWith;
            public TrieNode(int index) {
                this.index = index;
                children = new TrieNode[26];
                startsWith = new ArrayList<>();
            }
        }
        public List<List<String>> wordSquares(String[] words) {
            TrieNode root = new TrieNode(-1);
            constructTrie(words, root);
            List<List<String>> res = new ArrayList<>();
            int M = words[0].length();
            dfs(0, M, new ArrayList<>(), res, root);
            return res;
        }

        private void dfs(int level, int target, List<String> partial, List<List<String>> res, TrieNode root) {
            if (level == target) {
                res.add(new ArrayList<>(partial));
                return;
            }
            if (level > target) {
                return;
            }
            String prefix = prefixString(partial, level);
            List<String> next = getCnt(prefix, root);
            if (next != null && next.size() > 0) {
                for (String s : next) {
                    partial.add(s);
                    dfs(level + 1, target, partial, res, root);
                    partial.remove(partial.size() - 1);
                }
            }
        }

        private List<String> getCnt(String prefix, TrieNode root) {
            List<String> res = new ArrayList<>();
            char[] arr = prefix.toCharArray();
            TrieNode top = root;
            for (int i = 0; i < arr.length; i++) {
                int index = arr[i] - 'a';
                if (top.children[index] != null) {
                    top = top.children[index];
                } else {
                    return null;
                }
            }
            res.addAll(top.startsWith);
            return res;
        }

        //get prefix of current level
        private String prefixString(List<String> partial, int level) {
            if (level == 0) {
                return "";
            } else {
                StringBuilder res = new StringBuilder();
                for (int i = 0; i < partial.size(); i++) {
                    res.append(String.valueOf(partial.get(i).charAt(level)));
                }
                return res.toString();
            }

        }

        //construct the Trie
        private void constructTrie(String[] words, TrieNode root) {
            for (String word : words) {
                TrieNode top = root;
                top.startsWith.add(word);
                char[] arr = word.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    int index = arr[i] - 'a';
                    if (top.children[index] == null) {
                        top.children[index] = new TrieNode(index);
                    }
                    top = top.children[index];
                    top.startsWith.add(word);
                }
                top.string = word;
            }
        }
}
