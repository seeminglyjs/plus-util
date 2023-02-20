package com.source.plusutil.service.algorithmService

import javax.servlet.http.HttpServletRequest

interface SlidingWindowService {

    fun slidingWindowAction(request: HttpServletRequest, slidingWindowArr: String, slidingWindowRange: String);
}