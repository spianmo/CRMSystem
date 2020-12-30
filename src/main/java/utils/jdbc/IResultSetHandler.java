package utils.jdbc;

import java.sql.ResultSet;


public interface IResultSetHandler<T> {
    T handle(ResultSet rs);
}

