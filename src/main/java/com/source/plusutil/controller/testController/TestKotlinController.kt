package com.source.plusutil.controller.testController

import com.source.plusutil.test.TestKotlin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/plus/test/kotlin")
@RestController
class TestKotlinController {
    @GetMapping("/test1")
    fun getTestKotlin(): Long?{
        val testInstance1 = TestKotlin();
        return testInstance1.numPlusOne();
    }
}