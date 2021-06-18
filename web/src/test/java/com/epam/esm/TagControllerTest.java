package com.epam.esm;

import com.epam.esm.domain.Role;
import com.epam.esm.jwt.JwtTokenProvider;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.empty;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public class TagControllerTest {

    private static final String tagCreateTest = "{" +
            "    \"name\": \"tagCreateTest\"\n" +
            "}";
    private final Role role = new Role("ROLE_ADMIN");
    private final Set<Role> roles = new HashSet<Role>() {{
        add(role);
    }};
    private JwtTokenProvider jwtTokenProvider;
    private String token;

    @Autowired
    public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Before
    public void init() {
        token = jwtTokenProvider.createToken("admin", roles);
    }

    @Test
    public void byIdTest() {
        given().header("Authorization", "Bearer " + token)
                .when()
                .get("http://localhost:8080/tags/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.equalTo("testTagFirst"));
    }

    @Test
    public void indexTest() {
        given().header("Authorization", "Bearer " + token)
                .when()
                .get("http://localhost:8080/tags")
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", is(not(empty())));
    }

    @Test
    public void createTest() {
        given().header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(tagCreateTest)
                .post("http://localhost:8080/tags")
                .then()
                .assertThat()
                .statusCode(201)
                .body("name", Matchers.equalTo("tagCreateTest"));
    }

    @Test
    public void deleteTestPositive() {
        given().header("Authorization", "Bearer " + token)
                .when()
                .delete("http://localhost:8080/tags/33")
                .then()
                .assertThat()
                .statusCode(204);
    }

    @Test
    public void deleteTestNegative() {
        given().header("Authorization", "Bearer " + token)
                .when()
                .delete("http://localhost:8080/tags/333333")
                .then()
                .assertThat()
                .statusCode(404);
    }

}
