import org.junit.*;

import play.mvc.*;
import play.test.*;

import static play.test.Helpers.*;
import static org.junit.Assert.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;

import java.util.concurrent.TimeUnit;
import models.Todo;

// more docs here -> https://github.com/FluentLenium/FluentLenium
public class IntegrationTest {

    @Test
    public void checkBasicHtml() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, browser -> {
            Todo t = new Todo(false, "Eat Snacks");
            t.save();
            browser.goTo("http://localhost:3333");
            assertEquals("Eat Snacks", browser.$(".todo-text").getText());
            assertTrue(browser.title().contains("ToDo List"));
        });
    }

    @Test
    public void addNewTodo() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, browser -> {
            browser.goTo("http://localhost:3333");
            browser.fill("#textToSubmit").with("Buy Milk");
            browser.$("#add-form").submit();
            browser.await().atMost(5, TimeUnit.SECONDS).until(".todo-text").hasSize(1);
            assertEquals("Buy Milk", browser.$(".todo-text").getText());
        });
    }

    @Test
    public void markUnmarkTodoAsDone() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, browser -> {
            Todo t = new Todo(false, "Eat Snacks");
            t.save();
            browser.goTo("http://localhost:3333");
            assertEquals("text-decoration:none;", browser.$(".todo-text").getAttribute("style"));
            browser.$(".done-checkbox").click();
            browser.await().atMost(5, TimeUnit.SECONDS).until(".todo-text").with("style").contains("line-through").isPresent();
            browser.$(".done-checkbox").click();
            browser.await().atMost(5, TimeUnit.SECONDS).until(".todo-text").with("style").contains("none").isPresent();
        });
    }


}
