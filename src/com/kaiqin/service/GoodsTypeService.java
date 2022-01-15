package com.kaiqin.service;

import java.util.List;

import com.kaiqin.domain.GoodsType;

public interface GoodsTypeService {
		boolean save(GoodsType gt);
		List<GoodsType> queryByLevel();
		List<GoodsType> queryAll();
		int deleteType(int tid);
		List<GoodsType> queryNameAndFlag(String name,int flag);
}
