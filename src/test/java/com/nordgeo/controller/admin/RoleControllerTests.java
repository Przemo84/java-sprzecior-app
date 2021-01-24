package com.nordgeo.controller.admin;


import com.nordgeo.entity.Role;
import com.nordgeo.entity.User;
import com.nordgeo.service.Role.RoleService;
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
public class RoleControllerTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService roleService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testIndexAction() throws Exception {

        mockMvc.perform(get("/admin/roles"))
                .andExpect(status().isOk())
                .andExpect(view().name("roles.index"));
    }

    @Test
    public void testFormAction() throws Exception {

        mockMvc.perform(get("/admin/roles/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("role.form"));
    }

    @Test
    public void testEditFormAction() throws Exception {

        when(roleService.findById(anyInt())).thenReturn(new Role());

        mockMvc.perform(get("/admin/roles/form/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("role.form"));
    }

    @Test
    public void testSubmitAction() throws Exception {

        mockMvc.perform(post("/admin/roles/save")
                .param("name", "newRole"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/roles"));
    }

    @Test
    public void testDeleteAction() throws Exception {

        mockMvc.perform(get("/admin/roles/delete/3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/roles"));
    }


}
