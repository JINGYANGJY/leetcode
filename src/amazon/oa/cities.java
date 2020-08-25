package amazon.oa;

import java.util.*;

public class cities {
    public static void main(String[] args) {
        String[] cities = {"c1", "c2", "c3", "c4"};
        int[] xCoordinates = {3, 2, 1, 3};
        int[] yCoordinates = {3, 2, 3, 1};
        int numOfQueries = 3;
        String[] queries = {"c1", "c2", "c3","c4"};
        List<String> res = nearestCities(4, cities, xCoordinates, yCoordinates, 4, queries);
        for (String s : res) {
            System.out.println(s);
        }
    }
    static class City {
        String name;
        int x;
        int y;
        public City(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }
    }

    public static List<String> nearestCities(int numOfCities, String[] cities,
                                      int[] xCoordinates, int[] yCoordinates,
                                      int numOfQueries, String[] queries) {
        Map<String, City> map = new HashMap<>();
        Map<Integer, List<City>> rowMap = new HashMap<>();
        Map<Integer, List<City>> columnMap = new HashMap<>();
        for (int i = 0; i < numOfCities; i++) {
            int x = xCoordinates[i], y = yCoordinates[i];
            City c = new City(cities[i], x, y);
            rowMap.putIfAbsent(x, new ArrayList<>());
            columnMap.putIfAbsent(y, new ArrayList<>());
            rowMap.get(x).add(c);
            columnMap.get(y).add(c);
            map.put(cities[i], c);
        }
        sortList(rowMap);
        sortList(columnMap);

        List<String> res = new ArrayList<>();
        for (int i = 0; i < numOfQueries; i++) {
            City c = map.get(queries[i]);
            List<City> row = rowMap.get(c.x);
            List<City> column = columnMap.get(c.y);
            City cnt1 = findNearestCity(row, c, true);
            City cnt2 = findNearestCity(column, c, false);
            String ans = null;
            if (cnt1 != null && cnt2 != null) {
                int dis1 = distance(cnt1, c);
                int dis2 = distance(cnt2, c);
                if (dis1 == dis2) {
                    ans = cnt1.name.compareTo(cnt2.name) > 0 ? cnt2.name : cnt1.name;
                } else if (dis1 < dis2) {
                    ans = cnt1.name;
                } else {
                    ans = cnt2.name;
                }
            } else {
                ans = cnt1 == null && cnt2 == null ? "NONE" : cnt1 == null ? cnt2.name : cnt1.name;
            }
            res.add(ans);
        }
        return res;

    }

    private static City findNearestCity(List<City> list, City c, boolean sameRow) {
        int left = 0, right = list.size() - 1;
        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int original = sameRow ? c.y : c.x;
            int compare = sameRow ? list.get(mid).y : list.get(mid).x;
                if (original == compare) {
                    res = mid;
                    break;
                }
                if (original > compare) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
        }
        Integer l = res > 0 ? res - 1 : null;
        Integer r = res == list.size() - 1 ? null : res + 1;
        if (l == null && r == null) {
            return null;
        }
        if (l == null && r != null) {
            return list.get(r);
        } else if (r == null && l != null) {
            return list.get(l);
        } else {
            return distance(list.get(l), c) > distance(list.get(r), c) ? list.get(r) : list.get(l);
        }
    }

    private static int distance(City c1, City c2) {
        if (c1 == null || c2 == null) {
            return 0;
        }
        return Math.abs(c1.x - c2.x) + Math.abs(c1.y - c2.y);
    }

    private static void sortList(Map<Integer, List<City>> map) {
        for (List<City> list : map.values()) {
            Collections.sort(list, (a, b) -> {
                int res = a.x - b.x;
                if (res == 0) {
                    return a.y - b.y;
                }
                return res;
            });
        }
    }
}
