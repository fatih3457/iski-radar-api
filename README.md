**# İSKİ Radar API 🚀

![Java](https://img.shields.io/badge/Java-17-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green.svg)
![Maven](https://img.shields.io/badge/Maven-4.0.0-orange.svg)
![AWS](https://img.shields.io/badge/AWS-Elastic_Beanstalk-yellow.svg)
![CI/CD](https://img.shields.io/badge/CI/CD-GitHub_Actions-lightgrey.svg)

Bu proje, **İSKİ Radar** adlı Full Stack projenin **Backend** katmanıdır. Temel amacı, İSKİ'nin kamuya açık baraj doluluk verilerini periyodik olarak çekmek, işlemek ve bu verileri [İSKİ Radar Mobil Uygulaması](https://github.com/fatih3457/iski_radar_mobile) tarafından kullanılmak üzere temiz bir REST API üzerinden sunmaktır.

## 🌟 Projenin Amacı

Bu proje, aşağıdaki hedeflere ulaşmak için geliştirilmiştir:
-   Spring Boot kullanarak sağlam ve ölçeklenebilir bir REST API oluşturma becerisini göstermek.
-   Harici bir API'den veri çekme, işleme ve önbelleğe alma (caching) mekanizmalarını uygulamak.
-   Uygulamayı **AWS (Amazon Web Services)** üzerinde canlı bir ortama dağıtma (deployment) tecrübesi kazanmak.
-   **GitHub Actions** kullanarak otomatik bir **CI/CD (Sürekli Entegrasyon / Sürekli Dağıtım)** pipeline'ı kurmak.

## ✨ Özellikler

-   **Otomatik Veri Güncelleme:** `@Scheduled` anotasyonu kullanılarak, İSKİ API'sinden baraj verileri her saat başı otomatik olarak çekilir ve önbelleğe alınır. Bu, harici API üzerindeki yükü minimize eder ve performansı artırır.
-   **REST API Endpoint'leri:**
    -   `GET /api/dams`: Tüm barajların güncel doluluk oranlarını ve temel bilgilerini liste olarak döndürür.
    -   `GET /api/dams/{damName}/graph`: Belirtilen bir barajın son 14 günlük doluluk oranı verilerini, grafik çizimi için uygun formatta döndürür.
-   **Canlı Dağıtım:** Proje, **AWS Elastic Beanstalk** üzerinde canlı olarak çalışmaktadır.
-   **Otomatik CI/CD:** `main` dalına yapılan her `push` işlemi, GitHub Actions aracılığıyla projenin otomatik olarak test edilmesini, paketlenmesini ve AWS Elastic Beanstalk'e dağıtılmasını tetikler.

## 🛠️ Kullanılan Teknolojiler

-   **Dil:** Java 17
-   **Framework:** Spring Boot 3.x
-   **Bağımlılık Yönetimi:** Maven
-   **Kütüphaneler:**
    -   `Spring Web`: REST API ve web yetenekleri için.
    -   `Spring Scheduler`: Zamanlanmış görevler için (`@Scheduled`).
    -   `RestTemplate`: Harici API'lere HTTP istekleri yapmak için.
    -   `Jackson`: JSON verilerini Java nesnelerine ve tersine dönüştürmek için.
-   **Bulut Platformu:** AWS Elastic Beanstalk
-   **CI/CD:** GitHub Actions

## 🚀 Projeyi Lokalde Çalıştırma

1.  **Projeyi Klonlayın:**
    ```bash
    git clone https://github.com/kullaniciadin/iski-radar-api.git
    cd iski-radar-api
    ```
2.  **Gerekli Ayarları Yapın:**
    -   `DamService.java` sınıfı içinde, İSKİ API'sine erişim için geçerli bir `Bearer Token` gerekmektedir. Bu token, `iski.istanbul` sitesinin ağ istekleri incelenerek elde edilebilir.
3.  **Projeyi Çalıştırın:**
    -   Projeyi bir IDE (IntelliJ IDEA, Eclipse vb.) üzerinden açın ve çalıştırın.
    -   Veya Maven kullanarak komut satırından çalıştırın:
    ```bash
    mvn spring-boot:run
    ```
4.  **API'yi Test Edin:**
    -   Uygulama başladıktan sonra, `http://localhost:8090/api/dams` adresine bir GET isteği atarak baraj listesini alabilirsiniz.

## 📱 Frontend Projesi

Bu backend servisinin verilerini kullanan Flutter mobil uygulamasına göz atmak için:
➡️ **[İSKİ Radar Mobil Uygulama Deposu](https://github.com/fatih3457/iski_radar_mobile)****
