package com.source.plusutil.service.algorithmService;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class BasicServiceImpl implements BasicService{

    @Override
    public void twoPointerAction(HttpServletRequest request, String twoPointerArr, String twoPointerTarget) {
        request.setAttribute("twoPointerArr", twoPointerArr);
        request.setAttribute("twoPointerTarget", twoPointerTarget);

        int target = Integer.parseInt(twoPointerTarget);
        String [] arr = twoPointerArr.split("");
        //시작포인터 , 종료포인터, 카운트 변수, 합계 변수
        int start = 0, end = 0, count = 0, sum = 0;

        while(start < arr.length-1) {
            if(sum > target || end == arr.length-1) {
                sum-=Integer.parseInt(arr[start]);
                start +=1;
            }
            else { // sum <= target
                sum+=Integer.parseInt(arr[end]);
                end +=1;
            }
            if(sum==target) count++;
        }
        request.setAttribute("twoPointerResult", String.valueOf(count));
    }
}
