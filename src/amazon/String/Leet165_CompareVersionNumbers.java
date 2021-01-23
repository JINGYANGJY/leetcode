package amazon.String;

public class Leet165_CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        char[] arr1 = version1.toCharArray();
        char[] arr2 = version2.toCharArray();
        int index1 = 0;
        int index2 = 0;
        int v1 = 0;
        int v2 = 0;
        while (index1 < arr1.length || index2 < arr2.length) {
            if (index1 < arr1.length && arr1[index1] == '.') {
                index1++;
            }
            while (index1 < arr1.length && arr1[index1] != '.') {
                v1 = v1 * 10 + arr1[index1++] - '0';
            }
            if (index2 < arr2.length && arr2[index2] == '.') {
                index2++;
            }
            while (index2 < arr2.length && arr2[index2] != '.') {
                v2 = v2 * 10 + arr2[index2++] - '0';
            }
            if (v1 == v2) {
                v1 = 0;
                v2 = 0;
                continue;
            }
            return v1 > v2 ? 1 : -1;
        }
        return 0;
    }
}
