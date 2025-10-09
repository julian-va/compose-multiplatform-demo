# DemoComposeMultiplatform 📱

Una moderna aplicación de comercio electrónico multiplataforma construida con Kotlin Multiplatform y Compose Multiplatform, que muestra una arquitectura limpia y un amplio conjunto de características para Android e iOS.

## ✨ Características

- **Autenticación de Usuario**: Flujos seguros de inicio de sesión y registro.
- **Catálogo de Productos**: Navega por una lista de productos obtenidos de una API remota.
- **Detalles del Producto**: Visualiza información detallada de cada producto.
- **Sesiones Persistentes**: Funcionalidad "Recuérdame" para mantener a los usuarios conectados.
- **Arquitectura Limpia**: Sigue los principios de MVVM para una base de código escalable y mantenible.
- **UI Compartida**: Una única interfaz de usuario compartida para Android e iOS gracias a Compose Multiplatform.

## 📸 Capturas de Pantalla

*(Aquí puedes añadir capturas de pantalla de tu aplicación en ambas plataformas)*

| Android | iOS |
| :---: | :---: |
| *Pantalla de Login* | *Pantalla de Login* |
| *Pantalla de Inicio* | *Pantalla de Inicio* |
| *Pantalla de Detalle*| *Pantalla de Detalle*|

## 🛠️ Stack Tecnológico & Arquitectura

- **Kotlin Multiplatform**: Lógica de negocio compartida entre plataformas.
- **Compose Multiplatform**: UI declarativa compartida entre Android e iOS.
- **Koin**: Para inyección de dependencias.
- **Ktor Client**: Para realizar peticiones de red a la API REST.
- **Kotlinx Serialization**: Para el parseo de JSON.
- **Coroutines**: Para gestionar hilos en segundo plano y operaciones asíncronas.
- **Coil 3**: Para la carga de imágenes desde URLs.
- **Jetpack DataStore**: Para almacenamiento de datos simple y persistente (sesión y preferencias de usuario).
- **Arquitectura MVVM**: Modelo-Vista-Modelo de Vista para separar la UI de la lógica de negocio.
- **Navigation Compose**: Para la navegación entre pantallas.

## 🚀 Configuración e Instalación

### Prerrequisitos

- Android Studio
- Xcode (para la app de iOS)
- Plugin de Kotlin Multiplatform Mobile para Android Studio

### Ejecutando la App

1.  Clona el repositorio:
    ```bash
    git clone https://github.com/tu-usuario/DemoComposeMultiplatform.git
    ```
2.  Abre el proyecto en Android Studio.
3.  Deja que Gradle sincronice las dependencias del proyecto.

#### Android 🤖

Para compilar y ejecutar la versión de desarrollo de la app de Android, usa la configuración de ejecución `composeApp` desde el widget de ejecución en la barra de herramientas de tu IDE o compílala directamente desde la terminal:
```shell
./gradlew :composeApp:assembleDebug
```

#### iOS 🍏

Para compilar y ejecutar la versión de desarrollo de la app de iOS, selecciona `iosApp` en la configuración de ejecución y ejecútala en un simulador o un dispositivo real. Alternativamente, puedes abrir el directorio `/iosApp` en Xcode y ejecutarlo desde allí.

## 🔮 Mejoras Futuras

- [ ] Implementar la función "Olvidé mi contraseña".
- [ ] Añadir productos a un carrito de compras.
- [ ] Implementar un flujo de pago.
- [ ] Añadir tests unitarios y de UI.
