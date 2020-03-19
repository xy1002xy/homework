package com.huyibo.springgateway.filter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huyibo.springgateway.config.DataFilterConfig;
import com.huyibo.springgateway.model.ResultModel;
import com.huyibo.springgateway.util.DateUtils;
import com.huyibo.springgateway.util.JwtUtil;
import com.huyibo.springgateway.utils.PathUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;

/**
 * @program: spring-cloud-demo
 * @description: 登陆拦截
 * 拦截类型是FilterConstants.POST_TYPE，在路由方法响应之后拦截
 * 判断请求的uri是否是登录接口（与配置文件中设置的登录uri是否匹配），需要在配置文件配置登录接口地址
 * 判断登录方法返回成功，创建token，并添加到 response body或response header，返回给前端
 * @author: wxy
 * @create: 2020-03-13 21:39
 **/
@Component
@Slf4j
public class LoginAndJwtPostFilter extends ZuulFilter {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    DataFilterConfig dataFilterConfig;
    /**
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    /**
     * filterOrder：过滤的顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 2;
    }

    /**
     * shouldFilter：这里可以写逻辑判断，是否要过滤
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        //路径与配置的相匹配，则执行过滤
        RequestContext ctx = RequestContext.getCurrentContext();
        for (String pathPattern : dataFilterConfig.getUserLoginPath()) {
            if (PathUtil.isPathMatch(pathPattern, ctx.getRequest().getRequestURI())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 执行过滤器逻辑，登录成功时给响应内容增加token
     *
     * @return
     */

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        try {
            InputStream stream = ctx.getResponseDataStream();
            String body = StreamUtils.copyToString(stream, StandardCharsets.UTF_8);
            final ResultModel<HashMap<String, Object>> result = objectMapper.readValue(body, new TypeReference<ResultModel<HashMap<String, Object>>>() {
            });
            //result.getCode() == 0 表示登录成功
            if (result.getCode() == 0) {
                HashMap<String, Object> jwtClaims = new HashMap<String, Object>() {{
                    put("userId", result.getData().get("userId"));
                }};
                //过期时间 7 天
                Date expDate = DateUtils.getAfterDays(7);
                String token = jwtUtil.createJWT(expDate, jwtClaims);
                //body json增加token
                result.getData().put("token", token);
                //序列化body json,设置到响应body中
                body = objectMapper.writeValueAsString(result);
                ctx.setResponseBody(body);

                //响应头设置token
                ctx.addZuulResponseHeader("token", token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
