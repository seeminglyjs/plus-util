package com.source.plusutil.controller;

import com.source.plusutil.enums.returnUrl.ErrorReturnUrl;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.thymeleaf.exceptions.*;

import lombok.extern.slf4j.Slf4j;

//@RestControllerAdvice //바디 형식으로 맵핑됨
@ControllerAdvice 	//url 형식으로 맵핑됨
@Slf4j
public class RestErrorController {
	
	// 지원되지 않는 메소드 요청 시
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public String httpRequestMethodNotSupportedExceptionAdvice(HttpRequestMethodNotSupportedException e) { 
		log.info("HttpRequestMethodNotSupportedException" , e);
		return ErrorReturnUrl.ERROR_MAIN.getUrl();
	}
	
	
	// 잘못된 타입에 대한 요청 시
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public String methodArgumentTypeMismatchExceptionAdvice(MethodArgumentTypeMismatchException e) { 
		log.info("methodArgumentTypeMismatchExceptionAdvice" , e);
		return ErrorReturnUrl.ERROR_MAIN.getUrl();
	}
	
	// 타임리프에 대한 요청 input 오류 발생시
	@ExceptionHandler(TemplateInputException.class)
	public String TemplateInputExceptionAdvice(TemplateInputException e) { 
		log.info("TemplateInputException" , e);
		return ErrorReturnUrl.ERROR_MAIN.getUrl();
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public String noHandlerFoundExceptionAdvice(NoHandlerFoundException e) {
		log.info("NoHandlerFoundException" , e);
		return ErrorReturnUrl.ERROR_MAIN.getUrl();
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public String missingServletRequestParameterExceptionAdvice(MissingServletRequestParameterException e) {
		log.info("MissingServletRequestParameterException" , e);
		return ErrorReturnUrl.ERROR_MAIN.getUrl();
	}
//    @ExceptionHandler(BindException.class)
//    public ResponseEntity<Map<String, String>> handleValidationBindExceptions(BindException ex){
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors() //에러 정보를 가져온다.
//                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage())); //바인딩 이슈가 발생한 필드/
//        return ResponseEntity.badRequest().body(errors);
//    }
	
	
//	//에러 제이슨 객체 -> String 을(를) 만드는 메소드 
//	public static String makeErrorMessage(Exception e) {
//		String result = "";
//		log.info("===== "+ e.toString() + " =====");
//		JsonObject json = new JsonObject();	
//		json.addProperty("resultCode", "400");
//		String temp = e.toString().split(":")[0]; //앞에 exception 정보만 담는다.
//		json.addProperty("message", "Error caused by bad request - > " + temp);	
//		result = json.toString();
//		log.info("Error info -> [ " + result + " ]");
//		return result;
//	};
//	
//	//에러 제이슨 객체 -> String 을(를) 만드는 메소드 
//	public static String makeErrorMessage(Exception e, String checkValue) {
//		String result = "";
//		log.info("===== "+ e.toString() + " =====");
//		JsonObject json = new JsonObject();	
//		json.addProperty("resultCode", checkValue);
//		String temp = e.toString().split(":")[0]; //앞에 exception 정보만 담는다.
//		json.addProperty("message", "Error caused by bad request - > " + temp);	
//		result = json.toString();
//		log.info("Error info -> [ " + result + " ]");
//		return result;
//	};
	

//	// null 예외 처리
//	@ExceptionHandler(NullPointerException.class)
//	public String nullPointerExceptionAdvice(NullPointerException e) { 
//		String result = "";
//		result = makeErrorMessage(e);
//		return result;
//	}


//	// 지원되지 않는 형식의 파라미터 요청 시
//	@ExceptionHandler(HttpMessageNotReadableException.class)
//	public String httpMessageNotReadableExceptionAdvice(HttpMessageNotReadableException e) { 
//		String result = "";
//		result = makeErrorMessage(e);
//		return result;
//	}

//	// 핸들링 되지 않는 요청 발생 시
//	@ExceptionHandler(NoHandlerFoundException.class)
//	public ResponseEntity<Void> noHandlerFoundExceptionAdvice(NoHandlerFoundException e,  HttpServletRequest request) { 	
//		String ip = "";		
//		if (request.getHeader("X-Forwarded-For") == null) {
//			ip = request.getRemoteAddr();
//		}else{
//			ip = request.getHeader("X-Forwarded-For");
//		}
//		
//		log.info("\n"
//				+ "=====request uri info -> [ " + request.getRequestURI() + " ]=====\n"
//				+ "=====request Ip info -> [ " + ip + " ]=====\n"
//				+ "return HttpStatus.GONE -> 410 "
//				);	
//		String checkValue = "404";
//		//String result = makeErrorMessage(e, checkValue);
//		return new ResponseEntity<>(HttpStatus.GONE);
//	}
}
