package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import model.Coordenadas;
import model.Hito;
import model.Ruta;

public class HtmlGenerator {

	String main(String file, List<Ruta> rutas) throws IOException {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		String response = generateHtml(rutas);
		writer.write(response);

		writer.close();
		return response;
	}

	private String generateHtml(List<Ruta> rutas) throws IOException {
		String html = "";
		html += "<!DOCTYPE HTML>\n";
		html += "<html lang='es'>\n";
		html += "<head>\n";
		html += "<link rel='stylesheet' type='text/css' href='estilo.css' />\n";
		html += "<meta charset='UTF-8'>\n";
		html += "<meta name='description' content='SEW WEB PAGE'>\n";
		html += "<meta name='keywords' content='XML'>\n";
		html += "<meta name='author' content='FERNANDO DE LA TORRE CUEVA- UO245182'>\n";
		html += "<title>XML</title>\n";
		html += "</head>\n";
		html += "<body>\n";
		html += "<h1>RUTAS TURISTICAS</h1>\n";
		int i = 0;
		
		for(Ruta ruta: rutas) {
			i++;
			
			BufferedWriter writer;
			writer = new BufferedWriter(new FileWriter("ruta"+i+".kml"));
			writer.write(generateKML(ruta));
			writer.close();
			html += "<section>\n";
			html +="<h2>"+ruta.getNombre()+"</h2>\n";
			html += "<div class='inline'>\r\n";
			html += "<div class='grafica'>\n";
			html += "<img src='"+ generarSVG(ruta) +"' alt='imagen de los hitos 1' />\r\n";
			html += "</div>\r\n";
			html += "<pre>" + ruta.toString2() + "</pre>\n";
			html += "</div>\r\n";
			html += "</section>\n";
			html += "<section>\n";
			html += "<h2>HITOS DE LA RUTA</h2>\n";
			
			
			for(Hito h:ruta.getHitos()) {
				html += printHito(h);
			}
			html += "</section>\n";
		}
		
		html += "\n";
		
		html += "</body>\n";
		html += "</html>\n";



		return html;
	}

	private String generarSVG(Ruta ruta) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(ruta.getNombre().replace(" ", "") +".svg"));
		
		String svg = "<svg version='1.1' xmlns='http://www.w3.org/2000/svg'\r\n"
				+ "     width='700' height='720' viewBox='-10 -10 600 600'\r\n"
				+ "     style='background-color: lightgray'>\r\n"
				+ "  <line x1='0' y1='0' x2='0' y2='500' stroke='black' stroke-width='1' />\r\n"
				+ "  <line x1='0' y1='500' x2='510' y2='500' stroke='black' stroke-width='1' />\r\n"
				+ generarCoordenadasSVG(ruta) 
				+ "</svg>";
		writer.write(svg);

		writer.close();
		return ruta.getNombre().replace(" ", "") +".svg";
	}

	private String generarCoordenadasSVG(Ruta ruta) {
		double mayor = ruta.getCoordenadas().stream().map(x -> x.getAltura()).max(Comparator.naturalOrder()).get();
		int max = (int) mayor;
		int altura = 0;
		String svg ="";
		int x = 5;
		for(Coordenadas c : ruta.getCoordenadas()) {
			svg+= "<line x1='"+(x-1)+"' y1='"+(500-((int)(altura*500/max)))+"' x2='"+x+"' y2='"+
					(500-((int)c.getAltura()*500/max))+"' stroke='red' stroke-width='1' />\r\n";
			x++;
			altura = (int)c.getAltura();
			
			for(Hito h: ruta.getHitos()) {
				if(c.getLatiutud() == h.getLocalizacion().getCoordenadas().getLatiutud() &&
						c.getLongitud() == h.getLocalizacion().getCoordenadas().getLongitud()){
					svg+="<text text-anchor='middle' transform='translate("+(x+(h.getNombre().length()*2))+","+(505+(h.getNombre().length()*3)+") rotate(45)'>"+h.getNombre())+"</text>\n";
					svg+= "<circle cx='"+x+"' cy='"+500+"' r='2' stroke='red' stroke-width='2' fill='red' />\n";
					svg+= "<line x1='"+x+"' y1='"+500+"' x2='"+x+"' y2='"+(500-((int)c.getAltura()*500/max))+"' stroke='green' stroke-width='1' />\n";
				}
			}
			
		}
		return svg;
	}

	private String printHito(Hito h) {
		String hito = "";
		
		hito += "<div class='hito'>\n";
		hito+= "<h3>"+h.getNombre()+"</h3>\n";
		hito += "<pre>" + h.toString2() + "</pre>\n";
		for(String img : h.getGaleriaImg()) {
			hito+= "<img src='"+ img +"' alt='imagen de los hitos 1' />";
		}
		for(String video : h.getGaleriaVideo()) {
			hito+= "<video controls>\r\n"
					+ "<source src='"+ video +"' type='video/mp4'>\r\n"
					+ "Your browser does not support the video tag.\r\n"
					+ "</video>\n";
		}
		hito += "</div>\n";
		return hito;
	}

	private String generateKML(Ruta ruta) {
		String kml ="";
		kml += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
				+ "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\r\n"
				+ "<Document>\r\n"
				+ "<Placemark>\r\n"
				+ "<name>N1612080.LOG</name>\r\n"
				+ "<LineString>\r\n"
				+ "<extrude>1</extrude>\r\n"
				+ "<tessellate>1</tessellate>\r\n"
				+ "<coordinates>";
		for(Coordenadas c: ruta.getCoordenadas()) {
			kml += ""+ c.getLongitud()+"," + c.getLatiutud()+ "," + 0.0 + "\r\n";
		}
		kml+= "</coordinates>\r\n"
				+ "<altitudeMode>relativeToGround</altitudeMode>\r\n"
				+ "</LineString>\r\n"
				+ "<Style> id='lineaRoja'>\r\n"
				+ "<LineStyle>\r\n"
				+ "<color>#ff0000ff</color>\r\n"
				+ "<width>5</width>\r\n"
				+ "</LineStyle>\r\n"
				+ "</Style>\r\n"
				+ "</Placemark>\r\n"
				+ "</Document>\r\n"
				+ "</kml>";
		
		return kml;
	}

}
