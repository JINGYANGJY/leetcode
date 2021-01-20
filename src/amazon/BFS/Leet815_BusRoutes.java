package amazon.BFS;

import java.util.*;

public class Leet815_BusRoutes {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        /*
        -> least number of buses to reach destination
        -> graph problem
            vertex: each bus
            edge: if two buses has common stop, there is an edge between them
        -> construct graph
        -> find the shortest path from start to end
            -> BFS
                initial state:
                    all buses which contain S

                    Queue
                            / | |   \
                          edge
                          /||\
        */
        if (S == T) return 0;
        Map<Integer, Set<Integer>> busAndStops = constructBus(routes);
        Map<Integer, List<Integer>> graph = constructGraph(busAndStops);
        Deque<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        for (Integer bus : busAndStops.keySet()) {
            if (busAndStops.get(bus).contains(S)) {
                queue.offer(bus);
                visited.add(bus);
            }
        }
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer bus = queue.poll();
                if (busAndStops.get(bus).contains(T)) {
                    return res + 1;
                }
                if (!graph.containsKey(bus)) continue;
                for (Integer nei : graph.get(bus)) {
                    if (visited.add(nei)) {
                        queue.offer(nei);
                    }
                }
            }
            res++;
        }
        return -1;
    }

    private Map<Integer, List<Integer>> constructGraph(Map<Integer, Set<Integer>> busAndStops) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        List<Integer> buses = new ArrayList<>(busAndStops.keySet());
        for (int i = 0; i < buses.size(); i++) {
            for (int j = i + 1; j < buses.size(); j++) {
                if (hasCommon(busAndStops.get(buses.get(i)), busAndStops.get(buses.get(j)))) {
                    graph.putIfAbsent(buses.get(i), new ArrayList<>());
                    graph.putIfAbsent(buses.get(j), new ArrayList<>());
                    graph.get(buses.get(i)).add(buses.get(j));
                    graph.get(buses.get(j)).add(buses.get(i));
                }
            }
        }
        return graph;
    }

    private boolean hasCommon(Set<Integer> bus1, Set<Integer> bus2) {

        for (Integer i : bus1) {
            if (bus2.contains(i)) {
                return true;
            }
        }
        return false;
    }

    private Map<Integer, Set<Integer>> constructBus(int[][] routes) {
        Map<Integer, Set<Integer>> busAndStops = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j : routes[i]) {
                set.add(j);
            }
            busAndStops.put(i, set);
        }
        return busAndStops;
    }
}
