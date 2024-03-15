package com.bongsamaru.facility.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.facility.Service.FacilityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class FacilityInfoInterceptor implements HandlerInterceptor {

    @Autowired
    private FacilityService facilityService; // 이 부분을 추가

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 여기에서 세션에 값을 설정
        String facId = request.getParameter("facId");
        FacilityVO findVO = facilityService.getFacilityInfo(facId);
        request.getSession().setAttribute("fInfo", findVO);

        // 계속 진행할 수 있도록 true를 반환
        return true;
    }
}