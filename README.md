# Taller Backend

Aplicacion de gestion de estudiantes con API REST en Spring Boot, base H2 persistente y una interfaz HTML con Vanilla JavaScript y Tailwind CSS.

## Requisitos

- Java 25
- Maven 3.9 o posterior
- Node.js 20 o posterior
- npm

## Iniciar el backend

Desde la raiz del proyecto:

```powershell
mvn spring-boot:run
```

La API queda disponible en `http://localhost:8080/api/estudiantes`. La base H2 se guarda en `data/tallerdb.mv.db` y no se incluye en Git. Puedes cambiar la ruta definiendo la variable de entorno `TALLER_DB_PATH`.

Al iniciar por primera vez, `src/main/resources/data.sql` agrega los estudiantes de ejemplo de forma idempotente.

## Abrir la interfaz

Abre `index.html` con Live Server. CORS está habilitado para los orígenes locales `http://localhost:3000`, `http://localhost:5500` y `http://127.0.0.1:5500`.

## Ejecutar las pruebas E2E

Con el backend y Live Server en ejecución:

```powershell
npm install
npx playwright install chromium
npm run test:e2e
```

Para inspeccionar el test con un navegador visible:

```powershell
npm run test:e2e:headed
```

El test crea un estudiante con correo único, valida la tarjeta y el modal y elimina el registro de prueba al finalizar.

## Endpoints

| Método | Ruta | Acción |
| --- | --- | --- |
| GET | `/api/estudiantes` | Listar estudiantes |
| GET | `/api/estudiantes/{id}` | Consultar por ID |
| POST | `/api/estudiantes` | Crear estudiante |
| PUT | `/api/estudiantes/{id}` | Actualizar estudiante |
| DELETE | `/api/estudiantes/{id}` | Eliminar estudiante |
