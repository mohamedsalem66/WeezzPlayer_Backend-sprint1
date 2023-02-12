package com.frs.weezzplayer.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadDateFormatException  extends RuntimeException {
    public BadDateFormatException(
            String message
    ) {
        super(message);
    }

}
