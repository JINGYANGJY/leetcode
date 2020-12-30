package intuit.domain;

import interview.medium.HitCounter;

import java.util.*;

public class Domain3 {
    public static List<String> AdsConversion(String[] completedUserId, String[] adClicks, String[] allUserIp) {
        /*
        completedUserId：userIDs actually complete the buy operation
        adClicks: ip address time, stamp, item
        allUserIp: userID, user ip address
        how many completed buy operations in adClicks
        For each click of adClicks:
                ip address -> allUserIp Map<ip address, userID>
                    --> userId --> check if exists in completedUserId --> set<completedUserId>
                    if true
                        it is an actual buy operation --> item has 1 completed buy operations
        Map<String, Integer> item, completed buy operations

        1. allUserIp into HashMap  n is length of allUserIp
        2. completedUserId into HashSet m is length of completedUserId
        3. p is the length of adClicks
        Time: O(n + m + p)
         */
        HashMap<String, String> ipToUserID = new HashMap<>();
        HashSet<String> completedIDs = new HashSet<>(Arrays.asList(completedUserId));
        HashMap<String, Integer> actualComplete = new HashMap<>();
        HashMap<String, Integer> clickTimes = new HashMap<>();
        for (String IdAndIp : allUserIp) {
            String[] idip = IdAndIp.split(",");
            ipToUserID.put(idip[1], idip[0]); // Assume ip address in the string[] is unique
        }
        for (String click : adClicks) {
            String[] strings = click.split(",");
            String item = strings[2];
            clickTimes.put(item, clickTimes.getOrDefault(item, 0) + 1);
            String ip = strings[0];
            String id = ipToUserID.get(ip);
            if (completedIDs.contains(id)) {
                actualComplete.put(item, actualComplete.getOrDefault(item, 0) + 1);
            }
        }
        List<String> res = new ArrayList<>();
        for (String item : clickTimes.keySet()) {
            Integer actual = actualComplete.get(item);
            actual = actual == null ? 0 : actual;
            res.add(actual + " of " + clickTimes.get(item) + " " + item);
        }
        return res;
    }
    public static void main(String[] args) {
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
        List<String> result = AdsConversion(completedId, adClicks, allUser);
        System.out.println("[");
        for (String s : result) {
            System.out.println(s);
        }
        System.out.println("]");

    }
}
