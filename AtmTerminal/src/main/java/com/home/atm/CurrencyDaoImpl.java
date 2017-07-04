package com.home.atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository("currencyDaoImpl")
public class CurrencyDaoImpl implements CurrencyDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void insertCurrency (String currencyName) {
        String query = "insert into currency (currency_name) value(:p_currency_name)";
        Map namedParameters = new HashMap();
        namedParameters.put("p_currency_name", currencyName);
        namedParameterJdbcTemplate.update(query, namedParameters);
    }

    @Override
    public Currency findCurrencyByName (String currencyName) {
        String query = "select id, currency_name from currency where currency_name = :p_currency_name";
        SqlParameterSource namedParameters = new MapSqlParameterSource("p_currency_name", currencyName);
        return namedParameterJdbcTemplate.queryForObject (query, namedParameters, new RowMapper<Currency>() {
            @Override
            public Currency mapRow(ResultSet rs, int rowNum) throws SQLException {
                Currency currency = new Currency();
                currency.setCurrencyId(rs.getInt("id"));
                currency.setCurrencyName(rs.getString("currency_name"));
                return currency;
            }
        });
    }
}
