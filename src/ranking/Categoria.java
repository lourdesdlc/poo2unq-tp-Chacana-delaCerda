package ranking;

public class Categoria {
	private String nombre;
	private TipoRankeable tipoRankeable;

	public Categoria(String nombre, TipoRankeable tipoRankeable) {
		this.nombre = nombre;
		this.tipoRankeable = tipoRankeable;
	}

	public Categoria() {
	}

	public TipoRankeable getTipoRankeable() {
		return tipoRankeable;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean perteneceATipoRankeable(TipoRankeable tipoRankeable) {
		return this.tipoRankeable == (tipoRankeable);
	}
}
