package ee.ituk.memberlist.server.email;

import net.sargue.mailgun.Mail;
import net.sargue.mailgun.MailRequestCallback;
import net.sargue.mailgun.Response;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

@Service
public class EmailService {

    public static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Autowired
    private MailgunConfiguration mailgunConfig;

    @Autowired
    private VelocityEngine velocityEngine;

    public CompletableFuture<Response> sendAsync(String to, String templateName, VelocityContext context,
                                                  String subject) {
        Mail mail = Mail.using(mailgunConfig)
                .to(to)
                .subject(subject)
                .html(renderTemplate("html/" + templateName, context))
                .text(renderTemplate("plain/" + templateName, context))
                .build();
        return sendAsync(mail);
    }

    public CompletableFuture<Response> sendAsync(Mail mail) {
        CompletableFuture<Response> future = new CompletableFuture<>();
        mail.sendAsync(new MailRequestCallback() {
            @Override
            public void completed(Response response) {
                future.complete(response);
            }

            @Override
            public void failed(Throwable throwable) {
                future.completeExceptionally(throwable);
            }
        });
        return future;
    }

    private String renderTemplate(String templateName, VelocityContext context) {
        StringWriter writer = new StringWriter();
        try {
            velocityEngine.getTemplate("email/" + templateName + ".vm").merge(context, writer);
            return writer.getBuffer().toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public VelocityContext createContext() {
        VelocityContext context = new VelocityContext();
        context.put("datePattern", DATE_PATTERN);
        return context;
    }
}
