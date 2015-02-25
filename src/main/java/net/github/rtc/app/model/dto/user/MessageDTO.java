package net.github.rtc.app.model.dto.user;

import java.util.Date;

public class MessageDto {

    private String code;

    private String userName;

    private String text;

    private Date sendingDate;

    public MessageDto() {
    }

    public MessageDto(String userName, String text, Date sendingDate) {
        this.userName = userName;
        this.text = text;
        if (sendingDate != null) {
            this.sendingDate = new Date(sendingDate.getTime());
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getSendingDate() {
        return sendingDate == null ? null : new Date(sendingDate.getTime());
    }

    public void setSendingDate(Date sendingDate) {
        if (sendingDate != null) {
            this.sendingDate = new Date(sendingDate.getTime());
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
