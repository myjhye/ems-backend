package net.javaguides.ems.service;

import net.javaguides.ems.dto.TodoDto;
import net.javaguides.ems.entity.Todo;

import java.util.List;

public interface TodoService {

    // todo 등록
    TodoDto addTodo(TodoDto todoDto);


    // todo 조회 -> 단일
    TodoDto getTodo(Long id);


    // todo 조회 -> 전체
    List<TodoDto> getAllTodos();


     // todo 수정
    TodoDto updateTodo(TodoDto todoDto, Long id);


    // todo 삭제
    void deleteTodo(Long id);


    // todo 완료
    TodoDto completeTodo(Long id);

}
