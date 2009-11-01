package nl.gridshore.newsfeed.integration.xmpp;

import nl.gridshore.newsfeed.domain.Author;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>Aspect used to send a message to the author of a news item that it was received and posted on the webpage.
 * To be as non intrusive as possible we have used an Around advice.</p>
 * <p>This aspect requires a method with two parameters:</p>
 * <ul>
 * <li>argument 1 : author</li>
 * <li>argument 2 : title of item</li>
 * </ul>
 *
 * @author Jettro Coenradie
 */
@Aspect
public class SendMessageAspect {
    @Autowired
    private XmppMessagingService xmppMessagingService;

    public void sendMessageOnCreationOfNewsItem(ProceedingJoinPoint pjp) throws Throwable {
        pjp.proceed();
        Object[] args = pjp.getArgs();
        Author author = (Author) args[0];
        String title = (String) args[1];
        xmppMessagingService.sendMessage(author.getEmail(), title);
    }
}
