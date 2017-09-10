import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Airport {
	
	
	private static final String QUERY1 ="CREATE TABLE Client"
			+ "( id int auto_increment,"
			+ " firstname VARCHAR(60),"
			+ " lastname  VARCHAR(60),"
			+ " email VARCHAR(60) unique,"
			+ " password VARCHAR(60),"
			+ " PRIMARY KEY(id));";
	private static final String QUERY2 ="CREATE TABLE Planes"
			+ "( id int auto_increment,"
			+ " pilot_id int NOT NULL,"
			+ " daparts DATETIME,"
			+ " destination VARCHAR(80),"
			+ " PRIMARY KEY(id),"
			+ "	FOREIGN KEY(pilot_id) REFERENCES Pilot(id));";
	private static final String QUERY3 ="CREATE TABLE Clients_Planes"
			+ "( id	int	AUTO_INCREMENT,"
			+ " client_id	int	NOT	NULL,"
			+ " plane_id	int	NOT	NULL,"
			+ " PRIMARY	KEY(id),"
			+ " FOREIGN	KEY(client_id)	REFERENCES	Client(id),"
			+ " FOREIGN	KEY(plane_id)	REFERENCES	Planes(id) );";
	private static final String QUERY4 ="CREATE TABLE Pilot"
			+ "( id	int	AUTO_INCREMENT,"
			+ " rank Varchar(30),"
			+ " firstname VARCHAR(60),"
			+ " lastname  VARCHAR(60),"
			+ "	 PRIMARY KEY(id));";
	
	public static void main(String[] args){
		String url ="jdbc:mysql://localhost:3306/workshop2_Airport";
		
		
		
		AirportInserts Ar= new AirportInserts();
		String insPlane =Ar.insPlane;
		
		try	(Connection	conn	=	DriverManager.getConnection(url,	"root",	"coderslab"))	{
			
			System.out.println("Do you want to build or drop database B/(...) ?");
			 Scanner scn = new Scanner(System.in);
			 String decide= scn.nextLine();
			
			if (decide.equals("B")) {
				
				try (PreparedStatement stmt = conn.prepareStatement(QUERY4)) {
					stmt.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try (PreparedStatement stmt = conn.prepareStatement(QUERY1)) {
					stmt.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try (PreparedStatement stmt = conn.prepareStatement(QUERY2)) {
					stmt.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try (PreparedStatement stmt = conn.prepareStatement(QUERY3)) {
					stmt.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try (PreparedStatement stmt = conn.prepareStatement(insPlane)) {
					stmt.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else{
				
				DinstallationScrypt drop = new DinstallationScrypt();
				try (PreparedStatement stmt = conn.prepareStatement(drop.deinstall)) {
					stmt.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
			
			
	}
}
