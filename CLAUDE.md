# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Repository Purpose

This is a **teaching resource repository** for the course ISIS2603 - Desarrollo de Software en Equipos (Software Development in Teams) at Universidad de los Andes. It contains static HTML documentation, interactive learning tools, sample data, and templates — there is no buildable application.

The authoritative course resources live on the [GitHub Wiki](https://github.com/Uniandes-isis2603/recursos-isis2603/wiki).

## Working with the Repository

Since this is a static documentation repo, no build or test commands are needed. To view any resource, open the relevant HTML file directly in a browser:

- Main landing page: `docs/index.html`
- Individual guides: other files under `docs/`
- Bootstrap sample: `samplebootstrap/index.html`

## Repository Structure

| Path | Contents |
|------|----------|
| `docs/` | Interactive HTML documentation and tutorials (backend 3-layer) |
| `docs/front/` | Angular frontend tutorials (see `docs/front/CLAUDE.md`) |
| `docs/capturas/` | PNG screenshots used by the code-review guide artifacts |
| `ADR/` | Architecture Decision Record template (Spanish) |
| `json/` | Sample JSON datasets used in API examples |
| `images/` | Per-semester course images (folders named by semester, e.g. `202420/`) |
| `imagesWiki/` | Screenshots, diagrams, GIFs, and MP4s for the GitHub Wiki |
| `samplebootstrap/` | Bootstrap HTML grid layout example |
| `test-tabs.html` | Root-level scratch/prototype file for tab UI experiments |

## docs/ Architecture

The HTML files in `docs/` teach a **3-layer Java/Spring enterprise architecture**:

| Layer | Color code | Key files |
|-------|-----------|-----------|
| API REST (controllers, DTOs, endpoints) | green (`--api`) | `flujo-rest-3capas.html`, `uri-simulator.html`, `mini-postman-musica.html`, `postman-tests.html`, `postman-tests-guia.html`, `uml-dtos-musica.html` |
| Business Logic (services, domain rules) | blue (`--logic`) | `capa-logica-negocio.html`, `capa-logica-negocio-pruebas.html`, `quiz-logica-pruebas.html` |
| Persistence (JPA/ORM, entities) | amber (`--persist`) | `explorador-jpa-interactivo.html`, `jpa-element-collection.html`, `jpa_V2_gemini.html` |
| Cross-layer / general | (no accent color) | `layered-arch-tutorial.html`, `code-review-guide.html`, `code-review-guide-v1.html` |

The `index.html` landing page links to all of the above. Use `_template.html` as the canonical starting point when creating new backend artifacts.

## Design System (docs/)

All HTML files share a consistent inline CSS design system with these CSS custom properties:

- **Fonts:** DM Sans (body), JetBrains Mono (code), DM Serif Display (headings) — loaded from Google Fonts
- **Palette:** warm parchment backgrounds (`--bg: #f2efe9`, `--surface: #faf8f4`) with layer-color accents
- **Layer colors:** `--api` green, `--logic` blue, `--persist` amber (each has `-bg` and `-bdr` variants)
- **Shadows/radii:** `--shadow-sm`, `--shadow`, `--shadow-lg`, `--radius: 12px`

When editing HTML files, preserve this design system. Each file replicates the full `<style>` block (no shared stylesheet).

## docs/front/ — Angular Frontend

The `docs/front/` subdirectory contains interactive HTML artifacts teaching **Angular frontend development** for students who already understand the 3-layer backend. It has its own `docs/front/CLAUDE.md` with detailed guidance; key points:

- **Target**: Angular 17+ (standalone components, `inject()`, `@if`/`@for` control flow)
- **Layer color**: API REST green — same `--api` palette — because the frontend consumes the REST API
- **Back-link**: points to `../index.html` (main artifact index), or local `index.html` if one exists in `front/`
- **Template**: use `docs/front/_template-front.html` when creating new frontend artifacts
- **No Tailwind, no Font Awesome** — only Google Fonts CDN (plus Vue/Alpine if needed for interactivity)

Current artifacts in `docs/front/`:

| File | Topic |
|------|-------|
| `index-front.html` | Landing page for the frontend section |
| `html-css-intro.html` | HTML & CSS fundamentals intro |
| `styles-front.html` | Styling patterns and responsive layout |

## Writing Style for Artifact Content

All explanatory text inside HTML artifacts must follow these rules:

- **Third person only.** Write "el estudiante", "el desarrollador", "el equipo", "el framework", etc.
- **No second person** in any form: neither "vos", "tú", "usted", nor implied second person ("tu proyecto", "tu componente").
- This applies to all prose, callouts, highlights, and table descriptions inside the artifacts.

## Architecture Decision Records

Use `ADR/adr-template.md` as the template when creating new ADRs. The template is in Spanish and follows the standard MADR structure (Context → Decision Drivers → Considered Options → Decision Outcome → Pros/Cons).
