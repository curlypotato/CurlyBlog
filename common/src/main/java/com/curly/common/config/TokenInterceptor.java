package com.curly.common.config;

import com.curly.common.exception.base.BaseErrorEnum;
import com.curly.common.exception.base.BaseException;
import com.curly.common.util.RedisUtils;
import com.curly.common.util.TokenUtils;
import io.swagger.models.HttpMethod;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author broWsJle
 * @date 2022/11/14 15:17
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        JSONObject json=new JSONObject();

        //跨域请求会首先发一个option请求，直接返回正常状态并通过拦截器
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            System.out.println("OPTIONS请求，放行");
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        if (token!=null){
//            boolean result= TokenUtils.verify(token);
//            if (result){
//                System.out.println("通过拦截器");
//                return true;
//            }
            // 添加redis修改
            System.out.println("想要的token:"+token);
            if (redisUtils.get("token").equals(token)) {
                System.out.println("通过拦截器");
                return true;
            } else {
                System.out.println("已存在一个token，未通过拦截器");
                throw new BaseException(BaseErrorEnum.USER_INVALID);
            }
        }
        response.setContentType("application/json; charset=utf-8");
        try {
            json.put("msg","token verify fail");
            json.put("code","500");
            response.getWriter().append(json.toString());
            System.out.println("认证失败，未通过拦截器");
        } catch (Exception e) {
            return false;
        }
        /**
         * 还可以在此处检验用户存不存在等操作
         */
        return false;
    }
}
