package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.annon.AdminAccess;
import com.example.common.domain.R;
import com.example.common.enums.AckCode;
import com.example.common.validtor.AddValidator;
import com.example.common.validtor.EditValidator;
import com.example.common.vo.PageVo;
import com.example.dto.EnterpriseSearchDTO;
import com.example.dto.PhoneValidatorDTO;
import com.example.entity.Enterprise;
import com.example.service.EnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author： 17602
 * @Date： 2022/8/2 11:58
 * @Desc： 商家
 **/
@Api(tags = "商家信息")
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/enterprise")
public class EnterpriseController {
    @Autowired
    private EnterpriseService service;
    
    /**
     * 分页查询
     *
     * @param enterpriseSearchDTO
     *
     * @return
     */
    @AdminAccess
    @ApiOperation("管理员-商家分页查询")
    @GetMapping("/data")
    public R search(EnterpriseSearchDTO enterpriseSearchDTO) {
        IPage<Enterprise> search = service.search(enterpriseSearchDTO);
        PageVo<Enterprise> enterprisePageVo = PageVo.pageVo(search);
        return R.okHasData(enterprisePageVo);
    }
    
    @ApiOperation("商家分页查询")
    @GetMapping("/byself")
    public R search() {
        IPage<Enterprise> search = service.searchByEnterprise();
        PageVo<Enterprise> enterprisePageVo = PageVo.pageVo(search);
        return R.okHasData(enterprisePageVo);
    }
    
    @ApiOperation("检查电话是否存在")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "id为null代表新增", required = false),
            @ApiImplicitParam(name = "phone", value = "联系电话", required = true)
    })
    @GetMapping("/exists")
    public R findEmployeeById(@Valid PhoneValidatorDTO phoneValidatorDTO) {
        // 检查账号是否存在
        int count = service.checkPhoneExists(phoneValidatorDTO.getId(), phoneValidatorDTO.getPhone());
        
        return count == 0 ? R.ok() : R.build(AckCode.VALUE_IS_USED);
    }
    
    @AdminAccess
    @PostMapping("/add")
    public R addEnterprise(@RequestBody @Validated(value = AddValidator.class) Enterprise enterprise) {
        Enterprise hasEnterprise = hasEnterprise(enterprise);
        boolean save = false;
        if (ObjectUtil.isNull(hasEnterprise)) {
            // 取出密码进行加密
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String passEncoded = encoder.encode(enterprise.getLogin_pwd());
            enterprise.setLogin_pwd(passEncoded);
            // 存入加密后的密码
            save = service.save(enterprise);
        }
        return save ? R.ok() : R.build(AckCode.FAIL);
    }
    
    @AdminAccess
    @ApiOperation("通过审核")
    @ApiImplicitParam(name = "id", value = "商家", required = true, example = "1")
    @PostMapping("/approved")
    public R changeStatus(@RequestBody Map<String, Integer> map) {
        Integer id = map.get("id");
        Enterprise enterprise = service.getById(id);
        if (ObjectUtil.isNull(enterprise)) {
            return R.build(AckCode.USER_NOT_FOUND);
        }
        enterprise.setStatus(1);
        boolean count = service.updateById(enterprise);
        if (count) {
            // 发送短信
            log.info(enterprise.getName() + "\t商家通知已发送！");
        }
        return R.ok();
    }
    
    @AdminAccess
    @ApiOperation("查询主键获取商家数据")
    @ApiImplicitParam(name = "id", value = "主键", example = "1")
    @GetMapping("/{id}")
    public R findEnterpriseById(@PathVariable(value = "id") Long id) {
        Enterprise enterprise = service.getById(id);
        if (Objects.isNull(enterprise)) {
            return R.build(AckCode.NOT_FOUND_DATA);
        }
        enterprise.setCreate_time(null);
        enterprise.setUpdate_time(null);
        enterprise.setLogin_pwd(null);
        return R.okHasData(enterprise);
    }
    
    /**
     * 修改信息
     *
     * @param enterprise
     *
     * @return
     */
    @AdminAccess
    @ApiOperation("编辑商家信息")
    @PostMapping("/update")
    public R update(@RequestBody @Validated(value = {EditValidator.class}) Enterprise enterprise) {
        boolean update = false;
        // 查看是否有这个人
        Enterprise hasEnterprise = hasEnterprise(enterprise);
        if (ObjectUtil.isNotNull(hasEnterprise)) {
            if (ObjectUtil.isNotNull(enterprise.getLogin_pwd())) {
                enterprise.setLogin_pwd(hasEnterprise.getLogin_pwd());
            }
            update = service.updateById(enterprise);
        }
        return update ? R.ok() : R.build(AckCode.FAIL);
    }
    
    /**
     * 删除
     *
     * @param ids
     *
     * @return
     */
    @AdminAccess
    @ApiOperation("移除商家信息")
    @PostMapping("/del")
    public R delete(@RequestBody List<Integer> ids) {
        int counter = 0;
        boolean remove;
        for (Integer id : ids) {
            Enterprise byId = service.getById(id);
            if (ObjectUtil.isNotNull(byId)) {
                if (byId.getStatus() == 2) {
                    remove = service.removeById(id);
                } else {
                    byId.setStatus(1);
                    remove = service.updateById(byId);
                }
                counter += remove ? 1 : 0;
            }
        }
        return counter == ids.size() ? R.ok() : R.build(AckCode.FAIL);
    }
    
    
    @AdminAccess
    @ApiOperation("为分类菜单提供商家查询")
    @GetMapping("/enterprises")
    public R searchAllEnterprise(String enterpriseName) {
        LambdaQueryWrapper<Enterprise> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(enterpriseName)) {
            queryWrapper.like(Enterprise::getName, enterpriseName);
        }
        queryWrapper.orderBy(true, true, Enterprise::getStatus);
        queryWrapper.orderBy(true, true, Enterprise::getCreate_time);
        queryWrapper.select(Enterprise::getId, Enterprise::getName, Enterprise::getStatus);
        List<Enterprise> enterpriseList = service.list(queryWrapper);
        return R.okHasData(enterpriseList);
    }
    
    /**
     * excel导出
     *
     * @param enterpriseSearchDTO
     *
     * @return
     */
    @AdminAccess
    @ApiOperation("管理员-excel导出")
    @PostMapping("/export/excel")
    public void export(EnterpriseSearchDTO enterpriseSearchDTO, HttpServletResponse response) throws IOException {
        List<Enterprise> search = service.export(enterpriseSearchDTO);
        String[] headers = {"编号", "商家名称", "商家封面", "联系电话", "状态", "创建人", "创建时间"};
        
        Workbook wb = generateXlsx(headers, search);
        // response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        // response.setCharacterEncoding("utf-8");
        // wb.write(response.getOutputStream());
        
        // 把excel输出到响应流
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        try {
            wb.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(wb);
        }
    }
    
    public static Workbook generateXlsx(String[] header, List<Enterprise> example) throws IOException {
        // 创建工作文档对象
        Workbook wb = new XSSFWorkbook();
        
        String[] status = {"正常", "禁用", "未审核"};
        // 日期格式
        CreationHelper createHelper = wb.getCreationHelper();
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy/m/d h:mm"));
        
        // 创建sheet对象
        Sheet sheet = wb.createSheet("sheet1");
        // 循环写入行数据
        Row row = sheet.createRow(0);
        for (int i = 0; i < header.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(header[i]);
        }
        for (int i = 1; i < example.size(); i++) {
            row = sheet.createRow(i);
            // 循环写入列数据
            Enterprise enterprise = example.get(i);
            row.createCell(0).setCellValue(enterprise.getId());
            row.createCell(1).setCellValue(enterprise.getName());
            row.createCell(2).setCellValue(enterprise.getAlbum());
            row.createCell(3).setCellValue(enterprise.getPhone());
            Integer state = enterprise.getStatus();
            row.createCell(4).setCellValue(status[state]);
            row.createCell(5).setCellValue(enterprise.getCreate_by());
            Cell cell = row.createCell(6);
            cell.setCellValue(enterprise.getCreate_time());
            cell.setCellStyle(cellStyle);
        }
        return wb;
    }
    
    Enterprise hasEnterprise(Enterprise enterprise) {
        LambdaQueryWrapper<Enterprise> wrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Enterprise> eq = wrapper.eq(Enterprise::getPhone, enterprise.getPhone());
        LambdaQueryWrapper<Enterprise> last = eq.last("limit 1");
        return service.getOne(last);
    }
}
