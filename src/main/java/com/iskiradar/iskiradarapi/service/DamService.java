package com.iskiradar.iskiradarapi.service;

import com.iskiradar.iskiradarapi.dto.ApiResponse;
import com.iskiradar.iskiradarapi.dto.DamData;
import com.iskiradar.iskiradarapi.dto.DamGraphData;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@Service
public class DamService {

    private static final String ISKI_API_URL = "https://iskiapi.iski.istanbul/api/iski/baraj/listesi/v2";
    // Önbellek hala burada duruyor ama şimdilik doğrudan kullanmıyoruz.
    private List<DamData> damDataCache = Collections.emptyList();

    // @PostConstruct'ı GEÇİCİ OLARAK DEVRE DIŞI BIRAKTIK.
    // @PostConstruct
    // public void initialDataFetch() { ... }

    // @Scheduled'ı GEÇİCİ OLARAK DEVRE DIŞI BIRAKTIK.
    // @Scheduled(cron = "0 0 * * * ?")
    // public void scheduledDataFetch() { ... }


    // Bu metodu, artık önbellekten değil, her seferinde canlı veri çekecek şekilde güncelledik.
    public List<DamData> getDamData() {
        System.out.println("/api/dams isteği geldi. İSKİ'den canlı veri çekiliyor...");

        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();

            String bearerToken = "Bearer 53830814b24551a420492dd5c3a110213f21d95af47c659af2b12b5ec48424223df509eac54dcdfc0b1bcfcc7d8ab59aea9d6f18450e47fb2127b55dbac65367cfbdabb6218bedea69dc00f4e9dc7bb2eb13df54e673239968b39e9f42aaf46bccf9092985930e5119f05527bb7d9aeb6e8ba0d24d4ddde22bb8d3b034c2778e";
            headers.set("Authorization", bearerToken);
            headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36");
            headers.set("Referer", "https://iski.istanbul/");
            headers.set("Origin", "https://iski.istanbul");
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            // Gelen yanıtı ApiResponse<DamData> olarak bekliyoruz
            ParameterizedTypeReference<ApiResponse<DamData>> responseType =
                    new ParameterizedTypeReference<ApiResponse<DamData>>() {};

            ResponseEntity<ApiResponse<DamData>> response = restTemplate.exchange(
                    ISKI_API_URL,
                    HttpMethod.GET,
                    entity,
                    responseType
            );

            ApiResponse<DamData> apiResponse = response.getBody();

            if (apiResponse != null && apiResponse.getData() != null) {
                System.out.println("Veri başarıyla çekildi ve istemciye gönderiliyor.");
                return apiResponse.getData();
            }
        } catch (Exception e) {
            System.err.println("Canlı /api/dams verisi çekilirken hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }

        // Hata olursa veya veri gelmezse boş liste döndür.
        return Collections.emptyList();
    }


    // Grafik metodu da canlı veri çekecek şekilde ayarlı.
    public List<DamGraphData> getDamGraphData(String damName) {
        String apiDamName = mapDamNameToApiName(damName);
        String apiUrl = "https://iskiapi.iski.istanbul/api/iski/baraj/sonOnDortGunDoluluk/v2?barajAdi=" + apiDamName;

        RestTemplate restTemplate = new RestTemplate();
        System.out.println("Grafik verisi çekiliyor: " + apiUrl);

        try {
            HttpHeaders headers = new HttpHeaders();

            String bearerToken = "Bearer 53830814b24551a420492dd5c3a110213f21d95af47c659af2b12b5ec48424223df509eac54dcdfc0b1bcfcc7d8ab59aea9d6f18450e47fb2127b55dbac65367cfbdabb6218bedea69dc00f4e9dc7bb2eb13df54e673239968b39e9f42aaf46bccf9092985930e5119f05527bb7d9aeb6e8ba0d24d4ddde22bb8d3b034c2778e";
            headers.set("Authorization", bearerToken);
            headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36");
            headers.set("Referer", "https://iski.istanbul/");
            headers.set("Origin", "https://iski.istanbul");
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            ParameterizedTypeReference<ApiResponse<DamGraphData>> responseType =
                    new ParameterizedTypeReference<ApiResponse<DamGraphData>>() {};

            ResponseEntity<ApiResponse<DamGraphData>> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    entity,
                    responseType
            );

            ApiResponse<DamGraphData> apiResponse = response.getBody();

            if (apiResponse != null && apiResponse.getData() != null) {
                return apiResponse.getData();
            }
        } catch (Exception e) {
            System.err.println("Grafik verisi çekilirken hata: " + e.getMessage());
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    // Haritalama metodunun tam hali
    private String mapDamNameToApiName(String damName) {
        if (damName.equalsIgnoreCase("Büyükçekmece")) return "BCekmece";
        if (damName.equalsIgnoreCase("Ömerli")) return "Omerli";
        if (damName.equalsIgnoreCase("Pabuçdere")) return "Pabucdere";
        if (damName.equalsIgnoreCase("Sazlıdere")) return "Sazlidere";
        if (damName.equalsIgnoreCase("Terkos")) return "Terkos";
        if (damName.equalsIgnoreCase("Darlık")) return "Darlik";
        if (damName.equalsIgnoreCase("Alibey")) return "Alibey";
        if (damName.equalsIgnoreCase("Kazandere")) return "Kazandere";
        if (damName.equalsIgnoreCase("Elmalı")) return "Elmali";
        if (damName.equalsIgnoreCase("Istrancalar")) return "Istrancalar";

        // Varsayılan olarak, Türkçe karakterleri basitleştirip baş harfi büyük yapalım.
        String cleanedName = damName.replaceAll("ı", "i")
                .replaceAll("İ", "I")
                .replaceAll("ğ", "g")
                .replaceAll("Ğ", "G")
                .replaceAll("ü", "u")
                .replaceAll("Ü", "U")
                .replaceAll("ş", "s")
                .replaceAll("Ş", "S")
                .replaceAll("ö", "o")
                .replaceAll("Ö", "O")
                .replaceAll("ç", "c")
                .replaceAll("Ç", "C");
        return cleanedName.substring(0, 1).toUpperCase() + cleanedName.substring(1);
    }
}

// String bearerToken = "Bearer 53830814b24551a420492dd5c3a110213f21d95af47c659af2b12b5ec48424223df509eac54dcdfc0b1bcfcc7d8ab59aea9d6f18450e47fb2127b55dbac65367cfbdabb6218bedea69dc00f4e9dc7bb2eb13df54e673239968b39e9f42aaf46bccf9092985930e5119f05527bb7d9aeb6e8ba0d24d4ddde22bb8d3b034c2778e";            headers.set("Authorization", bearerToken);
