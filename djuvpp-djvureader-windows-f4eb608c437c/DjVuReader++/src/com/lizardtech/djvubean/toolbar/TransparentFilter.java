//C- -------------------------------------------------------------------
//C- Java DjVu (r) (v. 0.8)
//C- Copyright (c) 2004-2005 LizardTech, Inc.  All Rights Reserved.
//C- Java DjVu is protected by U.S. Pat. No.C- 6,058,214 and patents
//C- pending.
//C-
//C- This software is subject to, and may be distributed under, the
//C- GNU General Public License, Version 2. The license should have
//C- accompanied the software or you may obtain a copy of the license
//C- from the Free Software Foundation at http://www.fsf.org .
//C-
//C- The computer code originally released by LizardTech under this
//C- license and unmodified by other parties is deemed "the LIZARDTECH
//C- ORIGINAL CODE."  Subject to any third party intellectual property
//C- claims, LizardTech grants recipient a worldwide, royalty-free,
//C- non-exclusive license to make, use, sell, or otherwise dispose of
//C- the LIZARDTECH ORIGINAL CODE or of programs derived from the
//C- LIZARDTECH ORIGINAL CODE in compliance with the terms of the GNU
//C- General Public License.   This grant only confers the right to
//C- infringe patent claims underlying the LIZARDTECH ORIGINAL CODE to
//C- the extent such infringement is reasonably necessary to enable
//C- recipient to make, have made, practice, sell, or otherwise dispose
//C- of the LIZARDTECH ORIGINAL CODE (or portions thereof) and not to
//C- any greater extent that may be necessary to utilize further
//C- modifications or combinations.
//C-
//C- The LIZARDTECH ORIGINAL CODE is provided "AS IS" WITHOUT WARRANTY
//C- OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
//C- TO ANY WARRANTY OF NON-INFRINGEMENT, OR ANY IMPLIED WARRANTY OF
//C- MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.
//C-
//C- In addition, as a special exception, LizardTech Inc. gives permission
//C- to link the code of this program with the proprietary Java
//C- implementation provided by Sun (or other vendors as well), and
//C- distribute linked combinations including the two. You must obey the
//C- GNU General Public License in all respects for all of the code used
//C- other than the proprietary Java implementation. If you modify this
//C- file, you may extend this exception to your version of the file, but
//C- you are not obligated to do so. If you do not wish to do so, delete
//C- this exception statement from your version.
//C- -------------------------------------------------------------------
//C- Developed by Bill C. Riemers, Foxtrot Technologies Inc. as work for
//C- hire under US copyright laws.
//C- -------------------------------------------------------------------
//
package com.lizardtech.djvubean.toolbar;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;


/**
 * Set transparent
 *
 * @author Bill C. Riemers
 * @version $Revision: 1.2 $
 */
public class TransparentFilter
  extends RGBImageFilter
{
  //~ Constructors -----------------------------------------------------------

  /**
   * Creates a new GrayFilter object.
   */
  public TransparentFilter()
  {
    canFilterIndexColorModel = true;
  }

  //~ Methods ----------------------------------------------------------------

  /**
   * Filter to convert to gray.
   *
   * @param x coordinate of pixel.
   * @param y coordinate of pixel.
   * @param rgb red blue green value of pixel.
   *
   * @return a darker color for the pixel.
   */
  public int filterRGB(
    int x,
    int y,
    int rgb)
  {
    final int r     = 0xff & (rgb >> 16);
    final int g     = 0xff & (rgb >> 8);
    final int b     = 0xff & rgb;
    final int alpha = ((r > 245) && (b > 245) && (g < 10))
      ? 0x0
      : 0xff;

    return (alpha << 24) | (r << 16) | (g << 8) | b;
  }
}
