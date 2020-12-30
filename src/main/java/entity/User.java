package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName User
 * @Description TODO
 * @Author Finger
 * @Date 12/29/2020
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private Level accountLevel;

    public enum Level {
        ADMIN(2), EMPLOYEE(1), CUSTOMER(0);

        private Integer value;

        Level(Integer value) {
            this.value = value;
        }

        public static Level getEnum(int value) {
            switch (value) {
                case 0:
                    return CUSTOMER;
                case 1:
                    return EMPLOYEE;
                case 2:
                    return ADMIN;
                default:
                    return null;
            }
        }

        public Integer toInt() {
            return this.value;
        }

    }
}
