package com.epam.esm;

import com.epam.esm.domain.Tag;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.empty;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public class TagControllerTest {

    private static final String tagCreateTest = "{" +
            "    \"name\": \"tagCreateTest\"\n" +
            "}";

    @Test
    public void byIdTest() {
        get("http://localhost:8080/tags/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.equalTo("testTagFirst"));
    }

    @Test
    public void indexTest() {
        get("http://localhost:8080/tags")
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", is(not(empty())));
    }

    @Test
    public void createTest() {
        given()
                .contentType(ContentType.JSON)
                .body(tagCreateTest)
                .post("http://localhost:8080/tags")
                .then()
                .assertThat()
                .statusCode(201)
                .body("name",Matchers.equalTo("tagCreateTest"));
    }

    @Test
    public void deleteTestPositive() {
        delete("http://localhost:8080/tags/33")
                .then()
                .assertThat()
                .statusCode(204);
    }
    @Test
    public void deleteTestNegative() {
        delete("http://localhost:8080/tags/333333")
                .then()
                .assertThat()
                .statusCode(404);
    }

}
