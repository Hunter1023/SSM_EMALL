package com.hunter.ssm_emall.controller;

import com.hunter.ssm_emall.bean.Product;
import com.hunter.ssm_emall.bean.User;
import com.hunter.ssm_emall.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {

    @Resource
    private ProductService productService;

    //展示页
    @RequestMapping("/")
    public String index(@RequestParam(name = "type", required = false) String type,
                        @SessionAttribute(name = "user", required = false) User user,
                        Model model) {
        List<Product> productList = productService.getProductList(user, type);

        model.addAttribute("productList", productList);
        return "index";
    }

    //查看页
    @RequestMapping("/show")
    public String show(@RequestParam("id") Integer productId,
                       @SessionAttribute(name = "user", required = false) User user,
                       Model model) {
        Product product = productService.getProduct(user, productId);

        model.addAttribute("product", product);
        return "show";
    }

    //发布页
    @RequestMapping("/public")
    public String publicPage(@SessionAttribute(name = "user") User user) {
        if (user == null || user.getUserType() == 0) {
            return "redirect:/";
        }
        return "public";
    }

    //发布提交
    @RequestMapping(value = "/publicSubmit", method = RequestMethod.POST)
    public String publicSubmit(@Valid Product product,
                               BindingResult result,
                               @SessionAttribute(name = "user") User user,
                               Model model) {
        if (user == null || user.getUserType() == 0) {
            return "redirect:/";
        }
        //如果校验出错 或者 商品的种类已经超出1000种，那么就发布失败
        if (!result.hasErrors() && productService.getCount() <= 1000) {
            productService.publicProduct(product);
            model.addAttribute("product", product);
        }
        return "publicSubmit";
    }

    //编辑页
    @RequestMapping("/edit")
    public String edit(@SessionAttribute(name = "user") User user,
                       @RequestParam("id") Integer productId,
                       Model model) {
        if (user == null || user.getUserType() == 0) {
            return "redirect:/";
        }
        model.addAttribute("product", productService.getProduct(user, productId));

        return "edit";
    }

    //编辑提交
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String  editSubmit(@Valid Product product,
                              BindingResult result,
                              @SessionAttribute(name = "user") User user,
                              Model model){
        if (user == null || user.getUserType() == 0) {
            return "redirect:/";
        }
        //如果校验正确，就更新商品信息
        if (!result.hasErrors() && productService.updateProduct(product)) {
            model.addAttribute("product", product);
        }
        return "editSubmit";
    }

}
