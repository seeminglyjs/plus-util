package com.source.plusutil.algorithm;

import org.junit.jupiter.api.Test;

public class TwoPointer {

    @Test
    public void test(){
        int target = 5;

        int s = 0;
        int e= 0;
        int [] arr = {1,2,3,2,5,3,1,1,2};
        int start = 0, end = 0, count = 0, sum = 0;

        while(start < arr.length-1) {
            if(sum > target || end == arr.length-1) {
                sum-=arr[start];
                start +=1;
            }
            else { // sum <= target
                sum+=arr[end];
                end +=1;
            }

            if(sum==target) count++;
        }

        System.out.println(count);
    }
}
