package com.home.atm.command;

import com.google.common.base.Objects;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("printBalanceCommand")
public class PrintBalanceCommand implements Command {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final Logger LOGGER = Logger.getLogger(PrintBalanceCommand.class);

    @Autowired
    public PrintBalanceCommand(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<PrintBalance> executeDb(int accountId) {
        String query = "select currency_name cn,balance b" +
                " from debit d inner join account a on a.id = d.account_id" +
                " inner join currency c on c.id = d.currency_id" +
                " where d.account_id = :p_account_id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("p_account_id", accountId);
        return namedParameterJdbcTemplate.query(query, namedParameters, new RowMapper<PrintBalance>() {
            @Override
            public PrintBalance mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new PrintBalance(rs.getString("cn"), rs.getInt("b"));
            }
        });
    }

    @Override
    public CommandName getCommandOperation() {
        return CommandName.PRINT;
    }

}
