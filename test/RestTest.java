import org.junit.*;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;

import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.concurrent.Callable;
import static play.test.Helpers.*;
import play.Logger;

public class RestTest
{
    private static final int PORT = 3333;

    @Before
    public void setUp()
    {
        RestAssured.port = PORT;
    }

    @After
    public void tearDown()
    {
        RestAssured.reset();
    }

    @Test
    public void getAll_emptyDatabase()    
    {
        running(testServer(PORT, fakeApplication(inMemoryDatabase())), HTMLUNIT, browser ->  {
            String body = RestAssured.expect().statusCode(200)
                            .when().get("/todos/all").andReturn().body().asString();
            JsonNode node = Json.parse(body);
            Assert.assertTrue(node.isArray());
            Assert.assertEquals(0, node.size());
        });
    }

    @Test
    public void addTodosToDatabase()
    {
        running(testServer(PORT, fakeApplication(inMemoryDatabase())), HTMLUNIT, browser ->  {
            RestAssured.expect().statusCode(200).when().post("/todos?done=true&text='Buy Milk!'");
            RestAssured.expect().statusCode(200).when().post("/todos?done=false&text='Eat Snacks!'");


            String body = RestAssured.expect().statusCode(200)
                            .when().get("/todos/all").andReturn().body().asString();
            JsonNode node = Json.parse(body);
            Assert.assertTrue(node.isArray());
            Assert.assertEquals(2, node.size());
        });
    }

    @Test
    public void toggleDone()
    {
        running(testServer(PORT, fakeApplication(inMemoryDatabase())), HTMLUNIT, browser ->  {
            RestAssured.expect().statusCode(200).when().post("/todos?done=true&text='Buy Milk!'");
            String body = RestAssured.expect().statusCode(200)
                            .when().get("/todos/all").andReturn().body().asString();
            JsonNode node = Json.parse(body);
            long id1 = node.get(0).get("id").asLong();
            boolean done1 = node.get(0).get("done").asBoolean();

            Logger.debug(node.toString());

            RestAssured.expect().statusCode(200).when().post("/todos/done/" + id1);
            body = RestAssured.expect().statusCode(200)
                            .when().get("/todos/all").andReturn().body().asString();

            node = Json.parse(body);
            long id2 = node.get(0).get("id").asLong();
            boolean done2 = node.get(0).get("done").asBoolean();

            Assert.assertEquals(id1, id2);
            Assert.assertNotEquals(done1, done2);
        });
    }

    @Test
    public void deleteTodo()
    {
        running(testServer(PORT, fakeApplication(inMemoryDatabase())), HTMLUNIT, browser ->  {
            RestAssured.expect().statusCode(200).when().post("/todos?done=true&text='Buy Milk!'");
            String body = RestAssured.expect().statusCode(200)
                            .when().get("/todos/all").andReturn().body().asString();
            JsonNode node = Json.parse(body);
            long id1 = node.get(0).get("id").asLong();

            RestAssured.expect().statusCode(200).when().delete("/todos/" + id1);
            body = RestAssured.expect().statusCode(200)
                            .when().get("/todos/all").andReturn().body().asString();

            node = Json.parse(body);
            Assert.assertTrue(node.isArray());
            Assert.assertEquals(0, node.size());
        });
   }
}
