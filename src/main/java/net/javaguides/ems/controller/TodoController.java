package net.javaguides.ems.controller;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.TodoDto;
import net.javaguides.ems.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private TodoService todoService;


    // PreAuthorize : 권한 검사 -> admin만 접근 가능하도록
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping

    // todo 등록
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {

        TodoDto savedTodo = todoService.addTodo(todoDto);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }


    // todo 조회 -> 단일
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long id) {

        TodoDto todoDto = todoService.getTodo(id);

        return ResponseEntity.ok(todoDto);
    }


    // todo 조회 -> 전체
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodo() {

        List<TodoDto> todos = todoService.getAllTodos();

        return ResponseEntity.ok(todos);
    }



    // todo 수정
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updatedTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long id) {

        TodoDto updatedTodo = todoService.updateTodo(todoDto, id);

        return ResponseEntity.ok(updatedTodo);
    }


    // todo 삭제
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) {

        todoService.deleteTodo(id);

        return ResponseEntity.ok("해당 투두가 삭제됨");
    }


    // todo 완료
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long id) {

        TodoDto updatedTodo = todoService.completeTodo(id);

        return ResponseEntity.ok(updatedTodo);
    }


    // todo 미완료
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TodoDto> incompleteTodo(@PathVariable("id") Long id) {

        TodoDto updatedTodo = todoService.incompleteTodo(id);

        return ResponseEntity.ok(updatedTodo);
    }

}
