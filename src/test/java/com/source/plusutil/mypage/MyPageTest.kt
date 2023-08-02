package com.source.plusutil.mypage


import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class MyPageTest {

    @Test
    fun uuidNickTest(){
        println(UUID.randomUUID().toString().replace("-",""))
    }
}