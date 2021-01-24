package com.nordgeo.service.toolStatus;


import com.nordgeo.entity.Role;
import com.nordgeo.entity.Tool;
import com.nordgeo.entity.ToolStatus;
import com.nordgeo.entity.User;
import com.nordgeo.repository.ToolStatusRepository;
import com.nordgeo.security.AuthManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ToolStatusServiceTests {

    @InjectMocks
    private ToolStatusServiceImpl toolStatusService;

    @Mock
    private ToolStatusRepository toolStatusRepository;

    @Mock
    private AuthManager authManager;

    private final PageRequest pageRequest = mock(PageRequest.class);

    private final Tool tool = spy(Tool.class);

    private final ToolStatus toolStatus = spy(ToolStatus.class);

    @Before
    public void setup() {
        given(authManager.user()).willReturn(new User());

        this.tool.setId(1);
    }

    @Test
    public void shouldPerformFindAllToolStatuses() {
        //given pageRequest, tool

        //when
        toolStatusService.findAllToolStatuses(pageRequest, tool.getId());

        //then
        verify(toolStatusRepository, times(1)).findAllToolStatuses(pageRequest, tool.getId());
    }

    @Test
    public void shouldPerformSave() {
        //given tool

        //when
        toolStatusService.save(toolStatus, tool);

        //then
        verify(toolStatusRepository, times(1)).save(toolStatus);
    }

    @Test
    public void shouldPerformGetToolAverageOfRatings() {
        //given tool

        User authUser = authManager.user();
        authUser.setRole(new Role("Admin"));

        when(toolStatusRepository.findAverageOfRatings(tool.getId())).thenReturn(4.67);
        when(authManager.user()).thenReturn(authUser);
        //when
        toolStatusService.getToolAverageOfRatings(tool.getId());

        //then
        verify(toolStatusRepository, times(1)).findAverageOfRatings(tool.getId());
    }


}
