package modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Objects;

import oracle.security.o3logon.b;

/**
 * Clase dedicada a los  libros y sus utilidades.
 * @author Ivan, Luis y Sergio
 *
 */

public class Libros implements Comparable<Libros>{
	private int id_libro;
	private String titulo;
	private String autor;
	private String genero;
	private boolean prestado;
	private static int num_libro;
	
	static {
		try {
			num_libro=buscaMaxID();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Constructor de la clase al que no se le pasa el estado.
	 * @param titulo String
	 * @param autor String
	 * @param genero String
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */

	public Libros(String titulo,String autor,String genero) throws ClassNotFoundException, SQLException{
		this.id_libro=num_libro+1;
		this.titulo=titulo;
		this.autor=autor;
		this.genero=genero;
		this.prestado=false;
		num_libro++;
	}
	
	/**
	 * Constructor sobrecargado de la clase al que se le pasa el estado.
	 * @param id int
	 * @param titulo String
	 * @param autor String
	 * @param genero String
	 * @param estado boolean
	 */
	
	public Libros(int id,String titulo,String autor,String genero,boolean estado){
		this.id_libro=id;
		this.titulo=titulo;
		this.autor=autor;
		this.genero=genero;
		this.prestado=estado;
	}
	public static  int buscaMaxID() throws ClassNotFoundException, SQLException {
		int num=0;
		Statement st = Conector.conectar().createStatement();
		ResultSet rs = st.executeQuery("select nvl(max(id_libro),0) max_id from libros");
		if(rs.next()) {
			num=rs.getInt("max_id");
		}
		if(String.valueOf(num)==null) {
			return 0;
		}
		return num;
	}
	
	/**
	 * Constructor de copia de Libros
	 * @param obj Libros
	 */
	
	public Libros(Libros obj) {
		this.id_libro=obj.getId_libro();
		this.titulo=obj.getTitulo();
		this.autor=obj.getAutor();
		this.genero=obj.getGenero();
		this.prestado=obj.isPrestado();
	}
	
	/**
	 * Metodo que cambia el estado del libro a true en la base de datos
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void updateTrueBd() throws ClassNotFoundException, SQLException {
		try {
			String insert="update libros set estado=1 where id_libro ="+this.getId_libro();
			Statement st=Conector.conectar().createStatement();
			st.executeUpdate(insert);
			}finally {
				Conector.cerrar();
			}
		
	}

	
	/**
	 * Metodo que cambia el estado de un libro a true
	 * @param biblio Biblioteca
	 */
	
	public void updateStatusLibroTrue(Biblioteca biblio) {
		if(biblio.getLista_libros().contains(this)) {
			biblio.getLista_libros().remove(this);
			this.setPrestado(true);
			biblio.getLista_libros().add(this);
		}
	}
	
	/**
	 * Metodo que inserta un libro en la base de datos
	 * @param bi1 Biblioteca
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public void insertarLibroBD(Biblioteca bi1) throws SQLException, ClassNotFoundException {
		try {
		String insert=" Insert into libros values(?,?,?,?,?)";
		PreparedStatement st2=Conector.conectar().prepareStatement(insert);
		st2.setInt(1, this.getId_libro());
		st2.setString(2, this.getTitulo());
		st2.setString(3, this.getAutor());
		st2.setString(4, this.getGenero());
		st2.setInt(5, 0);
		st2.executeUpdate();
		bi1.getLista_libros().add(this);
		}finally {
			Conector.cerrar();
		}
		
	}
	
	/**
	 * Metodo que elimina un libro de la base de datos
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public void eliminarLibroBD() throws SQLException, ClassNotFoundException {
		try {
		String insert="delete from libros where id_libro ="+this.getId_libro();
		Statement st=Conector.conectar().createStatement();
		st.executeUpdate(insert);
		}finally {
			Conector.cerrar();
		}
	}
	
	

	/**
	 * Metodo que cambia el estado del libro a false en la base de datos
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public void updateFalseBd() throws ClassNotFoundException, SQLException {
		try {
			String insert="update libros set estado=0 where id_libro ="+this.getId_libro();
			Statement st=Conector.conectar().createStatement();
			st.executeUpdate(insert);
			}finally {
				Conector.cerrar();
			}
		
	}

	/**
	 * Metodo que modifica el estado de un libro a false
	 * @param biblio Biblioteca
	 */
	
	public void updateStatusLibroFalse(Biblioteca biblio) {
		if(biblio.getLista_libros().contains(this)) {
			biblio.getLista_libros().remove(this);
			this.setPrestado(false);
			biblio.getLista_libros().add(this);
		}
	}
	
	

	/**
	 * Metodo toString de la clase
	 */

	@Override
	public String toString() {
		String aux = "Libro: Id: " + id_libro + ", con titulo " + titulo + ", cuyo autor/a es " + autor + " y cuyo genero es " + genero;
		if(this.isPrestado()) {
			return aux+= ", y estado: prestado \n";
		}
		return aux+= ", y estado: disponible \n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_libro,titulo,autor,genero,prestado);
	}
	
	/**
	 * Metodo equals clase libros
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj instanceof Libros) {
			Libros aux=(Libros)obj;
			if(this.id_libro==aux.getId_libro()&& this.titulo.equalsIgnoreCase(aux.getTitulo())&& this.autor.equalsIgnoreCase(aux.getAutor())&&
					this.genero.equalsIgnoreCase(aux.genero)&&this.prestado==aux.isPrestado())
				return(true);

		}
		return false;

	}
	
	/**
	 * Metodo get id_libro
	 * @return devuelve el valor de id_libro
	 */

	public int getId_libro() {
		return id_libro;
	}

	/**
	 * Metodo set id_libro
	 * @param id_libro el valor que se le quiere dar al id_libro
	 */

	public void setId_libro(int id_libro) {
		this.id_libro = id_libro;
	}

	/**
	 * Metodo get titulo
	 * @return devuelve el valor del titulo del libro
	 */

	public String getTitulo() {
		return titulo;
	}

	/**
	 * Metodo set de titulo
	 * @param titulo el valor que se le quiere dar al titulo
	 */

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Metodo get autor
	 * @return devuelve el valor del autor del libro
	 */

	public String getAutor() {
		return autor;
	}

	/**
	 * Metodo set de autor
	 * @param autor el valor que se le quiere dar al autor
	 */

	public void setAutor(String autor) {
		this.autor = autor;
	}

	/**
	 * Metodo get genero
	 * @return devuelve el valor del genero del libro
	 */

	public String getGenero() {
		return genero;
	}

	/**
	 * Metodo set de genero
	 * @param genero el valor que se le quiere dar al genero
	 */

	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * Metodo get prestado
	 * @return devuelve el estado del libro
	 */

	public boolean isPrestado() {
		return prestado;
	}

	/**
	 * Metodo set de prestado
	 * @param prestado el valor que se le quiere dar a prestado
	 */

	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}
	
	public static int getNum_libro() {
		return num_libro;
	}

	public static void setNum_libro(int num_libro) {
		Libros.num_libro = num_libro;
	}

	/**
	 * Metodo por defecto de ordenacion de los libros
	 */
	
	@Override
	public int compareTo(Libros o) {
		int comparar=((Libros)o).getId_libro();
		return this.id_libro-comparar;
	}
}
