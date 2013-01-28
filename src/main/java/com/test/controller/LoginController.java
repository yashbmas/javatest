package com.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


@Controller
public class LoginController {
	
	@RequestMapping("/")
	public String showForm() {
		return "hello";
	}

	@RequestMapping(value="/info")
	public String displayWeatherInfo(@RequestParam("zipCode")String zip,
			Model model) {

		RestTemplate restTemplate = new RestTemplate();

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

		messageConverters.add(new MappingJacksonHttpMessageConverter());

		restTemplate.setMessageConverters(messageConverters);

		@SuppressWarnings("unused")
		String[] jsonResponse = restTemplate.getForObject(
				"http://api.wunderground.com/api/ed044d75b91fb500/conditions/q/"
						+zip+".json", String[].class);
		return "info";
	}
	
}