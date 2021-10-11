package com.aircjm.limon.project.monitor.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aircjm.limon.utils.poi.ExcelUtil;
import com.aircjm.limon.aspectj.lang.annotation.Log;
import com.aircjm.limon.aspectj.lang.enums.BusinessType;
import com.aircjm.limon.web.controller.BaseController;
import com.aircjm.limon.web.domain.AjaxResult;
import com.aircjm.limon.web.page.TableDataInfo;
import com.aircjm.limon.project.monitor.domain.SysLogininfo;
import com.aircjm.limon.project.monitor.service.ISysLogininforService;

/**
 * 系统访问记录
 *
 * @author aircjm
 */
@RestController
@RequestMapping("/monitor/logininfor")
public class SysLogininforController extends BaseController
{
    @Autowired
    private ISysLogininforService logininforService;

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysLogininfo logininfor)
    {
        startPage();
        List<SysLogininfo> list = logininforService.selectLogininforList(logininfor);
        return getDataTable(list);
    }

    @Log(title = "登陆日志", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('monitor:logininfor:export')")
    @GetMapping("/export")
    public AjaxResult export(SysLogininfo logininfor)
    {
        List<SysLogininfo> list = logininforService.selectLogininforList(logininfor);
        ExcelUtil<SysLogininfo> util = new ExcelUtil<SysLogininfo>(SysLogininfo.class);
        return util.exportExcel(list, "登陆日志");
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
    @Log(title = "登陆日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable Long[] infoIds)
    {
        return toAjax(logininforService.deleteLogininforByIds(infoIds));
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
    @Log(title = "登陆日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public AjaxResult clean()
    {
        logininforService.cleanLogininfor();
        return AjaxResult.success();
    }
}
