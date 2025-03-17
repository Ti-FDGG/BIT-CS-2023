import java.net.URI;

public class UseURI {
    public static void main(String[] args) {
        var myWebsite= URI.create("https://weibo.com:9000/zhanshan/home?wvr=5");
        System.out.println(myWebsite.getAuthority());//输出：weibo.com:9000
        System.out.println(myWebsite.getPath());//输出：/zhanshan/home
        System.out.println(myWebsite.getHost());//输出：weibo.com
        System.out.println(myWebsite.getPort());//输出：9000
        System.out.println(myWebsite.getScheme());//输出：https
        System.out.println(myWebsite.getQuery());//输出：wvr=5
    }
}
