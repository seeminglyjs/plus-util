package com.source.plusutil.mypage


import com.source.plusutil.config.PropertiesConfig
import com.source.plusutil.mypage.dto.MyPageInfoDto
import com.source.plusutil.mypage.dto.MyPageModifyDto
import com.source.plusutil.user.join.service.JoinService
import com.source.plusutil.user.UserInfoRepository
import com.source.plusutil.user.join.dto.JoinResultDto
import com.source.plusutil.user.dto.UserInfoDto
import com.source.plusutil.user.dto.UserJoinDto
import com.source.plusutil.utils.encrypt.AesUtil
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
    var config : PropertiesConfig? = null;

    @Autowired
    private lateinit var mockMvc: MockMvc
    /*
    테스트 계정 생성
     */
    private fun makeUser(): Optional<UserInfoDto>? {
        val userJoin : UserJoinDto = UserJoinDto()
        userJoin.userEmail = AesUtil.aes256Encrypt(config?.aes256key, config?.aes256iv, "test@test.tese${UUID.randomUUID().toString().replace("-","")[1]}.com")["encryptContent"]
        userJoin.userPassword = AesUtil.aes256Encrypt(config?.aes256key, config?.aes256iv, "!test123123!!")["encryptContent"]
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
        val myPageDto : MyPageInfoDto? = myPageService?.getMyPage(userInfoDtoOp?.get()?.userNo ?: -1, "yes")
        println("최초 생성 myPageDto -> ${myPageDto.toString()}")
        val firstViewCnt = myPageDto?.viewCnt;
        MatcherAssert.assertThat("myPageInfo is null", myPageDto == null, Is.`is`(false))

        /*
        최초 마이페이지 접속 유저 페이지 생성 후 중복 생성 여부 체크 [중복으로 생성 되면 안됨 ]
         */
        val myPageDtoExist : MyPageInfoDto? = myPageService?.getMyPage(myPageDto?.userNo ?: -1,"yes")
        MatcherAssert.assertThat("myPageDtoExist is null", myPageDtoExist == null, Is.`is`(false))
        MatcherAssert.assertThat("myPageDto != myPageDtoExist ", myPageDto?.userNo == myPageDtoExist?.userNo, Is.`is`(true))
        println("두번째 조회 후 최초 객체 myPageDto -> ${myPageDto.toString()}")
        println("myPageDtoExist -> ${myPageDtoExist.toString()}")
        println("같은 id를 가지기 때문에 [True] myPageDtoExist == myPageDto -> ${myPageDtoExist == myPageDto}")

        /*
        두번 째 조회시일때는 viewCnt + 1 이 되어 있어야함
         */
        MatcherAssert.assertThat("firstViewCnt+1 == myPageDtoExist.viewCnt ", (firstViewCnt?.plus(1)) == myPageDtoExist?.viewCnt, Is.`is`(true))

        /*
        좋아요 테스트
         */
        val myPageLikeDto : MyPageInfoDto? = myPageService?.likePlus(myPageDto?.userNo ?: -1)
        println("Before -> ${myPageDtoExist?.likeCnt!!}")
        println("After -> ${myPageLikeDto?.likeCnt!!}")
        MatcherAssert.assertThat("firstLikeCnt+1 > firstLikeCnt  is false", myPageLikeDto.likeCnt!! > myPageDtoExist.likeCnt!!, Is.`is`(true))

        val myPageModifyDto = MyPageModifyDto (
                userNo = myPageLikeDto.userNo,
                nickName = "testNick5599",
                description = myPageLikeDto.description,
                userName = myPageLikeDto.userName,
                userPhone = myPageLikeDto.userPhone,
                phoneShow = "y",
                nameShow = "y",
        )

        /*
        수정 테스트
         */
        val myPageInfoModifyDto : MyPageInfoDto? = myPageService?.modifyPage(myPageModifyDto)
        MatcherAssert.assertThat("MyPageInfoModifyDto is null", myPageInfoModifyDto == null, Is.`is`(false))
        MatcherAssert.assertThat("MyPageInfoModifyDto.nickName is not testNick5599", myPageInfoModifyDto?.nickName == "testNick5599", Is.`is`(true))

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