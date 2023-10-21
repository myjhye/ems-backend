package net.javaguides.ems.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    // TodoAPIException 발생 시 이 메소드 호출되어 처리
    @ExceptionHandler(TodoAPIException.class)
    public ResponseEntity<ErrorDetails> handleTodoAPIException(TodoAPIException exception, WebRequest webRequest) {


        // ErrorDetails 객체 생성해 예외에 대한 정보 포함
        ErrorDetails errorDetails = new ErrorDetails(

                LocalDateTime.now(), // 현재 시간
                exception.getMessage(), // 예외 메세지
                webRequest.getDescription(false) // 웹 요청에 대한 설명 -> false : 간단한 요청 정보만 포함
        );


        // http 응답 생성 & errorDetails 객체 포함해 클라이언트에게 전달
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
