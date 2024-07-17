package postgreSQL_Java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
	public static void main(String[] args) {
		
		try {
			Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/banco", "postgres", "admin");
			
			if(conexao != null) {
				System.out.println("Banco de dados conectado com sucesso!");
				Statement stm = conexao.createStatement();
				inserirDados(stm); 
				consultarDados(stm);
			} else {
				System.out.println("Conexão falhou!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	static void consultarDados(Statement stm) {
		String sql = "select category_id, category_name, description from categories";
		
		try {
			ResultSet result = stm.executeQuery(sql);
			
			while(result.next()) {
				System.out.println("ID: " + result.getInt("category_id") + 
						", Nome: " + result.getString("category_name") + 
						", Descrição: " + result.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	static void inserirDados(Statement stm) {
		String sql = "insert into categories (category_id, category_name) values ('16', 'Categoria 16xxx')";
		
		try {
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
