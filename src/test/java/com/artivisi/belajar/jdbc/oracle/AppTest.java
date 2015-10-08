/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.artivisi.belajar.jdbc.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author adi
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class AppTest {
    
    @Autowired private DataSource dataSource;
    
    @Test
    public void testInsert(){
        String query = "INSERT INTO BARANG (KODE, NAMA) VALUES(?, ?)";
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        jdbc.update(query, "KD002", "SAPU LIDI");
    }
    
    @Test
    public void testSelect(){
        String strQuery = "SELECT * FROM BARANG";
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        List<Map<String, String>> result = jdbc.query(strQuery, new DataMapper());
        for (Map<String, String> map : result) {
            System.out.println("Kode:"+map.get("KODE")+" Nama:"+map.get("NAMA"));
        }
    }
    
    public static final class DataMapper implements RowMapper<Map<String, String>> {

        public Map<String, String> mapRow(ResultSet rs, int i) throws SQLException {
            Map<String, String> rec = new HashMap<String, String>();
            rec.put("KODE", rs.getString("KODE"));
            rec.put("NAMA", rs.getString("NAMA"));
            return rec;
        }
        
    }
    
}
