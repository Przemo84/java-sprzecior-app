package com.nordgeo.data.mapper;

import com.nordgeo.data.dto.ToolDto;
import com.nordgeo.entity.Tool;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class ToolDtoMapper {

    public ToolDto map(Tool tool) throws ParseException {
        ToolDto toolDto = new ToolDto();

        if (tool.getId() != null)
            toolDto.setId(tool.getId());

        toolDto.setAvailable(tool.getAvailable());
        toolDto.setToolType(tool.getToolType());

        if (tool.getCalibrationDate() == null)
            toolDto.setCalibrationDate("");
        else
            toolDto.setCalibrationDate(new SimpleDateFormat("yyyy-MM-dd").format(tool.getCalibrationDate()));

        toolDto.setCompanyId(tool.getCompanyId());
        toolDto.setCreateDate(tool.getCreateDate());
        toolDto.setImage(tool.getImage());
        toolDto.setModel(tool.getModel());

        if (tool.getProductionDate() == null)
            toolDto.setProductionDate("");
        else
            toolDto.setProductionDate(new SimpleDateFormat("yyyy-MM-dd").format(tool.getProductionDate()));

        toolDto.setSerialNo(tool.getSerialNo());
        toolDto.setTakenDate(tool.getTakenDate());
        toolDto.setUnusable(tool.getUnusable());
        toolDto.setUnusableReason(tool.getUnusableReason());
        toolDto.setUser(tool.getUser());
        toolDto.setUnusableDate(tool.getUnusableDate());
        toolDto.setAverageRating(tool.getAverageRating());

        return toolDto;
    }

    public Tool map(ToolDto toolDto) throws ParseException {
        Tool tool = new Tool();

        if (toolDto.getId() != null)
            tool.setId(toolDto.getId());

        tool.setAvailable(toolDto.getAvailable());
        tool.setToolType(toolDto.getToolType());

        if (toolDto.getCalibrationDate() == null)
            tool.setCalibrationDate("");
        else
            tool.setCalibrationDate(new SimpleDateFormat("yyyy-MM-dd").format(toolDto.getCalibrationDate()));

        tool.setCompanyId(toolDto.getCompanyId());
        tool.setCreateDate(toolDto.getCreateDate());
        tool.setImage(toolDto.getImage());
        tool.setModel(toolDto.getModel());

        if (toolDto.getProductionDate() == null)
            tool.setProductionDate("");
        else
            tool.setProductionDate(new SimpleDateFormat("yyyy-MM-dd").format(toolDto.getProductionDate()));

        tool.setSerialNo(toolDto.getSerialNo());
        tool.setTakenDate(toolDto.getTakenDate());
        tool.setUnusable(toolDto.getUnusable());
        tool.setUnusableReason(toolDto.getUnusableReason());
        tool.setUser(toolDto.getUser());
        tool.setUnusableDate(toolDto.getUnusableDate());

        return tool;

    }
}
