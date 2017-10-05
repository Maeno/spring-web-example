package org.maeno.example.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maeno.example.config.MyBatisConfiguration;
import org.maeno.example.service.ProjectService;
import org.maeno.example.test.TestDataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(value = {LoginController.class,
        TestDataSourceConfig.class,
        MyBatisConfiguration.class,
        ProjectService.class})
public class LoginControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

//    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity()).build();
    }

    @Test
    public void index() throws Exception {
        this.mockMvc.perform(get("/").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andReturn();
    }

//    FIXME I want to solve this test case someday.
//    @Test
//    public void redirectToLogin() throws Exception {
//        this.mockMvc.perform(get("/hoge").with(anonymous()))
//                .andExpect(status().isFound())
//                .andExpect(redirectedUrl("/login"))
//                .andReturn();
//    }


    //    FIXME I want to solve this test case someday.
//    @Test
//    public void loginSuccess() throws Exception {
//        this.mockMvc.perform(post("/login")
//                .params(
//                        new LinkedMultiValueMap<String, String>() {{
//                            put("username", Arrays.asList("SCOTT"));
//                            put("password", Arrays.asList("TIGER"));
//                        }})
//                .accept(MediaType.TEXT_HTML))
//                .andExpect(status().isOk())
//                .andExpect(view().name("login"))
//                .andReturn();
//    }
    //    FIXME I want to solve this test case someday.
//    @Test
//    public void loginFailed() throws Exception {
//        this.mockMvc.perform(post("/login")
//                .params(
//                        new LinkedMultiValueMap<String, String>() {{
//                            put("username", Arrays.asList("XXX"));
//                            put("password", Arrays.asList("XXX"));
//                        }})
//                .accept(MediaType.TEXT_HTML))
//                .andExpect(status().isOk())
//                .andExpect(view().name("/main"))
//                .andReturn();
//    }
}
