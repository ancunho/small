package com.small.controller.backend;


import com.small.common.Const;
import com.small.common.ResponseCode;
import com.small.common.ServerResponse;
import com.small.service.FileService;
import com.small.service.ProductService;
import com.small.service.UserService;
import com.small.util.DateUtil;
import com.small.vo.PRODUCT;
import com.small.vo.USER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web. bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/managed/product/")
public class ProductManageController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private FileService fileService;

    private static final Logger logger = LoggerFactory.getLogger(ProductManageController.class);

    @RequestMapping(value = "save.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse productSave(HttpSession session, PRODUCT product) {
        USER user = (USER) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");
        }

        logger.info(">>>参数product : " + product.toString());

        if (userService.checkAdminRole(user).isSuccess()) {
            return productService.saveOrUpdateProduct(product);
        }

        return ServerResponse.createByErrorMessage("无权限操作");
    }

    @RequestMapping(value = "set_sales_status.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setSalesStatus(HttpSession session, Integer productId, Integer status) {
        USER user = (USER) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");
        }

        if (userService.checkAdminRole(user).isSuccess()) {
            return productService.setSalesStatus(productId, status);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping(value = "detail.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getProductDetail(HttpSession session, Integer productId) {
        USER user = (USER) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");
        }

        if (userService.checkAdminRole(user).isSuccess()) {
            return productService.manageProductDetail(productId);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping(value = "list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getProductList(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        USER user = (USER) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");
        }

        if (userService.checkAdminRole(user).isSuccess()) {
            return productService.getProductList(pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }


    @RequestMapping(value = "search.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse searchProduct(HttpSession session, String productName, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        USER user = (USER) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");
        }

        if (userService.checkAdminRole(user).isSuccess()) {
            return productService.searchProduct(productName, pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping(value = "singleUpload.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse singleUpload(HttpSession session, @RequestParam(value = "singleFileUpload", required = false)MultipartFile file, HttpServletRequest request) {
        USER user = (USER) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");
        }

        if (userService.checkAdminRole(user).isSuccess()) {
            String path = request.getServletContext().getRealPath(DateUtil.getDays());
            logger.info("文件的上传路径是：{}", path);
            if (file.getSize() > 0) {
                String targetFileName = fileService.upload(file, path);
                return ServerResponse.createBySuccess(targetFileName);
            } else {
                return null;
            }

        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping(value = "multipleUpload.do", method = RequestMethod.POST)
    @ResponseBody
    public Map multipleUpload(HttpSession session, @RequestParam(value = "multiFileUpload", required = false)MultipartFile[] multipartFiles, HttpServletRequest request) {
        Map<Object, Object> resultData = new HashMap<>();
        List<String> fileNameList = new ArrayList<>();

        USER user = (USER) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            resultData.put("errno", 10);
            return resultData;
        }

        if (userService.checkAdminRole(user).isSuccess()) {
            String path = request.getServletContext().getRealPath(DateUtil.getDays());
            logger.info("文件的上传路径是：{}", path);
            String targetFileName = "";
            for (MultipartFile fileItem : multipartFiles) {
                if (fileItem.getSize() > 0) {
                    targetFileName = fileService.upload(fileItem, path);
                    fileNameList.add(targetFileName);
                }
            }
            resultData.put("errno", 0);
            resultData.put("data", fileNameList);
        } else {
            resultData.put("errno", 1);
            resultData.put("data", "无权限操作");
        }
        return resultData;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public FileService getFileService() {
        return fileService;
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
