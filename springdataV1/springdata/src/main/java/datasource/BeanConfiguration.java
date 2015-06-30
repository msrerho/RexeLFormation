package datasource;


import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration // une classe qui contient les declarations des beans, le conteneur spring génere les definitions et les services fournies par les beans
@EnableJpaRepositories("repository") // le package contient le repository
/*@ComponentScan //le chemin des modeles sur lequel on applique les configuration*/
@PropertySource("classpath:application.properties")

public class BeanConfiguration {
	
	
	@Value("${db.driver}")
	private String driverClassName;
	@Value("${db.url}")
	private String url;
	@Value("${db.username}")
	private String username;
	@Value("${db.password}")
	private String password;
	@Value("${entitymanager.packages.to.scan}")
	private String packageToscan;
	
	@Bean  
	public DataSource dataSource() {  /*L'accès à une base de données via une DataSource on a pris un exemple de configuration
		d'une DataSource avec l'API DBCP (Fournir une implémentation de connexions vers une base de données) */
		
		//création d'une référence sur la DataSource 
		
		BasicDataSource ds = new org.apache.commons.dbcp.BasicDataSource();
		
		//Configure the database connection
		
		ds.setDriverClassName(driverClassName);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
			
		return ds;
	}
	
	// Déclararion de l'entity manager factory permettant de fournir les instances des gestionnaires d'entités 
	
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		
		//We need to create this object because it creates the JPA EntityManagerFactory.
		
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		
		lef.setDataSource(dataSource);
		lef.setJpaVendorAdapter(jpaVendorAdapter); //assurer la persistance 
		lef.setPackagesToScan(packageToscan); //on doit mentionner le package qui contient entity classe
		
		return lef;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() { // On spécifie ici l'adaptateur Spring pour l''implémentation JPA utilisée
		
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		
		return hibernateJpaVendorAdapter;
	}
	
	
	@Bean  //Because we are using JPA, we have to create a transaction manager bean
	public PlatformTransactionManager transactionManager() {
		
		return new JpaTransactionManager();
	}
	



	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
}