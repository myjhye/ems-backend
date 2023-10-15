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

}
