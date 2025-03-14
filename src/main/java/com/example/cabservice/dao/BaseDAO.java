package com.example.cabservice.dao;

import com.example.cabservice.dto.request.DriverRequestDto;
import com.example.cabservice.exceptions.DatabaseException;
import com.example.cabservice.exceptions.NotFoundException;
import com.example.cabservice.exceptions.ValidationException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO<T> {
    protected final Connection connection;

    public BaseDAO(Connection connection) {
        this.connection = connection;
    }

    protected List<T> executeQuery(String query, ResultSetMapper<T> mapper) throws DatabaseException {
        List<T> results = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                results.add(mapper.map(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error executing query: " + e.getMessage());
        }
        return results;
    }

    protected List<T> executeQueryWithParams(String query, PreparedStatementSetter setter, ResultSetMapper<T> mapper) throws DatabaseException {
        List<T> results = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            setter.set(stmt);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(mapper.map(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error executing query with parameters: " + e.getMessage());
        }
        return results;
    }

    protected void executeUpdateWithParams(String query, PreparedStatementSetter setter, String notFoundMessage) throws DatabaseException {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            setter.set(stmt);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0 && notFoundMessage != null) {
                throw new NotFoundException(notFoundMessage);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error executing update: " + e.getMessage());
        }
    }

    protected boolean executeExistsQuery(String query, PreparedStatementSetter setter) throws DatabaseException {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            setter.set(stmt);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new DatabaseException("Error checking existence: " + e.getMessage());
        }
    }

    public abstract void saveDriver(T entity) throws ValidationException, DatabaseException;

    public abstract void updateDriver(Long id, T entity) throws NotFoundException, ValidationException, DatabaseException;

    @FunctionalInterface
    interface ResultSetMapper<T> {
        T map(ResultSet rs) throws SQLException;
    }

    @FunctionalInterface
    interface PreparedStatementSetter {
        void set(PreparedStatement stmt) throws SQLException;
    }

    // Abstract method for mapping entity
    public abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;
}
