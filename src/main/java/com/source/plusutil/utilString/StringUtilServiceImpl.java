package com.source.plusutil.utilString;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.source.plusutil.utilString.dto.*;
import com.source.plusutil.utilString.enums.StringCaseEnum;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public non-sealed class StringUtilServiceImpl implements StringUtilService{

	static String [] INITIAL_STRING = {
			"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", 
			"ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", 
			"ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", 
			"ㅋ", "ㅌ", "ㅍ", "ㅎ" };

	/**
	 * 문자열 정보를 입력받아 바이트 수를 계산한다.
	 * 
	 */
	@Deprecated
	@Override
	public void getStringByte(HttpServletRequest request, String stringContent, String encoding, String emptyYn) {
		String stringContentByte = "에러가 발생했습니다.";
		Optional<String> stringContentOp = Optional.ofNullable(stringContent);
		if(stringContentOp.isPresent()) {//전달 문자열 존재
			if(encoding == null) {encoding = "UTF-8";}
			if(emptyYn == null) {emptyYn = "No";}
			try {
				int ByteInfo = 0;
				if(emptyYn.equals("Yes")) {
					ByteInfo = stringContentOp.get()
							.replaceAll(" ", "")
							.getBytes(encoding)
							.length;
				}else {
					ByteInfo = stringContentOp.get()
							.getBytes(encoding)
							.length;
				}
				stringContentByte = String.valueOf(ByteInfo);
				request.setAttribute("stringContent", stringContent);
				request.setAttribute("encoding", encoding);
				request.setAttribute("emptyYn", emptyYn);
				request.setAttribute("stringContentByte", stringContentByte);
			} catch (UnsupportedEncodingException e) {
				request.setAttribute("stringContent", stringContent);
				request.setAttribute("encoding", encoding);
				request.setAttribute("emptyYn", emptyYn);
				request.setAttribute("stringContentByte", stringContentByte);
				log.info("exception", e);
			}
		}
	}

	@Override
	public StringInitialResponseDto getInitialString(StringInitialRequestDto stringInitialRequestDto) {
		StringBuilder initialStringSb = new StringBuilder();
		StringBuilder stringContentSb = new StringBuilder();
		try{
			stringContentSb.append(stringInitialRequestDto.getStringContent());
		}catch (NullPointerException e){
			return StringInitialResponseDto.builder().stringContent("").initialString("요청 정보가 존재하지 않습니다.").build();
		}
		Optional<String> stringContentOp = Optional.of(stringContentSb.toString());
		//전달 문자열 존재
		for(int i = 0; i < stringContentOp.get().length(); i++) {
			String stringLocationInfo = String.valueOf(stringContentOp.get().charAt(i)); //현재 위치의 한글자 추출
			if(stringLocationInfo.matches(".*[가-힣]+.*")) {// 한글일 경우
				if(stringLocationInfo.charAt(0) >= 0xAC00) // 음절 시작코드(10진값, 문자) : 0xAC00 (44032 ,'가' )
				{
					int unicode = stringLocationInfo.charAt(0) - 0xAC00;
					int index = ((unicode - (unicode % 28))/28)/21;
					initialStringSb.append(INITIAL_STRING[index]);
				}
			} else {//한글이 아닐경우
				initialStringSb.append(stringLocationInfo);//변환없이 그대로 저장
			}
		}
		return StringInitialResponseDto.builder().stringContent(stringContentSb.toString()).initialString(initialStringSb.toString()).build();
	}

	/*
	 * 문자열에 길이를 계산하는 메소드
	 *
	 * @param request
	 * @param stringContent
	 * @return
	 */
	@Deprecated
	@Override
	public Map<String,String> getLengthString(HttpServletRequest request, String stringContent) {
		Map<String,String> responseMap = new HashMap<>();
		responseMap.put("stringContent", stringContent);
		responseMap.put("stringLength", String.valueOf(stringContent.length()));
		return responseMap;
	}

	@Override
	public StringConvertCaseResponseDto convertUpperAndLowerMain(StringConvertCaseRequestDto stringConvertCaseRequestDto) {
		StringBuilder stringContentSb = new StringBuilder();
		StringBuilder upperOrLowerSb = new StringBuilder();
		StringBuilder convertStringContentSb = new StringBuilder();
		try{
			stringContentSb.append(stringConvertCaseRequestDto.getStringContent());
			upperOrLowerSb.append(stringConvertCaseRequestDto.getUpperOrLower());
		}catch (NullPointerException e){
			return StringConvertCaseResponseDto.builder()
					.stringContent("")
					.upperOrLower("")
					.convertStringContent("요청 정보가 없습니다.")
					.build();
		}
		if(upperOrLowerSb.toString().equals(StringCaseEnum.UPPER.getStr()) || upperOrLowerSb.toString().equals(StringCaseEnum.LOWER.getStr())){
			if(upperOrLowerSb.toString().equals(StringCaseEnum.UPPER.getStr())){
				convertStringContentSb.append(stringConvertCaseRequestDto.getStringContent().toUpperCase());
			} else if (upperOrLowerSb.toString().equals(StringCaseEnum.LOWER.getStr())) {
				convertStringContentSb.append(stringConvertCaseRequestDto.getStringContent().toLowerCase());
			}
		}else{//대소문자 구분을 알 수 없을 경우
			return StringConvertCaseResponseDto.builder()
					.stringContent(stringContentSb.toString())
					.upperOrLower(upperOrLowerSb.toString())
					.convertStringContent("변환 값이 잘못되었습니다.")
					.build();
		}
		return StringConvertCaseResponseDto.builder()
				.stringContent(stringContentSb.toString())
				.upperOrLower(upperOrLowerSb.toString())
				.convertStringContent(convertStringContentSb.toString())
				.build();
	}

	@Override
	public StringSimilarityResponseDto checkSimilarity(StringSimilarityRequestDto stringSimilarityRequestDto) {
		int maxLen = Math.max(stringSimilarityRequestDto.getFirstContent().length(), stringSimilarityRequestDto.getSecondContent().length());
		LevenshteinDistance ld = new LevenshteinDistance();
		double result;
		double temp = ld.apply(stringSimilarityRequestDto.getFirstContent(), stringSimilarityRequestDto.getSecondContent());
		result = (maxLen - temp) / maxLen;
		String similarity = "유사성 : " + result * 100 + "% /";
		similarity += "유사거리 : " + temp;
		return StringSimilarityResponseDto.builder()
				.firstContent(stringSimilarityRequestDto.getFirstContent())
				.secondContent(stringSimilarityRequestDto.getSecondContent())
				.similarity(similarity)
				.build();
	}

	@Override
	public StringReplaceResponseDto stringReplace(StringReplaceRequestDto stringReplaceRequestDto) {
		if(Optional.ofNullable(stringReplaceRequestDto).isEmpty()) {
			return new StringReplaceResponseDto("값을 입력해주세요.", "값을 입력해주세요.", "값을 입력해주세요.");
		}else{
			return new StringReplaceResponseDto(
					stringReplaceRequestDto.getFindStr(),
					stringReplaceRequestDto.getReplaceStr(),
					stringReplaceRequestDto.getContent().replaceAll(stringReplaceRequestDto.getFindStr(),stringReplaceRequestDto.getReplaceStr())
			);
		}
	}
}
