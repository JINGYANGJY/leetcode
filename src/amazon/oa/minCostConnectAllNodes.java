package amazon.oa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class minCostConnectAllNodes {
    public static void main(String[] args) {
        int n = 6;
        List<List<Integer>> edges = Arrays.asList(Arrays.asList(1, 4), Arrays.asList(4, 5), Arrays.asList(2, 3));
        List<List<Integer>> newEdges = Arrays.asList(Arrays.asList(1, 2, 5), Arrays.asList(1, 3, 10), Arrays.asList(1, 6, 2), Arrays.asList(5, 6, 5));
        System.out.println(minimumCost(n, edges, newEdges));
    }

    public static int minimumCost(int n, List<List<Integer>> edges, List<List<Integer>> newEdges) {
        int res = 0;
        UnionFind uf = new UnionFind(n);
        for (List<Integer> edge : edges) {
            uf.union(edge.get(0), edge.get(1));
        }
        Collections.sort(newEdges, (a, b) -> (a.get(2) - b.get(2)));
        for (List<Integer> list : newEdges) {
            if (uf.union(list.get(0), list.get(1))) {
                res += list.get(2);
            }
            int components = uf.getComponent();
            if (components == 1) {
                break;
            }
        }
        return uf.getComponent() == 1 ? res : -1;
    }
   static class UnionFind {
        int n;
        int[] rank;
        int[] parent;
        public UnionFind(int N) {
            this.n = N;
            rank = new int[N + 1];
            parent = new int[N + 1];
            for (int i = 0; i <= N; i++) {
                parent[i] = i;
                rank[i] = i;
            }
        }
        public int find(int i) {
            while (i != parent[i]) {
                i = parent[i];
            }
            return i;
        }
        public boolean union(int i, int j) {
            int pa = find(i);
            int pb = find(j);
            if (pa == pb) return false;
            if (rank[pa] < rank[pb]) {
                parent[pa] = pb;
            } else if (rank[pa] < rank[pb]) {
                parent[pb] = pa;
            } else {
                parent[pb] = pa;
                rank[pa] += 1;
            }
            n--;
            return true;
        }

        public int getComponent(){
            return n;
        }
    }
}
