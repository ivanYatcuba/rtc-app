package net.github.rtc.app.model.entity.message;

import net.github.rtc.app.model.entity.AbstractPersistenceObject;
import net.github.rtc.util.annotation.validation.Validatable;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Validatable
public class Message extends AbstractPersistenceObject {

    @NotEmpty
    @Column
    private String receiverUserCode;

    @NotEmpty
    @Column
    private String senderUserCode;

    @NotEmpty
    @Column
    private String text;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date sendingDate;

    @NotEmpty
    @Column
    @Enumerated(EnumType.STRING)
    private MessageType type;

    @Column
    private boolean isRead = false;



    public Message() {
    }

    public Message(String receiverUserCode, String senderUserCode, String text, Date sendingDate, MessageType type) {
        this.receiverUserCode = receiverUserCode;
        this.senderUserCode = senderUserCode;
        this.text = text;
        if (sendingDate != null) {
            this.sendingDate = new Date(sendingDate.getTime());
        }
        this.type = type;
    }


    public String getReceiverUserCode() {
        return receiverUserCode;
    }

    public void setReceiverUserCode(String receiverUserCode) {
        this.receiverUserCode = receiverUserCode;
    }

    public String getSenderUserCode() {
        return senderUserCode;
    }

    public void setSenderUserCode(String senderUserCode) {
        this.senderUserCode = senderUserCode;
    }

    public Date getSendingDate() {
        return sendingDate == null ? null : new Date(sendingDate.getTime());
    }

    public void setSendingDate(Date sendingDate) {
        if (sendingDate != null) {
            this.sendingDate = new Date(sendingDate.getTime());
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
