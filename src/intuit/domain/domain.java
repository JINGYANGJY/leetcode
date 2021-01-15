package intuit.domain;

import java.util.*;

public class domain {
    /*
    给广告在每个domain上被click的次数. 要求返回domain及其所有sub domain 被click的总次数. （leetcode原题）
    ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
          for each input
                count
                domain
                    subdomain
                        -Map<String, Integer>
          Time:O(n * |average length of each domain|)
    */
    public static List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : cpdomains) {
            String[] strs = s.split(" ");
            int count = Integer.valueOf(strs[0]);
            char[] arr = strs[1].toCharArray();
            for (int i = -1; i < arr.length; i++) {
                if (i == -1 || arr[i] == '.') {
                    String domain = strs[1].substring(i + 1);
                    map.put(domain, map.getOrDefault(domain, 0) + count);
                }
            }
        }
        List<String> res = new ArrayList<>();
        for (String key : map.keySet()) {
            res.add(String.valueOf(map.get(key)) + " " + key);
        }
        return res;
    }

    public static List<String> commonHistory(String[] user1, String[] user2) {
        /*

        if user1[i] == user2[j]
            user1[i - 1] == user2[j - 1]
        dp[i][j]: for string ends with index i of user1 and string ends with index j of user2
                    the longgest common history
        start index
        global longgest
        dp[i][j]
                user1[i] == user2[j]
                    dp[i][j] = dp[i - 1][j - 1] + 1
                otherwise
                    dp[i][j] = 0

                 if dp[i][j] > longest
                    startIndex
          construct result

         */
        int n = user1.length;
        int m = user2.length;
        int[][] dp = new int[n + 1][m + 1];
        int startIndex = 0;
        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (user1[i - 1].equals(user2[j - 1])) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                if (dp[i][j] > max) {
                    max = dp[i][j];
                    startIndex = i - max;
                }
            }
        }
        List<String> res = new ArrayList<>();
        for (int i = startIndex; i < startIndex + max; i++) {
            res.add(user1[i]);
        }
        return res;
    }

    public static List<String> AdsConversion(String[] completedUserId, String[] adClicks, String[] allUserIp) {
        /*
         ipToID
         ip products
         ids
         for adclicks
            ip -> id ->
               Map<String, Integer> clickTimes
               Map<String, Integer> completed
               Set<String> completedUserId

          construct result
            O(n * |M| + n)
            O(nm)
            n is the length of adClicks
            M is average length of current record
         */
        Set<String> ids = new HashSet<>(Arrays.asList(completedUserId));
        Map<String, String> ipToId = new HashMap<>();
        for (String s : allUserIp) {
            String[] str = s.split(",");
            String id = str[0];
            String ip = str[1];
            ipToId.put(ip, id);
        }
        Map<String, Integer> clicks = new HashMap<>();
        Map<String, Integer> completed = new HashMap<>();
        for (String click : adClicks) {
            String[] strs = click.split(",");
            String ip = strs[0];
            String product = strs[2];
            clicks.put(product, clicks.getOrDefault(product, 0) + 1);
            if (ids.contains(ipToId.get(ip))) {
                completed.put(product, completed.getOrDefault(product, 0) + 1);
            }
        }
        List<String> res = new ArrayList<>();
        for (String key : clicks.keySet()) {
            res.add(key + " , " + clicks.get(key) + " , " + completed.get(key));
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("------------------------domain1---------------------------------------");
        String[] domains = { "900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org" };
        List<String> result = subdomainVisits(domains);
        System.out.println("[");
        for (String s : result) {
            System.out.println(s + ", ");
        }
        System.out.println("]");
        System.out.println("------------------------domain2---------------------------------------");
        String[] s1 = { "3234.html", "xys.html", "7hsaa.html" }; // user1
        String[] s2 = { "3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html" }; // user2
        List<String> result2 = commonHistory(s1, s2);
        System.out.println("[");
        for (String s : result2) {
            System.out.println(s + " ,");
        }
        System.out.println("]");
        System.out.println("------------------------domain3---------------------------------------");
        String[] completedId = { "3123122444", "234111110", "8321125440", "99911063" };
        String[] adClicks = { "122.121.0.1,2016-11-03 11:41:19,Buy wool coats for your pets",
                "96.3.199.11,2016-10-15 20:18:31,2017 Pet Mittens",
                "122.121.0.250,2016-11-01 06:13:13,The Best Hollywood Coats",
                "82.1.106.8,2016-11-12 23:05:14,Buy wool coats for your pets",
                "92.130.6.144,2017-01-01 03:18:55,Buy wool coats for your pets",
                "92.130.6.145,2017-01-01 03:18:55,2017 Pet Mittens" };
        String[] allUser = { "User_ID,IP_Address", "2339985511,122.121.0.155", "234111110,122.121.0.1",
                "3123122444,92.130.6.145", "39471289472,2001:0db8:ac10:fe01:0000:0000:0000:0000",
                "8321125440,82.1.106.8", "99911063,92.130.6.144" };
        List<String> result3 = AdsConversion(completedId, adClicks, allUser);
        for (String s : result3) {
            System.out.println(s);
        }
    }

}
