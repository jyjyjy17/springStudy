package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request")
public class MyLogger {
    //[UUID][requestURL] {message}
    private String uuid;
    private String requestUrl;

    public void setRequestUrl(String requestUrl) {
        this.requestUrl=requestUrl;
    }
    public void log(String message) {
        System.out.println(String.format("UUID: %s, Request URL: %s, Message: %s", uuid, requestUrl, message));
    }
    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println(String.format("[%s] request scope bean create ",uuid)+this);
        System.out.println("request bean created");
    }
    @PreDestroy
    public void close(){
        System.out.println("end");
    }
}
