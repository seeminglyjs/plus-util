package com.source.plusutil.algorithm

import javax.servlet.http.HttpServletRequest

interface SlidingWindowService {

    fun slidingWindowAction(request: HttpServletRequest, slidingWindowArr: String, slidingWindowRange: String);
}