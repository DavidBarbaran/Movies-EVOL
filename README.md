
# Movies - EVOL

<img src="https://i.imgur.com/FhCG4xc.png" />

[![Kotlin](https://img.shields.io/badge/kotlin-1.3.72-blue.svg)](http://kotlinlang.org) [![Gradle](https://img.shields.io/badge/gradle-6.1.1-%2366DCB8.svg)](https://developer.android.com/studio/releases/gradle-plugin)

Aplicación demo de películas para evaluación técnica en EVOL (TSnet).

:white_check_mark: 100% en **Kotlin**

:white_check_mark: Uso de **Jetpack Compose**

:white_check_mark: Consumo de web services con **Retrofit**

:white_check_mark: Clean **architecture**

:white_check_mark: Patrón de arquitectura **MVVM**

:white_check_mark: Inyección de dependencia con **Hilt**

:white_check_mark: Clean **code**


<a href="https://youtube.com/shorts/HYPzzvqmBzI?feature=share"><img src="https://i.imgur.com/6kifBl4.png" width="277"/></a>

<a href="https://github.com/DavidBarbaran/Movies-EVOL/releases/download/1.0.0-RC/app-release.apk"><img src="https://i.imgur.com/jMDgwCn.png" width="234"/></a>


## Comenzando

Estas instrucciones le proporcionarán una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y pruebas.

### Pre requisitos 📋
- [Android Studio](https://developer.android.com/studio/)
- [Git](https://git-scm.com/downloads)

### Instalación 🔧
Después de descargar Android Studio y git en su máquina local, ejecute el comando:

> git clone https://github.com/DavidBarbaran/Movies-EVOL

Abrir Android Studio y haga clic en la opción **Abrir un proyecto existente en Android Studio** y seleccione la carpeta donde se encuentra clonado el proyecto.

## Estructura del proyecto

![](https://i.imgur.com/rFNXXhn.png)

El proyecto esta desarrollado con **Clean Architecture** que consta de 3 capas que están estructuradas por paquetes según el feature.

### Capa de presentación :iphone:
La capa de presentación es la encargada de mostrar los datos e interactuar con la interfaz de usuario donde usamos el patrón de arquitectura Model-ViewModel-View (MVVM) usando los componentes de arquitectura de Android donde encontramos los siguientes elementos:

- Activity
- ViewModel
- UI Model

### Capa de dominio :large_orange_diamond:
La capa de dominio es la que se encarga de ejecutar la logica de negocio e interactúa con las capas de data y presentación donde encontramos los siguientes elementos:

- Use Cases
- Domain Entity
- Interactors

### Capa de datos :open_file_folder:
La capa de datos es la que se encarga de gestionar los datos locales y remotos de la aplicación, usando **Retrofit** para el consumo de web Services y Realm para guardar datos localmente:

- Repository
- Data Entity
- Data sources
- RestApi
- Database


## 📦 Bibliotecas usadas en el proyecto

### **Diseño**

-   [Jetpack Compose](https://developer.android.com/compose)
-   [Coil](https://github.com/coil-kt/coil)


### **Arquitectura**

- [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)

###  **Programación asíncrona**

-   [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines)

### **Base de datos**

- [Realm Kotlin](https://github.com/realm/realm-kotlin)

### **Firebase**

- [Firebase Analytics](https://firebase.google.com/docs/analytics)


### **Inyección de Dependencias**

-   [Hilt](https://dagger.dev/hilt/)


### **NET**

-   [Retrofit](https://github.com/square/retrofit)

### **Serialización**

-   [Gson](https://github.com/google/gson)