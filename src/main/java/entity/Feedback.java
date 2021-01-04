package entity;

import lombok.*;

import java.sql.Date;

/**
 * @ClassName Feedback
 * @Description TODO
 * @Author Finger
 * @Date 12/29/2020
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Feedback {
    private int produceId;
    private int customerId;
    private int feedbackId;
    private String content;
    private Date createTime;
    private int dealStatus;
}
