package com.nordgeo.service.tool;


import com.nordgeo.data.dto.ToolDto;
import com.nordgeo.data.mapper.ToolDtoMapper;
import com.nordgeo.entity.Role;
import com.nordgeo.entity.Tool;
import com.nordgeo.entity.ToolStatus;
import com.nordgeo.entity.User;
import com.nordgeo.repository.RoleRepository;
import com.nordgeo.repository.ToolRepository;
import com.nordgeo.repository.UserRepository;
import com.nordgeo.security.AuthManager;
import com.nordgeo.service.toolStatus.ToolStatusService;
import com.nordgeo.service.user.activity.UserActivitiesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ToolServiceTests {

    @InjectMocks
    private ToolServiceImpl toolService;

    @Mock
    private ToolRepository toolRepository;

    @Mock
    private ToolStatusService toolStatusService;

    @Mock
    private AuthManager authManager;

    @Mock
    private UserActivitiesService userActivitiesService;

    @Mock
    private ToolDtoMapper toolDtoMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    private final PageRequest pageRequest = mock(PageRequest.class);

    private Tool tool;

    @Before
    public void setup() {
        given(authManager.user()).willReturn(new User());

        this.tool = spy(Tool.class);
        this.tool.setId(1);
    }

    @Test
    public void shouldPerformFindById() {
        //given tool
        when(toolRepository.findOne(tool.getId())).thenReturn(tool);

        //when
        toolService.findById(tool.getId());

        //then
        verify(toolRepository, times(1)).findOne(tool.getId());
    }

    @Test
    public void shouldPerformFindAll() {
        //given pageRequest

        //when
        toolService.findAll(pageRequest);

        //then
        verify(toolRepository, times(1)).findToolByUnusableIsFalseOrUnusableNull(pageRequest);
    }

    @Test
    public void shouldPerformSave() throws ParseException {
        //given
        ToolDto toolDto = spy(ToolDto.class);

        toolDto.setCompanyId("001n");
        toolDto.setModel("TS 15 1 R1000");
        toolDto.setToolType("Leveler");
        toolDto.setSerialNo("1111111");
        toolDto.setProductionDate("2015-01-01");
        toolDto.setCalibrationDate("2015-01-01");
        toolDto.setAvailable(true);

        when(toolDtoMapper.map(toolDto)).thenReturn(tool);

        //when
        toolService.save(toolDto);

        //then
        verify(toolRepository, times(1)).save(tool);
    }

    @Test
    public void shouldPerformDelete() {
        //given tool

        User authUser = authManager.user();
        authUser.setRole(new Role("Admin"));

        when(toolRepository.findOne(tool.getId())).thenReturn(tool);
        when(authManager.user()).thenReturn(authUser);

        //when
        toolService.delete(tool.getId());

        //then
        verify(toolRepository, times(1)).delete(tool.getId());
    }

    @Test
    public void shouldPerformAppend() {
        //given tool

        User authUser = authManager.user();
        authUser.setRole(new Role("Admin"));

        when(toolRepository.findOne(tool.getId())).thenReturn(tool);
        when(authManager.user()).thenReturn(authUser);

        //when
        toolService.append(tool.getId());

        //then
        verify(toolRepository, times(1)).save(tool);
        verify(toolRepository, times(1)).findOne(tool.getId());
    }

    @Test
    public void shouldPerformFindAllAvailable() {
        //given pageRequest

        //when
        toolService.findAllAvailable(pageRequest);

        //then
        verify(toolRepository, times(1)).findToolsByAvailableIsTrue(pageRequest);
    }

    @Test
    public void shouldPerformFindAllUnavailable() {
        //given pageRequest

        //when
        toolService.findAllUnavailable(pageRequest);

        //then
        verify(toolRepository, times(1)).findToolsByAvailableIsFalseAndUnusableIsFalse(pageRequest);
    }

    @Test
    public void shouldPerformFindAllUserTools() {
        //given pageRequest

        //when
        toolService.findAllUserTools(pageRequest);

        //then
        verify(toolRepository, times(1)).findToolsByUser(authManager.user(), pageRequest);
    }

    @Test
    public void shouldPerformReturnTool() {
        //given tool

        ToolStatus toolStatus = new ToolStatus();
        toolStatus.setTool(tool);
        toolStatus.setDescription("Needs repair");

        when(toolRepository.findOne(tool.getId())).thenReturn(tool);
        doNothing().when(toolStatusService).save(toolStatus, tool);

        //when
        toolService.returnTool(toolStatus);

        //then
        verify(toolRepository, times(1)).save(tool);
    }

    @Test
    public void shouldPerformMakeUnusable() {
        //given tool
        when(toolRepository.findOne(tool.getId())).thenReturn(tool);

        //when
        toolService.makeUnusable(tool.getId(), "This tool needs calibration");

        //then
        verify(toolRepository, times(1)).save(tool);
    }

    @Test
    public void shouldPerformFindAllUnusable() {
        //given pageRequest

        //when
        toolService.findAllUnusable(pageRequest);

        //then
        verify(toolRepository, times(1)).findToolsByUnusableTrue(pageRequest);
    }

    @Test
    public void shouldPerformMakeUsable() {
        //given tool
        when(toolRepository.findOne(tool.getId())).thenReturn(tool);

        //when
        toolService.makeUsable(tool.getId());

        //then
        verify(toolRepository, times(1)).save(tool);
    }

}
