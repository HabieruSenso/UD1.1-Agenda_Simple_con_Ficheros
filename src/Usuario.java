import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable {

//	private static final long serialVersionUID = 1L;
	private String notas;
	private Date fechaNota;
	
	public Usuario(String notas, Date fechaNota) {
		this.notas = notas;
		this.fechaNota = fechaNota;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public Date getfechaNota() {
		return fechaNota;
	}

	public void setfechaNota(Date fechaNota) {
		this.fechaNota = fechaNota;
	}
}
