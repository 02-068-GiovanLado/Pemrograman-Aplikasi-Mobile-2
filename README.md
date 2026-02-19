# 📰 NewsFeed Simulator

Aplikasi simulasi news feed real-time yang dibangun menggunakan **Kotlin Multiplatform Compose** dengan tampilan clean dan profesional.

---

## Prasyarat

Pastikan sistem kamu sudah memiliki:

| Tools | Versi Minimum |
|---|---|
| JDK | 17+ |
| Android Studio / IntelliJ IDEA | Terbaru (2023.x+) |
| Kotlin | 1.9+ |
| Gradle | 8.x |

---

## Struktur Proyek

```
NewsFeed/
├── composeApp/
│   └── src/
│       └── commonMain/
│           └── kotlin/
│               └── org/example/project/
│                   └── App.kt          ← UI utama & ViewModel
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

---

## Cara Menjalankan

### 1. Clone Repository

```bash
git clone https://github.com/username/newsfeed-simulator.git
cd newsfeed-simulator
```

### 2. Buka di IntelliJ IDEA / Android Studio

- Buka **IntelliJ IDEA** atau **Android Studio**
- Pilih **File → Open** → arahkan ke folder proyek
- Tunggu Gradle sync selesai

### 3. Jalankan Aplikasi Desktop

```bash
./gradlew :composeApp:run
```

Atau lewat IDE:

- Buka panel **Gradle** di kanan
- Navigasi ke `composeApp → Tasks → compose desktop → run`
- Klik dua kali `run`

### 4. Jalankan di Android (opsional)

- Hubungkan perangkat Android atau jalankan emulator
- Pilih konfigurasi `composeApp` di toolbar atas
- Klik tombol **Run ▶**

---

## Cara Menggunakan Aplikasi

1. **Start** — Klik tombol `Start` untuk memulai simulasi feed berita. Berita baru akan muncul otomatis setiap 2 detik.
2. **Filter Kategori** — Klik tombol kategori (`Technology` / `Sports`) untuk mengganti filter berita yang ditampilkan.
3. **Baca Detail** — Klik salah satu kartu berita untuk memuat dan menampilkan detail berita di bagian bawah.
4. **Stop** — Klik tombol `Stop` untuk menghentikan simulasi dan membersihkan daftar berita.

---

## Fitur

- Simulasi news feed real-time menggunakan **Kotlin Flow**
- Filter berita berdasarkan kategori
- Penghitung berita yang sudah dibaca
- Tampilan detail berita dengan async loading
- UI responsif dengan **Jetpack Compose Multiplatform**

---

## Dependensi Utama

```kotlin
// build.gradle.kts
implementation(compose.runtime)
implementation(compose.foundation)
implementation(compose.material3)
implementation(compose.ui)
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.x")
```

---

## Troubleshooting

**Gradle sync gagal**
Pastikan JDK 17+ sudah dikonfigurasi di `File → Project Structure → SDK`.

**Aplikasi tidak muncul setelah `run`**
Coba jalankan dengan:
```bash
./gradlew :composeApp:run --stacktrace
```

**Error `Compose compiler version mismatch`**
Pastikan versi Kotlin dan Compose Compiler kompatibel. Lihat [tabel kompatibilitas resmi](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-compatibility-and-versioning.html).

---

## Lisensi

MIT License — bebas digunakan dan dimodifikasi.
