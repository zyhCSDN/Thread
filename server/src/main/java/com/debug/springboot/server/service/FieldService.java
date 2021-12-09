package com.debug.springboot.server.service;/**
 * Created by Administrator on 2019/10/13.
 */

import com.debug.springboot.model.entity.primary.CompareLog;
import com.debug.springboot.model.entity.primary.Item;
import com.debug.springboot.model.mapper.primary.CompareLogMapper;
import com.debug.springboot.model.mapper.primary.ItemMapper;
import com.debug.springboot.server.dto.ItemDto;
import com.debug.springboot.server.enums.ItemCompareEnum;
import com.github.dadiyang.equator.Equator;
import com.github.dadiyang.equator.FieldInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/10/13 22:47
 **/
@Service
public class FieldService {

    private static final Logger log= LoggerFactory.getLogger(FieldService.class);

    private static final SimpleDateFormat FORMAT=new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private Equator equator;

    public List<FieldInfo> compare(ItemDto dto) throws Exception{
        log.info("---新对象内容：{}",dto);

        Item entity=itemMapper.selectByPrimaryKey(dto.getId());
        ItemDto old=new ItemDto();
        BeanUtils.copyProperties(entity,old);
        old.setPurchaseTime(FORMAT.format(entity.getPurchaseTime()));
        log.info("---旧对象内容：{}",old);

        //执行更新
        BeanUtils.copyProperties(dto,entity);
        entity.setUpdateTime(new Date());
        entity.setPurchaseTime(FORMAT.parse(dto.getPurchaseTime()));
        itemMapper.updateByPrimaryKey(entity);

        //执行对象比较
        List<FieldInfo> infos=equator.getDiffFields(old,dto);
        log.info("---比较结果：{}",infos);

        //记录比较结果
        this.logCompareResult(infos);
        return infos;
    }

    @Autowired
    private CompareLogMapper logMapper;

    private void logCompareResult(List<FieldInfo> infos) throws Exception{
        if (infos!=null && !infos.isEmpty()){
            infos.stream().forEach(info -> {
                CompareLog compareLog=new CompareLog();
                String code=info.getFieldName();
                compareLog.setCode(code);
                compareLog.setName(String.valueOf(ItemCompareEnum.getFieldMap().get(code)));
                compareLog.setOldVal(String.valueOf(info.getFirstVal()));
                compareLog.setNewVal(String.valueOf(info.getSecondVal()));
                compareLog.setCreateTime(new Date());
                logMapper.insert(compareLog);
            });
        }
    }

}





















