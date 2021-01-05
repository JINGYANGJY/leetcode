package intuit.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Domain1 {
    /*
    给广告在每个domain上被click的次数. 要求返回domain及其所有sub domain 被click的总次数. （leetcode原题）
    ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
    for each domain
        its click counts
        its sub domain click counts

        its all subdomains
           e.g
                google.mail.com  900
                mail.com 900
                com 900

        Map<String, Integer> key: domain, valueL click count

        for each domain
            // 900 google.mail.com
            1. click count +  all its subdomains
                map exist
                    true: add click count
                    false: put a new domain
              m -- average of each domain length
              n -- number of domains
        Time: O(m * n)
     */
    public static List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> countMap = new HashMap<>();
        for (String s : cpdomains) {
            String[] countAndDomain = s.split(" ");
            int count = Integer.valueOf(countAndDomain[0]);
            char[] arr = countAndDomain[1].toCharArray();
            for (int i = -1; i < arr.length; i++) {
                if (i == -1 || arr[i] == '.') {
                    String domain = new String(arr, i + 1, arr.length - i - 1);
                    countMap.put(domain, countMap.getOrDefault(domain, 0) + count);
                }
            }
        }
        List<String> res = new ArrayList<>();
        for (String key : countMap.keySet()) {
            res.add(String.valueOf(countMap.get(key)) + " " + key);
        }
        return res;
    }

    public static void main(String[] args) {
        String[] domains = { "900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org" };
        //List<String> result = subdomainVisits(domains);
        List<String> result = domain(domains);
        System.out.println("[");
        for (String s : result) {
            System.out.println(s + ", ");
        }
        System.out.println("]");
    }

    public static List<String> domain(String[] input) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : input) {
            String[] countAndDomain = s.split(" ");
            Integer count = Integer.valueOf(countAndDomain[0]);
            String domain = countAndDomain[1];
            char[] arr = domain.toCharArray();
            for (int i = -1; i < arr.length; i++) {
                if (i == -1 || arr[i] == '.') {
                    String d = domain.substring(i + 1);
                    map.put(d, map.getOrDefault(d, 0) + count);
                }
            }
        }
        List<String> res = new ArrayList<>();
        for (String s : map.keySet()) {
            res.add(String.valueOf(map.get(s)) + " "+ s);
        }
        return res;
    }




}
