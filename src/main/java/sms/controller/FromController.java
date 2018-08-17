package sms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/form")
public class FromController {

    @RequestMapping("/post")
    public Map<String, String[]> post(HttpServletRequest request) {
        return  request.getParameterMap();
    }

    @RequestMapping("/post2")
    public String post2(HttpServletRequest request) {
        return  request.getParameter("test");
    }
}
