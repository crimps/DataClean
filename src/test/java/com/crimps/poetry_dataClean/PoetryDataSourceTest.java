package com.crimps.poetry_dataClean;

import com.crimps.poetry_dataClean.PoetryDataSource;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author crimps
 * @version 1.0  17/3/3
 * @modified crimps  17/3/3  <创建>
 */
public class PoetryDataSourceTest {
    private PoetryDataSource poetryDataSource = new PoetryDataSource();
    @Test
    public void getResultBySimpleSql() throws Exception {

    }

    @Test
    public void getMapBySimpleSql() throws Exception {
        String sql = "select * from tb_poetry";
        poetryDataSource.getMapBySimpleSql(sql);
    }

    @Test
    public void saveBySimpleSql() throws Exception {
        poetryDataSource.saveBySimpleSql("INSERT INTO tb_sentence VALUES(1,2,3,4,5,6,'蛤蛤蛤')");
    }

}