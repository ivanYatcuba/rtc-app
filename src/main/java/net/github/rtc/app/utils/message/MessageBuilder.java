package net.github.rtc.app.utils.message;

import net.github.rtc.app.model.entity.message.Message;
import net.github.rtc.app.model.entity.message.MessageType;

import java.util.Date;

public class MessageBuilder {

    private String senderCode;
    private String receiverCode;
    private Date sendingDate;
    private String text;
    private MessageType type;

    public MessageBuilder setSenderCode(String senderCode) {
        if (senderCode == null) {
            throw new IllegalArgumentException();
        }
        this.senderCode = senderCode;
        return this;
    }

    public MessageBuilder setReceiverCode(String receiverCode) {
        if (receiverCode == null) {
            throw new IllegalArgumentException();
        }
        this.receiverCode = receiverCode;
        return this;
    }

    public MessageBuilder setSendingDate(Date sendingDate) {
        if (sendingDate == null) {
            throw new IllegalArgumentException();
        }
        this.sendingDate = new Date(sendingDate.getTime());
        return this;
    }

    public MessageBuilder setText(String text) {
        if (text == null) {
            throw new IllegalArgumentException();
        }
        this.text = text;
        return this;
    }

    public MessageBuilder setType(MessageType type) {
        if (type == null) {
            throw new IllegalArgumentException();
        }
        this.type = type;
        return this;
    }

    public Message build() {
        final Message message = new Message();
        message.setSenderUserCode(senderCode);
        message.setReceiverUserCode(receiverCode);
        message.setSendingDate(sendingDate);
        message.setText(text);
        message.setType(type);
        message.setRead(false);
        return message;
    }
}
