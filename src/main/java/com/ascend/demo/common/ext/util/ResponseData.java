package com.ascend.demo.common.ext.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: ResponseData 
 * @Description: 针对系统很多接口返回错误信息都用throw new Exception 而新增的一个返回信息静态工厂类，此类类似此前使用的MessageModel类
 * 				 <br> 默认status为 200   成功的状态，可按自己业务设置此状态码 <br>   flag 是对已有的业务代码加的标识
 * @author wpl
 * @date 2018-12-25 11:05:78
 *
 */
public class ResponseData extends HashMap<String,Object>{
 
	private static final long serialVersionUID = 1L;

	public ResponseData() {
		put("flag", true);
		put("status", 200);
		put("msg", "操作成功");
	}

	/**
	 * 
	 *
	 * @Title:  error 
	 * @Description:  错误的信息,默认值：<br> flag=false  status=500   msg = 操作失败
	 * @return ResponseData     
	 *
	 * @author：wu  
	 * @date：2018-12-26 17:41:62
	 *
	 */
	public static ResponseData error() {
		return error(500, "操作失败");
	}

	/**
	 * 
	 *
	 * @Title:  error 
	 * @Description: 错误的返回信息,默认值：<br> flag =false
	 * @param msg  提示信息
	 * @return ResponseData     
	 *
	 * @author：wu  
	 * @date：2018-12-26 17:43:19
	 *
	 */
	public static ResponseData error(String msg) {
		return error(500, msg);
	}

	/**
	 * 
	 *
	 * @Title:  error 
	 * @Description: 错误的返回信息,默认值：<br> flag=false
	 * @param status 自定义状态码
	 * @param msg  返回的提示信息
	 * @return ResponseData     
	 *
	 * @author：wu  
	 * @date：2018-12-26 17:43:07
	 *
	 */
	public static ResponseData error(int status, String msg) {
		ResponseData r = new ResponseData();
		r.put("flag", false);
		r.put("status", status);
		r.put("msg", msg);
		return r;
	}

	/**
	 * 
	 *
	 * @Title:  success 
	 * @Description: 成功的返回信息, 默认值：<br>flag=true  status=200    msg=操作成功 
	 * @param msg 提示信息
	 * @return ResponseData     
	 *
	 * @author：wu  
	 * @date：2018-12-26 17:46:19
	 *
	 */
	public static ResponseData success(String msg) {
		ResponseData r = new ResponseData();
		r.put("msg", msg);
		return r;
	}

	/**
	 * 
	 *
	 * @Title:  success 
	 * @Description: 成功的返回信息,默认值：<br> flag=true  status=200   msg=操作成功 
	 * @param map 其他返回的信息，  若此map 中的key中包含  flag status msg  则默认的已有的key: flag status msg 的value会被覆盖
	 * @return ResponseData     
	 *
	 * @author：wu  
	 * @date：2018-12-26 17:49:12
	 *
	 */
	public static ResponseData success(Map<String, Object> map) {
		ResponseData r = new ResponseData();
		r.putAll(map);
		return r;
	}

	/**
	 *
	 * @Title:  success 
	 * @Description: 成功的返回信息,默认值：<br>  flag=true  status=200  msg=操作成功 
	 * @return ResponseData     
	 *
	 * @author：wu  
	 * @date：2018-12-26 17:53:16
	 *
	 */
	public static ResponseData success() {
		return new ResponseData();
	}

	/**
	 * 
	 *
	 * @Title:  setFlag 
	 * @Description: 更改  flag状态标识 <br>并且自动更新msg信息: true:操作成功    false：操作失败 <br> 自动更新status: true: 200  false 500 
	 * @param flag   true/false     
	 *
	 * @author：wu  
	 * @date：2018-12-26 18:00:41
	 *
	 */
	public ResponseData setFlag(boolean flag){
		if(flag){
			setMsg("操作成功");
			setStatus(200);
		}else{
			setMsg("操作失败");
			setStatus(500);
		}
		return put("flag", flag);
	}
	
	/**
	 * 
	 *
	 * @Title:  setStatus 
	 * @Description: 更改status 状态码
	 * @param status 新的状态码     
	 *
	 * @author：wu  
	 * @date：2018-12-26 18:01:47
	 *
	 */
	public ResponseData setStatus(int status){
		return put("status",status);
	}
	
	/**
	 * 
	 *
	 * @Title:  setMsg 
	 * @Description: 更改提示信息
	 * @param msg 提示的信息     
	 *
	 * @author：wu  
	 * @date：2018-12-26 18:01:08
	 *
	 */
	public ResponseData setMsg(String msg){
		return put("msg", msg);
	}
	
	/**
	 * 
	 *
	 * @Title:  setProties 
	 * @Description: 更新flag 和status  并且按flag自动将 msg 更新到默认提示信息
	 * @param flag
	 * @param status      
	 *
	 * @author：wu  
	 * @date：2018-12-27 09:17:12
	 *
	 */
	public void setProties(boolean flag,int status) {
		setFlag(flag);
		setStatus(status);
	}
	
	/**
	 * 
	 *
	 * @Title:  setProties 
	 * @Description: 更新status 和 msg 
	 * @param status
	 * @param msg      
	 *
	 * @author：wu  
	 * @date：2018-12-27 09:18:03
	 *
	 */
	public void setProties(int status,String msg) {
		setStatus(status);
		setMsg(msg);
	}
	
	/**
	 *
	 * @Title:  setProties 
	 * @Description: 更新flag 和msg  并且按flag 自动将 status 更新到默认编号
	 * @param flag
	 * @param msg      
	 *
	 * @author：wu  
	 * @date：2018-12-27 09:19:67
	 *
	 */
	public void setProties(boolean flag, String msg) {
		setFlag(flag);
		setMsg(msg);
	}
	
	/**
	 * 
	 *
	 * @Title:  setProties 
	 * @Description: 更新所有属性
	 * @param flag
	 * @param status
	 * @param msg      
	 *
	 * @author：wu  
	 * @date：2018-12-27 09:21:24
	 *
	 */
	public void setProties(boolean flag, int status,String msg) {
		put("flag",flag);
		setStatus(status);
		setMsg(msg);
	}
	
	/**
	 * @Description:  添加其他返回信息 <br>若key为  flag status msg  则已有的key: flag status msg 的value将会被覆盖
	 * @param key:键
	 * @param value: 信息的值
	 */
	@Override
	public ResponseData put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
