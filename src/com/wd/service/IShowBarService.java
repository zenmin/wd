package com.wd.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.wd.models.TbBar;
import com.wd.utils.Page;
import com.wd.utils.Project;

public interface IShowBarService {

	//ͨ�� ���� ��ѯ��Ʒ��Ϣ��ҳ�����ƣ�
	public JSONArray findProject(String Contition, String ContitionValue, String owner, String _class,long arrivePage,String power);
	//ͨ�� ���� ��ѯ��Ʒ��Ϣ����ҳ�����ƣ�
	public JSONArray findProject(String Contition, String ContitionValue, String owner, String _class);
	//ͨ�� ���� ��ѯ��Ʒ��Ϣ����������ҳ�����ƣ�
	public JSONArray findProjectAndExport(String Contition, String ContitionValue,String owner, String _class);
		
	//��ȡ����С��
	public List findOwner();
	//��ȡ������Ŀ
	public List findClass();
	
	//ͨ��BarNoֱ�Ӳ�ѯbar��
	public List findByBarNo(String barNo);
	
	//�޸�
	public void alter(Project p);
	//��ȡ�ܹ�ҳ����Ϣ
	public Page totalPageSize(String Contition,String owner, String name);
	
	//ͨ��BarNoֱ�Ӳ�ѯbar��(�޸�ר��)
	public List ByBarNo(String barNo);
	//ͨ��barNoɾ����
	public int deleteBar(String id);
	
	}
