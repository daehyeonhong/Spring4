package org.zerock.mapper;

import org.apache.ibatis.annotations.Insert;

public interface Sample02Mapper {

	@Insert("INSERT INTO table_sample2(col1)VALUES(#{data})")
	public int insertCol1(String data);

}