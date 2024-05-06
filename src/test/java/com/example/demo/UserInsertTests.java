package com.example.demo;

import com.example.demo.util.Dbconf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import org.hibernate.Session;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Map;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInsertTests {

    @PersistenceContext
    private EntityManager  entityManager;
    private static final Logger log = LoggerFactory.getLogger(UserInsertTests.class);
    private Dbconf dbconf;
    private Connection con = null;
    @Test
    public void testCount() {
        try {
            entityManager.createNativeQuery("INSERT INTO user_model (account_expired, account_locked, credentials_expired, email, enabled, name, password, username) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);")
            .setParameter(1, 0)
            .setParameter(2, 0)
            .setParameter(3, 0)
            .setParameter(4,  "tamnd@gmail.com")
            .setParameter(5, 1)
            .setParameter(6, "NGUYEN DINH TAM")
            .setParameter(7, "{bcrypt}$2a$10$iyZmEzd13MQGF46fnbUzO.ItgMPo2NoVCnVBvYPWIbqSh2WRI4W5i")
            .setParameter(8, "tamnd")
            .executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Test
    public void callProceduce() {
        Session session = entityManager.unwrap(Session.class);

		try 
		{
			ProcedureCall call = session.createStoredProcedureCall( "procedure_customer");
			call.registerParameter(3, Class.class, ParameterMode.REF_CURSOR);
			call.registerParameter(4, Class.class, ParameterMode.REF_CURSOR);
			call.registerParameter(5, Class.class, ParameterMode.REF_CURSOR);
			call.execute();
			Output output = call.getOutputs().getCurrent();
			List<Object[]> data_ = ( (ResultSetOutput) output ).getResultList();
			log.info("data : ",data_.size());
		} 
		catch (Exception ex) 
		{

			log.error("JRException : ", ex.getMessage());
		}		
    }
    private CallableStatement stmt = null;
    @Test
    public void callsql() {
        try {
            con = dbconf.getConnection();
            stmt = con.prepareCall("BEGIN procedure_customer(?, ? , ? , ? , ?); END;");
            stmt.setString(1, "1"); // DEPTNO
            stmt.setString(2, ""); 
            stmt.registerOutParameter(3, OracleTypes.CURSOR); //REF CURSOR
            stmt.registerOutParameter(4, OracleTypes.CURSOR); //REF CURSOR
            stmt.registerOutParameter(5, OracleTypes.CURSOR); //REF CURSOR
            stmt.executeQuery();
            ResultSet rs = ((OracleCallableStatement)stmt).getCursor(5);
            while (rs.next()) {
              System.out.println(rs.getString("name") ); 
            }
            rs.close();
            rs = null;
            stmt.close();
            stmt = null;
            con.close();
            con = null;
          }
          catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
          }
    }
    @Autowired
    private DataSource dataSource;
    private SimpleJdbcCall jdbcCall;
    @Test
    public void callsqljdbc() {
        try {
          JdbcTemplate template = new JdbcTemplate(dataSource);
          jdbcCall = new SimpleJdbcCall(template)
                  .withProcedureName("procedure_customer")
                  .declareParameters(
                          new SqlParameter("v_customerId", Types.VARCHAR),
                          new SqlParameter("v_search", Types.VARCHAR),
                          new SqlOutParameter("rc", Types.REF_CURSOR),
                          new SqlOutParameter("rc1", Types.REF_CURSOR),
                          new SqlOutParameter("rc2", Types.REF_CURSOR)
                          );
                          SqlParameterSource param = new MapSqlParameterSource()
                                  .addValue("v_customerId", "1")
                                  .addValue("v_search", "");
                      
                          Map map = jdbcCall.execute(param);
                          var userData = (List<Map>) map.get("rc");
          }
          catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
          }
    }

}
