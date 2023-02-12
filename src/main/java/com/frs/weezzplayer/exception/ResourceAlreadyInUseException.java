package com.frs.weezzplayer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class ResourceAlreadyInUseException extends RuntimeException {

    private String msg;
}



