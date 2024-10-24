package com.example.demo.interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BitwiseOr {
    public static void main(String[] args) {
        int[] intList = new int[]{1,2,3,4,5,6,7,8,9,10};
        countMaxOrSubsets(intList);
    }
    public static int countMaxOrSubsets(int[] nums) {
        // Step 1: Calculate the maximum bitwise OR
        int maxOR = calculateMaxBitwiseOR(Arrays.stream(nums).boxed().toList());

        // Step 2: Count subsets that achieve the maximum bitwise OR
        int count = countMaxBitwiseORSubsets(Arrays.stream(nums).boxed().toList(), maxOR);

        // Print the list of binary strings
        System.out.println(count);
        return count;
    }

    private static int calculateMaxBitwiseOR(List<Integer> intList) {
        int maxOR = 0;
        for (int num : intList) {
            maxOR |= num; // Calculate the maximum bitwise OR
        }
        return maxOR;
    }

    private static int countMaxBitwiseORSubsets(List<Integer> intList, int maxOR) {
        int count = 0;
        int n = intList.size();

        // Iterate through all subsets using bit masking
        for (int i = 0; i < (1 << n); i++) {
            int currentOR = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) { // Check if j-th bit is set
                    currentOR |= intList.get(j); // Update the current OR
                }
            }
            // Check if current OR equals the maximum OR
            if (currentOR == maxOR) {
                count++;
            }
        }
        return count;
    }
}
