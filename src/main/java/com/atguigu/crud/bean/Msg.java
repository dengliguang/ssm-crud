package com.atguigu.crud.bean;

import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.PageInfo;

/**
 * �����װ�˴����״̬����Ϣ�Լ���Ҫ���ص�����
 * @author Administrator
 *
 */
public class Msg {
	//״̬��   100 �ɹ�   200 ʧ��
	private int code;
	//������Ϣ
	private String msg;
	//���ظ������������
	private Map<String,Object> extend=new HashMap<String,Object>();
	
	public static Msg success(){
		Msg result=new Msg();
		result.setCode(100);
		result.setMsg("����ɹ�!");
		return result;
	}
	
	public static Msg  failure(){
		Msg result=new Msg();
		result.setCode(200);
		result.setMsg("����ʧ��!");
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
