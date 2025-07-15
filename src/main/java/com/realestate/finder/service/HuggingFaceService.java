package com.realestate.finder.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class HuggingFaceService {

	@Value("${huggingface.api.key}")
	private String apiKey;

	private static final String API_URL = "https://api-inference.huggingface.co/models/unitary/unbiased-toxic-roberta";

	public boolean isTextAppropriate(String text) {
		try {
			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + apiKey);
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			Map<String, Object> body = new HashMap<>();
			body.put("inputs", text);

			HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
			ResponseEntity<List> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, List.class);
			System.out.println("🔍 Hugging Face Response: " + response.getBody());

			List<Map<String, Object>> result = (List<Map<String, Object>>) ((List<?>) response.getBody()).get(0);
			for (Map<String, Object> labelScore : result) {
				String label = (String) labelScore.get("label");
				Double score = (Double) labelScore.get("score");

				if ((label.equalsIgnoreCase("toxicity") || label.equalsIgnoreCase("hate")
						|| label.equalsIgnoreCase("threat")) && score > 0.7) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			System.out.println("❌ Hugging Face API error: " + e.getMessage());
			return true;
		}
	}

	public boolean containsScamKeywords(String text) {
		List<String> scamKeywords = Arrays.asList("kapora", "noter gerekmez", "hemen gönder", "havale", "peşin",
				"elden ödeme", "sözleşme yok", "resmiyet gerekmez", "resmi değil");

		String lowerText = text.toLowerCase();
		return scamKeywords.stream().anyMatch(lowerText::contains);
	}
}
