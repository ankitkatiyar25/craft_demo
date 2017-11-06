package com.craft_demo.bean;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public abstract class ResponseBean {

	public String toJsonString() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}

}
