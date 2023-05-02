import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 1. @ClassDescription: 常用的日志框架： Log4j：Apache的一个开源项目，可以控制日志信息输送的目的地是控制台、文件、GUI组件等，可以控制每一条
 *     日志的输出格式，这些可以通过一个配置文件来灵活地进行配置，而不需要修改应用的代码。 虽然已经停止维护了，但目前绝大部分企业都是用的log4j。
 *   LogBack：是Log4j的一个改良版本
 *   Log4j2：Log4j2已经不仅仅是Log4j的一个升级版本了，它从头到尾都被重写了
 *
 *   Log4j2 在目前JAVA中的日志框架里，异步日志的性能是最高的，没有之一。
 *
 * web依赖中排除掉logging依赖，并且引入log4j2依赖。不只是starter-web中有logging jar包,
 * 如redis，mybatis中也有这个jar包，需要将他们也清除，否则依然会报错！
 *
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月21日 9:47
 */
@SpringBootTest
@Log4j2
public class LogTest {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.home"));
    }

    @Test //将指定日志打印到指定文件
    public void getLogInfo(){
        Logger event = LoggerFactory.getLogger("event");
        event.info("hello 2023");
    }
}