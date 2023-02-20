package com.source.plusutil.service.algorithmService

import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import io.github.oshai.KotlinLogging

private val logger = KotlinLogging.logger {}
@Service
class SlidingWindowServiceImpl:SlidingWindowService {
    override fun slidingWindowAction(request: HttpServletRequest, slidingWindowArr: String, slidingWindowRange: String) {
        val arr : Array<String> = slidingWindowArr.toCharArray().map { it.toString() }.toTypedArray()
        val range : Int = slidingWindowRange.toInt(); //인트형으로 변환
        var maxValue = Int.MIN_VALUE;
        var sum = 0;
        var count = 0;
        var index = 0;
        for(numStr in arr){
            if(count == range) {
                if( maxValue < sum){
                    maxValue = sum
                    logger.info("now maxValue $maxValue")
                }
                count--;
                sum -= arr[index].toInt();
                index++;
            };
            count++;
            sum += numStr.toInt();
        }
        request.setAttribute("slidingWindowArr",slidingWindowArr);
        request.setAttribute("slidingWindowRange",slidingWindowRange);
        request.setAttribute("slidingWindowResult",maxValue);
    }
}