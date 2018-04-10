package com.wd.utils;

import java.util.ArrayList;
import java.util.List;

import com.wd.models.TbGoods;

public class GoodsUtils {
	/**
	 * ����������ȡ����һ������
	 */
	public static List<TbGoods> getSplit(List<TbGoods> list){
		String class_ ;
		String[] c;
		List<TbGoods> goodsLsit = new ArrayList();
		for(TbGoods goods : list){
			class_ = goods.getClass_();
			if(class_.indexOf(">>") != -1){//�жϸ�class_����û��">>"����
				c = class_.split(">>");//�� ">>" ����������
				goods.setClass_(c[c.length-1]);
			}
			if(class_.indexOf("/") != -1){//�жϸ�class_����û��">>"����
				c = class_.split("/");//�� ">>" ����������
				goods.setClass_(c[c.length-1]);
			}
			goodsLsit.add(goods);
		}
		return goodsLsit;
	}
	public static String getFourClass(String class_){
		String[] fruit = {class_};
		if(class_.indexOf(">>") != -1){//�жϸ�class_����û��">>"����
			fruit = class_.split(">>");//�� ">>" ����������
		}
		if(class_.indexOf("/") != -1){//�жϸ�class_����û��">>"����
			fruit = class_.split("/");//�� ">>" ����������
		}
		return fruit[fruit.length-1];
	}
}
