package com.nordgeo.controller.app;


import com.nordgeo.entity.Tool;
import com.nordgeo.service.tool.ToolService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ToolControllerAppTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToolService toolService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testIndexAvailableAction() throws Exception {

        mockMvc.perform(get("/app/tools/available"))
                .andExpect(status().isOk())
                .andExpect(view().name("app.tools.index"));
    }

    @Test
    public void testIndexUnavailableAction() throws Exception {

        mockMvc.perform(get("/app/tools/unavailable"))
                .andExpect(status().isOk())
                .andExpect(view().name("app.tools.unavailable.index"));
    }

    @Test
    public void testIndexUserAction() throws Exception {

        mockMvc.perform(get("/app/tools/user"))
                .andExpect(status().isOk())
                .andExpect(view().name("app.tools.my.index"));
    }

    @Test
    public void testIndexUnusableAction() throws Exception {

        mockMvc.perform(get("/app/tools/unusable"))
                .andExpect(status().isOk())
                .andExpect(view().name("app.tools.index.unusable"));
    }

    @Test
    public void testCollectOneAction() throws Exception {

        mockMvc.perform(get("/app/tools/append/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/app/tools/available"));
    }

    @Test
    public void testReturnOneAction() throws Exception {

        mockMvc.perform(post("/app/tools/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("app.tools.form"));
    }

    @Test
    public void testFormAction() throws Exception {

        mockMvc.perform(get("/app/tools/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("app.tools.form"));
    }

    @Test
    public void testEditFormAction() throws Exception {

        when(toolService.findById(anyInt())).thenReturn(new Tool());

        mockMvc.perform(get("/app/tools/form/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("app.tools.form"));
    }

    @Test
    public void testSubmitAction() throws Exception {

        mockMvc.perform(post("/app/tools/save")
                .param("companyId", "1234_id")
                .param("toolType", "Laveler")
                .param("model", "Leica TS15"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/app/tools/available"));
    }

    @Test
    public void testMakeUnusableAction() throws Exception {

        mockMvc.perform(post("/app/tools/make-unusable")
                .param("id", "1")
                .param("unusableReason", "This tool needs calibration"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/app/tools/unusable"));
    }

    @Test
    public void testMakeUsableAction() throws Exception {

        mockMvc.perform(get("/app/tools/make-usable/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/app/tools/unusable"));
    }

    @Test
    public void testUnusableInfoAction() throws Exception {

        mockMvc.perform(get("/app/tools/unusable/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("app.tools.unusable.info"));
    }

}
