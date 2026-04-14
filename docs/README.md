# Taller — Perfil de Estudiante con TypeScript

Archivos del taller práctico de la asignatura **ISIS2603 · Desarrollo de Software en Equipos**.

---

## Contenido del zip

```
ejemplo-perfil.html          ← Paso 1: perfil estático (solo HTML + Bootstrap)
guia-estilos.html            ← Referencia de clases Bootstrap usadas en el ejemplo
taller-typescript.html       ← Paso 3: enunciado del taller práctico
tallerTS/
  ejemplo-perfil-dinamic.html  ← Paso 2: perfil dinámico (fetch + clases TS)
  data/
    student.json               ← Datos del estudiante (cargados con fetch)
  src/
    person.ts                  ← Código fuente TypeScript (Course, Person, loadPerson)
    person.js                  ← Versión compilada — la que usa el navegador
```

---

## Requisitos

### VSCode
El editor recomendado es **Visual Studio Code**, desarrollado por Microsoft y distribuido
de forma gratuita con licencia MIT. En la máquina virtual ya está instalado.
Si necesita instalarlo en otra máquina, descárguelo en <https://code.visualstudio.com>.

### Node.js y TypeScript
El compilador de TypeScript (`tsc`) ya está instalado en la máquina virtual.
Si trabaja en otra máquina, siga estos pasos:

1. Instale **Node.js** desde <https://nodejs.org>.
2. Instale TypeScript globalmente con npm:
   ```
   npm install -g typescript
   ```
3. Verifique la instalación:
   ```
   tsc --version
   ```

### Extensión Live Server (obligatoria para el ejemplo dinámico)
`ejemplo-perfil-dinamic.html` usa `fetch()` para cargar `data/student.json`.
Los navegadores bloquean `fetch` cuando el archivo se abre directamente (`file://`),
por lo que **necesita un servidor HTTP local**.

La forma más sencilla es la extensión **Live Server** de VSCode:

1. Abra VSCode → panel de Extensiones (`Ctrl+Shift+X`).
2. Busque `Live Server` (autor: Ritwick Dey) e instálela.

---

## Cómo ejecutar el ejemplo dinámico

### 1. Abrir la carpeta en VSCode
Desde VSCode: **Archivo → Abrir carpeta…** y seleccione la carpeta `tallerTS/`.

Verá esta estructura en el explorador de archivos:

```
tallerTS/
  data/student.json
  src/person.ts
  src/person.js
  ejemplo-perfil-dinamic.html
```

### 2. Iniciar Live Server
Haga clic derecho sobre `ejemplo-perfil-dinamic.html` en el explorador
y elija **"Open with Live Server"**.

VSCode abrirá el navegador en `http://127.0.0.1:5500/ejemplo-perfil-dinamic.html`
y el perfil del estudiante se cargará con los datos de `data/student.json`.

---

## Cómo modificar y recompilar TypeScript

Si edita `src/person.ts`, debe recompilar para que el navegador use los cambios:

### Opción A — Compilar una sola vez
Abra un terminal en VSCode (`Ctrl+ñ`) dentro de `tallerTS/` y ejecute:
```
tsc src/person.ts --target ES2020 --module ES2020 --outDir src
```
Esto sobreescribe `src/person.js` con la versión compilada.

### Opción B — Modo vigilancia (recompila automáticamente al guardar)
En la raíz de `tallerTS/`, ejecute primero:
```
tsc --init
```
Esto crea `tsconfig.json`. Luego inicie el modo vigilancia:
```
tsc --watch
```
Cada vez que guarde `person.ts`, TypeScript recompilará `person.js` automáticamente.

---

## Estructura de las clases TypeScript (`src/person.ts`)

```typescript
class Course {
  constructor(
    public name: string,
    public professor: string
  ) {}
}

class Person {
  constructor(
    public name: string,
    public code: string,
    public idNumber: string,
    public age: number,
    public address: string,
    public phone: string,
    public courses: Course[]
  ) {}
}

async function loadPerson(url: string): Promise<Person> {
  const response = await fetch(url);
  const data = await response.json();
  const courses = data.courses.map(
    (c: any) => new Course(c.name, c.professor)
  );
  return new Person(
    data.name, data.code, data.idNumber,
    data.age, data.address, data.phone, courses
  );
}
```

El HTML importa la versión compilada como módulo ES:
```html
<script type="module">
  import { loadPerson } from './src/person.js';
  ...
</script>
```

---

*ISIS2603 · Universidad de los Andes — generado con Claude Code*
