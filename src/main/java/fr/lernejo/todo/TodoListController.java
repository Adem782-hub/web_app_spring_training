package fr.lernejo.todo;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todo")
public class TodoListController {
    private final TodoRepository todoRepository;

    public TodoListController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @PostMapping
    public void addTodo(@RequestBody Todo todo) {
        TodoEntity entity = new TodoEntity();
        entity.setMessage(todo.message());
        entity.setAuthor(todo.author());
        todoRepository.save(entity);
    }

    @GetMapping
    public List<Todo> getTodos() {
        return ((List<TodoEntity>) todoRepository.findAll())
                .stream()
                .map(entity -> new Todo(entity.getMessage(), entity.getAuthor()))
                .collect(Collectors.toList());
    }
}

