# CLAUDE.md — docs/front/

This file provides guidance to Claude Code when working in the `docs/front/` section
of the ISIS2603 resources repository.

## Purpose

This folder contains interactive HTML artifacts that teach **frontend development
with Angular**, focused on building responsive web applications that consume a
REST API built with Spring Boot (documented in `docs/`).

The target audience is students who already understand the 3-layer backend
(API REST → Business Logic → Persistence) and are now learning to connect
a web client to that API.

## Topics Covered

Artifacts in this folder address the Angular development workflow:

| Topic | Scope |
|-------|-------|
| Angular project structure | Modules, components, services, routing |
| HTTP Client | `HttpClient`, `HttpClientModule`, consuming REST endpoints |
| Components & templates | `@Component`, `@Input`, `@Output`, data binding, `*ngFor`, `*ngIf` |
| Reactive Forms | `FormBuilder`, `Validators`, form submission |
| Services & Dependency Injection | `@Injectable`, `providedIn`, injecting services into components |
| Routing | `RouterModule`, `Routes`, `routerLink`, `ActivatedRoute` |
| Responsive layout | CSS Grid, Flexbox, Angular Material or plain CSS |
| Error handling | `HttpErrorResponse`, RxJS `catchError`, user feedback |
| Auth (optional) | JWT interceptors, route guards |

## Design System

All artifacts in `docs/front/` **must follow the same canonical design system**
as the backend artifacts in `docs/`. Use `docs/_template.html` as the starting point.

### Key rules
- **Fonts**: DM Sans (body) · DM Serif Display (headings) · JetBrains Mono (code/meta)
- **Color palette**: same CSS custom properties as `docs/_template.html`
- **Layer color for frontend**: use the **API REST green** palette
  (`--layer: #0e7a5a`, `--layer-bg: #e6f5f0`, `--layer-bdr: #9dd9c5`)
  since the frontend consumes the API layer
- **Header**: canonical dark header (`#1c1814`) with `back-link` pointing to
  `../index.html` (one level up) or to a local `index.html` if one exists in `front/`
- **Mobile**: always include `@media (max-width: 640px)` block
- **No Tailwind, no Font Awesome**, no CDNs beyond Google Fonts and Vue/Alpine
  if needed for interactivity

### back-link target
Since artifacts live in `docs/front/`, the back-link should point to
`../index.html` to return to the main artifact index, OR to `index.html`
if a local front index is created.

## Artifact Creation Prompt

When creating a new artifact for this folder, use the prompt in
`docs/_template.html` plus these additions:

```
- Archivo destino: docs/front/[nombre].html
- Capa: API REST (verde: --layer: #0e7a5a)
- back-link href: "../index.html"
- Tema Angular: [describe el tema específico]
- El artefacto debe mostrar código Angular real (TypeScript + HTML template)
  con bloques <pre><code> en JetBrains Mono
- Los snippets de código deben conectarse conceptualmente con el API REST
  documentado en docs/ (endpoints, DTOs, códigos HTTP)
```

## Connection to Backend Artifacts

Frontend artifacts should reference and complement the backend artifacts in `docs/`:

| Frontend topic | Connects to backend artifact |
|---------------|------------------------------|
| HttpClient GET/POST | `docs/mini-postman-musica.html`, `docs/uri-simulator.html` |
| Handling HTTP responses | `docs/postman-tests.html`, `docs/postman-tests-guia.html` |
| Displaying entity data | `docs/uml-dtos-musica.html` (DTO structure) |
| Error handling / status codes | `docs/flujo-rest-3capas.html` |

## File Naming Convention

```
[feature]-[type].html

Examples:
  http-client-intro.html       ← intro to HttpClient
  componentes-lista.html       ← list component pattern
  formulario-reactivo.html     ← reactive forms
  routing-navegacion.html      ← routing basics
  servicios-inyeccion.html     ← services & DI
```

## Angular Version

Target **Angular 17+** (standalone components, `inject()` function,
`@if` / `@for` control flow syntax). Avoid legacy NgModule patterns
unless specifically teaching the migration path.
