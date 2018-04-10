package com.wd.utils;

import java.util.ArrayList;
import java.util.List;

import com.wd.models.TbGoods;

public class GoodsUtils {
	/**
	 * 本类用来读取最后的一个分类
	 */
	public static List<TbGoods> getSplit(List<TbGoods> list){
		String class_ ;
		String[] c;
		List<TbGoods> goodsLsit = new ArrayList();
		for(TbGoods goods : list){
			class_ = goods.getClass_();
			if(class_.indexOf(">>") != -1){//判断该class_中有没有">>"符号
				c = class_.split(">>");//以 ">>" 符号来隔开
				goods.setClass_(c[c.length-1]);
			}
			if(class_.indexOf("/") != -1){//判断该class_中有没有">>"符号
				c = class_.split("/");//以 ">>" 符号来隔开
				goods.setClass_(c[c.length-1]);
			}
			goodsLsit.add(goods);
		}
		return goodsLsit;
	}
	public static String getFourClass(String class_){
		String[] fruit = {class_};
		if(class_.indexOf(">>") != -1){//判断该class_中有没有">>"符号
			fruit = class_.split(">>");//以 ">>" 符号来隔开
		}
		if(class_.indexOf("/") != -1){//判断该class_中有没有">>"符号
			fruit = class_.split("/");//以 ">>" 符号来隔开
		}
		return fruit[fruit.length-1];
	}
}
