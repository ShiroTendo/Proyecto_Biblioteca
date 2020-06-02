package modelo;

import java.sql.Date;

public class Prestamos {
	
	private Date fecha_inicio;
	private Date fecha_fin;
	private int socio_asocidado;
	private int libro_asociado;
	
	
	
	
	public Prestamos(Date inicio,Date fin,int socio,int libro) {
		this.fecha_inicio=inicio;
		this.fecha_fin=fin;
		this.socio_asocidado=socio;
		this.libro_asociado=libro;

	}




	@Override
	public String toString() {
		return "Prestamos [fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin + ", libro_asociado="
				+ libro_asociado + ", socio_asocidado=" + socio_asocidado + "]";
	}




	public Date getFecha_inicio() {
		return fecha_inicio;
	}




	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}




	public Date getFecha_fin() {
		return fecha_fin;
	}




	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}




	public int getLibro_asociado() {
		return libro_asociado;
	}




	public void setLibro_asociado(int libro_asociado) {
		this.libro_asociado = libro_asociado;
	}




	public int getSocio_asocidado() {
		return socio_asocidado;
	}




	public void setSocio_asocidado(int socio_asocidado) {
		this.socio_asocidado = socio_asocidado;
	}
	
	
	
	

}
