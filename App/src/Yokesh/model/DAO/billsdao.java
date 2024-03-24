package Yokesh.model.DAO;

import java.sql.Connection;
import java.sql.Statement;

import Yokesh.controller.connection;
import Yokesh.controller.user;

public class billsdao {
    public static Connection con;
    public static void generate (user us,int id,int count,int tot,String prname) throws Exception
    {
      con=connection.getconnection();
      String s="insert into bills values(0,'"+us.getId()+"','"+prname+"','"+count+"','"+tot+"','not paid')";
      Statement st=con.createStatement();
      st.executeUpdate(s);
    }
    public static int update(int id)throws Exception
    {
        String h=String.valueOf(id);
        con=connection.getconnection();
      String s="update bills set paid='paid' where name='"+h+"'";
      Statement st=con.createStatement();
      return st.executeUpdate(s);
    }
}
