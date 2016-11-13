package uade.ioo.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ranking {

	private List<ItemRanking> items;

	public Ranking() {
		this.items = new ArrayList<ItemRanking>();
	}

	public List<ItemRanking> obtenerRankingPorJuego(String juegoId) {
		List<ItemRanking> result = new ArrayList<ItemRanking>();
		for (Iterator<ItemRanking> it = items.iterator(); it.hasNext();) {
			ItemRanking ir = it.next();
			if (ir.getJuego().equals(juegoId)) {
				result.add(ir);
			}
		}
		return result;
	}

	public List<ItemRanking> obtenerRankingPorUsuario(String usuarioId) {
		List<ItemRanking> result = new ArrayList<ItemRanking>();
		for (Iterator<ItemRanking> it = items.iterator(); it.hasNext();) {
			ItemRanking ir = it.next();
			if (ir.getUser().getId().equals(usuarioId)) {
				result.add(ir);
			}
		}
		return result;
	}

	public ItemRanking obtenerRankingPorUsuarioJuego(String usuarioId, String juegoId) {
		List<ItemRanking> rank = this.obtenerRankingPorUsuario(usuarioId);
		ItemRanking result = null;
		for (Iterator<ItemRanking> it = rank.iterator(); (it.hasNext() && result == null);) {
			ItemRanking aux = it.next();
			if (aux.getJuego().getNombre().equals(juegoId)) {
				result = aux;
			}
		}
		return result;
	}

	public void actualizarRankingJuego(Usuario user, Juego juego) {
		ItemRanking ir = getItemRanking(user.getId(), juego.getNombre());
		if (ir == null) {
			ir = new ItemRanking(user, juego, juego.getNivelActual());
			this.items.add(ir);
		} else {
			ir.setNivel(juego.getNivelActual());
		}
	}

	private ItemRanking getItemRanking(String userId, String juegoId) {
		ItemRanking result = null;
		for (Iterator<ItemRanking> it = this.items.iterator(); (it.hasNext() && result == null);) {
			ItemRanking aux = it.next();
			if (aux.getJuego().getNombre().equals(juegoId) && aux.getUser().getId().equals(userId)) {
				result = aux;
			}
		}
		return result;
	}

	public void eliminarRankingUsuario(String usuarioId) {
		for (Iterator<ItemRanking> it = this.items.iterator(); it.hasNext();) {
			ItemRanking ir = it.next();
			if (ir.getUser().getId().equals(usuarioId)) {
				it.remove();
			}
		}
	}
}
