package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient3 {

    private String url;

    public NetworkClient3() {
        System.out.println("NetworkClient.NetworkClient =" + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect(){
        System.out.println("NetworkClient.connect :" + url);
    }

    public void call(String msg){
        System.out.println("NetworkClient.call :" + url + " : "+ msg);
    }

    public void disconnect(){
        System.out.println("NetworkClient.disconnect :" + url);
    }

    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetworkClient2.init");
        connect();
        call("Constructor Connect Msg");
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient2.close");
        disconnect();
    }
}
