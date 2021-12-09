package com.debug.springboot.server.service.impl;/**
 * Created by Administrator on 2019/10/27.
 */

import com.debug.springboot.model.entity.primary.Item;
import com.debug.springboot.model.mapper.primary.ItemMapper;
import com.debug.springboot.server.enums.Constant;
import com.debug.springboot.server.request.FileUploadRequest;
import com.debug.springboot.server.service.IFileService;
import com.debug.springboot.server.service.CommonFileService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 上传文件
 * @Author:debug (SteadyJack)
 * @Date: 2019/10/27 11:08
 **/
@Service
public class FileService implements IFileService{

    private static final Logger log= LoggerFactory.getLogger(FileService.class);

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private CommonFileService commonFileService;

    //第一种方法
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String uploadFileV1(MultipartHttpServletRequest request) throws Exception {
        MultipartFile multipartFile=request.getFile("appendix");

        String itemName=request.getParameter("itemName");
        String itemCode=request.getParameter("itemCode");
        String itemTotal=request.getParameter("itemTotal");

        Item item=new Item(itemName,itemCode,Long.valueOf(itemTotal));
        item.setPurchaseTime(DateTime.now().toDate());
        itemMapper.insertSelective(item);

        String url="";
        if (item.getId()>0){
            url=commonFileService.upload(multipartFile,item.getId(), Constant.SysModule.ModuleItem);
        }
        return url;
    }

    //第二种方法
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String uploadFileV2(MultipartFile file, FileUploadRequest request) throws Exception {
        Item item=new Item(request.getItemName(),request.getItemCode(),request.getItemTotal());
        item.setPurchaseTime(DateTime.now().toDate());
        itemMapper.insertSelective(item);

        String url="";
        if (item.getId()>0){
            url=commonFileService.upload2(file,item.getId(), Constant.SysModule.ModuleItem);
        }
        return url;
    }
}



































