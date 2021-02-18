package com.kjtpay.lombok;

/**
 * @Package: com.kjtpay.lombok
 * @ClassName: LombokTest
 * @author: caojiaqi
 * @Date: Created in 2021/2/18 14:55
 * @Description： TODO
 */
public class LombokTest {
    public static void main(String[] args) {
        User user = User.builder().build();
        System.out.println(user);
    }
}
