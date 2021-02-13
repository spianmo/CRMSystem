package utils.jdbc;

import java.sql.ResultSet;


public interface IResultSetHandler<T> {
    public T handle(ResultSet rs);
}

