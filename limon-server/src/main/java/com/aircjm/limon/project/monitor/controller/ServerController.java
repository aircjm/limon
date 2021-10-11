package com.aircjm.limon.project.monitor.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aircjm.limon.framework.web.controller.BaseController;
import com.aircjm.limon.framework.web.domain.AjaxResult;
import com.aircjm.limon.framework.web.domain.Server;

/**
 * 服务器监控
 * 
 * @author aircjm
 */
@RestController
@RequestMapping("/monitor/server")
public class ServerController extends BaseController
{
    @PreAuthorize("@ss.hasPermi('monitor:server:list')")
    @GetMapping()
    public AjaxResult getInfo() throws Exception
    {
        Server server = new Server();
        server.copyTo();
        return AjaxResult.success(server);
    }
}
