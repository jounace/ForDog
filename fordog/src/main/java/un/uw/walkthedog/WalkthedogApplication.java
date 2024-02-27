package un.uw.walkthedog;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.properties")
@SpringBootApplication
public class WalkthedogApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalkthedogApplication.class, args);
    }

    @Autowired
private Environment env;

    @Bean(name = "mysqlDatasource")
    @Qualifier("mysqlDatasource")
    @Primary
    public BasicDataSource mysqlDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        String url = env.getProperty("mysql.datasource.jdbc.url");
        dataSource.setDriverClassName("com.mysql.jdbc.driver");
        dataSource.setUrl(url);
        dataSource.setUsername(env.getProperty("mysql.datasource.username"));
        dataSource.setPassword(env.getProperty("mysql.datasource.password"));
        return dataSource;
    }

    @Bean(name = "mysqljdbctemple")
    public JdbcTemplate mysqljdbctemple(@Qualifier("mysqlDatasource")DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}
