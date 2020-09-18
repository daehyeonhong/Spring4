package org.zerock.mapper;

import org.apache.ibatis.annotations.Insert;

public interface Sample01Mapper {

	@Insert("INSERT INTO table_sample1(col1)VALUES(#{data})")
	public int insertCol1(String data);

}