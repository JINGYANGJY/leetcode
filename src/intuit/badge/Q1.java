package intuit.badge;

import java.util.*;

public class Q1 {
    /*
 We are working on a security system for a badged-access room in our company's building.
 Given an ordered list of employees who used their badge to enter or exit the room, write a function that returns two collections:
 1. All employees who didn't use their badge while exiting the room – they recorded an enter without a matching exit.
 2. All employees who didn't use their badge while entering the room  – they recorded an exit without a matching enter.
     */
    public static List<List<String>> misMatched (String[][] records) {
        /*
        -> for each employee
                entry == exit
            for each record
                Map<String, Integer> exits
                Map<String, Integer> entry
              for map
                entry > exits   entryList
                entry < exits   exitList


            for each entry
                    should have an exit
            sequence entry before exit
            Clarify:
                recorded by time sequence?
            for each person
                if his entry == exit
                if entry > exit
                    entry
                other
                    exit
                Map<String, Integer> entryMap
                                      exitMap
                Set<String>
                - for each record
                -
                    for each employee
                             entry == exit
                                    if
                              entry > exit
                                    entry
                                otherwise
                                    exit
            Time: O(n)
            n == record.length
        */
        Map<String, Integer> entryMap = new HashMap<>();
        Map<String, Integer> exitMap = new HashMap<>();
        Set<String> employees = new HashSet<>();
        for (String[] s : records) {
            if (s[1].equals("exit")) {
                exitMap.put(s[0], exitMap.getOrDefault(s[0], 0) + 1);
            } else {
                entryMap.put(s[0], entryMap.getOrDefault(s[0], 0) + 1);
            }
            employees.add(s[0]);
        }
        List<String> exitList = new ArrayList<>();
        List<String> entryList = new ArrayList<>();
        for (String s : employees) {
            Integer entryTimes = entryMap.get(s);
            Integer exitTimes = exitMap.get(s);
            entryTimes = entryTimes == null ? 0 : entryTimes;
            exitTimes = exitTimes == null ? 0 : exitTimes;
            if (entryTimes > exitTimes) {
                entryList.add(s);
            } else if (entryTimes < exitTimes) {
                exitList.add(s);
            }
        }
        List<List<String>> res = new ArrayList<>();
        res.add(entryList);
        res.add(exitList);
        return res;
    }

    public static void main(String[] args) {
        String[][] badge_records = new String[][] {
                    {"Martha", "exit"},
                    {"Paul","enter"},
                    {"Martha","enter"},
                    {"Martha","exit"},
                    {"Jennifer", "enter"},
                    {"Paul","enter"},
                    {"Curtis","enter"},
                    {"Paul","exit"},
                    {"Martha","enter"},
                    {"Martha","exit"},
                    {"Jennifer", "exit"},
        };
        List<List<String>> res = misMatched(badge_records);
        for (List<String> r : res) {
            for (String s : r) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
