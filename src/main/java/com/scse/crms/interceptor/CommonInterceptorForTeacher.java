package com.scse.crms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.scse.crms.po.User;


//��֤��¼
public class CommonInterceptorForTeacher extends HandlerInterceptorAdapter{  
    private final Logger log = LoggerFactory.getLogger(CommonInterceptorForTeacher.class);  
    /* 
     * ��������ӳ�䵽��Ҫ���ص�·�� 
 
    private String mappingURL; 
 
    public void setMappingURL(String mappingURL) { 
               this.mappingURL = mappingURL; 
    } 
  */  
    /** 
     * ��ҵ��������������֮ǰ������ 
     * �������false 
     *     �ӵ�ǰ������������ִ��������������afterCompletion(),���˳��������� 
     * �������true 
     *    ִ����һ��������,ֱ�����е���������ִ����� 
     *    ��ִ�б����ص�Controller 
     *    Ȼ�������������, 
     *    �����һ������������ִ�����е�postHandle() 
     *    �����ٴ����һ������������ִ�����е�afterCompletion() 
     */  
    @Override  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
        
        User user =  (User) request.getSession().getAttribute("user");  
        if(!user.getRole().equals("teacher")){  
            response.setCharacterEncoding("UTF-8");  
            response.getWriter().write("Insufficient authority");
            //response.sendRedirect("/CRMS/sign_in.html");
            //request.getRequestDispatcher("login.action").include(request, response);  
            return false;  
        }else  
            return true;
    }  
  
    /** 
     * ��ҵ��������������ִ����ɺ�,������ͼ֮ǰִ�еĶ��� 
     * ����modelAndView�м������ݣ����統ǰʱ�� 
     */  
    @Override  
    public void postHandle(HttpServletRequest request,  
                           HttpServletResponse response, Object handler,  
                           ModelAndView modelAndView) throws Exception {  
        
    }  
  
    /** 
     * ��DispatcherServlet��ȫ����������󱻵���,������������Դ�� 
     * 
     * �����������׳��쳣ʱ,��ӵ�ǰ����������ִ�����е���������afterCompletion() 
     */  
    @Override  
    public void afterCompletion(HttpServletRequest request,  
                                HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {  
        log.info("==============ִ��˳��: 3��afterCompletion================");  
    }  
  
}  
