package com.escl.citi.error;

public class RestError {


    public enum Code {
        Default(999),
        NotEmpty(101),
        Email(103),
        Pattern(104),
        EmailDuplication(105),
        UsernameDuplication(106),
        Size(107),

        UNAUTHORIZED(1000),
        CREDENTIALS(1001),
        TOKEN_EXPIRED(1002),
        DISABLED(1003),
        DEMO_EXPIRED(1004),


        ALERTS_LIMIT(3001),
        ALERT_DUPLICATION(3002),

        MISSING_POLL_ANSWERS(5001);


        int code;

        Code(int i) {
            code = i;
        }

        public int getCode() {
            return code;
        }
    }

}
