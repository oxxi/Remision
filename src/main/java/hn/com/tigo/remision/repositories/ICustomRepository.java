package hn.com.tigo.remision.repositories;


import java.time.LocalDate;
import java.util.List;

public interface ICustomRepository<T,K> {

    List<T> getAll();
    List<T> getAllByRange(LocalDate ini, LocalDate end);
    T getBy(String param);
    void add(T entity);
//    void update(T entity);
//    void delete(Long id);
    List<T> getBy(K param);


}
