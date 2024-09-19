package com.hard.cegAndranovelona.intercepeteur;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.hard.cegAndranovelona.modeles.Administrateur;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class MyInterceptor implements HandlerInterceptor {
    static final List<String> uriNotProtect = new ArrayList<>(Arrays.asList("/loginAdmin","/logonAdmin","/crypte"));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LocalDateTime dateTime=LocalDateTime.now();
        String uri=request.getRequestURI();
        int index = uri.indexOf("?");
        uri = (index != -1) ? uri.substring(0, index) : uri;
        System.out.println(dateTime+"-----------------------------------------URL : " + request.getRequestURI());

        HttpSession session=request.getSession();
        Administrateur admin=new Administrateur();
        admin.setEtat(1);
        admin.setNom("koto");
        admin.setMotDepasse("12345");
        session.setAttribute("admin", admin);
        if (uriNotProtect.contains(uri)) {
            System.out.println(dateTime+"-------------------------------------Url found and not secure");
            System.out.println(dateTime+"---------------------------------------------------------------------------------------------------------------------");
            System.out.println(dateTime+"-------------------------------------Url found and not secure");
            return true;
        }else{
            if (session.getAttribute("admin")==null) {
                response.sendRedirect("/loginAdmin?msg=url not found");
                return false;
            }
        }
        return true;
    }
}
