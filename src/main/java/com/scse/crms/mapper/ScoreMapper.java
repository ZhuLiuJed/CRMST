package com.scse.crms.mapper;

import java.util.List;

import com.scse.crms.vo.ParaForScore;
import com.scse.crms.vo.ScoreVo;

public interface ScoreMapper {
	public List<ScoreVo> selectScoreWithClassid(ParaForScore para);
}
