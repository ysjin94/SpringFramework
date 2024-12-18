package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient2{

    private String url;

    public NetworkClient2() {
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

    public void init() throws Exception {
        System.out.println("NetworkClient2.init");
        connect();
        call("Constructor Connect Msg");
    }

    public void close() throws Exception {
        System.out.println("NetworkClient2.close");
        disconnect();
    }
}
