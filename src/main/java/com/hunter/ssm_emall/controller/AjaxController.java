package com.hunter.ssm_emall.controller;

import com.hunter.ssm_emall.bean.Product;
import com.hunter.ssm_emall.bean.User;
import com.hunter.ssm_emall.service.ProductService;
import com.hunter.ssm_emall.service.TrxService;
import com.hunter.ssm_emall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api")
public class AjaxController {

    @Resource
    private UserService userService;
    @Resource
    private TrxService trxService;
    @Resource
    private ProductService productService;

    //登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Model login(HttpSession session,
                       @RequestParam("userName") String username,
                       @RequestParam("password") String password,
                       Model model) {
        User user = userService.getUser(username, password);
        //验证用户名和密码正确
        if (user != null) {//登录成功
            model.addAttribute("code", 200);
            model.addAttribute("message", "登录成功");
            model.addAttribute("result", true);
            session.setAttribute("user", user);
        } else {
            model.addAttribute("code", 400);//失败的code自定义，前端对此无限制
            model.addAttribute("message", "用户名或密码错误");
            model.addAttribute("result", false);
        }
        return model;
    }

    //上传图片
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Model upload(HttpServletRequest request,
                        @RequestPart("file") MultipartFile img,
                        Model model) throws IOException {
        if (!img.isEmpty()) {
            //为上传的文件重命名
            String fileName = img.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf('.'));
            fileName = new Date().getTime() + suffix;
            //获取webapp部署的目录，函数的参数 是根目录下的子目录路径
            String localPath = request.getSession().getServletContext().getRealPath("image/")
                    + fileName;
            File image = new File(localPath);
            if (!image.getParentFile().exists()) {
                image.getParentFile().mkdirs();
            }
            img.transferTo(image);
            //声明图片的相对路径
            String imgUrl = "image/" + fileName;
            model.addAttribute("code", 200);
            model.addAttribute("message", "图片上传成功");
            model.addAttribute("result", imgUrl);
        } else {
            model.addAttribute("code", 400);
            model.addAttribute("message", "图片上传失败");
        }
        return model;
    }

    //删除
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Model deleteProduct(@SessionAttribute(name = "user") User user,
                               @RequestParam("id") Integer id,
                               Model model) {
        if (user.getUserType() == 0 || !productService.deleteProdct(id)) {
            model.addAttribute("code", 400);
            model.addAttribute("message", "删除失败");
            model.addAttribute("result", false);
        } else {
            model.addAttribute("code", 200);
            model.addAttribute("message", "删除成功");
            model.addAttribute("result", true);
        }
        return model;
    }


    //购买
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public Model buy(@SessionAttribute(name = "user") User user,
                     @RequestBody List<Product> productList,
                     Model model) {
        //如果购买成功
        if (trxService.buy(user, productList, new Date().getTime())) {
            model.addAttribute("code", 200);
            model.addAttribute("message", "购买成功");
            model.addAttribute("result", true);
        } else {
            model.addAttribute("code", 400);
            model.addAttribute("message", "购买失败");
            model.addAttribute("result", false);
        }

        return model;
    }
}