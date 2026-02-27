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
| `docs/` | Interactive HTML documentation and tutorials |
| `ADR/` | Architecture Decision Record template (Spanish) |
| `json/` | Sample JSON datasets used in API examples |
| `images/` | Per-semester course images (folders named by semester, e.g. `202420/`) |
| `imagesWiki/` | Screenshots, diagrams, GIFs, and MP4s for the GitHub Wiki |
| `samplebootstrap/` | Bootstrap HTML grid layout example |

## docs/ Architecture

The HTML files in `docs/` teach a **3-layer Java/Spring enterprise architecture**:

| Layer | Color code | Key files |
|-------|-----------|-----------|
| API REST (controllers, DTOs, endpoints) | green (`--api`) | `flujo-rest-3capas.html`, `uri-simulator.html`, `mini-postman-musica.html`, `postman-tests.html` |
| Business Logic (services, domain rules) | blue (`--logic`) | `capa-logica-negocio.html`, `capa-logica-negocio-pruebas.html`, `quiz-logica-pruebas.html` |
| Persistence (JPA/ORM, entities) | amber (`--persist`) | `explorador-jpa-interactivo.html`, `jpa-element-collection.html`, `jpa_V2_gemini.html` |

The `index.html` landing page links to all of the above.

## Design System (docs/)

All HTML files share a consistent inline CSS design system with these CSS custom properties:

- **Fonts:** DM Sans (body), JetBrains Mono (code), DM Serif Display (headings) — loaded from Google Fonts
- **Palette:** warm parchment backgrounds (`--bg: #f2efe9`, `--surface: #faf8f4`) with layer-color accents
- **Layer colors:** `--api` green, `--logic` blue, `--persist` amber (each has `-bg` and `-bdr` variants)
- **Shadows/radii:** `--shadow-sm`, `--shadow`, `--shadow-lg`, `--radius: 12px`

When editing HTML files, preserve this design system. Each file replicates the full `<style>` block (no shared stylesheet).

## Architecture Decision Records

Use `ADR/adr-template.md` as the template when creating new ADRs. The template is in Spanish and follows the standard MADR structure (Context → Decision Drivers → Considered Options → Decision Outcome → Pros/Cons).
