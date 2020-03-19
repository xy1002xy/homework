package com.huyibo.springgateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: spring-cloud-demo
 * @description: 实现身份认证，参数校验，参数传递功能
 * @author: wxy
 * @create: 2020-03-13 21:35
 **/
public class CustomFilter extends ZuulFilter {


    /**
     * filterType：过滤器类型
     * <p>
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
//        return FilterConstants.POST_TYPE;
    }

    /**
     * filterOrder：过滤的顺序 序号配置可参照 https://blog.csdn.net/u010963948/article/details/100146656
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * shouldFilter：判断是否要执行过滤
     *
     * @return true表示需要过滤，将对该请求执行run方法
     */
    public boolean shouldFilter() {
        return true;
    }

    /**
     * run：具体过滤的业务逻辑，可做身份验证，校验参数等等
     *
     * @return
     */

    public Object run() {

        //获取请求上下文对象
        RequestContext ctx = RequestContext.getCurrentContext();
        //获取request对象
        HttpServletRequest request = ctx.getRequest();
        //获取response对象
        HttpServletResponse response = ctx.getResponse();
        //添加请求头，传递到业务服务
        ctx.addZuulRequestHeader("xxx", "xxx");
        //添加响应头，返回给前端
        ctx.addZuulResponseHeader("xxx", "xxx");
        return null;
    }
}
