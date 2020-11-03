package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import model.Coordenadas;
import model.Distancia;
import model.Duracion;
import model.Hito;
import model.Localizacion;
import model.Ruta;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Parser {
	List<Ruta> a() {
		List<Ruta> rutas= new ArrayList<>();
		try {
			File inputFile = new File("rutas.xml");
			File out = new File("rutas.html");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("RutaTuristica");
			System.out.println("----------------------------");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Ruta ruta = new Ruta();
					Element eElement = (Element) nNode;
					ruta.setNombre(eElement.getAttribute("nombre"));
					ruta.setTipo(eElement.getAttribute("tipo"));
					ruta.setDificultad(eElement.getAttribute("dificaultad"));
					ruta.setPuntuacion(Integer.parseInt(eElement.getAttribute("puntuacion")));
					ruta.setAgencia(eElement.getAttribute("Agencia"));
					ruta.setDescripcion(eElement.getElementsByTagName("Descripcion").item(0).getTextContent());
					Duracion duracion = new Duracion();
					Node nDuracion = eElement
							.getElementsByTagName("Duracion")
							.item(0);
					if (nDuracion.getNodeType() == Node.ELEMENT_NODE) {
						Element eDuracion = (Element) nDuracion;
						duracion.setFecha(eDuracion
								.getElementsByTagName("fecha")
								.item(0).getTextContent());
						duracion.setHora(eDuracion
								.getElementsByTagName("hora")
								.item(0).getTextContent());
						duracion.setTiempo(eDuracion
								.getElementsByTagName("tiempo")
								.item(0).getTextContent());
					}
					ruta.setDuracion(duracion);

					List<String> recomendaciones = new ArrayList<>();
					Node nRecomendaciones = eElement
							.getElementsByTagName("Recomendaciones")
							.item(0);
					if (nRecomendaciones.getNodeType() == Node.ELEMENT_NODE) {
						NodeList recomendacionesList = nRecomendaciones.getChildNodes();
						for (int i = 0; i < recomendacionesList.getLength(); i++) {
							Node r = recomendacionesList.item(i);
							recomendaciones.add(r.getTextContent());
						}
					}
					ruta.setRecomendaciones(recomendaciones);


					List<String> referencias = new ArrayList<>();
					Node nReferencias = eElement
							.getElementsByTagName("Referencias")
							.item(0);
					if (nReferencias.getNodeType() == Node.ELEMENT_NODE) {
						NodeList referenciasList = nReferencias.getChildNodes();
						for (int i = 0; i < referenciasList.getLength(); i++) {
							Node r = referenciasList.item(i);
							referencias.add(r.getTextContent());
						}
					}
					ruta.setReferencias(referencias);

					Localizacion localizacion = new Localizacion();
					Coordenadas coordenadas = new Coordenadas();
					Node nLocalizacion = eElement
							.getElementsByTagName("Localizacion")
							.item(0);
					if (nLocalizacion.getNodeType() == Node.ELEMENT_NODE) {
						Element l = (Element) nLocalizacion;
						localizacion.setCiudad(l
								.getElementsByTagName("Ciudad")
								.item(0).getTextContent());
						localizacion.setDirección(l
								.getElementsByTagName("direccion")
								.item(0).getTextContent());
						Node nCoordenadas = l
								.getElementsByTagName("coordenadas")
								.item(0);
						if (nCoordenadas.getNodeType() == Node.ELEMENT_NODE) {
							Element c = (Element) nCoordenadas;
							coordenadas.setLatiutud(Double.parseDouble(c.getAttribute("lat")));
							coordenadas.setLongitud(Double.parseDouble(c.getAttribute("lon")));
							coordenadas.setAltura(Double.parseDouble(c
									.getElementsByTagName("altura")
									.item(0).getTextContent()));
						}

					}
					localizacion.setCoordenadas(coordenadas);
					ruta.setLocalizacion(localizacion);


					List<Coordenadas> listaCoordenadas = new ArrayList<>();
					Node nCoordenadas = eElement
							.getElementsByTagName("coordenadasRuta")
							.item(0);
					if (nCoordenadas.getNodeType() == Node.ELEMENT_NODE) {
						NodeList coordenadasList = nCoordenadas.getChildNodes();
						for (int i = 0; i < coordenadasList.getLength(); i++) {
							Coordenadas coordenada = new Coordenadas();
							Node r = coordenadasList.item(i);
							if (r.getNodeType() == Node.ELEMENT_NODE) {
								Element coor = (Element) r;
								coordenada.setLatiutud(Double.parseDouble(coor.getAttribute("lat")));
								coordenada.setLongitud(Double.parseDouble(coor.getAttribute("lon")));
								coordenada.setAltura(Double.parseDouble(coor
										.getElementsByTagName("altura")
										.item(0).getTextContent()));
								listaCoordenadas.add(coordenada);
							}
						}
					}
					ruta.setCoordenadas(listaCoordenadas);


					List<Hito> listaHitos = new ArrayList<>();
					Node nHitos = eElement
							.getElementsByTagName("Hitos")
							.item(0);
					if (nHitos.getNodeType() == Node.ELEMENT_NODE) {
						NodeList hitosList = nHitos.getChildNodes();
						for (int i = 0; i < hitosList.getLength(); i++) {
							Hito hito = new Hito();
							Node r = hitosList.item(i);
							if (r.getNodeType() == Node.ELEMENT_NODE) {
								Element eHito = (Element) r;
								hito.setNombre(eHito.getAttribute("nombre"));
								hito.setDescripcion(eHito.getElementsByTagName("Descripcion")
										.item(0).getTextContent());
								Distancia d = new Distancia();
								d.setKilometros(Double.parseDouble(eHito.getElementsByTagName("distancia-hito-anterior")
										.item(0).getTextContent()));
								hito.setDistancia(d);
								Localizacion localizacionHito = new Localizacion();
								Coordenadas coordenadasHito = new Coordenadas();
								Node nLocalizacionHito = eHito
										.getElementsByTagName("Localizacion")
										.item(0);
								if (nLocalizacionHito.getNodeType() == Node.ELEMENT_NODE) {
									Element l = (Element) nLocalizacionHito;
									localizacionHito.setCiudad(l
											.getElementsByTagName("Ciudad")
											.item(0).getTextContent());
									localizacionHito.setDirección(l
											.getElementsByTagName("direccion")
											.item(0).getTextContent());
									Node nCoordenadasHito = l
											.getElementsByTagName("coordenadas")
											.item(0);
									if (nCoordenadasHito.getNodeType() == Node.ELEMENT_NODE) {
										Element c = (Element) nCoordenadasHito;
										coordenadasHito.setLatiutud(Double.parseDouble(c.getAttribute("lat")));
										coordenadasHito.setLongitud(Double.parseDouble(c.getAttribute("lon")));
										coordenadasHito.setAltura(Double.parseDouble(c
												.getElementsByTagName("altura")
												.item(0).getTextContent()));
									}

								}
								localizacionHito.setCoordenadas(coordenadasHito);
								hito.setLocalizacion(localizacionHito);
								
								List<String> galeriaImg = new ArrayList<>();
								Node nGaleriaImg = eHito.getElementsByTagName("Galeria-img")
										.item(0);
								if (nGaleriaImg.getNodeType() == Node.ELEMENT_NODE) {
								NodeList imgList = nGaleriaImg.getChildNodes();
									for (int j = 0; j < imgList.getLength(); j++) {
										Node nImg = imgList.item(j);
										Element eImg = (Element) nImg;
										galeriaImg.add(eImg.getTextContent());
									}
								}
								hito.setGaleriaImg(galeriaImg);
								
							    List<String> galeriaVideo = new ArrayList<>();
								Node nGaleriaVideo = eHito
										.getElementsByTagName("Galeria-video")
										.item(0);
								if (nGaleriaVideo.getNodeType() == Node.ELEMENT_NODE) {
									NodeList VideoList = nGaleriaVideo.getChildNodes();
									for (int j = 0; j < VideoList.getLength(); j++) {
										Node nVideo = VideoList.item(j);
										galeriaVideo.add(nVideo.getTextContent());
									}
								}
								hito.setGaleriaVideo(galeriaVideo);
							
								
								listaHitos.add(hito);
							}
						}
						ruta.setHitos(listaHitos);
					}

					int i = 2;
					i+=2;
					rutas.add(ruta);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rutas;
	}
}
