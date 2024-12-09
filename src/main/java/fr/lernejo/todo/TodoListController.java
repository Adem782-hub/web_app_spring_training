package fr.lernejo.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoListController {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoListController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public List<TodoEntity> getTodos() {
        return (List<TodoEntity>) todoRepository.findAll();
    }

    @PostMapping
    public void addTodo(@RequestBody TodoEntity todoEntity) {
        todoRepository.save(todoEntity);
    }
}

