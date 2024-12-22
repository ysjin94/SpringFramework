package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    // Use ObjectProvider
//    private final ObjectProvider<MyLogger> ObjectProvider;

    // Use Proxy
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURI = request.getRequestURI();

//        MyLogger myLogger = ObjectProvider.getObject();
        myLogger.setReqeustUrl(requestURI);

        myLogger.log("Controller test");

        logDemoService.logic("Test id");

        return "OK";
    }

}
