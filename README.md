## AI-Powered Real Estate Listing API

Bu proje, Spring Boot ile geliştirilmiş, kullanıcıların emlak ilanları oluşturabildiği,filtreleyebildiği ve yönetebildiği bir RESTful API'dir. 
Hugging Face modelleri ile ilan içeriklerini analiz ederek **toksik, spam veya dolandırıcılık içeren içerikleri otomatik olarak engeller**.
Proje JWT ile güvenlidir ve canlı olarak Render.com üzerinde yayındadır. 

## Canlı API Linki:
https://real-estate-ai-api.onrender.com

## Özellikler
 
-  Kullanıcı kayıt & giriş (JWT Authentication)
-  Emlak İlanı oluşturma, güncelleme, silme, listeleme
-  Şehir, fiyat, oda, m², kategori ve tür bazlı filtreleme
-  Base64 ile görsel yükleme
-  Kategori oluşturma ve listeleme
-  Favori ilan sistemi
-  Hugging Face ile içerik güvenlik kontrolü
-  JWT tabanlı kimlik doğrulama
-  PostgreSQL veritabanı

## İçerik Kontrolü (AI Destekli)
İlan açıklamaları, Hugging Face’in unitary/unbiased-toxic-roberta modeli ile otomatik olarak denetlenir. Bu sayede: 
Uygunsuz, toksik, nefret söylemi içeren ilanlar yayınlanmadan engellenir.
Dolandırıcılık ihtimali olan ilanlar da anahtar kelime kontrolü ile tespit edilir.

## Güvenlik & Doğrulama
Hugging Face API, Bearer Token ile güvenli şekilde çağrılır.
Model sonuçları label ve score ile değerlendirilir.
Belirli etiketlerde (toxicity, hate, threat) skor %70 üzerindeyse ilan reddedilir.

## Deployment
Render.com üzerinde canlı
.env secrets ile yapılandırıldı
Her git push sonrası otomatik deploy
Bu kod, veritabanında Türkçe karakter sorunlarını çözmek için normalize edilmiş filtreleme yapıyor. 
unaccent fonksiyonunu PostgreSQL'de etkinleştirerek kullanıcı "İstanbul" da yazsa "istanbul" da yazsa sonuç dönmesini garanti altına alıyor. 
Specification kullanarak da sorguları dinamik ve okunabilir tutuyor.


## Demo Videosu 

https://github.com/user-attachments/assets/d18b9007-9ad2-4920-8197-475e2cbbca80


## Canlı API Linki

https://real-estate-ai-api.onrender.com/

## Teknolojiler
Java 17
Spring Boot
Spring Security (JWT)
PostgreSQL
Hugging Face API
Render.com (Deployment)

## Nasıl Çalıştırılır?
PostgreSQL veritabanı kur ve yapılandır
.env dosyasına Hugging Face API key ve DB bilgilerini ekle
Projeyi başlat:
bash
./mvnw spring-boot:run

## Örnek Kullanım
Giriş / Kayıt
POST /api/auth/register
json
{
  "email": "user@example.com",
  "password": "password123"
}
POST /api/auth/authenticate
json
{
  "email": "user@example.com",
  "password": "password123"
}

## İlan Oluşturma
POST /api/properties
http
KopyalaDüzenle
Headers: Authorization: Bearer <JWT>
json
--
{
  "title": "Geniş ve Ferah Daire",
  "description": "Merkezi konumda, huzurlu bir yaşam.",
  "city": "İstanbul",
  "district": "Kadıköy",
  "price": 2500000,
  "roomCount": 3,
  "squareMeters": 120,
  "categoryId": 1,
  "listingType": "SALE"
}

## İlan Filtreleme
POST /api/properties/filter
json
--
{
  "city": "İstanbul",
  "minPrice": 1000000,
  "maxPrice": 5000000,
  "roomCount": 3
}

## Görsel Ekleme
POST /api/images/upload/{propertyId}
http
KopyalaDüzenle
Headers: Authorization: Bearer <JWT>
json
--
{
  "base64": "data:image/png;base64,iVBORw0KGgoAAAANS..."
}

Örnek Engellenecek Açıklama:
json
{
  "title": "Yatırım Fırsatı Kaçmaz!",
  "description": "Sadece bugün için 500.000 TL'ye lüks villa! İletişim için hemen WhatsApp'tan ulaşın, detaylar gizli. Tapu sonradan verilecek"
} Kullanılan Model:
unitary/unbiased-toxic-roberta 🔗 https://huggingface.co/unitary/unbiased-toxic-roberta

## AI-Powered Real Estate Listing API

This project is a RESTful API built with Spring Boot that allows users to create, filter, and manage real estate listings. It integrates Hugging Face models to analyze listing content and automatically block toxic, spam, or fraudulent entries. The API is secured with JWT authentication and is live on Render.com.

## Live API:
https://real-estate-ai-api.onrender.com

## Features
User registration & login (JWT Authentication)
Create, update, delete, and list real estate listings
Advanced filtering by:
City
Price range
Room count
Square meters
Category & listing type
Image upload using Base64
Category creation and listing
Favorite listings system
AI-powered content moderation (Hugging Face)
JWT-based security
PostgreSQL database

## AI Content Moderation

Listing descriptions are automatically analyzed using the unitary/unbiased-toxic-roberta model from Hugging Face.

This ensures:

Toxic, offensive, or hate speech content is blocked
Potential scam listings are detected via keyword analysis
Moderation Logic
The Hugging Face API is called securely using a Bearer Token
Model outputs include labels and confidence scores
Listings are rejected if scores exceed 70% for:
Toxicity
Hate
Threat

## Security & Authentication
JWT-based authentication system
Secure Hugging Face API integration via Bearer Token
Protected endpoints for authorized users only

## Deployment
Deployed on Render.com
Environment variables managed via .env
Automatic deployment on every Git push
Additional Notes
PostgreSQL unaccent function is enabled to handle Turkish characters
(e.g., "İstanbul" and "istanbul" return the same results)
Dynamic and readable queries are implemented using Spring Specifications

## Demo

demo-real-estate-api.mp4

## Technologies Used
Java 17
Spring Boot
Spring Security (JWT)
PostgreSQL
Hugging Face API
Render.com (Deployment)
⚙️ Getting Started
Set up and configure PostgreSQL
Add your Hugging Face API key and database credentials to .env
Run the project:
./mvnw spring-boot:run

Example Usage
Authentication

Register

POST /api/auth/register
{
  "email": "user@example.com",
  "password": "password123"
}

Login

POST /api/auth/authenticate
{
  "email": "user@example.com",
  "password": "password123"
}
Create Listing
POST /api/properties
Headers: Authorization: Bearer <TOKEN>

{
  "title": "Spacious and Bright Apartment",
  "description": "Peaceful living in a central location.",
  "city": "Istanbul",
  "district": "Kadikoy",
  "price": 2500000,
  "roomCount": 3,
  "squareMeters": 120,
  "categoryId": 1,
  "listingType": "SALE"
}
Filter Listings
POST /api/properties/filter
{
  "city": "Istanbul",
  "minPrice": 1000000,
  "maxPrice": 5000000,
  "roomCount": 3
}

Upload Image
POST /api/images/upload/{propertyId}
Headers: Authorization: Bearer <TOKEN>

{
  "base64": "data:image/png;base64,iVBORw0KGgoAAAANS..."
}
Example of Blocked Content
{
  "title": "Unmissable Investment Opportunity!",
  "description": "Luxury villa for only 500,000 TL today! Contact via WhatsApp immediately, details are hidden. Title deed will be provided later."
}
## Model Used

 https://huggingface.co/unitary/unbiased-toxic-roberta



