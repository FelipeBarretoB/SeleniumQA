# SeleniumQA - Automatización de Pruebas para OpenCart

![Java](https://img.shields.io/badge/Java-11-orange)
![Selenium](https://img.shields.io/badge/Selenium-4.26.0-green)
![JUnit](https://img.shields.io/badge/JUnit-5.11.4-blue)
![Maven](https://img.shields.io/badge/Maven-Build-red)

## 📋 Descripción del Proyecto

**SeleniumQA** es un framework de automatización de pruebas desarrollado en Java utilizando Selenium WebDriver para realizar pruebas end-to-end de la aplicación e-commerce OpenCart. El proyecto implementa el patrón Page Object Model (POM) para mantener un código organizado, reutilizable y de fácil mantenimiento.

### 🎯 Objetivo

Automatizar las pruebas funcionales críticas de la plataforma OpenCart, incluyendo:
- **Gestión de cuentas de usuario** (registro, inicio de sesión)
- **Proceso de compra completo** (selección de productos, carrito, checkout)
- **Validación de datos** con integración de archivos Excel
- **Reportes de resultados** automatizados

## 🏗️ Arquitectura del Proyecto

### Estructura de Directorios

```
SeleniumQA/
├── src/
│   ├── main/java/com/opencart/
│   │   ├── pages/           # Page Object Model classes
│   │   │   ├── BasePage.java
│   │   │   ├── HomePage.java
│   │   │   ├── RegisterPage.java
│   │   │   ├── LogInPage.java
│   │   │   ├── CartPage.java
│   │   │   ├── CheckoutPage.java
│   │   │   └── ...
│   │   └── utils/           # Clases utilitarias
│   │       ├── Constants.java
│   │       ├── Excel.java
│   │       └── Verify.java
│   ├── test/java/com/opencart/test/
│   │   ├── BaseTest.java    # Configuración base de pruebas
│   │   ├── CuentaTest.java  # Pruebas de gestión de cuentas
│   │   └── ComprarTest.java # Pruebas de proceso de compra
│   └── resources/
│       ├── data.xlsx        # Datos de prueba
│       └── output.xlsx      # Resultados de pruebas
└── pom.xml
```

### 🔧 Tecnologías Utilizadas

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| **Java** | 11 | Lenguaje de programación principal |
| **Selenium WebDriver** | 4.26.0 | Automatización del navegador web |
| **JUnit 5** | 5.11.4 | Framework de pruebas unitarias |
| **WebDriverManager** | 5.8.0 | Gestión automática de drivers |
| **Apache POI** | 5.4.0 | Manipulación de archivos Excel |
| **AssertJ** | 3.27.0 | Aserciones fluidas para pruebas |
| **Maven** | - | Gestión de dependencias y build |

## 🚀 Características Principales

### ✅ Funcionalidades Implementadas

1. **Page Object Model (POM)**
   - Separación clara entre lógica de pruebas y elementos de la página
   - Reutilización de código y fácil mantenimiento
   - Encapsulación de localizadores y acciones

2. **Gestión de Cuentas de Usuario**
   - Registro de nuevas cuentas con validación
   - Inicio de sesión con credenciales válidas e inválidas
   - Integración con datos desde Excel

3. **Proceso de Compra E2E**
   - Navegación por categorías y subcategorías
   - Selección y adición de productos al carrito
   - Proceso completo de checkout
   - Verificación de pedidos completados

4. **Manejo de Datos**
   - Lectura de datos de prueba desde archivos Excel
   - Escritura de resultados a archivos Excel
   - Generación de números aleatorios para evitar duplicados

5. **Verificaciones Avanzadas**
   - Sistema de verificaciones soft assertions
   - Reportes detallados de fallos
   - Validación de elementos y mensajes

## 🛠️ Instalación y Configuración

### Prerrequisitos

- **Java 11** o superior
- **Maven 3.6** o superior
- **Chrome Browser** (para ejecución de pruebas)
- **IDE** recomendado: IntelliJ IDEA o Eclipse

### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/FelipeBarretoB/SeleniumQA.git
   cd SeleniumQA
   ```

2. **Instalar dependencias**
   ```bash
   mvn clean install
   ```

3. **Compilar el proyecto**
   ```bash
   mvn compile
   ```

4. **Verificar la configuración**
   ```bash
   mvn test-compile
   ```

## 📊 Ejecución de Pruebas

### Ejecutar Todas las Pruebas
```bash
mvn test
```

### Ejecutar Pruebas Específicas

**Pruebas de Cuenta de Usuario:**
```bash
mvn test -Dtest=CuentaTest
```

**Pruebas de Proceso de Compra:**
```bash
mvn test -Dtest=ComprarTest
```

### Ejecutar Métodos de Prueba Específicos
```bash
mvn test -Dtest=CuentaTest#registrarCuenta
mvn test -Dtest=ComprarTest#comprarTestExcel
```

## 📝 Casos de Prueba Disponibles

### 👤 CuentaTest.java
| Método | Descripción | Orden |
|--------|-------------|-------|
| `registrarCuenta()` | Registro básico de nueva cuenta | 1 |
| `registrarCuentaConExcel()` | Registro usando datos de Excel | 2 |
| `ingresarSesion()` | Inicio de sesión exitoso | 3 |
| `ingresarSesionConExcel()` | Login con datos de Excel | 4 |
| `ingresarSesionConExcelFail()` | Login fallido para validar errores | 5 |

### 🛒 ComprarTest.java
| Método | Descripción | Orden |
|--------|-------------|-------|
| `comprarTest()` | Proceso básico de compra | 1 |
| `comprarTestExcel()` | Compra con datos de Excel | 2 |
| `comprarTestExcelFail()` | Validación de fallos en compra | 3 |

## 📁 Archivos de Configuración

### Constants.java
```java
public static String URL = "https://opencart.abstracta.us/";
public static String FILE_PATH_EXCEL = "src/main/resources/data.xlsx";
public static String OUTPUT_FILE_PATH_EXCEL = "src/main/resources/output.xlsx";
```

### data.xlsx
Contiene datos de prueba organizados en hojas:
- **Hoja 0**: Datos de productos para pruebas de compra
- **Hoja 1**: Datos de usuarios para registro y login
- **Hoja 2**: Datos para pruebas de fallos

## 📈 Reportes y Resultados

Los resultados de las pruebas se guardan automáticamente en:
- **output.xlsx**: Resultados detallados de las pruebas
- **Consola**: Logs detallados de ejecución
- **Maven Surefire Reports**: Reportes HTML estándar

## 🤝 Contribución

1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -am 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crea un Pull Request

## 📋 Mejores Prácticas Implementadas

- ✅ **Page Object Model** para mantenibilidad
- ✅ **WebDriverManager** para gestión automática de drivers
- ✅ **Soft Assertions** para pruebas robustas
- ✅ **Data-Driven Testing** con Excel
- ✅ **Configuración centralizada** de constantes
- ✅ **Manejo de timeouts** y waits explícitos
- ✅ **Generación de datos únicos** para evitar conflictos

## 📞 Soporte

Para reportar bugs o solicitar nuevas características, por favor crea un [issue](https://github.com/FelipeBarretoB/SeleniumQA/issues) en el repositorio.

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

---

**Desarrollado con ❤️ para automatización de pruebas de calidad**
