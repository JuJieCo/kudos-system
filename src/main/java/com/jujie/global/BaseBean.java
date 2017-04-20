package com.jujie.global;

import java.sql.ResultSet;

import org.springframework.jdbc.core.RowMapper;

public abstract class BaseBean implements RowMapper{
	
	public abstract Object mapRow(ResultSet rs, int rownum) throws java.sql.SQLException ;
	
}

