//package com.source.plusutil.user;
//
//import java.util.Optional;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.transaction.Transactional;
//
//import com.source.plusutil.user.join.dto.JoinResultDto;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.source.plusutil.config.PropertiesConfig;
//import com.source.plusutil.user.dto.UserInfoDto;
//import com.source.plusutil.user.dto.UserJoinDto;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@RequiredArgsConstructor
//@Transactional
//@Service
//@Slf4j
//public class JoinServiceBackUp {
//    private final PropertiesConfig config;
//
//    private final PasswordEncoder passwordEncoder;
//
//    //생성자 객체 주입
//    private final UserInfoRepository userInfoRepository;
//
//    /*
//     * 회원가입 유저 정보 저장 메소드
//     *
//     * @param userEmail
//     * @param userPassword
//     * @param request
//     */
//    public JoinResultDto joinAction(UserJoinDto userJoinDto, HttpServletRequest request) {
//        JoinResultDto joinResultDto = new JoinResultDto();
//        boolean joinSuccess = false; //회원가입 성공여부 체크 변수
//        log.info("joinAction UserJoinDto ->["+userJoinDto.toString()+"]");
//        if(joinDataCheck(userJoinDto.getUserEmail()) && joinDataCheck(userJoinDto.getUserPassword())) {//정해진 데이터가 모두 정상인 경우
//            log.info("userEmail -> [" + userJoinDto.getUserEmail() + "]");
//            log.info("userPassword -> [" + userJoinDto.getUserPassword() + "]");
//            if(validateDuplicateUser(userJoinDto.getUserEmail())) {//유저 로그인 계정 중복 여부 체크
//                try{
//                    userInfoRepository.save(UserInfoDto.makeUser(userJoinDto, passwordEncoder)); //만들어진 유저정보 db 저장하기
//                    joinSuccess = true;//에외없이 처리되면 회원가입 성공
//                }catch (Exception e) {
//                    log.info("[joinDataVaildCheck false] =====");
//                    log.info("[Exception info]", e);
//                }
//            }else{
//                joinResultDto.setMessage("이미 존재하는 아이디입니다.");
//            }
//        }
//        joinResultDto.setCheck(joinSuccess);
//        //회원가입 성공여부 객체 저장
//        return joinResultDto;
//    }
//
//    /*
//     * 회원가입 유저정보의 중복 여부를 체크하는 메소드
//     *
//     * @param userEmail
//     * @return
//     */
//    private boolean validateDuplicateUser(String userEmail) {
//        Optional<UserInfoDto> userInfoDtoOptinoal = userInfoRepository.findByUserEmail(userEmail);
//        UserInfoDto userInfo = userInfoDtoOptinoal.orElse(null); //null 여부 체크
//        if(userInfo == null) {
//            log.info("validateDuplicateUser UserInfoDto -> [ null ] ");
//            log.info("[joinAction available] ===== ");
//            return true;
//        }else {
//            log.info("validateDuplicateUser exist -> [" + userInfo.toString() + "] ");
//            return false;
//        }
//    }
//
//    /*
//     * 회원가입 정보가 유효한지 여부를 체크하는 메소드
//     *
//     * @param data
//     * @return
//     */
//    public boolean joinDataCheck(String data) {
//        //요청데이터가 null이거난 없을 경우 false
//        return data != null && !data.equals(config.getNoData());
//    }
//
//    /*
//     * 회원가입 실패시에 동작할 메소드
//     *
//     * @param request
//     */
//    public JoinResultDto joinFail(HttpServletRequest request) {
//        log.info("joinFail Method start =====");
//        return new JoinResultDto();
//    }
//}
