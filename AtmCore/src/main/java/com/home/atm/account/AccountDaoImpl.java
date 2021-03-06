package com.home.atm.account;

import com.google.common.base.Optional;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("accountDaoImpl")
public class AccountDaoImpl implements AccountDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Resource
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<Account> findAccountByName(String accountInput) {
        String query = "select id i, account_name a from account where account_name = :p_account_name";
        SqlParameterSource namedParameters = new MapSqlParameterSource("p_account_name", accountInput);
        List<Account> listOfAccounts = namedParameterJdbcTemplate.query(query, namedParameters, new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Account(rs.getString("a"), rs.getInt("i"));
            }
        });

        if (listOfAccounts.isEmpty()) {
            return Optional.absent();
        }
        if (listOfAccounts.size() > 1) {
            throw new IllegalStateException("Found more than one account with name " + accountInput +
                    " please check your database data!");
        }
        return Optional.of(listOfAccounts.get(0));
    }

    @Override
    public int createAccount(String accountName) {
        String query = "insert into account (account_name) value(:p_account_name)";
        SqlParameterSource namedParameters = new MapSqlParameterSource("p_account_name", accountName);
        int rows = namedParameterJdbcTemplate.update(query, namedParameters);
        if (rows == 0) {
            System.out.println("No column was changed!");
            throw new IllegalStateException("No column was changed!");
        }
        return rows;
    }

    @Override
    public void deleteAccount(int accountId) {
        String query = "delete from account where id = :p_account_id";
        SqlParameterSource namedParameter = new MapSqlParameterSource("p_account_id", accountId);
        namedParameterJdbcTemplate.update(query, namedParameter);
    }

    @Override
    public List<Account> getAllAccounts() {
        String query = "select * from account";
        return namedParameterJdbcTemplate.query(query, new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                Account account = new Account();
                account.setAccountId(rs.getInt(1));
                account.setAccountName(rs.getString(2));
                return account;
            }
        });
    }
}
