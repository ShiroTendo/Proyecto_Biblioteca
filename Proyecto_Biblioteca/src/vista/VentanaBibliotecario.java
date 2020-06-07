package vista;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelo.Bibliotecario;
import modelo.Libros;
import modelo.Prestamos;
import modelo.Socio;
import net.miginfocom.swing.MigLayout;

public class VentanaBibliotecario extends JFrame implements ActionListener, WindowListener{

	//PESTA�A IMPRIMIR
	private JLabel tituloImprimir;
	private JButton imprimirBibliotecarios;
	private JButton imprimirLibros;
	private JButton imprimirPrestamos;
	private JButton imprimirSocios;
	
	//PESTA�A MOSTRARTODO
	private JLabel tituloMostrar;
	private JButton mostrarBibliotecarios;
	private JButton mostrarLibros;
	private JButton mostrarPrestamos;
	private JButton mostrarSocios;
	private DefaultTableModel modeloMostrar;
	private JTable tablaMostrar;
	
	//PESTA�A A�ADIR/BORRAR LIBRO
	private JLabel tituloAnadirBorrar;
	private JLabel anadir;
	private JLabel borrar;
	private JLabel tituloLibro;
	private JLabel autorLibro;
	private JLabel generoLibro;
	private JLabel idLibroBorrar;
	private JTextField escribirTitulo;
	private JTextField escribirAutor;
	private JTextField escribirGenero;
	private JTextField borrarIdLibro;
	private JButton anadirLibro;
	private JButton borrarLibro;
	
	//PESTA�A PRESTAR/DEVOLVER
	private JLabel tituloPrestarDevolver;
	private JLabel tituloPrestar;
	private JLabel tituloDevolver;
	private JLabel idSocioPrestar;
	private JLabel idLibroPrestar;
	private JLabel idSocioDevolver;
	private JLabel idLibroDevolver;
	private JTextField escribirIdSocioPrestar;
	private JTextField escribirIdLibroPrestar;
	private JTextField escribirIdSocioDevolver;
	private JTextField escribirIdLibroDevolver;
	private JButton prestar;
	private JButton devolver;
	
	//PESTA�A A�ADIR/ELIMINAR BIBLIOTECARIOS
	private JLabel tituloAnadirEliminarBibiliotecarios;
	private JLabel tituloAnadirBibiliotecarios;
	private JLabel tituloEliminarBibliotecarios;
	private JLabel nombreBibliotecario;
	private JLabel apellidosBibliotecario;
	private JLabel telefonoBibliotecario;
	private JLabel dniBibliotecario;
	private JLabel idBibliotecario;
	private JTextField escribirNombreBibliotecario;
	private JTextField escribirApellidosBibliotecario;
	private JTextField escribirTelefonoBibliotecario;
	private JTextField escribirDniBibliotecario;
	private JTextField escribirIdBibliotecario;
	private JButton anadirBibliotecario;
	private JButton eliminarBibliotecario;
	
	//PANELES
	private JTabbedPane pestanas;
	private JPanel panelImprimir;
	private JPanel panelMostrar;
	private JPanel panelAnadirBorrar;
	private JPanel panelPrestarDevolver;
	private JPanel panelAnadirEliminarBibliotecarios;
	private JScrollPane scrollMostrar;
	
	private Bibliotecario bibliotecario;
	
	public VentanaBibliotecario(Bibliotecario b) {
		bibliotecario = new Bibliotecario(b);
		crearAlgo();
	}
	
	public void crearAlgo() {
		
		//CREAR PESTA�A
		pestanas = new JTabbedPane();
		
		//CREAR PANEL IMPRIMIR
		panelImprimir = new JPanel();
		panelImprimir.setLayout(new MigLayout());
		
		//DATOS PANEL IMPRIMIR
		tituloImprimir = new JLabel("BIENVENIDO/A " + bibliotecario.getNombre().toUpperCase());
		
		imprimirBibliotecarios = new JButton("Imprimir bibliotecarios");
		imprimirLibros = new JButton("Imprimir libros");
		imprimirPrestamos = new JButton("Imprimir pr�stamos");
		imprimirSocios = new JButton("Imprimir socios");
		
		imprimirBibliotecarios.addActionListener(this);
		imprimirLibros.addActionListener(this);
		imprimirPrestamos.addActionListener(this);
		imprimirSocios.addActionListener(this);
		
		panelImprimir.add(tituloImprimir, "align center, wrap");
		panelImprimir.add(imprimirBibliotecarios);
		panelImprimir.add(imprimirLibros, "skip, wrap");
		panelImprimir.add(imprimirPrestamos);
		panelImprimir.add(imprimirSocios, "skip");
		
		//A�ADIR PANEL IMPRIMIR A LA PESTA�A
		pestanas.addTab("pesta�a1", panelImprimir);
		
		//CREAR PANEL MOSTRAR
		panelMostrar = new JPanel();
		panelMostrar.setLayout(new MigLayout());
		
		//A�ADIR PANEL A PESTA�A
		pestanas.addTab("pesta�a2", panelMostrar);
		
		//DATOS PANEL MOSTRAR
		tituloMostrar = new JLabel("BIENVENIDO/A " + bibliotecario.getNombre().toUpperCase().toUpperCase());
		
		tablaMostrar = new JTable();
		scrollMostrar = new JScrollPane(tablaMostrar);
		
		mostrarBibliotecarios = new JButton("Mostrar bibliotecarios");
		mostrarLibros = new JButton("Mostrar libros");
		mostrarPrestamos = new JButton("Mostrar pr�stamos");
		mostrarSocios = new JButton("Mostrar socios");
		
		mostrarBibliotecarios.addActionListener(this);
		mostrarLibros.addActionListener(this);
		mostrarPrestamos.addActionListener(this);
		mostrarSocios.addActionListener(this);
		
		panelMostrar.add(tituloMostrar, "align center, wrap");
		panelMostrar.add(mostrarBibliotecarios);
		panelMostrar.add(mostrarLibros, "skip, wrap");
		panelMostrar.add(mostrarPrestamos);
		panelMostrar.add(mostrarSocios, "skip, wrap");
		panelMostrar.add(scrollMostrar, "span2, align center");
		
		//CREAR PANEL A�ADIR/BORRAR LIBRO
		panelAnadirBorrar = new JPanel();
		panelAnadirBorrar.setLayout(new MigLayout());
		
		//A�ADIR PANEL A PESTA�A
		pestanas.addTab("pesta�a3", panelAnadirBorrar);
		
		//DATOS PANEL MOSTRAR
		
		tituloAnadirBorrar = new JLabel("BIENVENIDO/A " + bibliotecario.getNombre().toUpperCase());
		
		anadir = new JLabel("A�ADIR LIBROS");
		borrar = new JLabel("BORRAR LIBROS");
		tituloLibro = new JLabel("T�tulo del libro: ");
		autorLibro = new JLabel("Autor del libro: ");
		generoLibro = new JLabel("G�nero del libro: ");
		idLibroBorrar = new JLabel("ID del libro: ");
		
		escribirTitulo = new JTextField(30);
		escribirAutor = new JTextField(25);
		escribirGenero = new JTextField(15);
		borrarIdLibro = new JTextField(25);
		
		anadirLibro = new JButton("A�adir libro");
		borrarLibro = new JButton("Borrar libro");
		
		anadirLibro.addActionListener(this);
		borrarLibro.addActionListener(this);
		
		panelAnadirBorrar.add(tituloAnadirBorrar, "skip2, align center, wrap");
		panelAnadirBorrar.add(anadir, "span2, align center");
		panelAnadirBorrar.add(borrar, "skip2, align center, wrap");
		panelAnadirBorrar.add(tituloLibro);
		panelAnadirBorrar.add(escribirTitulo, "wrap");
		panelAnadirBorrar.add(autorLibro);
		panelAnadirBorrar.add(escribirAutor);
		panelAnadirBorrar.add(idLibroBorrar, "skip");
		panelAnadirBorrar.add(borrarIdLibro, "wrap");
		panelAnadirBorrar.add(generoLibro);
		panelAnadirBorrar.add(escribirGenero, "wrap");
		panelAnadirBorrar.add(anadirLibro, "span2, align center");
		panelAnadirBorrar.add(borrarLibro, "skip2, align left");
	
		//CREAR PANEL PRESTAR/DEVOLVER LIBRO
		panelPrestarDevolver = new JPanel();
		panelPrestarDevolver.setLayout(new MigLayout());
		
		//A�ADIR PANEL A PESTA�A
		pestanas.addTab("pesta�a4", panelPrestarDevolver);
		
		//DATOS PANEL PRESTAR DEVOLVER
		tituloPrestarDevolver = new JLabel("BIENVENIDO/A " + bibliotecario.getNombre().toUpperCase());
		
		tituloPrestar = new JLabel("PR�STAMO DE LIBROS");
		tituloDevolver = new JLabel("DEVOLUCI�N DE LIBROS");
		idSocioPrestar = new JLabel("ID del socio: ");
		idLibroPrestar = new JLabel("ID del libro: ");
		idSocioDevolver = new JLabel("ID del socio: ");
		idLibroDevolver = new JLabel("ID del libro: ");
		
		escribirIdSocioPrestar = new JTextField(10);
		escribirIdLibroPrestar = new JTextField(10);
		escribirIdSocioDevolver = new JTextField(10);
		escribirIdLibroDevolver = new JTextField(10);
		
		prestar = new JButton("Prestar libro");
		devolver = new JButton("Devolver libro");
		prestar.addActionListener(this);
		devolver.addActionListener(this);
		
		panelPrestarDevolver.add(tituloPrestarDevolver, "skip2, align center, wrap");
		panelPrestarDevolver.add(tituloPrestar, "span2, align center");
		panelPrestarDevolver.add(tituloDevolver, "skip2, align center, wrap");
		panelPrestarDevolver.add(idSocioPrestar);
		panelPrestarDevolver.add(escribirIdSocioPrestar);
		panelPrestarDevolver.add(idSocioDevolver, "skip");
		panelPrestarDevolver.add(escribirIdSocioDevolver, "wrap");
		panelPrestarDevolver.add(idLibroPrestar);
		panelPrestarDevolver.add(escribirIdLibroPrestar);
		panelPrestarDevolver.add(idLibroDevolver, "skip");
		panelPrestarDevolver.add(escribirIdLibroDevolver, "wrap");
		panelPrestarDevolver.add(prestar, "span2, align center");
		panelPrestarDevolver.add(devolver, "skip2");
		
		//CREAR PANEL A�ADIR/ELIMINAR BIBLIOTECARIOS
		panelAnadirEliminarBibliotecarios = new JPanel();
		panelAnadirEliminarBibliotecarios.setLayout(new MigLayout());
		
		//A�ADIR PANEL A PESTA�A
		pestanas.addTab("pesta�a5", panelAnadirEliminarBibliotecarios);
		
		//DATOS PANEL A�ADIR/ELIMINAR BIBLIOTECARIOS
		tituloAnadirEliminarBibiliotecarios = new JLabel("BIENVENIDO/A " + bibliotecario.getNombre().toUpperCase());
		tituloAnadirBibiliotecarios = new JLabel("A�ADIR BIBLIOTECARIOS");
		tituloEliminarBibliotecarios = new JLabel("ELIMINAR BIBLIOTECARIOS");
		nombreBibliotecario = new JLabel("Nombre: ");
		apellidosBibliotecario = new JLabel("Apellidos: ");
		telefonoBibliotecario = new JLabel("Tel�fono: ");
		dniBibliotecario = new JLabel("DNI: ");
		idBibliotecario = new JLabel("Id bibliotecario: ");
		
		escribirNombreBibliotecario = new JTextField(15);
		escribirApellidosBibliotecario = new JTextField(20);
		escribirTelefonoBibliotecario = new JTextField(9);
		escribirDniBibliotecario = new JTextField(8);
		escribirIdBibliotecario = new JTextField(10);
		
		anadirBibliotecario = new JButton("A�adir");
		eliminarBibliotecario = new JButton("Eliminar");
		anadirBibliotecario.addActionListener(this);
		eliminarBibliotecario.addActionListener(this);
		
		panelAnadirEliminarBibliotecarios.add(tituloAnadirEliminarBibiliotecarios, "skip2, align center, wrap");
		panelAnadirEliminarBibliotecarios.add(tituloAnadirBibiliotecarios, "span4");
		panelAnadirEliminarBibliotecarios.add(tituloEliminarBibliotecarios, "wrap");
		panelAnadirEliminarBibliotecarios.add(nombreBibliotecario);
		panelAnadirEliminarBibliotecarios.add(escribirNombreBibliotecario, "wrap");
		panelAnadirEliminarBibliotecarios.add(apellidosBibliotecario);
		panelAnadirEliminarBibliotecarios.add(escribirApellidosBibliotecario);
		panelAnadirEliminarBibliotecarios.add(idBibliotecario, "skip");
		panelAnadirEliminarBibliotecarios.add(escribirIdBibliotecario, "wrap");
		panelAnadirEliminarBibliotecarios.add(telefonoBibliotecario);
		panelAnadirEliminarBibliotecarios.add(escribirTelefonoBibliotecario, "wrap");
		panelAnadirEliminarBibliotecarios.add(dniBibliotecario);
		panelAnadirEliminarBibliotecarios.add(escribirDniBibliotecario, "wrap");
		panelAnadirEliminarBibliotecarios.add(anadirBibliotecario);
		panelAnadirEliminarBibliotecarios.add(eliminarBibliotecario, "skip3, align left");
		
		this.add(pestanas);
		setVisible(true);
		setTitle("Imprimir");
		setResizable(false);
		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        this.setSize(width/3, height/2);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(imprimirBibliotecarios)) {
			try {
				MainVentana.biblioteca.imprimirBibliotecarios();
				JOptionPane.showMessageDialog(this, "Bibliotecarios impresos con �xito");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource().equals(imprimirLibros)) {
			try {
				MainVentana.biblioteca.imprimirLibros();
				JOptionPane.showMessageDialog(this, "Libros impresos con �xito");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource().equals(imprimirPrestamos)) {
			try {
				MainVentana.biblioteca.imprimirPrestamos();
				JOptionPane.showMessageDialog(this, "Pr�stamos impresos con �xito");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource().equals(imprimirSocios)) {
			try {
				MainVentana.biblioteca.imprimirSocios();
				JOptionPane.showMessageDialog(this, "Socios impresos con �xito");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource().equals(mostrarBibliotecarios)) {
			String listaBibliotecarios[] = {"Cod", "Nombre", "Apellidos", "Tel�fono", "DNI"};
			modeloMostrar = new DefaultTableModel(null, listaBibliotecarios);
			tablaMostrar.setModel(modeloMostrar);
			if(MainVentana.biblioteca.getLista_bibliotecarios().size()!=0) {
				ArrayList<Bibliotecario> orden= new ArrayList<Bibliotecario>(MainVentana.biblioteca.getLista_bibliotecarios());
				Collections.sort(orden);
				for (Bibliotecario i : orden) {
					introducirBibliotecario(i);
				}
			
			}
		}
		if(e.getSource().equals(mostrarLibros)) {
			String listaLibros[] = {"ID", "T�tulo", "Autor", "G�nero", "Estado"};
			limpiarTabla(tablaMostrar, modeloMostrar);
			modeloMostrar = new DefaultTableModel(null, listaLibros);
			tablaMostrar.setModel(modeloMostrar);
			if(MainVentana.biblioteca.getLista_libros().size()!=0) {
				ArrayList<Libros> orden= new ArrayList<Libros>(MainVentana.biblioteca.getLista_libros());
				Collections.sort(orden);
				for (Libros i : orden) {
					introducirLibro(i);
				}
			}
		}
		if(e.getSource().equals(mostrarPrestamos)) {
			String listaPrestamos[] = {"Fecha_Inicio", "Fecha_Fin", "Cod_Socio", "ID_Libro"};
			limpiarTabla(tablaMostrar, modeloMostrar);
			modeloMostrar = new DefaultTableModel(null, listaPrestamos);
			tablaMostrar.setModel(modeloMostrar);
			if(MainVentana.biblioteca.getLista_prestamos().size()!=0) {
				ArrayList<Prestamos> orden = new ArrayList<Prestamos>(MainVentana.biblioteca.getLista_prestamos());
				Collections.sort(orden);
				for (Prestamos i : orden) {
					introducirPrestamos(i);
				}
			}
		}
		if(e.getSource().equals(mostrarSocios)) {
			String listaSocios[] = {"Cod", "Nombre", "Apellidos", "Tel�fono", "DNI", "ID_Libro"};
			limpiarTabla(tablaMostrar, modeloMostrar);
			modeloMostrar = new DefaultTableModel(null, listaSocios);
			tablaMostrar.setModel(modeloMostrar);
			if(MainVentana.biblioteca.getLista_socios().size()!=0) {
				ArrayList<Socio> orden = new ArrayList<Socio>(MainVentana.biblioteca.getLista_socios());
				Collections.sort(orden);
				for (Socio i : orden) {
					introducirSocios(i);
				}
			}
		}
		
		if(e.getSource().equals(anadirLibro)) {
			try {
				if(escribirTitulo.getText().equals("") && escribirAutor.getText().equals("") && escribirGenero.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Rellene los campos por favor");
				}
				else {
					Libros aux = new Libros(escribirTitulo.getText(), escribirAutor.getText(), escribirGenero.getText());
					aux.insertarLibroBD(MainVentana.biblioteca);
					JOptionPane.showMessageDialog(this, "Libro a�adidio con �xito");
				}
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource().equals(borrarLibro)) {
			if(borrarIdLibro.getText().equals(""))
				JOptionPane.showMessageDialog(this, "Rellene el campo por favor");
			else {
				Libros aux = MainVentana.biblioteca.buscaLibro(Integer.parseInt(borrarIdLibro.getText()));
				try {
					MainVentana.biblioteca.borradoTotalLibro(aux);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(this, "Libro borrado con �xito");
			}
		}
		if(e.getSource().equals(prestar)) {
			if(escribirIdSocioPrestar.getText().equals("") && escribirIdLibroPrestar.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Rellene los campos por favor");
			}
			else {
				try {
					int aux = bibliotecario.PrestarLibro(Integer.parseInt(escribirIdSocioPrestar.getText()), Integer.parseInt(escribirIdLibroPrestar.getText()), MainVentana.biblioteca);
					opcionesPrestar(aux);
				} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		}
		if(e.getSource().equals(devolver)) {
			if(escribirIdSocioDevolver.getText().equals("") && escribirIdLibroDevolver.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Rellene los campos por favor");
			}
			else {
				try {
					int aux = bibliotecario.DevolverLibro(Integer.parseInt(escribirIdSocioDevolver.getText()), Integer.parseInt(escribirIdLibroDevolver.getText()), MainVentana.biblioteca);
					opcionesDevolver(aux);
				} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		}
		if(e.getSource().equals(anadirBibliotecario)) {
			if(escribirNombreBibliotecario.getText().equals("") && escribirApellidosBibliotecario.getText().equals("") && escribirTelefonoBibliotecario.getText().equals("") && escribirDniBibliotecario.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Rellene los campos por favor");
			}
			else {
				String dni =comprobarDni(escribirDniBibliotecario.getText());
				try {
					Bibliotecario aux = new Bibliotecario(dni, escribirNombreBibliotecario.getText(), escribirApellidosBibliotecario.getText(), Integer.parseInt(escribirTelefonoBibliotecario.getText()));
					aux.insertarBibliotecarioBD(MainVentana.biblioteca);
					JOptionPane.showMessageDialog(this, "Bibliotecario a�adido con �xito");
				} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
	
	public String comprobarDni(String comdni) {
        String regex = "\\d{7}[a-zA-Z]";
        if(Pattern.matches(regex, comdni)) {
            return comdni.toUpperCase();
        }
        JOptionPane.showMessageDialog(this, "DNI no v�lido, respete el formato");
        return null;
    }
	
	public void introducirBibliotecario(Bibliotecario b) {
		Bibliotecario aux;
		if(b!=null) {
			Object lista[] = new Object[5];
			lista [0] = b.getCod_Emple();
			lista [1] = b.getNombre();
			lista [2] = b.getApellidos();
			lista [3] = b.getN_telefono();
			lista [4] = b.getDni();
			modeloMostrar.addRow(lista);
		}
		else
			JOptionPane.showMessageDialog(this, "No se ha encontrado nada en la base.");
	}
	
	public void introducirLibro(Libros l) {
		Libros aux;
		if(l!=null) {
			Object lista[] = new Object[5];
			lista [0] = l.getId_libro();
			lista [1] = l.getTitulo();
			lista [2] = l.getAutor();
			lista [3] = l.getGenero();
			if(l.isPrestado())
				lista [4] = "prestado";
			else
				lista [4] = "disponible";
			modeloMostrar.addRow(lista);
		}
		else
			JOptionPane.showMessageDialog(this, "No se ha encontrado un libro con ese ID en la base de datos");
	}
	
	public void introducirPrestamos(Prestamos p) {
		Prestamos aux;
		if(p!=null) {
			Object lista[] = new Object[5];
			lista [0] = p.getFecha_inicio();
			lista [1] = p.getFecha_fin();
			lista [2] = p.getSocio_asocidado();
			lista [3] = p.getLibro_asociado();
			modeloMostrar.addRow(lista);
		}
		else
			JOptionPane.showMessageDialog(this, "No se ha encontrado nada en la base");
	}
	
	public void introducirSocios(Socio s) {
		Socio aux;
		if(s!=null) {
			Object lista[] = new Object[6];
			lista [0] = s.getCod_Socio();
			lista [1] = s.getNombre();
			lista [2] = s.getApellidos();
			lista [3] = s.getN_telefono();
			lista [4] = s.getDni();
			lista [5] = s.devuelveID();
			modeloMostrar.addRow(lista);
		}
		else
			JOptionPane.showMessageDialog(this, "No se ha encontrado nada en la base");
	}
	
	public void opcionesPrestar(int x) {
		switch(x) {
			case 0: JOptionPane.showMessageDialog(this, "Pr�stamo realizado con �xito");
					break;
			case 1: JOptionPane.showMessageDialog(this, "El libro ya est� prestado, pruebe m�s tarde");
					break;
			case 2: JOptionPane.showMessageDialog(this, "El libro no existe, introduzca un ID v�lido");
					break;
			case 3: JOptionPane.showMessageDialog(this, "Has alcanzado el l�mite de libros prestados, devuelve alguno");
					break;
			case 4: JOptionPane.showMessageDialog(this, "El usuario no se encuentra en la base de datos. introduzca un ID v�lido");
					break;
		}
	}
	
	public void opcionesDevolver(int x) {
		switch(x) {
			case 0: JOptionPane.showMessageDialog(this, "Devoluci�n realizada con �xito");
					break;
			case 1: JOptionPane.showMessageDialog(this, "Introduzca unos datos validos, puesto que los introducidos no son referentes a ningun prestamo");
					break;
		}
	}
	
	public static void limpiarTabla(JTable tabla, DefaultTableModel modelo) {
        int filas = tabla.getRowCount()-1;
        for (int i = filas; i >= 0; i--)
            modelo.removeRow(i);
    }
	
}