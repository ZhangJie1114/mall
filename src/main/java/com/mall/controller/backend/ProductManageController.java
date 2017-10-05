package com.mall.controller.backend;

import com.google.common.collect.Maps;
import com.mall.common.Const;
import com.mall.common.ResponseCode;
import com.mall.common.ServerResponse;
import com.mall.pojo.Product;
import com.mall.pojo.User;
import com.mall.service.IFileService;
import com.mall.service.IProductService;
import com.mall.service.IUserService;
import com.mall.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by root on 9/9/17.
 */
@Controller
@RequestMapping("/manage/product/")
public class ProductManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IFileService iFileService;

    @RequestMapping(value = "save.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse productSave(HttpSession session, Product product){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        //校验是否管理员
        if(iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //添加业务逻辑
            return iProductService.saveOrUpdateProduct(product);
        }else{
            return ServerResponse.createByErrorMessage("登录的用户没有权限，需要管理员权限");
        }
    }

    @RequestMapping(value = "set_sale_status.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse setSaleStatus(HttpSession session, Integer productId, Integer status){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        //校验是否管理员
        if(iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //添加业务逻辑
            return iProductService.setSaleStatus(productId,status);
        }else{
            return ServerResponse.createByErrorMessage("登录的用户没有权限，需要管理员权限");
        }
    }

    @RequestMapping(value = "detail.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse manageProductDetail(HttpSession session, Integer productId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        //校验是否管理员
        if(iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //添加业务逻辑
            return iProductService.manageProductDetail(productId);
        }else{
            return ServerResponse.createByErrorMessage("登录的用户没有权限，需要管理员权限");
        }
    }

    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse manageProductList(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        //校验是否管理员
        if(iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //添加业务逻辑
            return iProductService.manageProductList(pageNum,pageSize);
        }else{
            return ServerResponse.createByErrorMessage("登录的用户没有权限，需要管理员权限");
        }
    }

    @RequestMapping(value = "search.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse manageProductSearch(HttpSession session, String productName, Integer productId, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        //校验是否管理员
        if(iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //添加业务逻辑
            return iProductService.manageProductSearch(productId, productName, pageNum, pageSize);
        }else{
            return ServerResponse.createByErrorMessage("登录的用户没有权限，需要管理员权限");
        }
    }

    @RequestMapping(value = "upload.do")
    @ResponseBody
    public ServerResponse manageProductUpload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        //校验是否管理员
        if(iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //添加业务逻辑
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = iFileService.upload(file, path);
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;

            Map fileMap = Maps.newHashMap();
            fileMap.put("uri", targetFileName);
            fileMap.put("url", url);

            return ServerResponse.createBySuccess(fileMap);
        }else{
            return ServerResponse.createByErrorMessage("登录的用户没有权限，需要管理员权限");
        }
    }

    @RequestMapping(value = "richtext_img_upload.do")
    @ResponseBody
    public Map manageProductRichtextImgUpload(HttpSession session, @RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        Map resultMap = Maps.newHashMap();
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        //富文本中对于返回值有要求，这里使用simditor，因此按simditor的标准返回
        //    {
        //       "success": true/false,
        //       "msg": "error message", # optional
        //       "file_path": "[real file path]"
        //     }
        if(user == null){
            resultMap.put("success", false);
            resultMap.put("mgs", "用户未登录，请登录管理员");
            return resultMap;
        }
        //校验是否管理员
        if(iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //添加业务逻辑
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = iFileService.upload(file, path);
            if(StringUtils.isBlank(targetFileName)){
                resultMap.put("success", false);
                resultMap.put("mgs", "上传失败");
                return resultMap;
            }
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
            resultMap.put("success", true);
            resultMap.put("mgs", "上传成功");
            resultMap.put("file_url", url);
            response.addHeader("Access-Control-Allow-Headers", "X-File-Name");
            return resultMap;
        }else{
            resultMap.put("success", false);
            resultMap.put("mgs", "登录的用户没有权限，需要管理员权限");
            return resultMap;
        }
    }
}
