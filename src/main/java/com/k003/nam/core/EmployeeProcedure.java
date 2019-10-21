package com.k003.nam.core;

import com.k003.nam.object.Employee;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.sql.Types;

public class EmployeeProcedure extends StoredProcedure {
    public EmployeeProcedure(DataSource dataSource, String procedureName){
        super(dataSource,procedureName);

//        declareParameter(new SqlParameter("ID"), Types.INTEGER);
//        declareParameter(new SqlInOutParameter("FIRST_NAME"),Types.VARCHAR);
    }
}
