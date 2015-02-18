package net.github.rtc.app.utils.message;

import net.github.rtc.app.model.entity.message.Message;
import net.github.rtc.app.model.entity.message.MessageType;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;

public class MessageBuilderTest {

    private String senderCode = "1";
    private String receiverCode = "2";
    private Date sendingDate = new Date();
    private String text = "test";
    private MessageType type = MessageType.SYSTEM;


    public void testMessageBuilding() {
        Message message = (new MessageBuilder()).setReceiverCode(receiverCode).
                setSenderCode(senderCode).setSendingDate(sendingDate).setText(text).setType(type).build();
        assertEquals(senderCode, message.getSenderUserCode());
        assertEquals(receiverCode, message.getReceiverUserCode());
        assertEquals(text, message.getText());
        assertEquals(sendingDate, message.getSendingDate());
        assertEquals(type, message.getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullReceiver() {
        new MessageBuilder().setReceiverCode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullSender() {
        new MessageBuilder().setSenderCode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullDate() {
        new MessageBuilder().setSendingDate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullText() {
        new MessageBuilder().setText(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullType() {
        new MessageBuilder().setType(null);
    }

}
