package uade.ioo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import uade.ioo.exception.InvalidAccionException;
import uade.ioo.exception.ValidationException;
import uade.ioo.model.IdentificacionObjetos;
import uade.ioo.model.ItemRanking;
import uade.ioo.model.Juego;
import uade.ioo.model.Nivel;
import uade.ioo.model.Pieza;
import uade.ioo.model.Ranking;
import uade.ioo.model.Rompecabezas;
import uade.ioo.model.Usuario;

public class Controlador {

	private List<Usuario> usuarios;
	private List<Juego> juegos;
	private Ranking ranking;
	private Usuario usuarioActual;
	private Juego juegoActual;

	public Controlador() {
		this.usuarios = new ArrayList<Usuario>();
		this.juegos = new ArrayList<Juego>();
		this.ranking = new Ranking();

		this.initJuegos();
	}

	private void initJuegos() {
		List<Nivel> nivelesRompeCabezas = new ArrayList<Nivel>();
		List<Pieza> piezas = new ArrayList<Pieza>();
		piezas.add(new Pieza("0_0", "/images/dog_0-0.jpeg"));
		piezas.add(new Pieza("0_1", "/images/dog_0-1.jpeg"));
		piezas.add(new Pieza("1_0", "/images/dog_1-0.jpeg"));
		piezas.add(new Pieza("1_1", "/images/dog_1-1.jpeg"));

		Nivel n1 = new Nivel(1, piezas);
		nivelesRompeCabezas.add(n1);

		piezas = new ArrayList<Pieza>();
		piezas.add(new Pieza("0_0", "/images/smb_0-0.jpg"));
		piezas.add(new Pieza("0_1", "/images/smb_0-1.jpg"));
		piezas.add(new Pieza("0_2", "/images/smb_0-2.jpg"));
		piezas.add(new Pieza("1_0", "/images/smb_1-0.jpg"));
		piezas.add(new Pieza("1_1", "/images/smb_1-1.jpg"));
		piezas.add(new Pieza("1_2", "/images/smb_1-2.jpg"));
		piezas.add(new Pieza("2_0", "/images/smb_2-0.jpg"));
		piezas.add(new Pieza("2_1", "/images/smb_2-1.jpg"));
		piezas.add(new Pieza("2_2", "/images/smb_2-2.jpg"));
		Nivel n2 = new Nivel(2, piezas);
		nivelesRompeCabezas.add(n2);

		Juego r = new Rompecabezas("rompecabezas", nivelesRompeCabezas);
		this.juegos.add(r);
		
		/*------------------------------------------------------------------------------------
		 * Juego de Identificacion De objetos*/
		
		List<Nivel> nivelesIdentificacionObjetos = new ArrayList<Nivel>();
		List<Pieza> piezasIdentificacionObjetos = new ArrayList<Pieza>();
		piezasIdentificacionObjetos.add(new Pieza("Respuesta1", "/images/A.jpg"));
		piezasIdentificacionObjetos.add(new Pieza("B", "/images/B.jpg"));
		piezasIdentificacionObjetos.add(new Pieza("C", "/images/C.jpg"));
		piezasIdentificacionObjetos.add(new Pieza("D", "/images/D.jpg"));
		piezasIdentificacionObjetos.add(new Pieza("Respuesta1","/images/Pregunta1.jpg"));

		
		Nivel n1IdenObj = new Nivel(1, piezasIdentificacionObjetos);
		nivelesIdentificacionObjetos.add(n1IdenObj);
		
		Juego i = new IdentificacionObjetos("Identificacion de Objetos", nivelesIdentificacionObjetos);
		this.juegos.add(i);
		
		this.usuarios.add(new Usuario("leo","leo","leo"));
	
		
	}

	public void login(String userId) throws ValidationException {
		Usuario u = buscarUsuario(userId);
		if (u != null) {
			this.usuarioActual = u;
		} else {
			throw new ValidationException("El usuario no existe!");
		}
	}

	private Usuario buscarUsuario(String userId) {
		Usuario result = null;
		for (Iterator<Usuario> it = this.usuarios.iterator(); (it.hasNext() && result == null);) {
			Usuario u = it.next();
			if (u.getId().equals(userId)) {
				result = u;
			}
		}
		return result;
	}

	private boolean usuarioValido(String userId) {
		return buscarUsuario(userId) != null;
	}

	public void crearUsuario(String userId, String nombre, String apellido) throws ValidationException {
		if (!usuarioValido(userId)) {
			this.usuarios.add(new Usuario(userId, nombre, apellido));
		} else {
			throw new ValidationException("El usuario ya existe!");
		}
	}

	public void modificarUsuario(String nombre, String apellido) {
		this.usuarioActual.setNombre(nombre);
		this.usuarioActual.setApellido(apellido);
	}

	/**
	 * Borra el usuario de la coleccion de usuarios y tambien su ranking para
	 * todos los juegos
	 * 
	 * @param userId
	 */
	public void eliminarUsuario() {
		this.ranking.eliminarRankingUsuario(this.usuarioActual.getId());
		this.usuarios.remove(this.usuarioActual);
	}

	public void logout() {
		this.usuarioActual = null;
	}

	public List<Juego> getJuegos() {
		return juegos;
	}

	public void cargarJuego(String juegoId) {
		this.juegoActual = buscarJuego(juegoId);
		ItemRanking ir = this.ranking.obtenerRankingPorUsuarioJuego(this.usuarioActual.getId(),
				this.juegoActual.getNombre());
		if (ir != null) {
			juegoActual.cargarNivel(ir.getNivel().getNumero());
		} else {
			juegoActual.cargarNivel(1);
		}

	}

	private Juego buscarJuego(String juegoId) {
		Juego result = null;
		for (Iterator<Juego> it = this.juegos.iterator(); (it.hasNext() && result == null);) {
			Juego aux = it.next();
			if (aux.getNombre().equals(juegoId)) {
				result = aux;
			}
		}
		return result;
	}

	public void procesarAccion(String origen, String destino) throws InvalidAccionException {
		this.juegoActual.procesarAccion(origen, destino);
	}

	public boolean nivelCompleto() {
		return this.juegoActual.nivelCompleto();
	}

	public Juego getJuegoActual() {
		return juegoActual;
	}

	public Usuario getUsuarioActual() {
		return this.usuarioActual;
	}

	public void actualizarJuegoNivelCompleto() {
		this.juegoActual.incrementarNivelActual();
		this.ranking.actualizarRankingJuego(this.usuarioActual, this.juegoActual);
	}
}
