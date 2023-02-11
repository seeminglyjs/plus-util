package com.source.plusutil.filter.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.source.plusutil.enums.returnUrl.ErrorReturnUrl;
import com.source.plusutil.utils.http.HttpParamCheckUtil;
import org.springframework.core.annotation.Order;

import lombok.extern.slf4j.Slf4j;

@WebFilter(urlPatterns= {"/plus/*"})
@Order(1) //가장 처음동작 필터
@Slf4j
public class SqlInjectionFilter_01 implements Filter {
	
	//Sql 인젝션 검증에 활용한 문자 배열
	private static final String URL_ARR_NA[] = {
			"SELECT", "CREATE", "DROP", "ALERT", "RENAME", "TRUNCATE", "GRANT", "REVOKE", "COMMIT", "ROLLBACK",
			"INSERT", "UPDATE", "DELETE", "FROM", "DECLARE", "PROCEDURE", "SHOW", "SLEEP",
			"DATABASE()", "INFORMATION_SCHEMA", "KILL", "FOUND_ROWS", "ROW_COUNT"
	};
	
	//배열을 set 자료구조로 변경
	private static final Set<String> WORD_SET = new HashSet<String>(Arrays.asList(URL_ARR_NA)); 

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.debug("===== SqlInjectionCheck init =====");
	}

	//필터 시작
	@Override
	public void doFilter(ServletRequest serRequest, ServletResponse serResponse, FilterChain chain)
			throws IOException, ServletException {
		log.debug("===== SqlInjectionCheck Filter start =====");

		HttpServletRequest request = (HttpServletRequest)serRequest;
		HttpServletResponse response = (HttpServletResponse)serResponse;

		//아이피 정보를 가져온다.
		String ip = "";
		if (request.getHeader("X-Forwarded-For") == null) {
			ip = request.getRemoteAddr();
		}else{
			ip = request.getHeader("X-Forwarded-For");
		}

		//url 경로정보 가져온다.
		String path = request.getRequestURI();

		log.info("\n"
				+ "=====request url path info -> [ " + path + " ]=====\n"
				+ "=====request Ip info -> [ " + ip + " ]=====\n"
				);	

		//파라미터 정보를 가져온다.
		Map<String, String> map = HttpParamCheckUtil.httpRequestParamToMap(request);

		if(map.isEmpty()) {
			log.info("===== SqlInjectionCheck getParameterNames is empty =====");
		}else { //파라미터 정보 검증하기
			for(String value : map.values()) {
				log.info("value check ->" + value);
				value = value.toUpperCase();
				String [] valueArr = value.split(" ");
				for (String splitValue : valueArr) {
					log.info("splitValue ->" + splitValue);
					if (WORD_SET.contains(splitValue)) {
						log.info("[param] blockStringCheck info exception value-> " + splitValue);
						response.sendRedirect(ErrorReturnUrl.ERROR_MAIN.getUrl()); //문자열 집합에 걸릴 경우 에러페이지로 보낸다.
						return;
					}
				}
			}	
		}
		chain.doFilter(request, response);
	} 
}
