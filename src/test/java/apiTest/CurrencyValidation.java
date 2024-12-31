package apiTest;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import resources.Utils;

public class CurrencyValidation extends Utils {

	@Test
	public void currencyTest() {
		try {
			RestAssured.baseURI = getPropertyValue("baseUrl");
			Response response = given().contentType(ContentType.JSON).when().get(getPropertyValue("currencyAPI")).then().extract().response();
			
			String bpiUSD = getJsonPath(response, "bpi.USD.code");
			Assert.assertEquals(bpiUSD, "USD");
			String bpiGBP = getJsonPath(response, "bpi.GBP.code");
			Assert.assertEquals(bpiGBP, "GBP");
			String bpiEUR = getJsonPath(response, "bpi.EUR.code");
			Assert.assertEquals(bpiEUR, "EUR");
			
			Assert.assertEquals(getJsonPath(response, "bpi.GBP.description"), "British Pound Sterling");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String req() {
		return "{\"time\":{\"updated\":\"Dec 31, 2024 05:42:53 UTC\",\"updatedISO\":\"2024-12-31T05:42:53+00:00\",\"updateduk\":\"Dec 31, 2024 at 05:42 GMT\"},\"disclaimer\":\"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org\",\"chartName\":\"Bitcoin\",\"bpi\":{\"USD\":{\"code\":\"USD\",\"symbol\":\"&#36;\",\"rate\":\"92,728.539\",\"description\":\"United States Dollar\",\"rate_float\":92728.5389},\"GBP\":{\"code\":\"GBP\",\"symbol\":\"&pound;\",\"rate\":\"73,949.155\",\"description\":\"British Pound Sterling\",\"rate_float\":73949.1552},\"EUR\":{\"code\":\"EUR\",\"symbol\":\"&euro;\",\"rate\":\"88,961.071\",\"description\":\"Euro\",\"rate_float\":88961.0711}}}";
	}
}
