package com.blade.manager.system.permission.model.login;

import java.io.Serializable;

/**
 * 验证码返回实体
 *
 * @author blade
 * 2019/9/19 11:09
 */
public class ImgResult implements Serializable{


    private String img;
    private String uuid;

    public ImgResult(String img, String uuid) {
        this.img = img;
        this.uuid = uuid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
