package io.renren.ureport.datasource;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bstek.ureport.definition.datasource.BuildinDatasource;

/**
 * Ureport 数据源
 *
 */

@Component
public class UReportDataSource implements BuildinDatasource {
    private static final String NAME = "renren-fast";
    private Logger log = LoggerFactory.getLogger(UReportDataSource.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            log.error("Ureport 数据源 获取连接失败！");
            e.printStackTrace();
        }
        return null;
    }

}
