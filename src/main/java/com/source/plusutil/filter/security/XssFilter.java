package com.source.plusutil.filter.security;

import com.source.plusutil.utils.http.HttpParamCheckUtil;
import com.source.plusutil.utils.protect.XSSRequestWrapper;
import com.source.plusutil.utils.protect.XSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@WebFilter(urlPatterns="/*")
@Order(2)
@Slf4j
public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException, IOException {

        XSSRequestWrapper wrappedRequest = new XSSRequestWrapper(
                (HttpServletRequest) request);

        String body = IOUtils.toString(wrappedRequest.getReader());
        log.debug("XSS-FILTER BODY INFO -> " + body);

        if(!"".equals(body))
        {
            JSONObject oldJsonObject = new JSONObject(body);
            JSONObject newJsonObject = new JSONObject();

            for(String key : oldJsonObject.keySet())
            {
                newJsonObject.put(key, XSSUtils.stripXSS(oldJsonObject.get(key).toString()));
            }
            wrappedRequest.resetInputStream(newJsonObject.toString().getBytes());

        }
        Map<String, String> map = HttpParamCheckUtil.httpRequestParamToMap(wrappedRequest);
        log.info("map -> " + map.toString());
        if(!map.isEmpty()){
            wrappedRequest.resetInputStream(XSSUtils.stripXSSToMap(map).toString().getBytes());
        }
        chain.doFilter(wrappedRequest, response);
    }
}