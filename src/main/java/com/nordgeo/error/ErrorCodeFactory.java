package com.nordgeo.error;

public class ErrorCodeFactory {


    public static RestError.Code create(String constraint) {

        switch (constraint) {
            case "NotNull":
            case "NotEmpty":
            case "NotBlank":
                return RestError.Code.NotEmpty;
            case "Email":
                return RestError.Code.Email;
            case "Pattern":
                return RestError.Code.Pattern;
            case "Size":
                return RestError.Code.Size;
            case "Duplicate.userForm.email":
                return RestError.Code.EmailDuplication;
            case "Duplicate.userForm.username":
                return RestError.Code.UsernameDuplication;
            default:
                return RestError.Code.Default;
        }
    }
}
