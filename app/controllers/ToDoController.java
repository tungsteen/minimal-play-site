package controllers;

import play.mvc.*;
import models.Todo;
import play.Logger;
import java.util.*;
import play.libs.*;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class ToDoController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result getTodos() {
        List<Todo> todos = Todo.find.all();

        JsonNode json = Json.toJson(todos);
        return ok(json);
    }

    public Result addTodo(Todo newTodo) {
        Logger.debug("addTodo: Get new todo!");
        newTodo.save();
        JsonNode jsonTodo = Json.toJson(newTodo);
        return ok(jsonTodo);
    }

    public Result doneTodo(Long id)
    {
        Todo todo = Todo.find.byId(id);
        todo.done = !todo.done;
        Logger.debug("doneTodo: Change done flag of entry: " + id + " to " + todo.done);
        todo.update();
        JsonNode jsonDone = Json.toJson(todo.done);
        return ok(jsonDone);
    }

    public Result deleteTodo(Long id)
    {
        Todo todo = Todo.find.byId(id);
        todo.delete();
        return ok();
    }

}
