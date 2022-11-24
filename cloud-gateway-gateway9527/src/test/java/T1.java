import java.time.ZonedDateTime;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2022年11月23日 16:26
 */
public class T1 {

    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now(); //默认时区
        System.out.println(now);
    }
}