package entity.vo;

import java.sql.Date;

import entity.Feedback;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName FeedbackVo
 * @Description TODO
 * @Author Finger
 * @Date 1/11/2021
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FeedbackVo {
    private int produceId;
    private String produceName;
    private int customerId;
    private String customerName;
    private String employeeName;
    private int feedbackId;
    private String content;
    private Date createTime;
    private Feedback.Status dealStatus;
}
