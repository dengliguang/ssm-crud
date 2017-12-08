package com.atguigu.crud.bean;

import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.PageInfo;

/**
 * 该类封装了处理的状态和信息以及需要返回的数据
 * @author Administrator
 *
 */
public class Msg {
	//状态码   100 成功   200 失败
	private int code;
	//返回信息
	private String msg;
	//返回给浏览器的数据
	private Map<String,Object> extend=new HashMap<String,Object>();
	
	public static Msg success(){
		Msg result=new Msg();
		result.setCode(100);
		result.setMsg("处理成功!");
		return result;
	}
	
	public static Msg  failure(){
		Msg result=new Msg();
		result.setCode(200);
		result.setMsg("处理失败!");
		return result;
	}
	
	public Msg add(String key,Object obj){
		this.getExtend().put(key, obj);
		return this;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
	
	
	
}
