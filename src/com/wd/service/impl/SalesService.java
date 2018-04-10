package com.wd.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wd.dao.ISalesDAO;
import com.wd.dao.IShowBarDAO;
import com.wd.models.TbBar;
import com.wd.models.TbGoods;
import com.wd.models.TbSales;
import com.wd.service.ISalesService;
import com.wd.utils.ExportSalesExcel;
import com.wd.utils.ReadUpdateSalesExcel1;
@SuppressWarnings("rawtypes")
public class SalesService implements ISalesService{

	private ISalesDAO dao;
	private IShowBarDAO barDao;
	private ExportSalesExcel exportSalesExcel;

	public ISalesDAO getDao() {
		return dao;
	}

	public void setDao(ISalesDAO dao) {
		this.dao = dao;
	}

	@Override
	public List findSales(String startTime, String endTime, String barNo,
			String specialId,int nowPage,String barclass) {
		// TODO Auto-generated method stub
		return dao.findSales(startTime, endTime, barNo, specialId,nowPage,barclass);
	}

	@Override
	public int updateSales(File file) {
		ReadUpdateSalesExcel1 readWriteExcel = new ReadUpdateSalesExcel1();
		List<TbSales> list = new ArrayList<TbSales>();
		try {
			//  ȡ������������ ��װΪsales����  ����List��
			list = readWriteExcel.updateSales(file);
			//			System.out.println("service:" + list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return dao.updateSales(list);
	}
	//��ȡ���ۼ�¼������ͨ�����ۼ�¼�е�BerNo��ȡ��Bar��ļ�¼��Ȼ����ͨ��Bar�е�goodsNo��ȡGoods��ļ�¼
	@SuppressWarnings("unchecked")
	public ByteArrayInputStream findSalesAndBarAndGoods(String startTime, String endTime, String barNo,
			String specialId,int[] checkCondition){
		ByteArrayInputStream inputSales = null;
		String bar ;//berNo
		//			TbSales tbSales = new TbSales();
		List<TbSales> salesList = new ArrayList();//����List
		List colList= new ArrayList();//������װColmap��List
		TbBar tbBar = new TbBar();
		TbGoods tbGoods = new TbGoods();


		salesList = dao.findSalesAndBarAndGoods(startTime, endTime, barNo, specialId);
		System.out.println("saleService:" + salesList);
		if(salesList.size() != 0){
			for(TbSales tbSales:salesList){
				Map<String, Object> exportCol = new HashMap();
				bar = tbSales.getBarNo();//��ȡÿһ��barNo��Ȼ��ͨ�����barNo ȥ�ҵ�bar����Ϣ�����һ�ȡ��goodsNo
				try {
					tbBar = (TbBar) barDao.findByBarNo(bar).get(0);
					System.out.println("tbBar" + tbBar);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("û�к����۱��Ӧ�ļ�¼��");
					break;
				}
				tbGoods = tbBar.getTbGoods();
				for(int condition: checkCondition){
					switch(condition){
					case 0:
						exportCol.put("barNo", bar);
						break;
					case 1:
						exportCol.put("data", tbSales.getDate());
						break;
					case 2:
						exportCol.put("salesStock", tbSales.getSalesStock());
						break;
					case 3:
						exportCol.put("special", tbSales.getSpecial());
						break;
					case 4:
						exportCol.put("znoe", tbSales.getZone());
						break;
					case 5:
						exportCol.put("goodsNo", tbGoods.getGoodsNo());
						break;
					case 6:
						exportCol.put("barName", tbBar.getBarName());
						break;
					case 7:
						exportCol.put("owner", tbGoods.getGoodsOwner());
						break;
					case 8:
						exportCol.put("class_", tbGoods.getClass_());
						break;
					default:
						break;
					}
				}
				colList.add(exportCol);
			}
			//�����Ѿ���ȡ���û����赼����������
			//����͵���ExportSalesExcel ��������һ��Excel��Ȼ����Ӧ��ҳ���ṩ����
			try {
				inputSales= exportSalesExcel.createExcel(colList, checkCondition);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("����Excelʱ���ִ���");
				e.printStackTrace();
			}
		}else{
			System.out.println("û���ҵ�");
			return null;
		}
		return inputSales;
	}

	public IShowBarDAO getBarDao() {
		return barDao;
	}

	public void setBarDao(IShowBarDAO barDao) {
		this.barDao = barDao;
	}

	public ExportSalesExcel getExportSalesExcel() {
		return exportSalesExcel;
	}

	public void setExportSalesExcel(ExportSalesExcel exportSalesExcel) {
		this.exportSalesExcel = exportSalesExcel;
	}

	// ��ҳ��
	@Override
	public String getSalesCount(String startTime, String endTime, String barNo,String specialId,int nowPage){
		// TODO Auto-generated method stub
		long i=  dao.getSalesCount(startTime, endTime, barNo, specialId, nowPage);
		long j = i%10;
		long k = i/10;
		if(j==0) {
			
			return new DecimalFormat("#").format(k);
		}else {
			return new DecimalFormat("#").format(k+1);
		}
	}

}
