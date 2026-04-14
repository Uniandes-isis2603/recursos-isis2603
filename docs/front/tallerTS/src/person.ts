// ── Interfaces — shape of the JSON data ──────────────────────────────────────

interface CourseData {
  name:      string;
  professor: string;
}

interface PersonData {
  name:     string;
  code:     string;
  idNumber: string;
  age:      number;
  address:  string;
  phone:    string;
  courses:  CourseData[];
}

// ── Domain classes ────────────────────────────────────────────────────────────

export class Course {
  name:      string;
  professor: string;

  constructor(name: string, professor: string) {
    this.name      = name;
    this.professor = professor;
  }
}

export class Person {
  name:     string;
  code:     string;
  idNumber: string;
  age:      number;
  address:  string;
  phone:    string;
  courses:  Course[];

  constructor(
    name:     string,
    code:     string,
    idNumber: string,
    age:      number,
    address:  string,
    phone:    string,
    courses:  Course[]
  ) {
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

export async function loadPerson(url: string): Promise<Person> {
  const response = await fetch(url);
  if (!response.ok) throw new Error(`HTTP ${response.status} — ${url}`);

  const data: PersonData = await response.json();

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
