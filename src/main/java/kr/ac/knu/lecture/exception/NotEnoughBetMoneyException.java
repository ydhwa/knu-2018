package kr.ac.knu.lecture.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotEnoughBetMoneyException extends RuntimeException{
}
