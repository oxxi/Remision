package hn.com.tigo.remision.repositories.remision;

import hn.com.tigo.remision.entities.remision.LogEntity;
import hn.com.tigo.remision.repositories.ICustomRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class LogRepositoryImpl implements ICustomRepository<LogEntity,Long> {

    private final DataSource dataSource;

    public LogRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;

    }


    @Override
    public List<LogEntity> getAll()  {
        //get last 6 months records, (architect want this to avoid overload)
        JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
        String sql = "SELECT USUARIO,FECHA,MODULO,ACCION,OBJETO,LLAVE,IP FROM LOG WHERE FECHA BETWEEN ADD_MONTHS(SYSDATE,-6) AND SYSDATE  order by FECHA desc ";
        List<Map<String,Object>> executedQuery = jdbcTemplate.queryForList(sql);
        return getLogEntities(executedQuery);

    }



    @Override
    public List<LogEntity> getAllByRange(LocalDate ini, LocalDate end) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
        String sql = "SELECT USUARIO,FECHA,MODULO,ACCION,OBJETO,LLAVE,IP FROM LOG WHERE FECHA BETWEEN ? AND ? order by FECHA desc";
        List<Map<String,Object>> executedQuery = jdbcTemplate.queryForList(sql,ini,end);
        return getLogEntities(executedQuery);

    }

    private List<LogEntity> getLogEntities(List<Map<String, Object>> executedQuery) {

        if(executedQuery.isEmpty()) return null;

        return executedQuery.stream().map(row->{
            LogEntity entity = new LogEntity();
            entity.setAction(String.valueOf(row.get("ACCION")));
            entity.setIp(String.valueOf(row.get("IP")));
            entity.setKey(String.valueOf(row.get("LLAVE")));
            entity.setObject(String.valueOf(row.get("OBJETO")));
            entity.setModule(String.valueOf(row.get("MODULO")));
            entity.setUserName(String.valueOf(row.get("USUARIO")));
            entity.setCreatedAt(Timestamp.valueOf(String.valueOf(row.get("FECHA"))).toLocalDateTime());
            return entity;
        }).collect(Collectors.toList());
    }

    @Override
    public LogEntity getBy(String param) {
        return null;
    }

    @Override
    public void add(LogEntity entity) {
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
            String sql = "INSERT INTO LOG(USUARIO,FECHA,MODULO,ACCION,OBJETO,LLAVE,IP) VALUES(?,SYSDATE,?,?,?,?,?)";
            jdbcTemplate.update(sql,entity.getUserName(),entity.getModule(),entity.getAction(),entity.getObject(),entity.getKey(),entity.getIp());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(LogEntity entity) {

    }

    @Override
    public void delete(Long id) {

    }

}
