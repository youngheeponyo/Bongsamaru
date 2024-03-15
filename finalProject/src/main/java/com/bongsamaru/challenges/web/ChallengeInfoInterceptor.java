package com.bongsamaru.challenges.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bongsamaru.challenges.service.ChallengesService;
import com.bongsamaru.common.VO.ChallengesVO;
import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.facility.Service.FacilityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ChallengeInfoInterceptor implements HandlerInterceptor {

    @Autowired
    private ChallengesService challengeService; // 이 부분을 추가

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
    	 String chalIdParam = request.getParameter("chalId");

    	    if (chalIdParam != null) {
    	        Integer chalId = Integer.parseInt(chalIdParam);
    	        ChallengesVO findVO = challengeService.getChallengeInfo(chalId);
    	        request.getSession().setAttribute("cInfo", findVO);

    	        // 계속 진행할 수 있도록 true를 반환
    	        return true;
    	    } else {
    	        // 적절한 처리를 수행하거나 예외처리
    	        return false;
    	    }
    }
}