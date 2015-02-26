package net.github.rtc.app.model.entity.message;

import net.github.rtc.app.model.entity.AbstractPersistenceObject;
import net.github.rtc.util.annotation.validation.Validatable;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Validatable
public class Message extends AbstractPersistenceObject {

    private static final int SUBJECT_LENGTH = 50;

    @NotEmpty
    @Column
    private String receiverUserCode;

    @NotEmpty
    @Column
    private String senderUserCode;

    @Length(max = SUBJECT_LENGTH)
    @NotEmpty
    @Column
    private String subject;

    @NotEmpty
    @Column
    private String description;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date sendingDate;

    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    private MessageType type;

    @Column
    private boolean isRead = false;



    public Message() {
    }

    public Message(String receiverUserCode, String senderUserCode, String subject, String description, Date sendingDate, MessageType type) {
        this.receiverUserCode = receiverUserCode;
        this.senderUserCode = senderUserCode;
        this.subject = subject;
        this.description = description;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
