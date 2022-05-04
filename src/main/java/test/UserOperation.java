package test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import entity.User;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import support.BaseConfig;
import type.Mensagens;

public class UserOperation extends BaseConfig{
	
	User user;
	
	@BeforeClass
	public void init() {
		user = gerarUsuarioRandomico();
	}
	
	@Test
	public void CEN01_criarUsuario() {
		
		ExtractableResponse<Response> response = 
		given()
			.spec(requestSpec)
			.body(user)
		.when()
			.post("/users")
		.then()
			.spec(responseSpec)
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("usersCreat.json"))
			.body("code", is(HttpStatus.SC_CREATED))
			.extract();
		
		user.id = response.path("data.id");
		user.created_at = response.path("data.created_at");
		user.updated_at = response.path("data.updated_at");
	}
//	
//	@Test
//	public void CEN02_consultarUsuario() {
//		given()
//			.spec(requestSpec)
//			.pathParam("id", user.id)
//		.when()
//			.get("/users/{id}")
//		.then()
//			.spec(responseSpec)
//			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("usersCreat.json"))
//			.body("code", is(HttpStatus.SC_OK))
//			.body("data.id", is(user.id))
//			.body("data.name", is(user.name))
//			.body("data.email", is(user.email))
//			.body("data.gender", is(user.gender))
//			.body("data.status", is(user.status))
//			.body("data.created_at", is(user.created_at))
//			.body("data.updated_at", is(user.updated_at));
//	}
//	
//	@Test
//	public void CEN03_alterarUsuario() {
//		User userUpdate = gerarUsuarioRandomico();
//		
//		given()
//			.spec(requestSpec)
//			.pathParam("id", user.id)
//			.body(userUpdate)
//		.when()
//			.put("/users/{id}")
//		.then()
//			.spec(responseSpec)
//			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("usersCreat.json"))
//			.body("code", is(HttpStatus.SC_OK))
//			.body("data.id", is(user.id))
//			.body("data.name", is(userUpdate.name))
//			.body("data.email", is(userUpdate.email))
//			.body("data.gender", is(userUpdate.gender))
//			.body("data.status", is(userUpdate.status));
//	}
//	
//	
//	@Test
//	public void CEN04_deletarUsuario() {
//		given()
//			.spec(requestSpec)
//			.pathParam("id", user.id)
//		.when()
//			.delete("/users/{id}")
//		.then()
//			.spec(responseSpec)
//			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("usersDelete.json"))
//			.body("code", is(HttpStatus.SC_NO_CONTENT));
//	}
	
	@Test
	public void CEN05_criarUsuarioSemAutenticacao() {
		
		user = gerarUsuarioRandomico();
		
		given()
			.spec(requestSpecUnauthorized)
			.body(user)
		.when()
			.post("/users")
		.then()
			.spec(responseSpec)
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("usersAuth.json"))
			.body("code", is(HttpStatus.SC_UNAUTHORIZED))
			.body("data.message", is(Mensagens.AUTHENTICATION_FAILED.getMensagem()));
	}
	
//	@Test
//	public void CEN06_criarUsuarioCamposObrigatorios() {
//		
//		user = new User();
//		
//		given()
//			.spec(requestSpec)
//			.body(user)
//		.when()
//			.post("/users")
//		.then()
//			.spec(responseSpec)
//			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("usersBlank.json"))
//			.body("code", is(HttpStatus.SC_UNPROCESSABLE_ENTITY))
//			.body("data.field[0]", is("email"))
//			.body("data.message[0]", is(Mensagens.CAMPO_NAO_PODE_ESTAR_EM_BRANCO.getMensagem()))
//			.body("data.field[1]", is("name"))
//			.body("data.message[1]", is(Mensagens.CAMPO_NAO_PODE_ESTAR_EM_BRANCO.getMensagem()))
//			.body("data.field[2]", is("gender"))
//			.body("data.message[2]", is(Mensagens.CAMPO_NAO_PODE_ESTAR_EM_BRANCO.getMensagem()))
//			.body("data.field[3]", is("status"))
//			.body("data.message[3]", is(Mensagens.CAMPO_NAO_PODE_ESTAR_EM_BRANCO.getMensagem()));
//	}
//	
//	@Test
//	public void CEN07_criarUsuarioComEmailJaUtilizado() {
//		
//		User userExistente = gerarUsuarioRandomico();
//		
//		given()
//		.spec(requestSpec)
//		.body(userExistente)
//	.when()
//		.post("/users");
//		
//		given()
//			.spec(requestSpec)
//			.body(userExistente)
//		.when()
//			.post("/users")
//		.then()
//			.spec(responseSpec)
//			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("usersAlreadyTaken.json"))
//			.body("code", is(HttpStatus.SC_UNPROCESSABLE_ENTITY))
//			.body("data.field[0]", is("email"))
//			.body("data.message[0]", is(Mensagens.DADO_JA_UTILIZADO.getMensagem()));
//	}
//	
//	@Test
//	public void CEN08_consultarUsuarioInexistente() {
//		given()
//		.spec(requestSpec)
//		.pathParam("id", 220590)
//		.when()
//		.get("/users/{id}")
//		.then()
//		.spec(responseSpec)
//		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("usersNon.json"))
//		.body("code", is(HttpStatus.SC_NOT_FOUND))
//		.body("data.message", is(Mensagens.USUARIO_NAO_ENCONTRADO.getMensagem()));
//	}
//	
//	@Test
//	public void CEN09_alterarUsuarioInexistente() {
//		User userUpdate = gerarUsuarioRandomico();
//		
//		given()
//			.spec(requestSpec)
//			.pathParam("id", 220590)
//			.body(userUpdate)
//		.when()
//			.put("/users/{id}")
//		.then()
//			.spec(responseSpec)
//			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("usersNon.json"))
//			.body("code", is(HttpStatus.SC_NOT_FOUND))
//			.body("data.message", is(Mensagens.USUARIO_NAO_ENCONTRADO.getMensagem()));
//	}
//	
//	@Test
//	public void CEN10_alterarUsuarioSemCampoObrigatorio() {
//		user = gerarUsuarioRandomico();
//		
//		ExtractableResponse<Response> response = 
//		given()
//			.spec(requestSpec)
//			.body(user)
//		.when()
//			.post("/users")
//		.then()
//			.extract();
//		
//		User userUpdate = new  User();
//		
//		given()
//			.spec(requestSpec)
//			.pathParam("id", response.path("data.id"))
//			.body(userUpdate)
//		.when()
//			.put("/users/{id}")
//		.then()
//			.spec(responseSpec)
//			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("usersBlank.json"))
//			.body("code", is(HttpStatus.SC_UNPROCESSABLE_ENTITY))
//			.body("data.field[0]", is("email"))
//			.body("data.message[0]", is(Mensagens.CAMPO_NAO_PODE_ESTAR_EM_BRANCO.getMensagem()))
//			.body("data.field[1]", is("name"))
//			.body("data.message[1]", is(Mensagens.CAMPO_NAO_PODE_ESTAR_EM_BRANCO.getMensagem()))
//			.body("data.field[2]", is("gender"))
//			.body("data.message[2]", is(Mensagens.CAMPO_NAO_PODE_ESTAR_EM_BRANCO.getMensagem()))
//			.body("data.field[3]", is("status"))
//			.body("data.message[3]", is(Mensagens.CAMPO_NAO_PODE_ESTAR_EM_BRANCO.getMensagem()));
//	}
//	
//	@Test
//	public void CEN11_deletarUsuarioInexistente() {
//		given()
//			.spec(requestSpec)
//			.pathParam("id", 220590)
//		.when()
//			.delete("/users/{id}")
//		.then()
//			.spec(responseSpec)
//			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("usersNon.json"))
//			.body("code", is(HttpStatus.SC_NOT_FOUND))
//			.body("data.message", is(Mensagens.USUARIO_NAO_ENCONTRADO.getMensagem()));
//	}
//	
	@Test
	public void CEN12_criarUsuarioComAutenticacaoInvalida() {
		given()
			.header("Authorization", "Bearer 987654321abcdefg")
			.spec(requestSpecUnauthorized)
			.body(user)
		.when()
			.post("/users")
		.then()
			.spec(responseSpec)
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("usersAuth.json"))
			.body("code", is(HttpStatus.SC_UNAUTHORIZED))
			.body("data.message", is(Mensagens.AUTHENTICATION_FAILED.getMensagem()));
	}
	
	
	
	
	private User gerarUsuarioRandomico() {
		Faker usuario = new Faker();
		return new User(usuario.lordOfTheRings().character(), usuario.internet().emailAddress(), usuario.demographic().sex(), "Active");
	}
}