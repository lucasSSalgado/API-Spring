package sprintBoot.animeAPI.exceptions;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ExceptionDetails {

    protected String msg;
    protected String status;
    protected int numberStatus;
    protected String userMsg;
    protected LocalDateTime timeStanp;    
}
