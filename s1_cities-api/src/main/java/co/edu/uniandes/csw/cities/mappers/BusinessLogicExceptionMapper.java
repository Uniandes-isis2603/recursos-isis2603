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
package co.edu.uniandes.csw.cities.mappers;

import co.edu.uniandes.csw.cities.exceptions.BusinessLogicException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Convertidor de Excepciones BusinessLogicException a mensajes REST.
 *
 * El error se transforma bajo el siguiente modelo:
 * Codigo de respuesta: <code style="color: #c7254e; background-color: #f9f2f4;">412 Precodition Failed</code>
 * Respuesta: La razon del error
 * 
 *
 * @author ISIS2603
 */
@Provider
public class BusinessLogicExceptionMapper implements ExceptionMapper<BusinessLogicException> {

    /**
     * Generador de una respuesta a partir de una excepción
     *
     * @param exception excepión a convertir a una respuesta REST
     * @return La respuesta HTTP con el error.
     */
    @Override
    public Response toResponse(BusinessLogicException exception) {
        // retorna una respuesta
        return Response.status(Response.Status.PRECONDITION_FAILED)
                .entity(getInitCause(exception).getLocalizedMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build(); //To change body of generated methods, choose Tools | Templates.
    }

    private Throwable getInitCause(Throwable e) {
        if (e.getCause() != null) {
            return getInitCause(e.getCause());
        } else {
            return e;
        }
    }

}
