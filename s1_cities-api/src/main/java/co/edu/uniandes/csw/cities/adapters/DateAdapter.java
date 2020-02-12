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
package co.edu.uniandes.csw.cities.adapters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Clase adaptador que formatea las fechas en formato serialiable utilizando la
 * convención: yyyy-MM-dd. Ej: 2018-02-12
 *
 * @author ISIS2603
 */
public class DateAdapter extends XmlAdapter<String, Date> {
    
      private static final Logger LOGGER = Logger.getLogger(DateAdapter.class.getName());

    /**
     * Thread safe {@link DateFormat}.
     */
    private static final ThreadLocal<DateFormat> DATE_FORMAT_TL = new ThreadLocal<DateFormat>() {

        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    @Override
    public Date unmarshal(String v) throws Exception {
        LOGGER.log(Level.INFO, "input date "+v);
        return DATE_FORMAT_TL.get().parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        if (v == null) {
            return null;
        }
        return DATE_FORMAT_TL.get().format(v);
    }
}
