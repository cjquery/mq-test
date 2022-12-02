package com.kjtpay.lombok;
import lombok.Builder;
import lombok.Data;
/**
 * @Package: com.kjtpay.lombok
 * @ClassName: User
 * @author: caojiaqi
 * @Date: Created in 2021/2/18 14:52
 * @Description： TODO
 */
@Data
@Builder
public class User {
    @Builder.Default
    private String name="111";
}
