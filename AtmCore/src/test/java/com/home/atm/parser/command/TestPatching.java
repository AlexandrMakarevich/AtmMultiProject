package com.home.atm.parser.command;

import com.tacitknowledge.util.migration.MigrationException;
import com.tacitknowledge.util.migration.jdbc.AutoPatchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:atmCore-test.xml")
public class TestPatching {

    @Resource(name = "dataSource")
    private DataSource dataSource;

    @Test
    public void test() throws MigrationException {
        AutoPatchService autoPatchService = new AutoPatchService();
        autoPatchService.setDataSource(dataSource);
        autoPatchService.setPatchPath("db_scripts");
        autoPatchService.setSystemName("atm_user");
        autoPatchService.setDatabaseType("mysql");
        autoPatchService.patch();
    }
}
