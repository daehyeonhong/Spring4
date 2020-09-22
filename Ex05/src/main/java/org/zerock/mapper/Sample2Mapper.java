package org.zerock.mapper;

import org.apache.ibatis.annotations.Insert;

public interface Sample2Mapper {

	@Insert("INSERT INTO table_sample2(col1)VALUES(#{value})")
	public int insertCol1(String value);

}