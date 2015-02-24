package net.github.rtc.app.service.message;

import net.github.rtc.app.dao.generic.GenericDao;
import net.github.rtc.app.dao.message.MessageDao;
import net.github.rtc.app.model.dto.user.MessageDTO;
import net.github.rtc.app.model.entity.message.Message;
import net.github.rtc.app.service.generic.AbstractGenericServiceImpl;
import net.github.rtc.app.service.user.UserService;
import net.github.rtc.app.model.dto.SearchResults;
import net.github.rtc.app.service.builder.SearchResultsBuilder;
import net.github.rtc.app.service.builder.SearchResultsMapper;
import net.github.rtc.app.model.dto.filter.MessageSearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl  extends AbstractGenericServiceImpl<Message> implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserService userService;

    @Override
    protected GenericDao<Message> getDao() {
        return messageDao;
    }

    @Override
    public SearchResults<MessageDTO> searchMessagesForUser(MessageSearchFilter searchFilter) {
            final SearchResultsBuilder<Message, MessageDTO> resultsBuilder = new SearchResultsBuilder<>();
            return resultsBuilder.setSearchResultsToTransform(search(searchFilter)).
                    setSearchResultsMapper(getMessageMapper()).build();
    }

    @Override
    public Message readMessage(String messageCode) {
        final  Message message = findByCode(messageCode);
        if (!message.isRead()) {
            message.setRead(true);
            return messageDao.update(message);
        }
        return message;
    }

    @Override
    public int getUserUnreadMessageCount(String userCode) {
        return messageDao.getUnreadMessageCont(userCode);
    }

    /**
     * Returns an object that map list of messages to messageDTOs list
     * @return anonymous class mapper
     */
    private SearchResultsMapper<Message, MessageDTO> getMessageMapper() {
        return new SearchResultsMapper<Message, MessageDTO>() {
            @Override
            public List<MessageDTO> map(List<Message> searchResults) {
                final List<MessageDTO> outputResults = new ArrayList<>();
                for (Message msg : searchResults) {
                    final MessageDTO messageDTO = new MessageDTO();
                    messageDTO.setUserName(userService.findByCode(msg.getSenderUserCode()).getSurnameName());
                    messageDTO.setText(msg.getText());
                    messageDTO.setSendingDate(msg.getSendingDate());
                    messageDTO.setCode(msg.getCode());
                    outputResults.add(messageDTO);
                }
                return outputResults;
            }
        };
    }
}
