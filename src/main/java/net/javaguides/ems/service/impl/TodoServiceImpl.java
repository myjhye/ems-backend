package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.TodoDto;
import net.javaguides.ems.entity.Todo;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.repository.TodoRepository;
import net.javaguides.ems.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;


    // todo 등록
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



    // todo 조회 -> 단일
    @Override
    public TodoDto getTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("해당 투두가 없습니다")
                );

        return modelMapper.map(todo, TodoDto.class);
    }


    // todo 조회 -> 전체
    @Override
    public List<TodoDto> getAllTodos() {

        List<Todo> todos = todoRepository.findAll();

        return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());
    }



    // todo 수정
    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(("해당 투두가 없습니다")));

        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }
}
