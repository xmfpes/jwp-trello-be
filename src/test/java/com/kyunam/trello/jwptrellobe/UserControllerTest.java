package com.kyunam.trello.jwptrellobe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.kyunam.trello.jwptrellobe.domain.UserDto;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
  @Value("${local.server.port}")
  private int serverPort;

  @Before
  public void setup() {
    RestAssured.port = serverPort;
  }

  @Test
  public void create() throws Exception {
    UserDto newUser = new UserDto("hell", "javajigi@slipp.net", "password");
    given()
      .contentType(ContentType.JSON)
      .body(newUser)
    .when()
      .post("/api/users")
    .then()
      .statusCode(HttpStatus.CREATED.value());
  }
}