package model;

import java.util.List;

public class Ruta {
	String nombre;
	String tipo;
	String dificultad;
	int puntuacion;
	String agencia;
	Duracion duracion;
	List<String> recomendaciones;
	Localizacion localizacion;
	List<String> referencias;
	List<Coordenadas> coordenadas;
	List<Hito> hitos;
	private String descripcion;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDificultad() {
		return dificultad;
	}
	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public Duracion getDuracion() {
		return duracion;
	}
	public void setDuracion(Duracion duracion) {
		this.duracion = duracion;
	}
	public List<String> getRecomendaciones() {
		return recomendaciones;
	}
	public void setRecomendaciones(List<String> recomendaciones) {
		this.recomendaciones = recomendaciones;
	}
	public Localizacion getLocalizacion() {
		return localizacion;
	}
	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}
	public List<String> getReferencias() {
		return referencias;
	}
	public void setReferencias(List<String> referencias) {
		this.referencias = referencias;
	}
	public List<Coordenadas> getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(List<Coordenadas> coordenadas) {
		this.coordenadas = coordenadas;
	}
	public List<Hito> getHitos() {
		return hitos;
	}
	public void setHitos(List<Hito> hitos) {
		this.hitos = hitos;
	}
	

	public void setDescripcion(String textContent) {
		this.descripcion = textContent;
	}
	public String toString2() {
		return "Nombre de la ruta\n\t" + nombre + "\n"+ "Tipo\n\t" + tipo + "\n"+ "Dificultad\n\t" + dificultad + "\n"+ "Puntuacion\n\t" + puntuacion
				+ "\n"+ "Agencia\n\t" + agencia + "\n"+ "Duracion\n\t" + duracion.getTiempo()+" horas" + "\n"+ "Descripcion\n\t" + descripcion+"\n";
	}
	
	
	
	
}
