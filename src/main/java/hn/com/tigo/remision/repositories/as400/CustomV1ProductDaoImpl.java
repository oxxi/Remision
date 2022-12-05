package hn.com.tigo.remision.repositories.as400;

import hn.com.tigo.remision.entities.as400.TestAs400Entity;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.validation.Valid;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Setter
public class CustomV1ProductDaoImpl implements ICustomV1ProductDao{

    private DataSource _dataSource;




    public CustomV1ProductDaoImpl( @Value("${spring.as400.datasource.driver-class-name}" ) String driver,
                                   @Value("${spring.as400.datasource.url}") String url,
                                   @Value("${spring.as400.datasource.username}") String userName,
                                   @Value("${spring.as400.datasource.password}") String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //dataSource.setDriverClassName("com.ibm.as400.access.AS400JDBCDriver");
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        this._dataSource = dataSource;
    }


    @Override
    public void addProduct(TestAs400Entity testAs400Entity) {

    }

    @Override
    public void deleteProduct(TestAs400Entity testAs400Entity) {

    }

    @Override
    public void editProduct(TestAs400Entity testAs400Entity) {

    }

    @Override
    public List<TestAs400Entity> listProduct() {
        String sql = "SELECT COL1,COL2,COL3 FROM BECADOS.V1TESTLOCK";
        List<TestAs400Entity> list = new ArrayList<>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(this._dataSource);

        List<Map<String,Object>> executedQuery = jdbcTemplate.queryForList(sql);
        for (Map<String,Object> row : executedQuery) {
            TestAs400Entity entity = new TestAs400Entity();
            entity.setColumn1((BigDecimal) row.get("COL1"));
            entity.setColumn2(String.valueOf(row.get("COL2")));
            entity.setColumn3(String.valueOf(row.get("COL3")));
            list.add(entity);
        }
        return list;
    }

    @Override
    public TestAs400Entity getBy() {
        return null;
    }
}
