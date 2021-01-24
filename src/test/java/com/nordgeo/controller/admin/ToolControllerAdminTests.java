package com.nordgeo.controller.admin;


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
public class ToolControllerAdminTests {

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
    public void testIndexAction() throws Exception {

        mockMvc.perform(get("/admin/tools"))
                .andExpect(status().isOk())
                .andExpect(view().name("tools.index"));
    }

    @Test
    public void testIndexUnusableAction() throws Exception {

        mockMvc.perform(get("/admin/tools/unusable"))
                .andExpect(status().isOk())
                .andExpect(view().name("tools.index.unusable"));
    }

    @Test
    public void testFormAction() throws Exception {

        mockMvc.perform(get("/admin/tools/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("tools.form"));
    }

    @Test
    public void testEditFormAction() throws Exception {

        when(toolService.findById(anyInt())).thenReturn(new Tool());

        mockMvc.perform(get("/admin/tools/form/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("tools.form"));
    }

    @Test
    public void testSubmitAction() throws Exception {

        mockMvc.perform(post("/admin/tools/save")
                .param("companyId", "1234_id")
                .param("toolType", "Laveler")
                .param("model", "Leica TS15"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/tools"));
    }

    @Test
    public void testDeleteAction() throws Exception {

        mockMvc.perform(get("/admin/tools/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/tools"));
    }

    @Test
    public void testMakeUnusableAction() throws Exception {

        mockMvc.perform(post("/admin/tools/make-unusable")
                .param("id", "1")
                .param("unusableReason", "This tool needs calibration"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/tools/unusable"));
    }

    @Test
    public void testMakeUsableAction() throws Exception {

        mockMvc.perform(get("/admin/tools/make-usable/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/tools/unusable"));
    }

    @Test
    public void testUnusableInfoAction() throws Exception {

        mockMvc.perform(get("/admin/tools/unusable/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("tools.unusable.info"));
    }




}
