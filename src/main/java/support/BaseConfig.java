package support;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import type.Contantes;

public class BaseConfig {
	
	protected static RequestSpecification requestSpec;
	protected static RequestSpecification requestSpecUnauthorized;
	protected static ResponseSpecification responseSpec;
	
	@BeforeClass
	public static void configInicial() {
		RestAssured.baseURI = Contantes.URL_BASE.getnome();
		
		requestSpec  = 
				new RequestSpecBuilder()
				.addHeader("Authorization", Contantes.ACCESS_TOKEN.getnome())
				.setContentType(ContentType.JSON)
				.setRelaxedHTTPSValidation()
				.log(LogDetail.ALL)
				.build();
		
		requestSpecUnauthorized  = 
				new RequestSpecBuilder()
				.setContentType(ContentType.JSON)
				.setRelaxedHTTPSValidation()
				.log(LogDetail.ALL)
				.build();
		
		responseSpec = 
				new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(HttpStatus.SC_OK)
				.log(LogDetail.ALL)
				.build();
	}
}