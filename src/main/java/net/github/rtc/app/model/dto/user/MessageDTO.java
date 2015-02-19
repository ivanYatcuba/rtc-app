package net.github.rtc.app.model.dto.user;

import java.util.Date;

public class MessageDTO {

    private String userName;

    private String text;

    private Date sendingDate;

    public MessageDTO() {
    }

    public MessageDTO(String userName, String text, Date sendingDate) {
        this.userName = userName;
        this.text = text;
        this.sendingDate = sendingDate;
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
        return sendingDate;
    }

    public void setSendingDate(Date sendingDate) {
        this.sendingDate = sendingDate;
    }
}
