package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.VisitorDao;
import model.entities.Dweller;
import model.entities.Visitor;

public class VisitorDaoJDBC implements VisitorDao{
	
private Connection conn;
	
	public VisitorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void insert(Visitor obj) {
		PreparedStatement st = null;
		try {
			st= conn.prepareStatement(
					"INSERT INTO visitor "
					+"(Nome, HoraEntrada, HoraSaida, DwellerId) "
					+"VALUES "
					+"(?, ?, ?, ?) ",
					Statement.RETURN_GENERATED_KEYS
					);
			st.setString(1, obj.getName());
			st.setInt(2, obj.getHoraEntrada());
			st.setInt(3, obj.getHoraSaida());
			st.setString(4, obj.getDweller().getName());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Visitor obj) {
		PreparedStatement st = null;
		try {
			st= conn.prepareStatement(
					"UPDATE visitor "
					+"SET Name = ?, HoraEntrada = ?, HoraSaida = ?, DwellerName = ? "
					+"WHERE Id = ? "
					);
			st.setString(1, obj.getName());
			st.setInt(2, obj.getHoraEntrada());
			st.setInt(3, obj.getHoraSaida());
			st.setString(4, obj.getDweller().getName());
			st.setInt(5, obj.getId());
			
			st.executeUpdate();
			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"DELETE FROM visitor WHERE Id = ? "
					);
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Visitor findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT visitor.*,Name as DweName "
					+"FROM visitor INNER JOIN dweller "
					+"ON visitor.DwellerId = dweller.Id "
					+"WHERE visitor.Id = ? "
					);
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Dweller dwe = instantiateDweller(rs);
				Visitor obj = instantiateVisitor(rs, dwe);
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Visitor instantiateVisitor(ResultSet rs, Dweller dwe) throws SQLException {
		Visitor obj = new Visitor();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Nome"));
		obj.setHoraEntrada(rs.getInt("HoraEntrada"));
		obj.setHoraSaida(rs.getInt("HoraSaida"));
		obj.setDweller(dwe);
		return obj;
	}

	private Dweller instantiateDweller(ResultSet rs) throws SQLException {
		Dweller dwe = new Dweller();
		dwe.setId(rs.getInt("DwellerId"));
		dwe.setName(rs.getString("DwellerName"));
		return dwe;
	}

	@Override
	public List<Visitor> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT visitor.*,dweller.Name as DwellerName "
					+"FROM visitor INNER JOIN dweller "
					+"ON visitor.DwellerId = dweller.Id "
					+"ORDER BY Name "
					);
			
			rs = st.executeQuery();
			
			List<Visitor> list = new ArrayList<>();
			Map<Integer, Dweller> map = new HashMap<>();
			
			while(rs.next()) {
				
				Dweller dwe = map.get(rs.getInt("DwellerId"));
				
				if (dwe == null) {
					dwe = instantiateDweller(rs);
					map.put(rs.getInt("DwellerId"), dwe);
					
				}
				
				Visitor obj = instantiateVisitor(rs, dwe);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
