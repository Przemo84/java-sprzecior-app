package com.nordgeo.controller.admin;


import com.nordgeo.service.history.ActionHistoryService;
import com.nordgeo.validators.DatesSelectValidator;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ActionHistoryControllerTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActionHistoryService actionHistoryService;

    @MockBean
    private DatesSelectValidator datesSelectValidator;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testIndexAction() throws Exception {

        mockMvc.perform(get("/admin/history"))
                .andExpect(status().isOk())
                .andExpect(view().name("history.index"));
    }


    @Test
    public void testExportAction() throws Exception {

        mockMvc.perform(get("/admin/history/export"))
                .andExpect(status().is3xxRedirection());
    }


}
