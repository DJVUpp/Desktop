package com.lizardtech.djview;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import com.lizardtech.djview.frame.Frame;
import com.lizardtech.djvubean.DjVuBean;
import com.lizardtech.djvubean.outline.CreateThumbnails;

// NOTE: rotation was available in previous versions.
public class FullBookView extends JPanel {

	public JScrollPane ThumblainsScrollPane;
	public static int PagesCount;
	public static boolean Continous = false;
	public static boolean Is_Rotated = false;
	private DefaultListModel<JPanel> pages;
	private JList<JPanel> ThumblainsList;
	private final DjVuBean djvubean;
	private final Frame frame;
	private final JPanel TOP_PANEL;
	private final JPanel BEAN_PANEL;
	private final CardLayout CARD_LAYOUT;
	private final int PAGE_WIDHT;
	private final int PAGE_HEIGHT;
	private Thread renderer;

	// private int Pagenum = 0;
	public FullBookView(final DjVuBean djvubean, final Frame frame, JPanel beanPanel) {

		this.djvubean = djvubean;
		this.frame = frame;
		this.BEAN_PANEL = beanPanel;
		CARD_LAYOUT = (CardLayout) beanPanel.getLayout();
		PAGE_WIDHT = 720;
		PAGE_HEIGHT = 768;

		TOP_PANEL = new JPanel(new BorderLayout());
		TOP_PANEL.setBackground(Color.gray);

		beanPanel.add(TOP_PANEL, "FullBook");
		CARD_LAYOUT.first(beanPanel);
		init();
		start();
	}

	/**
	 * initialize the variables and views.
	 */
	public final void init() {

		ThumblainsList = new JList();
		ThumblainsScrollPane = new JScrollPane(ThumblainsList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pages = new DefaultListModel<>();
		pages.setSize(PagesCount);

		// tell the ThumblainsList to use the panel array for its data
		ThumblainsList.setModel(pages);
		ThumblainsList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// TODO: revisite next statement.
				djvubean.setPage(djvubean.getPage());

				if (e.getClickCount() == 1) {
					JList JLelement = (JList) e.getSource();
					djvubean.setPageString("" + (JLelement.getSelectedIndex() + 1));
					frame.Thumbpanel.ThumblainsList.setSelectedIndex(JLelement.getSelectedIndex());
					// Pagenum = ((JLelement.getSelectedIndex() + 1));
				}
				if (e.getClickCount() == 2) {

					JList JLelement = (JList) e.getSource();
					djvubean.setPageString("" + (JLelement.getSelectedIndex() + 1));
					// Pagenum = ((JLelement.getSelectedIndex() + 1));
					Switch_FullBookView(false);
				}
			}
		});
		ThumblainsList.setCellRenderer(new com.lizardtech.djview.ImageListCellRenderer(PAGE_WIDHT, PAGE_WIDHT));

		ThumblainsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ThumblainsList.setLayoutOrientation(JList.VERTICAL);
		ThumblainsList.setFixedCellWidth(PAGE_WIDHT);
		ThumblainsList.setFixedCellHeight(PAGE_HEIGHT + 10);
		ThumblainsList.setFocusable(false);

		// put our JList in a JScrollPane
		ThumblainsScrollPane.setPreferredSize(BEAN_PANEL.getPreferredSize());
		ThumblainsScrollPane.setWheelScrollingEnabled(true);
		TOP_PANEL.add(ThumblainsScrollPane, BorderLayout.CENTER);

		renderer = new Thread(new Runnable() {
			int oldIndex = -1;
			int index;

			// TODO: find another way to set and clear the pages.
			// TODO: extend the DefaultListModel to add a default empty page!!,
			// may result in exceeded memory usage, IF so use a custom list
			// renderer.
			@Override
			public void run() {
				while (true) {
					index = ThumblainsScrollPane.getVerticalScrollBar().getValue()
							/ ThumblainsList.getFixedCellHeight();

					if (index != oldIndex) {
						System.out.println("index: " + index);
						try {
							int olderIndex = oldIndex;
							int diff = Math.abs(index - olderIndex);
							// pre-cleaning pages.
							if (diff > 1) {
								pages.clear();
								pages.setSize(PagesCount);
							}
							// setting new page.
							pages.set(index, getPage(index));
							oldIndex = index; // set older index in case of
												// Exception.

							pages.set(index + 1, getPage(index + 1));
							pages.set(index - 1, getPage(index - 1));

							// cleaning pages.
							int sign = olderIndex - index > 0 ? 1 : -1;
							if (diff == 1) {
								pages.set(olderIndex + sign, null);
							} else if (diff == 2) {
								pages.set(olderIndex, null);
								pages.set(olderIndex + sign, null);
							}
						} catch (IOException | ArrayIndexOutOfBoundsException ignored) {
						}
					} else {
						try {
							Thread.sleep(200);
						} catch (InterruptedException ignored) {
						}
					}
				}
			}

			/**
			 * Renders the indexed DjVu page.
			 *
			 * @param pageNo
			 *            The number of the page to render.
			 * @throws IOException
			 *             thrown when the File can't be red.
			 */
			private JPanel getPage(int pageNo) throws IOException, ArrayIndexOutOfBoundsException {
				if (pages.get(pageNo) == null) {
					JLabel tempLabel;
					JPanel tempPanel;

					tempLabel = new JLabel("" + (pageNo + 1));
					tempLabel.setHorizontalTextPosition(JLabel.CENTER);
					tempLabel.setVerticalTextPosition(JLabel.BOTTOM);
					tempLabel.setForeground(Color.GRAY);

					// create the corresponding panels
					tempPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
					tempPanel.add(tempLabel);
					tempPanel.setForeground(Color.GRAY);

					// add the images to jlabels with text
					tempLabel.setSize(PAGE_WIDHT, PAGE_HEIGHT);
					tempLabel.setIcon(new ImageIcon(CreateThumbnails.generateThumbnail(pageNo, PAGE_WIDHT, PAGE_HEIGHT)));

					return tempPanel;
				}
				return pages.get(pageNo);
			}
		});
	}

	/**
	 * Starts the renderer thread, this is done in a separate method to avoid
	 * memory reordering.
	 */
	private void start() {
		renderer.start();
	}

	public void Switch_FullBookView(Boolean Is_Continous) {
		if (Is_Continous) {
			if (Continous) {
				return;
			}
			CARD_LAYOUT.show(BEAN_PANEL, "FullBook");
			Continous = true;
			Is_Rotated = false;
		} else {

			CARD_LAYOUT.show(BEAN_PANEL, "Bean");
			Is_Rotated = false;
			Continous = false;

		}

	}

	public void rotate(final int degree) {
		CARD_LAYOUT.show(BEAN_PANEL, "Rotate");
		Is_Rotated = true;
		Continous = false;

	}

	private Boolean switchFlag(Boolean X) {
		if (X == false) {
			return true;
		} else {
			return false;
		}
	}

	public static BufferedImage rotateImage(BufferedImage src, double degrees) {
		// if (degrees==0) return src;
		double radians = Math.toRadians(degrees);

		int srcWidth = src.getWidth();
		int srcHeight = src.getHeight();

		double sin = Math.abs(Math.sin(radians));
		double cos = Math.abs(Math.cos(radians));
		int newWidth = (int) Math.floor(srcWidth * cos + srcHeight * sin);
		int newHeight = (int) Math.floor(srcHeight * cos + srcWidth * sin);

		BufferedImage result = new BufferedImage(newWidth, newHeight, src.getType());
		Graphics2D g = result.createGraphics();
		g.translate((newWidth - srcWidth) / 2, (newHeight - srcHeight) / 2);
		g.rotate(radians, srcWidth / 2, srcHeight / 2);
		g.drawRenderedImage(src, null);

		return result;
	}

	public static void setPagesCount(int pagesCount) {
		PagesCount = pagesCount;
	}

	public JPanel getTopPanel() {
		return TOP_PANEL;
	}

}
