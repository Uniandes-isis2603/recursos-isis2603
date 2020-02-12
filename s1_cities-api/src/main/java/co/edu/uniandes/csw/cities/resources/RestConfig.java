/*
MIT License

Copyright (c) 2019 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
/*
 * RestConfig.java
 * Configura el servicio JAX-RS.
 */
package co.edu.uniandes.csw.cities.resources;

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;

/**
 * Clase que indica que este proyecto web ofrece servicios REST. Adicionalmente,
 * esta clase define el prefijo por defecto de las rutas a los recursos.
 *
 * (non-Javadoc)
 *
 * @see javax.ws.rs.core.Application
 * @author ISIS2603
 */
@ApplicationPath("/api")
public class RestConfig extends Application {
}
