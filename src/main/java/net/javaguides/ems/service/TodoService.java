package net.javaguides.ems.service;

import net.javaguides.ems.dto.TodoDto;

public interface TodoService {

    // todo 등록
    TodoDto addTodo(TodoDto todoDto);


    // todo 조회 -> 단일
    TodoDto getTodo(Long id);

}
