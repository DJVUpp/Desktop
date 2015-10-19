/*
 * JStickyNotes, Copyright (C) Feb 23, 2009 - Jonatan Rico (jrico) jnrico@gmail.com
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

public class Pair<TypeA, TypeB> {

    private TypeA objectA;
    private TypeB objectB;

    public Pair(TypeA objectA, TypeB objectB) {
        this.objectA = objectA;
        this.objectB = objectB;
    }

    public TypeA getObjectA() {
        return objectA;
    }

    public TypeB getObjectB() {
        return objectB;
    }

    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;
        if (object instanceof Pair) {
            Pair pair = (Pair) object;
            isEqual = getObjectA().equals(pair.getObjectA()) && getObjectB().equals(pair.getObjectB());
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        return getObjectA().hashCode() | getObjectB().hashCode();
    }

    public static <TypeA, TypeB> Pair<TypeA, TypeB> create(TypeA objectA, TypeB objectB) {
        return new Pair<TypeA, TypeB>(objectA, objectB);
    }
}
