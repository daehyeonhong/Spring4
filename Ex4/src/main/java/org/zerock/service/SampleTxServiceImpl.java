package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mapper.Sample01Mapper;
import org.zerock.mapper.Sample02Mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SampleTxServiceImpl implements SampleTxService {

	@Setter(onMethod_ = @Autowired)
	private Sample01Mapper sample01Mapper;
	@Setter(onMethod_ = @Autowired)
	private Sample02Mapper sample02Mapper;

	@Transactional
	@Override
	public void addData(String value) {
		log.info("mapper1::::::::::");
		sample01Mapper.insertCol1(value);
		log.info("mapper2::::::::::");
		sample02Mapper.insertCol1(value);
		log.info("end");
	}

}