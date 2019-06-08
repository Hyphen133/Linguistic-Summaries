package app.loading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TennisMatchDatabaseLoader {

    @Autowired
    EntityManager entityManager;


    public List<Object> getObjectColumn(String tableName, String columnName){
        Query query = entityManager.createNativeQuery("SELECT " + columnName + " FROM " + tableName);
        return query.getResultList();
    }

    public <T> List<T> getColumn(String tableName, String columnName, Class<T> type){
        List<Object> objectColumn = getObjectColumn(tableName, columnName);
        List<T> list = new ArrayList<>();

        for (Object o : objectColumn) {
            list.add(type.cast(o));
        }

        return list;
    }
}
