package com.frs.weezzplayer.repository.interfaces;

import com.frs.weezzplayer.model.EmailContext.AbstractEmailContext;

import javax.mail.MessagingException;

public interface EmailService {
    void sendmail(final AbstractEmailContext email ) throws MessagingException;;
}
