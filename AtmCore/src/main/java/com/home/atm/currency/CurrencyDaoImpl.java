package com.home.atm.currency;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service("currencyDaoImpl")
public class CurrencyDaoImpl implements CurrencyDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Resource
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int addCurrency(String currencyName) {
        String query = "insert into currency (currency_name) value(:p_currency_name)";
        SqlParameterSource namedParameters = new MapSqlParameterSource("p_currency_name", currencyName);
        int column = namedParameterJdbcTemplate.update(query, namedParameters);
        if (column == 0) {
            System.out.println("No column was changed!");
            throw new IllegalStateException("No column was changed!");
        }
        return column;
    }

    @Override
    public void deleteCurrency(int currencyId) {
        String query = "delete from currency where id = :p_currency_id";
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        namedParameter.addValue("p_currency_id", currencyId);
        namedParameterJdbcTemplate.update(query, namedParameter);
    }

    @Override
    public List<Currency> getAllCurrency() {
        String query = "select * from currency";
        List<Currency> currencyList = namedParameterJdbcTemplate.query(query, new RowMapper<Currency>() {
            @Override
            public Currency mapRow(ResultSet resultSet, int i) throws SQLException {
                Currency currency = new Currency();
                currency.setName(resultSet.getString(2));
                currency.setId(resultSet.getInt(1));
                return currency;
            }
        });
        return currencyList;
    }
}
