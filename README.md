# 🇱🇰 Lanka Radio Live

<div align="center">

![Android](https://img.shields.io/badge/Android-8.0%2B-green?style=for-the-badge&logo=android)
![Kotlin](https://img.shields.io/badge/Kotlin-100%25-purple?style=for-the-badge&logo=kotlin)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Material%203-blue?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-orange?style=for-the-badge)
![Platform](https://img.shields.io/badge/Platform-Mobile-success?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Active-brightgreen?style=for-the-badge)

### 🎧 Listen to Sri Lanka's Favorite Radio Stations Anytime, Anywhere

Modern Android Radio Streaming App built with **Kotlin**, **Jetpack Compose**, **Material 3**, and **ExoPlayer**.

</div>

---

## 📱 Overview

**Lanka Radio Live** is a modern Android application designed to bring together Sri Lanka's most popular radio stations in one beautiful and easy-to-use platform.

Whether you're listening to music, news, religious programs, educational content, or entertainment, Lanka Radio Live provides a seamless streaming experience with modern Android features.

---

## ✨ Features

### 🎵 Radio Streaming

- Live radio streaming
- High-quality audio playback
- ExoPlayer integration
- Background playback support
- Auto reconnect on network failure
- Buffer status indication
- Fast station switching

### ❤️ Favorites

- Add stations to favorites
- Remove favorites
- Dedicated favorites screen
- Local storage using Room Database

### 🔍 Search

- Instant station search
- Search by station name
- Fast filtering

### 🎚️ Audio Controls

- Play / Pause
- Stop
- Next Station
- Previous Station
- Bluetooth headset controls
- Lock screen controls

### 🔔 Notification Controls

- Media style notifications
- Play/Pause controls
- Next/Previous controls
- Close player action
- Current station information

### 🌙 Appearance

- Material Design 3
- Dynamic Colors
- Light Theme
- Dark Theme
- System Theme Support

### 🌐 Multi-Language Support

- 🇬🇧 English
- 🇱🇰 Sinhala
- 🇱🇰 Tamil

### ⏰ Sleep Timer

- 15 Minutes
- 30 Minutes
- 45 Minutes
- 60 Minutes
- Custom Timer

### 📊 Smart Features

- Recently Played
- Playback History
- Station Statistics
- Playback Analytics
- Data Saving Mode

### 🚗 Android Auto

- Android Auto Support
- Car Friendly Interface
- Safe Driving Controls

### ☁️ Backup & Restore

- Backup Favorites
- Restore Favorites
- Local Data Protection

---

## 📻 Available Radio Stations

| Station | Category |
|----------|----------|
| City FM | Government |
| Swadesheeya Sewaya | Government |
| Kandurata Sewaya | Government |
| Rajarata Sewaya | Government |
| Neth FM | Private |
| Sirasa FM | Private |
| Ran FM | Private |
| Hiru FM | Private |
| Siyatha FM | Private |
| Lakhanda FM | Government |
| Shree FM | Tamil |
| Sha FM | Tamil |
| Yes FM | English |
| Shakthi FM | Tamil |
| FM Derana | Private |

---

## 🖼️ Screenshots

### Home Screen

<img src="screenshots/home.png" width="250"/>

### Player Screen

<img src="screenshots/player.png" width="250"/>

### Favorites

<img src="screenshots/favorites.png" width="250"/>

### Settings

<img src="screenshots/settings.png" width="250"/>

> Add screenshots inside a `screenshots` folder.

---

## 🏗️ Architecture

```text
Presentation Layer
│
├── UI (Jetpack Compose)
├── ViewModels
│
Domain Layer
│
├── Use Cases
├── Repository Interfaces
│
Data Layer
│
├── Repository Implementations
├── Local Database (Room)
├── Remote Data Sources
│
Core
│
├── ExoPlayer Service
├── Notification Manager
├── Media Session
└── Utilities
```

---

## 🛠️ Tech Stack

### Language

- Kotlin

### UI

- Jetpack Compose
- Material Design 3

### Architecture

- MVVM
- Clean Architecture
- Repository Pattern

### Dependency Injection

- Hilt

### Database

- Room Database

### Preferences

- DataStore

### Networking

- Retrofit
- OkHttp

### Image Loading

- Coil

### Media

- ExoPlayer
- Media Session

---

## 🔐 Required Permissions

```xml
<uses-permission android:name="android.permission.INTERNET"/>

<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

<uses-permission android:name="android.permission.FOREGROUND_SERVICE"/>

<uses-permission android:name="android.permission.WAKE_LOCK"/>

<uses-permission android:name="android.permission.POST_NOTIFICATIONS"/>
```

---

## 🚀 Installation

### Clone Repository

```bash
git clone https://github.com/warunachamira/Lanka-Radio-Live.git
```

### Open Project

```bash
Open Android Studio
↓
Select Open Project
↓
Sync Gradle
↓
Run Application
```

---

## 📂 Project Structure

```text
com.lankaradiolive

├── data
│   ├── local
│   ├── remote
│   └── repository
│
├── domain
│   ├── model
│   ├── repository
│   └── usecase
│
├── presentation
│   ├── home
│   ├── player
│   ├── favorites
│   ├── settings
│   └── search
│
├── service
│
├── di
│
└── core
```

---

## 🌟 Upcoming Features

- 📻 100+ Sri Lankan Radio Stations
- 🎙️ Radio Recording
- 📺 Chromecast Support
- ⌚ Wear OS Support
- ☁️ Cloud Backup
- 📅 Program Schedule Guide
- 🤖 Smart Recommendations
- 📈 Trending Stations
- 🔊 Advanced Equalizer

---

## 👨‍💻 Developer

### G.R.W.C.P. Disanayaka

**Qualifications**

- SLTS
- BIT - University of Colombo School of Computing (UCSC)

**Contact Information**

📧 Email: warunachamira@gmail.com

📞 Phone: +94 71 926 0183

---

## 🤝 Contributing

Contributions are welcome!

If you would like to improve Lanka Radio Live:

1. Fork the repository
2. Create a new branch
3. Commit changes
4. Push to your branch
5. Open a Pull Request

---

## 🐞 Bug Reports

Found a bug?

Please create an issue with:

- Device Model
- Android Version
- App Version
- Steps to Reproduce

---

## ⭐ Support the Project

If you like this project:

⭐ Star this repository

🍴 Fork this repository

📢 Share it with others

---

## 📄 License

This project is licensed under the MIT License.

---

<div align="center">

### 🇱🇰 Made for Sri Lankan Radio Lovers Worldwide

**Lanka Radio Live**

Listen • Enjoy • Stay Connected

</div>
