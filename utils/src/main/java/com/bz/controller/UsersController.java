package com.bz.controller;

import com.bz.pojo.Commons;
import com.bz.pojo.EAResult;
import com.bz.pojo.TBank;
import com.bz.service.UsersService;
import com.bz.util.ExcelUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class UsersController {

    @Resource
    private UsersService usersService;
        @RequestMapping("{url}")
        public String Usl(@PathVariable  String url){
            return url;
        }

    /**
     *
     * @param request
     * @param page
     * @param rows
     * @return 查询个人信息
     */
        @ResponseBody
        @RequestMapping("getMe")
        public EAResult showUser(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "5") int rows){
                return usersService.show(request,page, rows);
        }

    /**
     * 加载2我的卡
     * @return
     */
    @ResponseBody
    @RequestMapping("user/getMyCart")
    public List<Map<String,Object>> getMyCart(HttpServletRequest request){
        return usersService.getMyCart(request);
    }

    /**
     * 导入的到处
     */
    @RequestMapping("user/export")
     public void export(HttpServletRequest req , HttpServletResponse res) throws IOException {
        List<TBank> datas = usersService.showAll();
        String path = "D:\\deaID\\account\\src\\main\\webapp\\money.xlsx";
//        String path = "D:\\deaID\\account\\src\\main\\webapp\\sources.xls";
        ExcelUtil.print(datas,req,res,path);
    }
  /**
     * 导入当前页
     */

    @RequestMapping("user/export/page")
     public void exportByPage(Commons commons, HttpServletRequest req , HttpServletResponse res) throws IOException {
        System.out.println(commons);
        EAResult show = usersService.show(req, commons.getPage(), commons.getRows());
        String path = "D:\\deaID\\account\\src\\main\\webapp\\money.xlsx";
//        String path = "D:\\deaID\\account\\src\\main\\webapp\\sources.xls";
        ExcelUtil.print((List<TBank>) show.getRows(),req,res,path);
    }

}
