package tiktok;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class subsetMerge {
        public static void main(String[] args) {
            List<String> subnetsStrs = new ArrayList<>();
            subnetsStrs.removeAll(subnetsStrs);
            subnetsStrs.add("11.23.1.0/24");
            subnetsStrs.add("11.23.0.0/24");
            subnetsStrs.add("11.23.7.0/24");
            subnetsStrs.add("11.23.6.0/24");
            subnetsStrs.add("11.23.3.0/24");
            subnetsStrs.add("11.23.2.0/24");
            subnetsStrs.clear();
            System.out.println(subnetsStrs.size());
            List<String> res = mergeSubnets(subnetsStrs);
            System.out.println(res.toString());
        }

        static List<String> mergeSubnets(List<String> subnetStrs) {
            PriorityQueue<Subnet> subnets = new PriorityQueue<>(
                    (a, b) -> a.maskLen == b.maskLen ? Long.compare(a.mask, b.mask)
                            : Integer.compare(b.maskLen, a.maskLen));
            for (String subnet : subnetStrs) {
                subnets.offer(parseSubnet(subnet));
            }
            List<String> res = new ArrayList<>();
            while (!subnets.isEmpty()) {
                Subnet subnet = subnets.poll();
                if (!subnets.isEmpty() && subnets.peek().maskLen == subnet.maskLen
                        && (subnet.mask ^ subnets.peek().mask) ==
                        1L << (32 - subnet.maskLen)) {
                    subnets.poll();
                    subnets.offer(
                            new Subnet(subnet.maskLen - 1, removeLastBit(subnet.mask, subnet.maskLen - 1)));
                } else {
                    res.add(subnet.toString());
                }
            }
            return res;
        }

        static private long removeLastBit(long mask, long newMaskLen) {
            long tmp = 0;
            for (int i = 0; i < 32; ++i) {
                tmp <<= 1;
                if (i < newMaskLen) {
                    ++tmp;
                }
            }
            return mask & tmp;
        }

        static private Subnet parseSubnet(String subnet) {
            String[] strs = subnet.split("/");
            long mask = 0;
            for (String num : strs[0].split("\\.")) {
                mask = mask * 256 + Long.parseLong(num);
            }
            return new Subnet(Integer.parseInt(strs[1]), mask);
        }

        private static class Subnet {

            int maskLen;
            long mask;

            public Subnet(int maskLen, long mask) {
                this.maskLen = maskLen;
                this.mask = mask;
            }

            @Override
            public String toString() {
                return String.valueOf(mask / (1 << 24)) + '.' + mask / (1 << 16) % 256 + '.'
                        + mask / (1 << 8) % 256 + '.' + mask % 256 + '/' + maskLen;
            }
        }
}
