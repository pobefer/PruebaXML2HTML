package main;

import java.io.IOException;
import java.util.List;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import model.Ruta;

public class main {

	public static void main(String[] args) throws IOException, UnirestException {
		Parser p = new Parser();
		List<Ruta> rutas = p.a();
		HtmlGenerator h = new HtmlGenerator();
		String source = h.main("index.html", rutas);
		String response = null;
		HttpResponse<JsonNode> uniResponse = Unirest.post("https://validator.w3.org/nu")
			    .header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.101 Safari/537.36")
			    .header("Content-Type", "text/html; charset=UTF-8")
			    .queryString("out", "gnu")
			    .body(source)
			    .asJson();
		response = uniResponse.getStatusText();
		System.out.println(response);
		System.out.println(uniResponse.getStatus());
	  }
		

	

}
