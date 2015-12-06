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
package com.lizardtech.djvubean.outline;

import com.lizardtech.djvubean.DjVuBean;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;


/**
 * This class is used for wrapping outline navigation with a scrollpane.
 *
 * @author Bill C. Riemers
 * @version $Revision: 1.2 $
 */
public class OutlineScrollPane
  extends ScrollPane
{
  //~ Instance fields --------------------------------------------------------

  private final Outline outline;

  //~ Constructors -----------------------------------------------------------

  /**
   * Creates a new OutlineScrollPane object.
   *
   * @param bean DjVuBean to navigate.
   */
  public OutlineScrollPane(final DjVuBean bean)
  {
    outline = new Outline(bean);
    add(outline);
  }

  //~ Methods ----------------------------------------------------------------

  /**
   * Query the maximum size for this scroll pane.
   *
   * @return the preferred size of the outline.
   */
  public Dimension getMaximumSize()
  {
    return outline.getPreferredSize();
  }

  /**
   * Query the minimum size for this scroll pane.
   *
   * @return the minimum size of this scroll pane.
   */
  public Dimension getMinimumSize()
  {
    final Dimension retval = outline.getMinimumSize();
    retval.width = 0;

    return retval;
  }

  /**
   * Query the preferred size for this scroll pane.
   *
   * @return the preferred size of this scroll pane.
   */
  public Dimension getPreferredSize()
  {
    final Dimension retval = outline.getPreferredSize();
    final int       width = (outline.getFontWidth() * 12) + 15;

    if(retval.width > width)
    {
      retval.width = width;
    }

    return retval;
  }
}
