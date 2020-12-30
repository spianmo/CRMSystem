package bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ResultEntity
 * @Description TODO
 * @Author Finger
 * @Date 11/15/2020
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResultEntity<T> {
    private int code;
    private String message;
    private T data;
}
