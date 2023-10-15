package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.TodoDto;
import net.javaguides.ems.entity.Todo;
import net.javaguides.ems.repository.TodoRepository;
import net.javaguides.ems.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        // dto -> entity 변환
        Todo todo= modelMapper.map(todoDto, Todo.class);


        // todo jpa entity
        Todo savedTodo = todoRepository.save(todo);


        // entity -> dto 변환
        TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);

        return savedTodoDto;
    }
}
