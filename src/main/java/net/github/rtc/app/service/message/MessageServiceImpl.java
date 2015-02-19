package net.github.rtc.app.service.message;

import net.github.rtc.app.dao.generic.GenericDao;
import net.github.rtc.app.dao.message.MessageDao;
import net.github.rtc.app.model.dto.builder.MessageDTOBuilder;
import net.github.rtc.app.model.dto.user.MessageDTO;
import net.github.rtc.app.model.entity.message.Message;
import net.github.rtc.app.service.generic.AbstractGenericServiceImpl;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import net.github.rtc.app.utils.datatable.search.SearchResultsBuilder;
import net.github.rtc.app.utils.datatable.search.SearchResultsMapper;
import net.github.rtc.app.utils.datatable.search.filter.MessageSearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl  extends AbstractGenericServiceImpl<Message> implements MessageService {

    @Autowired
    private MessageDao messageDao;

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

    private SearchResultsMapper<Message, MessageDTO> getMessageMapper() {
        return new SearchResultsMapper<Message, MessageDTO>() {
            @Override
            public List<MessageDTO> map(List<Message> searchResults) {
                final List<MessageDTO> outputResults = new ArrayList<>();
                for (Message msg : searchResults) {
                    final MessageDTOBuilder dtoBuilder = new MessageDTOBuilder(msg);
                    outputResults.add(dtoBuilder.build());
                }
                return outputResults;
            }
        };
    }
}
