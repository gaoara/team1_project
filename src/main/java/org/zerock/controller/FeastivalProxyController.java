package org.zerock.controller;

import java.net.URI;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.zerock.openapi.Body;
import org.zerock.openapi.Items;
import org.zerock.openapi.Result;
import org.zerock.util.DateUtil;

@RestController
@RequestMapping("/festival")
public class FeastivalProxyController {
	
	static Logger logger = LoggerFactory.getLogger(FeastivalProxyController.class);
	private static final String SERVICE_KEY = "oMYSCkfnU%2BrM%2F6ad8zAICkGBj0eUCOxJc9bR%2F8MHuzhfo62P6cGA1YVZ7iY5QnDedVyfk5tMhc0Wu42fjDJ%2BcA%3D%3D";
	
	@RequestMapping(value="/list", method = RequestMethod.GET )
	public ResponseEntity<Body> test() {
		
		Result result = null;
		
		DateUtil date = new DateUtil();
		String tmFc = date.gettmFc();
		logger.info("tmFc = " + date.gettmFc());
		
		RestTemplate restTemplate = new RestTemplate();
		String baseURI = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival"
							+ "?ServiceKey=" + SERVICE_KEY
							+ "&numOfRows=10"
							+ "&eventstartdate=20160908"
							+ "&pageNo=1"
							+ "&MobileOS=ETC"
							+ "&MobileApp=TestApp"
							+ "&_type=json";

		URI uri = URI.create(baseURI);
		logger.info("request uri : " + uri);
		
		result = restTemplate.getForObject(uri, Result.class);
		logger.info(restTemplate.getForObject(uri, String.class));
		
		Body body = result.getResponse().getBody();
		System.out.println("items = " + body.getItems().getItem());

		return new ResponseEntity<Body>(body, HttpStatus.OK);
		
	}	
}