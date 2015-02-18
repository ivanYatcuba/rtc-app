package net.github.rtc.app.dao.message;

import net.github.rtc.app.dao.generic.GenericDao;
import net.github.rtc.app.model.entity.message.Message;

import javax.annotation.Resource;

@Resource
public interface MessageDao  extends GenericDao<Message> {
}
