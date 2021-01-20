package amazon.DFS.Backtracking;

import java.util.*;

/*
https://leetcode.com/problems/synonymous-sentences/
 */
public class Leet1258_SynonymousSentences {
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        /*
        step 1: connect all equivalent words
                model as a graph problem
                    vertex: all distinct word
                    edge: each pair of equivalent words has one edge between them
        step 2:
                DFS backtracking
                            I
                            am
                          /     |    \
                        happy  joy cheerful
                         /
                      today..
                Time: O(|V| + |E| + |V| ^ n)
                n is the number of words in the text
        */
        Map<String, List<String>> wordGraph = constructGraph(synonyms);
        Map<String, List<String>> connectedComponents = getConnectedComponents(wordGraph, text);
        List<String> res = new ArrayList<>();
        String[] strs = text.split(" ");
        dfs(0, strs, new StringBuilder(), res, connectedComponents);
        return res;
    }

    private Map<String, List<String>> getConnectedComponents(Map<String, List<String>> wordGraph, String text) {
        Map<String, List<String>> connectedComponents = new HashMap<>();
        for (String str : text.split(" ")) {
            Set<String> visited = new HashSet<>();
            List<String> list = new ArrayList<>();
            components(str, list, visited, wordGraph);
            Collections.sort(list);
            connectedComponents.put(str, list);
        }
        return connectedComponents;
    }

    private void components(String cur, List<String> list, Set<String> visited, Map<String, List<String>> wordGraph) {
        if (visited.contains(cur)) {
            return;
        }
        list.add(cur);
        visited.add(cur);
        if (wordGraph.containsKey(cur)) {
            for (String s : wordGraph.get(cur)) {
                components(s, list, visited, wordGraph);
            }
        }

    }

    private Map<String, List<String>> constructGraph(List<List<String>> synonyms) {
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> strs : synonyms) {
            String v = strs.get(0);
            String w = strs.get(1);
            graph.putIfAbsent(v, new ArrayList<>());
            graph.putIfAbsent(w, new ArrayList<>());
            graph.get(v).add(w);
            graph.get(w).add(v);
        }
        return graph;
    }

    private void dfs(int index, String[] strs, StringBuilder partialRes, List<String> res, Map<String, List<String>> connectedComponents) {
        if (index == strs.length) {
            res.add(new String(partialRes.substring(0, partialRes.length() - 1)));
            return;
        }
        List<String> neis = connectedComponents.get(strs[index]);
        int len = partialRes.length();
        if (neis != null && neis.size() > 0) {
            for (String nei : neis) {
                partialRes.append(nei);
                partialRes.append(" ");
                dfs(index + 1, strs, partialRes, res, connectedComponents);
                partialRes.setLength(len);
            }
        } else {
            partialRes.append(strs[index]);
            partialRes.append(" ");
            dfs(index + 1, strs, partialRes, res, connectedComponents);
            partialRes.setLength(len);
        }
    }
}
