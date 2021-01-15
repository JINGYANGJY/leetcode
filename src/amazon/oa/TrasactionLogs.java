package amazon.oa;

import java.util.*;

public class TrasactionLogs {
    /*
    Your Amazonian team is responsible for maintaining a monetary transaction service. The transactions are tracked in a log file.
    A log file is provided as a string array where each entry represents a transaction to service. Each transaction consists of:

    sender_user_id, Unique identifier for the user that initiated the
    transaction. It consists of only digits with at most 9 digits.
    recipient_user_id: Unique identifier for the user that is receiving the
    transaction. It consists of only digits with at most 9 digits.
    amount_of_transaction: The amount of the transaction. It consists of only digits with at most 9 digits.

    The values are separated by a space. For example, "sender_user_id recipient_user_id amount_of_transaction".
    Users that perform an excessive amount of transactions might be abusing the service so you have been tasked to
    identify the users that have a number of transactions over a threshold.
    The list of user ids should be ordered in ascending numeric value.
     */
    public String[] processLogFile(String[] logs, int threshold) {
        /*
        step 1:
            for each log
                record this transaction happens to whom
                if send != receive

                otherwise ==
        */
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String log : logs) {
            String[] str = log.split(" ");
            if (str[0].equals(str[1])) {
                frequencyMap.put(str[0], frequencyMap.getOrDefault(str[0], 0) + 1);
            } else {
                frequencyMap.put(str[0], frequencyMap.getOrDefault(str[0], 0) + 1);
                frequencyMap.put(str[1], frequencyMap.getOrDefault(str[1], 0) + 1);
            }
        }
        List<String> res = new ArrayList<>();
        for (String key : frequencyMap.keySet()) {
            if (frequencyMap.get(key) >= threshold) {
                res.add(key);
            }
        }
        Collections.sort(res);
        return res.toArray(new String[res.size()]);
    }
}
