# MoodTrack - Bitacora de Emociones

MoodTrack es una pequeña aplicación que permite a los usuarios registrar cómo se sintieron durante el día, asociar una emoción, escribir una breve reflexión, y vincular hábitos que intentaron cumplir ese día.

---

## 🛠️ Herramientas de Prueba

Para ejecutar las solicitudes, se recomienda usar una de las siguientes herramientas de cliente HTTP:

* **Postman** / **Insomnia** (Interfaz gráfica)
* **cURL** (Línea de comandos)

---
### Crear Usuarios (POST)

Permite registrar un nuevo usuario.

| Método | URL |
| :--- | :--- | 
| **POST** | `/api/v1/usuarios` |

#### 🚀 Ejemplo de Solicitud (Payloads)

Aquí se muestran un ejemplo de cuerpo de solicitud válido:

Usuario 1
```json
{
    "nombre": "Alejandro",
    "email": "ale@gmail.com",
    "perfil": {
        "bio": "Bio",
        "colorFavorito": "Azul",
        "fraseDelDia": "Hoy es una nueva oportunidad"
    }
}
```

### Obtener Usuarios (GET)

Permite obtener los usuarios registrados en la aplicación.

| Método | URL |
| :--- | :--- | 
| **GET** | `/api/v1/usuarios` |

---

### Crear Hábito (POST)

Permite registrar un nuevo hábito con su nivel de importancia.

| Método | URL |
| :--- | :--- | 
| **POST** | `/api/v1/habitos` |

#### 🚀 Ejemplo de Solicitud (Payloads)

Aquí se muestran dos ejemplos de cuerpos de solicitud válidos:

Habito 1
```json
{
    "descripcion": "Comer saludable",
    "nivelDeImportanciaEnum": "ALTO"
}
```
Habito 2
```json
{
    "descripcion": "Hacer ejercicio",
    "nivelDeImportanciaEnum": "ALTO"
}
```

### Obtener Hábitos (GET)

Permite obtener los habitos creados 

| Método | URL | 
| :--- | :--- | 
| **GET** | `/api/v1/habitos` |

---

### Crear Entrada diaria (POST)

Permite registrar una nueva entrada diaria.

| Método | URL |
| :--- | :--- | 
| **POST** | `/api/v1/entrada-diaria` |

#### 🚀 Ejemplo de Solicitud (Payloads)

Aquí se muestran dos ejemplos de cuerpos de solicitud válidos:

EntradaDiaria 1
```json
{
    "usuarioId": "[ID DE USUARIO]",
    "fecha": "2025-11-29",
    "reflexion": "Tuve muchos tareas, pero logré terminar la mayoría. Necesito organizarme mejor.",
    "emocion": "Cansado",
    "habitosIds": [1, 2]
}
```
> **Importante:** Para la opción 1 se debe de crear anteriormente los habitos correspondientes, sino utilizar la opción 2.

EntradaDiaria 2
```json
{
    "usuarioId": "[ID DE USUARIO]",
    "fecha": "2025-11-30",
    "reflexion": "Me levanté temprano y cumplí con mi agenda matutina. Me siento productivo y en paz.",
    "emocion": "Relajado",
    "habitos": [
        {
            "id": 1,
            "descripcion": "Meditación 10 min",
            "nivelDeImportanciaEnum": "BAJO"
        },
        {
            "id": 2,
            "descripcion": "Beber 2L de agua",
            "nivelDeImportanciaEnum": "MEDIO"
        },
        {
            "id": 3,
            "descripcion": "Lectura 30 min",
            "nivelDeImportanciaEnum": "MEDIO"
        }
    ]
}
```
