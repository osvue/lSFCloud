package com.bz.controller;

import com.bz.pojo.EAResult;
import com.bz.pojo.TBank;
import com.bz.service.TBankService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class TBankController {
    @Resource
    private TBankService tBankServiceImpl;
    @ResponseBody
    @RequestMapping(value = "tbank" ,method = RequestMethod.GET)
    public String getNextNum(){
        return tBankServiceImpl.getNextNum();
    }
    @ResponseBody
    @RequestMapping(value = "tbank/ins" , method = RequestMethod.POST)
    public String insertBank(TBank tBank,String juc, HttpServletRequest request){

        String juc1 = request.getSession().getAttribute("juc").toString();

        EAResult result = tBankServiceImpl.insertTbank(tBank, request);
        return result.getStatus().toString();
    }
    @RequestMapping("bankAdd")
    public String bankAdd(HttpServletRequest request){
        String uid = UUID.randomUUID().toString();
        request.getSession().setAttribute("juc", uid);
        return "bankAdd";
    }

    @RequestMapping("tbank/inMoney/{ins}/{bnum}")
    public String ios(@PathVariable Integer ins ,@PathVariable String bnum , Model model) {
        model.addAttribute("ins",ins);
        model.addAttribute("bnum",bnum);
        return "ios";
    }

    @RequestMapping("tbank/outMoney/{ins}/{bnum}")
    public String ous(@PathVariable Integer ins ,@PathVariable String bnum , Model model) {
        model.addAttribute("ins",ins);
        model.addAttribute("bnum",bnum);
        return "out";
    }

    /**
     * 修改
     * @param bankNum
     * @param type
     * @param money
     * @return
     */
    @ResponseBody
    @RequestMapping("tbank/iomoney")
    public String inoutMoney(String bankNum,Integer type, double money){
        return  tBankServiceImpl.updateTBank(bankNum, type, money,1);
    }

    /**
     * 取款
     * @param bankNum
     * @param type
     * @param money
     * @return
     */
    @ResponseBody
    @RequestMapping("tbank/outmoney")
    public String outoutMoney(String bankNum,Integer type, double money){
        return  tBankServiceImpl.updateTBank(bankNum, type, money,0);
    }

    /**
     * 转账
     * @param myNum 我的卡号
     * @param money 金额
     * @param bankNum 对方卡号
     * @param userName 对方用户
     * @return
     */
    @ResponseBody
    @RequestMapping("tbank/accountBank")
    public String accountMoney(String myNum ,double money,String bankNum , String userName ){
        return tBankServiceImpl.updAccountMoney(myNum, money, bankNum, userName);
    }
    @ResponseBody
    @RequestMapping("tbank/authortication")
    public  List<Map<String,Object>> showVs(HttpServletRequest req){
        return tBankServiceImpl.showVs(req);
    }
    @ResponseBody
    @RequestMapping("tbank/showcart")
    public  List<Map<String,Object>> showEveryOneCart(){
        return tBankServiceImpl.showEveryOneCart();
    }

    /**
     * 显示未来七天
     * @return
     */
    @ResponseBody
    @RequestMapping("tbank/seven")
    public  List<Map<String,Object>> showSevenNewCart(){
        return tBankServiceImpl.showSevenNewCart();
    }

    /**
     * 批量新增
     */
    @ResponseBody
    @RequestMapping("tbank/batch")
    public Map<String,Object> importDataBatchInsert(MultipartFile file){
        try {
            return tBankServiceImpl.insImportAndInsert(file.getInputStream(), file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
