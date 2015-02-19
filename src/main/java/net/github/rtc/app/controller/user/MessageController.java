package net.github.rtc.app.controller.user;



import net.github.rtc.app.controller.common.MenuItem;
import net.github.rtc.app.model.dto.user.MessageDTO;
import net.github.rtc.app.model.entity.message.MessageStatus;
import net.github.rtc.app.service.message.MessageService;
import net.github.rtc.app.utils.AuthorizedUserProvider;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import net.github.rtc.app.utils.datatable.search.filter.MessageSearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/message")
public class MessageController implements MenuItem {
    private static final String ROOT = "portal/user/message";
    private static final String MESSAGE_FILTER = "messageFilter";
    private static final String MESSAGE_STATUS = "messageStatus";
    private static final String MESSAGES = "messages";

    @Autowired
    private MessageService messageService;

    @RequestMapping(method = RequestMethod.GET)
    public String messageList() {
        return ROOT + "/message";
    }

    @RequestMapping(value = "/messageTable", method = RequestMethod.GET)
    public ModelAndView messageTable(@ModelAttribute(MESSAGE_FILTER) MessageSearchFilter searchFilter) {
        final ModelAndView mav = new ModelAndView(ROOT + "/messageTable");
        final SearchResults<MessageDTO> searchResults = messageService.searchMessagesForUser(searchFilter);

        mav.addObject(MESSAGES, searchResults.getResults());
        mav.addAllObjects(searchResults.getPageModel().getPageParams());
        return mav;
    }

    @RequestMapping(value = "/remove/{code}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeMessage(@PathVariable String code) {
        messageService.deleteByCode(code);
    }

    @ModelAttribute(MESSAGE_FILTER)
    public MessageSearchFilter getMessageSearchFilter() {
        final MessageSearchFilter messageSearchFilter = new MessageSearchFilter();
        messageSearchFilter.setUserCode(AuthorizedUserProvider.getAuthorizedUser().getCode());
        return messageSearchFilter;
    }

    @ModelAttribute(MESSAGE_STATUS)
    public MessageStatus[] getStats() {
        return MessageStatus.values();
    }

    @Override
    public String getMenuItem() {
        return MESSAGES;
    }
}
