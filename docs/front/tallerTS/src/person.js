// Compiled from src/person.ts — ES module for the browser (no build step needed).
// When TypeScript is available: tsc --module esnext --target es2020 src/person.ts

// ── Domain classes ────────────────────────────────────────────────────────────

export class Course {
  constructor(name, professor) {
    this.name      = name;
    this.professor = professor;
  }
}

export class Person {
  constructor(name, code, idNumber, age, address, phone, courses) {
    this.name     = name;
    this.code     = code;
    this.idNumber = idNumber;
    this.age      = age;
    this.address  = address;
    this.phone    = phone;
    this.courses  = courses;
  }
}

// ── Loader ────────────────────────────────────────────────────────────────────

export async function loadPerson(url) {
  const response = await fetch(url);
  if (!response.ok) throw new Error(`HTTP ${response.status} — ${url}`);

  const data = await response.json();

  const courses = data.courses.map(c => new Course(c.name, c.professor));

  return new Person(
    data.name,
    data.code,
    data.idNumber,
    data.age,
    data.address,
    data.phone,
    courses
  );
}
