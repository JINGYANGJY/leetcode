package amazon.oa;

import java.util.Arrays;

public class MST {
    public static int findMin(int totalCity, int[][] totalRoads, int[][] roadCost) {
        Arrays.sort(roadCost, (n1, n2)-> n1[2] - n2[2]);
        UnionFind unionFind = new UnionFind(totalCity);
        for (int[] road : totalRoads) {
            unionFind.union(road[0],road[1]);
        }
        int res = 0;
        for (int[] cost : roadCost) {
            int component = unionFind.component;
            int curComponent = unionFind.union(cost[0],cost[1]);
            if (component == curComponent) {
                continue;
            }
            res += cost[2];
            if (curComponent == 1) return res;
        }
        return -1;
    }
    
    public static void main(String[] args) {
        int numTotalAvailableCities = 6;
        int numTotalAvailableRoads = 3;
        int[][] roadsAvailable = {{1, 4},{4, 5},{2, 3}};
        int numNewRoadsConstruct = 4;
        int[][] costNewRoadsConstruct = {{1, 2, 5 }, {1, 3, 10}, {1, 6, 2}, {5, 6, 5}};
        System.out.println(findMin(numTotalAvailableCities, roadsAvailable, costNewRoadsConstruct));
    }

    static class UnionFind {
        int[] parent;
        int component;
        public UnionFind(int n) {
            parent = new int[n + 1];
            component = n;
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
        }

        public int union(int i, int j) {
            int parentI = findParent(i);
            int parentJ = findParent(j);
            if (parentI != parentJ) {
                component--;
            }
            parent[parentI] = parentJ;
            return component;
        }

        public int findParent(int i) {
            if (parent[i] == i) {
                return i;
            }
            parent[i] = findParent(parent[i]);
            return parent[i];
        }
    }
}
