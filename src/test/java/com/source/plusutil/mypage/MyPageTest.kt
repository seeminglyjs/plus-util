package com.source.plusutil.mypage


import com.source.plusutil.mypage.dto.MyPageDto
import com.source.plusutil.mypage.dto.MyPageInfoDto
import com.source.plusutil.mypage.dto.MyPageRequestDto
import com.source.plusutil.user.JoinService
import com.source.plusutil.user.UserInfoRepository
import com.source.plusutil.user.dto.JoinResultDto
import com.source.plusutil.user.dto.UserInfoDto
import com.source.plusutil.user.dto.UserJoinDto
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.test.web.servlet.MockMvc
import java.util.*
import javax.transaction.Transactional

@SpringBootTest
@AutoConfigureMockMvc
open class MyPageTest {

    @Autowired
    var joinService : JoinService? = null;

    @Autowired
    var myPageService : MyPageService? = null;

    @Autowired
    var userInfoRepository : UserInfoRepository? = null;

    @Autowired
    private lateinit var mockMvc: MockMvc
    /*
    테스트 계정 생성
     */
    private fun makeUser(): Optional<UserInfoDto>? {
        val userJoin : UserJoinDto = UserJoinDto()
        userJoin.userEmail = "test@test.tese${UUID.randomUUID().toString().replace("-","")[1]}.com";
        userJoin.userPassword = "!test123123!!";
        val result : JoinResultDto? =  joinService?.joinAction(userJoin, MockHttpServletRequest())
        return if(result?.check == true) userInfoRepository?.findByUserEmail(userJoin.userEmail)
        else null
    }

    /*
    uuid 생성 테스트
     */
    @Test
    fun uuidNickTest(){
        println(UUID.randomUUID().toString().replace("-",""))
    }

    /*
    mypage 정보 조회
     */
    @Test
    @Transactional
    open fun getMyPageInfo(){
        val userInfoDtoOp : Optional<UserInfoDto>?  = makeUser();
        MatcherAssert.assertThat("userInfoDtoOp is null" , userInfoDtoOp?.isPresent ?: false, Is.`is`(true));
        val myPageDto : MyPageInfoDto? = myPageService?.getMyPage(userInfoDtoOp?.get()?.userNo ?: -1)
        println("최초 생성 myPageDto -> ${myPageDto.toString()}")
        val firstViewCnt = myPageDto?.viewCnt;
        MatcherAssert.assertThat("myPageInfo is null", myPageDto == null, Is.`is`(false))

        /*
        최초 마이페이지 접속 유저 페이지 생성 후 중복 생성 여부 체크 [중복으로 생성 되면 안됨 ]
         */
        val myPageDtoExist : MyPageInfoDto? = myPageService?.getMyPage(myPageDto?.userNo ?: -1)
        MatcherAssert.assertThat("myPageDtoExist is null", myPageDtoExist == null, Is.`is`(false))
        MatcherAssert.assertThat("myPageDto != myPageDtoExist ", myPageDto?.userNo == myPageDtoExist?.userNo, Is.`is`(true))
        println("두번째 조회 후 최초 객체 myPageDto -> ${myPageDto.toString()}")
        println("myPageDtoExist -> ${myPageDtoExist.toString()}")
        println("같은 id를 가지기 때문에 [True] myPageDtoExist == myPageDto -> ${myPageDtoExist == myPageDto}")

        /*
        두번 째 조회시일때는 viewCnt + 1 이 되어 있어야함
         */
        MatcherAssert.assertThat("firstViewCnt+1 == myPageDtoExist.viewCnt ", (firstViewCnt?.plus(1)) == myPageDtoExist?.viewCnt, Is.`is`(true))
    }

    @Test
    @Transactional
    open fun nickNameDuplicateCheck(){
//        val requestJson = """
//            {
//                "nickName": "myNickname",
//                "checkStatus": "SUCCESS"
//            }
//        """.trimIndent()
//
//        mockMvc.perform(
//                get("/plus/my/check/nickname/duplicate")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestJson)
//        )
//                .andExpect(status().isOk)
        // Add more assertions as needed
    }
}