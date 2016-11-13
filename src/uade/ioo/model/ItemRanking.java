package uade.ioo.model;

public class ItemRanking {

	private Usuario user;
	private Juego juego;
	private Nivel nivel;

	public ItemRanking(Usuario user, Juego juego, Nivel nivel) {
		this.user = user;
		this.juego = juego;
		this.nivel = nivel;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
}
