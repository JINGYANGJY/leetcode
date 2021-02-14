package amazon.HashTable;

import java.util.*;

public class Leet1152_AnalyzeUserWebsiteVisitPattern {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        /*
            goals to find the 3-sequence visited by largest nubmer fo users
            for each user
                Map<user, List<String>> visited;
            for each two users
                find common 3-sequence
                    Map<List<String>, Integer>  <3-sequence, frequency>
            List<String> res;
            int maxFre;
        */
        Map<String, List<String>> visitPattern = constructVisitedPattern(username, timestamp, website);
        List<String> users = new ArrayList<>(visitPattern.keySet());
        Map<List<String>, Integer> patternCount = new HashMap<>();
        int maxFre = 0;
        List<String> res = new ArrayList<>();
        for (String user : visitPattern.keySet()) {
            List<String> visitList = visitPattern.get(user);
            Set<List<String>> set = new HashSet<>();
            for (int i = 0; i < visitList.size(); i++) {
                for (int j = i + 1; j < visitList.size(); j++) {
                    for (int k = j + 1; k < visitList.size(); k++) {
                        List<String> sequence = Arrays.asList(visitList.get(i), visitList.get(j), visitList.get(k));
                        if (set.contains(sequence)) continue;
                        set.add(sequence);
                        patternCount.put(sequence, patternCount.getOrDefault(sequence, 0) + 1);
                        int count = patternCount.get(sequence);
                        if (count > maxFre) {
                            maxFre = count;
                            res = sequence;
                        } else if (count == maxFre) {
                            for (int n = 0; n < 3; n++) {
                                if (res.get(n).equals(sequence.get(n))) {
                                    continue;
                                } else {
                                    if (res.get(n).compareTo(sequence.get(n)) > 0) {
                                        res = sequence;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return res;
    }
    class Triple {
        String username;
        int timestamp;
        String website;
        public Triple(String username, int timestamp, String website) {
            this.username = username;
            this.timestamp = timestamp;
            this.website = website;
        }
    }
    private Map<String, List<String>> constructVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<Triple> list = new ArrayList<>();
        Map<String, List<String>> visitPattern = new HashMap<>();
        for (int i = 0; i < username.length; i++) {
            list.add(new Triple(username[i], timestamp[i], website[i]));
        }
        Collections.sort(list, (a, b) -> {
            return a.timestamp - b.timestamp;
        });

        for (int i = 0; i < list.size(); i++) {
            String user = list.get(i).username;
            String site = list.get(i).website;
            visitPattern.putIfAbsent(user, new ArrayList<>());
            visitPattern.get(user).add(site);
        }
        return visitPattern;
    }
}
