package entity;

import java.sql.Date;

import bean.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private Status dealStatus;
    public enum Status implements BaseEnum {
        /**
         * 待解决
         */
        TO_BE_SOLVED(0,"待解决"),
        /**
         * 处理中
         */
        PROCESSING(1,"处理中"),
        /**
         * 已处理
         */
        PROCESSED(2,"已处理"),
        /**
         * 通道关闭
         */
        CLOSED(3,"通道关闭");

        private final Integer value;
        private final String desc;

        Status(Integer value,String desc) {
            this.value = value;
            this.desc = desc;
        }

        @Override
        public Integer getCode() {
            return this.value;
        }

        @Override
        public String getDesc() {
            return this.desc;
        }

    }
}
