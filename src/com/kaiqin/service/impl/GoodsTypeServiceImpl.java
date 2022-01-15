package com.kaiqin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaiqin.dao.GoodsTypeDao;
import com.kaiqin.domain.GoodsType;
import com.kaiqin.service.GoodsTypeService;


@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {
	
	@Autowired
	private GoodsTypeDao goodsTypeDao;
	
	@Override
	public boolean save(GoodsType gt) {
		return goodsTypeDao.save(gt)>0;
	}

	@Override
	public List<GoodsType> queryByLevel() {
		return goodsTypeDao.queryByLevel();
	}

	@Override
	public List<GoodsType> queryAll() {
		return goodsTypeDao.queryAll();
	}

	@Override
	public int deleteType(int tid) {
		return goodsTypeDao.deleteType(tid);
	}

	@Override
	public List<GoodsType> queryNameAndFlag(String name, int flag) {
		return goodsTypeDao.queryNameAndFlag(name, flag);
	}

}
