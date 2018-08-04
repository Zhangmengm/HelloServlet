package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Hero;


public class HeroDao {
	public HeroDao(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?"
				+ "characterEncoding=UTF-8","root","123456");
	}
	public int getTotal(){
		int total=0;
		try(Connection c=getConnection();Statement s=c.createStatement()){
			String sql="select count(*) from hero";
			ResultSet rs=s.executeQuery(sql);
			while(rs.next()){
				total=rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return total;
	}
	public void addHero(Hero hero){
		String sql="insert into hero values(null,?,?,?)";
		try(Connection c=getConnection();PreparedStatement ps=c.prepareStatement(sql)){
			ps.setString(1, hero.getName());
			ps.setFloat(2, hero.getHp());
			ps.setInt(3, hero.getDamage());
			ps.execute();
			ResultSet rs=ps.getGeneratedKeys();
			if(rs.next()){
				hero.setId(rs.getInt(1));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void updateHero(Hero hero){
		String sql="update hero set name=?,hp=?,damage=? where id=?";
		try(Connection c=getConnection();PreparedStatement ps=c.prepareStatement(sql)){
			ps.setString(1, hero.getName());
			ps.setFloat(2, hero.getHp());
			ps.setInt(3, hero.getDamage());
			ps.setInt(4, hero.getId());
			ps.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void deleteHero(int id){
		String sql="delete from hero where id="+id;
		try(Connection c=getConnection();Statement s=c.createStatement()){
			s.execute(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public Hero getHero(int id){
		String sql="select * from hero where id="+id;
		Hero hero=null;
		try(Connection c=getConnection();Statement s=c.createStatement()){
			ResultSet rs=s.executeQuery(sql);
			if(rs.next()){
				hero=new Hero();
				hero.setId(id);
				hero.setName(rs.getString(2));
				hero.setHp(rs.getFloat(3));
				hero.setDamage(rs.getInt(4));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return hero;
	}
	public List<Hero> listHero(int start,int count){
		List<Hero> heroList=new ArrayList<>();
		String sql="select * from hero order by id desc limit ?,?";
		try(Connection c=getConnection();PreparedStatement ps=c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Hero hero=new Hero();
				hero.setId(rs.getInt(1));
				hero.setName(rs.getString(2));
				hero.setHp(rs.getFloat(3));
				hero.setDamage(rs.getInt(4));
				heroList.add(hero);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return heroList;
	}
}
