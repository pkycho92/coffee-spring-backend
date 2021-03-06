package coffee;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import coffee.controller.AboutController;
import coffee.data.AboutRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AboutController.class, secure = false)
public class AboutControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AboutRepository aboutRepository;
	
	List<About> res;
	
	@Before
	public void setUp() {
		res = new ArrayList<About>();
		About about = new About();
		about.setName("Test");
		res.add(about);
	}
	
	@Test
	public void getAbouts() throws Exception{
		
		Mockito.when(aboutRepository.findAllByOrderByPosition())
			.thenReturn(res);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/abouts")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andReturn();
		
		
		JSONAssert.assertEquals("[{'id':null,'position':null,'name':'Test','description':null,'image':null}]", 
				result.getResponse().getContentAsString(),
				false);
	}
	
	@Test
	public void postAbouts() throws Exception{
		About about = res.get(0);
		
		Mockito.when(aboutRepository.save(about))
			.thenReturn(about);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/abouts")
				.content("{\"id\":null,\"position\":null,\"name\":\"Test\",\"description\":null,\"image\":null}")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder)
			.andExpect(status().isCreated())
			.andReturn();
		
		JSONAssert.assertEquals("{\"id\":null,\"position\":null,\"name\":\"Test\",\"description\":null,\"image\":null}", 
				result.getResponse().getContentAsString(),
				false);
	}
}
