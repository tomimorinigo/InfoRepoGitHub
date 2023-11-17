package main;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GitHubApiExercise {
	public static void main(String[] args) {
		
        // URL de la API de GitHub para obtener información de un repositorio público
        String apiUrl = "https://api.github.com/repos/octocat/hello-world";
        
        // Realizar la solicitud HTTP utilizando HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();
        
        HttpResponse<String> response = null;
        
        try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
     
		String json = response.body();
		
		ObjectMapper objectMapper = new ObjectMapper();
		RepositoryInfo repo = null;
		
		try {
			repo = objectMapper.readValue(json, RepositoryInfo.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println(repo);
    }
}
