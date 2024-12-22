package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    //Proxy
    private final MyLogger myLogger;

    //Use ObjectProvider
//    private final ObjectProvider<MyLogger> objectProvider;

    public void logic(String id){
//        MyLogger myLogger = objectProvider.getObject();
        myLogger.log("service id = " + id);
    }
}
