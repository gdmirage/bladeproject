package com.blade.archetype.entity;

import com.blade.archetype.validation.constraints.ValidCardNumber;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * TODO:
 *
 * @author Blade
 * @date 2019/5/1 17:31
 */
public class User {

    @Max(value = 10000)
    private long id;

    @NotNull
    private String userName;

    private String mobile;

    @NotNull
    @ValidCardNumber
    private String cardNumber;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
