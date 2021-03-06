package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Visitor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Integer horaEntrada;
	private Integer horaSaida;
	private Dweller dweller;
	
	public Visitor() {
		
	}
	
	public Visitor(Integer id, String name, Integer horaEntrada, Integer horaSaida, Dweller dweller) {
		this.id = id;
		this.name = name;
		this.horaEntrada = horaEntrada;
		this.horaSaida = horaSaida;
		this.dweller = dweller;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Integer horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Integer getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Integer horaSaida) {
		this.horaSaida = horaSaida;
	}
	
	public Dweller getDweller() {
		return dweller;
	}

	public void setDweller(Dweller dweller) {
		this.dweller = dweller;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((horaEntrada == null) ? 0 : horaEntrada.hashCode());
		result = prime * result + ((horaSaida == null) ? 0 : horaSaida.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Visitor other = (Visitor) obj;
		if (horaEntrada == null) {
			if (other.horaEntrada != null)
				return false;
		} else if (!horaEntrada.equals(other.horaEntrada))
			return false;
		if (horaSaida == null) {
			if (other.horaSaida != null)
				return false;
		} else if (!horaSaida.equals(other.horaSaida))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Visitor [id=" + id + ", name=" + name + ", horaEntrada=" + horaEntrada + ", horaSaida=" + horaSaida
				+ ", dwller= "+ dweller+"]";
	}

	

	
	
	
	
	
}
