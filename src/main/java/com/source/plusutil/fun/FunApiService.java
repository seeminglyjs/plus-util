package com.source.plusutil.fun;

import com.source.plusutil.config.PropertiesConfig;
import com.source.plusutil.utils.http.HttpRequestUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class FunApiService {

    private final PropertiesConfig config;

    public void numberInfoRequest(HttpServletRequest request, String dataNumberInput, String dataNumberSelect){
        String url = "http://numbersapi.com/";
        HttpRequestUtil httpRequestUtil = new HttpRequestUtil();
        String numberApiResult;
        request.setAttribute("dataNumberInput",dataNumberInput);
        request.setAttribute("dataNumberSelect",dataNumberSelect);

        if(dataNumberSelect.equals("dataNumber")){
            numberApiResult = httpRequestUtil.httpApiGetRequestReturnString(url+dataNumberInput);
        }else if(dataNumberSelect.equals("dataMonthDay")){
            numberApiResult = httpRequestUtil.httpApiGetRequestReturnString(url+dataNumberInput+"/date");
        }else{//year
            numberApiResult = httpRequestUtil.httpApiGetRequestReturnString(url+dataNumberInput+"/year");
        }
        String papagoResult;
        if(numberApiResult != null && !numberApiResult.equals("") && !numberApiResult.equals("Fail")){
            numberApiResult = numberApiResult.replaceAll(" ", "");
            String papagoUrl= "https://openapi.naver.com/v1/papago/n2mt?source=en&target=ko&text=";
            papagoResult = httpRequestUtil.naverPapagoPostRequest(papagoUrl+numberApiResult,config.getNaverPapagoId(), config.getNaverPapagoSecret());
            if(papagoResult == null || papagoResult.equals("") || papagoResult.equals("Fail")){
                request.setAttribute("numberInfoResult", numberApiResult);
            }else{
                request.setAttribute("numberInfoResult", papagoResult);
            }
        }else{
            request.setAttribute("numberInfoResult", "API 요청이 실패했습니다.");
        }
    }
}
