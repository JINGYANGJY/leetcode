package oracle.hard;

import java.util.ArrayList;
import java.util.List;

/*
Given a 2D board and a list of words from the dictionary, find all words in the board.
Each word must be constructed from letters of sequentially adjacent cell, where "adjacent"
cells are those horizontally or vertically neighboring.
The same letter cell may not be used more than once in a word.
Example:

Input:
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]
Clarification:
    each character can only be used once?
    only contains lowercase?
    input: words list and a board
    output: words list which can be searched in the board
M1: check word exist in the board
    for each word in words
        check if exists in the board by using dfs
        e.g
            oath
            dfs levels: 4
                each level: 4 directions, if same go to next level
                base case:  level == word.length
                mark visited: true
            1st: board all positions which is 'o'
            2nd:                         4 directions
           ...
     Time: O(|words| * (N*M)^|word|)
    optimization
M2:  check all words
       construct Trie with all words
       avoid some duplicate prefix
       Time: O(M(4 * 3 ^ (L - 1)))
       where MM is the number of cells in the board and LL is the maximum length of words. initiallly we have 4 directions to
       explore, the rest we only need to explore at most 3 directions
 */
public class WordSearch2 {
    static class TrieNode {
        boolean isWord;
        String word;
        TrieNode[] children;
        public TrieNode() {
            isWord = false;
            children = new TrieNode[26];
        }
    }

    public static List<String> findWords (char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        constructTrie(root, words);
        List<String> res = new ArrayList<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int w = 0; w < 26; w++) {
            if (root.children[w] != null) {
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        if (board[i][j] - 'a' == w) {
                            dfs(root.children[w], i, j,board, visited, res);
                        }
                    }
                }
            }
        }

        return res;
    }
    static int[][] dir = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static void dfs(TrieNode top, int i, int j, char[][] board, boolean[][] visited, List<String> res) {
        if (top.isWord) {
            res.add(top.word);
            top.isWord = false;
        }
        visited[i][j] = true;
        for (int w = 0; w < 26; w++) {
            if (top.children[w] != null) {
                for (int[] d : dir) {
                    int dx = d[0] + i;
                    int dy = d[1] + j;
                    if (dx < 0 || dy < 0 || dx == board.length || dy == board[0].length) {
                        continue;
                    }
                    if (!visited[dx][dy] && board[dx][dy] - 'a' == w) {
                        dfs(top.children[w], dx, dy, board, visited, res);
                    }
                }
            }
        }
        visited[i][j] = false;
    }
    private static void constructTrie(TrieNode root, String[] words) {
        for (String s : words) {
            TrieNode top = root;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int index = c - 'a';
                if (top.children[index] == null) {
                    top.children[index] = new TrieNode();
                }
                top = top.children[index];
            }
            top.isWord = true;
            top.word = s;
        }
    }

    public static void main(String[] args) {
        char[][] board = {
                {'a', 'b'},
                {'a', 'a'}
        };
//        char[][] board = {
//
//        };
        String[] words = {"aba","baa","bab","aaab","aaa","aaaa","aaba"};
        List<String> res = findWords(board, words);
        for (String s : res) {
            System.out.println(s);
        }
    }
}
