package com.kyunam.trello.jwptrellobe;

import static io.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import com.kyunam.trello.jwptrellobe.domain.UserDto;
import com.kyunam.trello.jwptrellobe.domain.UserRepository;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
  @Value("${local.server.port}")
  private int serverPort;

  @Autowired
  UserRepository userRepository;
  
  @Before
  public void setup() {
    RestAssured.port = serverPort;
  }

  @Test
  public void create() throws Exception {
    UserDto newUser = new UserDto("xmfpes", "javajigi@slipp.net", "1234");
    createRestAssured(newUser, "/api/users", HttpStatus.CREATED);
  }
  
  @Test
  public void login() throws Exception {
	  createRestAssured(new UserDto("xmfpes", "1234"), "/login", HttpStatus.OK);
  }
  
  public void createRestAssured(Object obj, String url, HttpStatus status) {
	  given()
      .contentType(ContentType.JSON)
      .body(obj)
    .when()
      .post(url)
    .then()
      .statusCode(status.value());
  }
  
  
}