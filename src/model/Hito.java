package model;

import java.util.List;

public class Hito {
	String nombre;
	String descripcion;
	Localizacion localizacion;
	Distancia distancia;
	List<String> galeriaImg;
	List<String> galeriaVideo;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Localizacion getLocalizacion() {
		return localizacion;
	}
	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}
	public Distancia getDistancia() {
		return distancia;
	}
	public void setDistancia(Distancia distancia) {
		this.distancia = distancia;
	}
	public List<String> getGaleriaImg() {
		return galeriaImg;
	}
	public void setGaleriaImg(List<String> galeriaImg) {
		this.galeriaImg = galeriaImg;
	}
	public List<String> getGaleriaVideo() {
		return galeriaVideo;
	}
	public void setGaleriaVideo(List<String> galeriaVideo) {
		this.galeriaVideo = galeriaVideo;
	}
	
	public String toString2() {
		return "Descripcion\n\t"+descripcion + " \n Distancia hito anterior: "+ 
				distancia.getKilometros() +"kms \n \t " + localizacion.getCiudad() + 
				localizacion.getDirección() + "\n";
	}
	
	

}
