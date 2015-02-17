package net.github.rtc.app.service.message;

import net.github.rtc.app.dao.generic.GenericDao;
import net.github.rtc.app.dao.message.MessageDao;
import net.github.rtc.app.model.entity.message.Message;
import net.github.rtc.app.service.generic.AbstractGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageServiceImpl  extends AbstractGenericServiceImpl<Message> implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    protected GenericDao<Message> getDao() {
        return messageDao;
    }

}
