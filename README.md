# 📰 NewsFeed Simulator

Aplikasi simulasi news feed real-time menggunakan **Kotlin Multiplatform Compose**.

## Cara Menjalankan

### 1. Clone Repository

```bash
git clone https://github.com/username/newsfeed-simulator.git
cd newsfeed-simulator
```

### 2. Buka Proyek

- Buka **IntelliJ IDEA** atau **Android Studio**
- Pilih **File → Open** → arahkan ke folder proyek
- Tunggu Gradle sync selesai

### 3. Jalankan di Desktop

```bash
./gradlew :composeApp:run
```

Atau lewat IDE: panel **Gradle → composeApp → Tasks → compose desktop → run**

### 4. Jalankan di Android *(opsional)*

- Hubungkan perangkat Android atau jalankan emulator
- Pilih konfigurasi `composeApp` di toolbar atas
- Klik tombol **Run ▶**

---

## Troubleshooting

**Gradle sync gagal** → Pastikan JDK 17+ dikonfigurasi di `File → Project Structure → SDK`

**Aplikasi tidak muncul** → Jalankan dengan `./gradlew :composeApp:run --stacktrace`

**Compose compiler mismatch** → Cek [tabel kompatibilitas resmi](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-compatibility-and-versioning.html)
