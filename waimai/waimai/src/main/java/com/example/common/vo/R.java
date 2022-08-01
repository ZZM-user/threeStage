package com.example.common.vo;

import cn.hutool.db.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.HashMap;

/**
 * 响应接口类
 */
public class R extends HashMap<String,Object> {
    public static final String CODE="code";
    public static final String MESSAGE="message";
    public static final String DATA="data";

    /**
     * 默认成功，不需要返回值
     * @return
     */
    public static R   success(){
         return success(null);
    }

    /**
     * 成功，需要返回数据
     * @param data
     * @return
     */
    public static R  success(Object data){
         R  r=new R();
         r.put(CODE, CodeEnum.SUCCESS.getCode());
         r.put(MESSAGE,CodeEnum.SUCCESS.getMessage());
         if(data!=null){
             if(data instanceof IPage){
                 //ipage转换为 pagevo
                 PageVO  pageVO=new PageVO();
                 IPage ipage= (IPage) data;

                 pageVO.setTotal(ipage.getTotal());
                 pageVO.setRows(ipage.getRecords());

                 r.put(DATA, pageVO);
             }else {
                 r.put(DATA, data);
             }
         }
         return r;
    }




    /**
     * 失败
     * @return
     */
    public static R  failed(){
        return failed(CodeEnum.Failed);
    }

    /**
     * 失败
     * @param codeEnum
     * @return
     */
    public static R  failed(CodeEnum codeEnum){
        R  r=new R();
        r.put(CODE, codeEnum.getCode());
        r.put(MESSAGE,codeEnum.getMessage());
        return r;
    }

    /**
     * 失败
     * @param message
     * @return
     */
    public static R  failed(String message){
        R  r=new R();
        r.put(CODE, CodeEnum.DATA_VALIDATED.getCode());
        r.put(MESSAGE,message);
        return r;
    }


}
