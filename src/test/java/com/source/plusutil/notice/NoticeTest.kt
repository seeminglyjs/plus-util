package com.source.plusutil.notice

import com.source.plusutil.notice.dto.NoticeDeleteRequestDto
import com.source.plusutil.notice.dto.NoticeDto
import com.source.plusutil.notice.dto.NoticeUpdateRequestDto
import com.source.plusutil.notice.dto.NoticeWriteRequestDto
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.hamcrest.core.Is
import org.hamcrest.core.IsNot
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@SpringBootTest
open class NoticeTest {

    @Autowired
    var noticeService: NoticeService? = null

    @Autowired
    var noticeRepository: NoticeRepository? = null

    @PersistenceContext
    private val entityManager: EntityManager? = null

    /*
       테스트 용도의 게시글을 만드는 메소드
     */
    private fun makeNoticeWriteDto(): NoticeDto {
        val randomTitle = "Title" + UUID.randomUUID().toString().split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[2]
        val randomContent = "Content" + UUID.randomUUID().toString().split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[2]
        val noticeWriteRequestDto = NoticeWriteRequestDto(randomTitle, randomContent, "notice", 0)
        MatcherAssert.assertThat("==== Title is null  !!!!", noticeWriteRequestDto.noticeTitle, Is.`is`(IsNot.not(Matchers.nullValue())))
        MatcherAssert.assertThat("==== Content is null  !!!!", noticeWriteRequestDto.noticeContent, Is.`is`(IsNot.not(Matchers.nullValue())))
        MatcherAssert.assertThat("==== Category is null  !!!!", noticeWriteRequestDto.category, Is.`is`(IsNot.not(Matchers.nullValue())))
        return NoticeDto.writeNotice(Objects.requireNonNull(noticeService!!.makeNoticeWriteDto("test123@123.123", noticeWriteRequestDto)))
    }

    @Test
    @Transactional
    open //클래스보다 메소드 단위의 Transactional 우선순위가 높다
    fun noticeDetailTest() {
        println("========noticeDetailTest Start!!!============")
        val writeNoticeDto = makeNoticeWriteDto()
        entityManager!!.persist(writeNoticeDto)
        val noticeNo = writeNoticeDto.noticeNo
        println("noticeNo ->$noticeNo")
        noticeRepository!!.save(writeNoticeDto)
        val noticeDetailDto = noticeService!!.getNoticeDetailInfo(null, noticeNo)
        MatcherAssert.assertThat("noticeDetailDto is null !!!!", Is.`is`(IsNot.not(Matchers.nullValue())))
        println(noticeDetailDto)
        println("========noticeDetailTest Success!!!============")
        println()
        println()
        println()
    }

    @Test
    @Transactional
    open //클래스보다 메소드 단위의 Transactional 우선순위가 높다
    fun noticeWriteTest() {
        println("========noticeWriteTest Start!!!============")
        val writeNoticeDto = makeNoticeWriteDto()
        entityManager!!.persist(writeNoticeDto)
        val noticeNo = writeNoticeDto.noticeNo
        println(noticeNo)
        noticeRepository!!.save(writeNoticeDto)
        val afterWriteNoticeDtoOp = noticeRepository!!.findById(noticeNo)
        MatcherAssert.assertThat("==== afterWriteNoticeDtoOp is null  !!!!", afterWriteNoticeDtoOp.isPresent, Is.`is`(true))
        println("========Save OK!!!============")
        val afterWriteNoticeDto = afterWriteNoticeDtoOp.get()
        println("Save Notice info -> $afterWriteNoticeDto")
        if (afterWriteNoticeDto.noticeNo == noticeNo) {
            println("notice Save Ok noticeNo is -> $noticeNo")
            noticeRepository!!.deleteById(noticeNo) //공지사항 번호 기준으로 해당 게시글 삭제
            val afterDeleteNoticeDtoOp = noticeRepository!!.findById(noticeNo)
            MatcherAssert.assertThat("==== afterDeleteNoticeDtoOp is still exist  !!!!", afterDeleteNoticeDtoOp.isPresent, Is.`is`(false))
            println("========Delete OK!!!============")
        } else {
            MatcherAssert.assertThat("==== noticeNo findById Fail  !!!!", noticeNo, Is.`is`(true))
        }
        println("========noticeWriteTest Success!!!============")
        println()
        println()
        println()
    }

    @Test
    @Transactional
    open //클래스보다 메소드 단위의 Transactional 우선순위가 높다 테스트가 붙은 transaction은 항상 Rolled back transaction for test 가 호출되어 롤백 된다..!!!!!
    //    @Rollback(false) // rollback 되지 않도록 설정
    fun noticeDeleteByNoticeNo() {
        println("=========noticeDeleteByNoticeNo start =============")
        val writeNoticeDto = makeNoticeWriteDto()
        entityManager!!.persist(writeNoticeDto)
        val noticeNo = writeNoticeDto.noticeNo
        println(noticeNo)
        noticeRepository!!.save(writeNoticeDto)
        println("save noticeNo ->$noticeNo")
        val noticeDeleteRequestDto = NoticeDeleteRequestDto(noticeNo, 0)
        val noticeDeleteResponseDto = noticeService!!.deleteNoticeInfo(noticeDeleteRequestDto)
                ?: error("noticeDeleteResponseDto 는 Null 값이 될 수 없습니다. Test Case를 확인하세요. [TestName -> noticeDeleteByNoticeNo]")
        MatcherAssert.assertThat("==== noticeDeleteResponseDto is getDeleteOk -> It shouldn't return true . Because it is an unauthorized request. !!!!", noticeDeleteResponseDto.deleteOk, Is.`is`(false))
        println("====== 단순 DELETE noticeRepository 호출 테스트 ======")
        noticeRepository!!.deleteById(noticeNo)
        val afterDeleteNoticeDtoOp = noticeRepository!!.findById(noticeNo)
        MatcherAssert.assertThat("==== afterDeleteNoticeDtoOp is still exist  !!!!", afterDeleteNoticeDtoOp.isPresent, Is.`is`(false))
        println("========Delete OK!!!============")
        println("=========noticeDeleteByNoticeNo end =============")
        println()
        println()
        println()
    }

    @Test
    @Transactional //클래스보다 메소드 단위의 Transactional 우선순위가 높다 테스트가 붙은 transaction은 항상 Rolled back transaction for test 가 호출되어 롤백 된다..!!!!!
    @WithMockUser(username = "test", roles = ["ADMIN"])
    open //임의로 어드민 계정 만들어서 테스트 하기
    fun noticeUpdateByNoticeNo() {
        println("=========noticeUpdateByNoticeNo start =============")
        val writeNoticeDto = makeNoticeWriteDto()
        val title = writeNoticeDto.title
        entityManager!!.persist(writeNoticeDto)
        val noticeNo = writeNoticeDto.noticeNo
        println(noticeNo)
        noticeRepository!!.save(writeNoticeDto)
        println("save noticeNo ->$noticeNo")
        val noticeUpdateRequestDto = NoticeUpdateRequestDto(noticeNo, "updateTitle99999", "UpdateContent", "notice")
        println("noticeService.updateNoticeInfo Call !!!")
        noticeService!!.updateNoticeInfo(noticeUpdateRequestDto)
        val afterUpdateNoticeDtoOp = noticeRepository!!.findById(noticeNo)
        assert(afterUpdateNoticeDtoOp.isPresent) { "afterUpdateNoticeDtoOp is Null!!!!!!!!!" }
        val noticeDto = afterUpdateNoticeDtoOp.get()
        println("noticeDto -> $noticeDto")
        println("Before title -> $title")
        MatcherAssert.assertThat("noticeService updateNoticeInfo 시도했지만 변경되지 않았습니다. ", noticeDto.title, Is.`is`(IsNot.not(title)))
        println("=========noticeUpdateByNoticeNo Success =============")
        println()
        println()
        println()
    }
}