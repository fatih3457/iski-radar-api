package com.iskiradar.iskiradarapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iskiradar.iskiradarapi.dto.ApiResponse;
import com.iskiradar.iskiradarapi.dto.DamData;
import com.iskiradar.iskiradarapi.dto.OverviewData; // Yeni DTO'yu import ediyoruz
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class DamService {

    private List<DamData> damDataCache = Collections.emptyList();
    private String lastUpdateTime = "Bilinmiyor";

    @PostConstruct
    public void loadDataFromFile() {
        System.out.println("Uygulama başlangıcında mock verisi dosyadan okunuyor...");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ClassPathResource resource = new ClassPathResource("mock-dams.json");
            InputStream inputStream = resource.getInputStream();

            ApiResponse<DamData> apiResponse = objectMapper.readValue(inputStream, new TypeReference<ApiResponse<DamData>>() {});

            if (apiResponse != null && apiResponse.getData() != null) {
                this.damDataCache = apiResponse.getData();

                // Veri yüklendiğinde güncelleme zamanını kaydedelim.
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                sdf.setTimeZone(TimeZone.getTimeZone("Europe/Istanbul"));
                this.lastUpdateTime = sdf.format(new Date());

                System.out.println(this.damDataCache.size() + " adet baraj verisi dosyadan başarıyla yüklendi. Son Güncelleme: " + this.lastUpdateTime);
            }
        } catch (Exception e) {
            System.err.println("Mock verisi okunurken hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Bu metot artık sadece önbellekteki veriyi döndürür.
    public List<DamData> getDamData() {
        return this.damDataCache;
    }

    // YENİ EKLENEN METOT: Ortalama ve diğer özet bilgileri hesaplar.
    public OverviewData getOverviewData() {
        OverviewData overview = new OverviewData();

        if (damDataCache == null || damDataCache.isEmpty()) {
            overview.setBarajSayisi(0);
            overview.setGenelDolulukOraniOrtalamasi(0.0);
            overview.setSonGuncellemeZamani("Veri Yok");
            return overview;
        }

        double average = damDataCache.stream()
                .mapToDouble(dam -> {
                    try {
                        // Virgül yerine nokta olmasını sağlayalım
                        String correctedRate = dam.getDolulukOrani().replace(',', '.');
                        return Double.parseDouble(correctedRate);
                    } catch (NumberFormatException e) {
                        // Eğer oran sayıya çevrilemezse, ortalamaya dahil etme (0 kabul et)
                        return 0.0;
                    }
                })
                .average()
                .orElse(0.0);

        overview.setBarajSayisi(damDataCache.size());
        overview.setGenelDolulukOraniOrtalamasi(average);
        overview.setSonGuncellemeZamani(this.lastUpdateTime);

        return overview;
    }
}