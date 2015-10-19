/*
 * JStickyNotes, Copyright (C) Feb 22, 2009 - Jonatan Rico (jrico) jnrico@gmail.com
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 */

package jrico.jstickynotes.util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class XmlReaderWriter {

    private XmlReaderWriter() {
    }

    public static <T> T readObjectFromFile(File file) {
        T object = null;
        XMLDecoder decoder = null;
        try {
            InputStream is = new FileInputStream(file);
            decoder = new XMLDecoder(new BufferedInputStream(is));
            object = (T) decoder.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (decoder != null) {
                decoder.close();
            }
        }
        return object;
    }

    public static <T> T readObjectFromFile(String file) {
        return (T) readObjectFromFile(new File(file));
    }

    public static <T> T readObjectFromString(String xml) {
        T object = null;
        XMLDecoder decoder = null;
        try {
            InputStream is = new ByteArrayInputStream(xml.getBytes());
            decoder = new XMLDecoder(new BufferedInputStream(is));
            object = (T) decoder.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (decoder != null) {
                decoder.close();
            }
        }
        return object;
    }

    public static void writeObjectsToFile(File file, Object... objects) {
        XMLEncoder encoder = null;
        try {
            OutputStream os = new FileOutputStream(file);
            encoder = new XMLEncoder(new BufferedOutputStream(os));
            for (Object object : objects) {
                encoder.writeObject(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (encoder != null) {
                encoder.close();
            }
        }
    }

    public static void writeObjectsToFile(String file, Object... objects) {
        writeObjectsToFile(new File(file), objects);
    }

    public static String writeObjectsToString(Object... objects) {
        OutputStream os = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(os));
        for (Object object : objects) {
            encoder.writeObject(object);
        }
        encoder.close();
        return os.toString();
    }
}
