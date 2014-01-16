/*
 * Copyright (C) 2013 by Array Systems Computing Inc. http://www.array.ca
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option)
 * any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, see http://www.gnu.org/licenses/
 */
package org.esa.beam.visat.toolviews.cbir;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
    Labeling Panel
 */
public class LabelingPanel extends TaskPanel {

    private final static String instructionsStr = "Click and drag patches in the relevant list and drop into the irrelevant list";

    public LabelingPanel() {
        super("Labeling");

        createPanel();

        repaint();
    }

    public void returnFromLaterStep() {
    }

    public boolean canRedisplayNextPanel() {
        return false;
    }

    public boolean hasNextPanel() {
        return true;
    }

    public boolean canFinish() {
        return false;
    }

    public TaskPanel getNextPanel() {
        return new QueryPanel();
    }

    public boolean validateInput() {
        return true;
    }

    private void createPanel() {

        final JPanel instructPanel = new JPanel(new BorderLayout(2, 2));
        instructPanel.add(createTitleLabel(), BorderLayout.NORTH);
        instructPanel.add(createTextPanel(null, instructionsStr), BorderLayout.CENTER);
        this.add(instructPanel, BorderLayout.NORTH);

        final JPanel relPanel = new JPanel(new BorderLayout(2, 2));
        relPanel.setBorder(BorderFactory.createTitledBorder("Relevant Images"));

        final PatchDrawer drawer = new PatchDrawer(2500, 100);
        final JScrollPane scrollPane1 = new JScrollPane(drawer, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                                                                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        final DragScrollListener dl = new DragScrollListener(drawer);
        drawer.addMouseListener(dl);
        drawer.addMouseMotionListener(dl);

        relPanel.add(scrollPane1, BorderLayout.NORTH);

        final JPanel irrelPanel = new JPanel(new BorderLayout(2, 2));
        irrelPanel.setBorder(BorderFactory.createTitledBorder("Irrelevant Images"));

        final PatchDrawer drawer2 = new PatchDrawer(2500, 100);
        final JScrollPane scrollPane2 = new JScrollPane(drawer2, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                                                                 JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        final DragScrollListener dl2 = new DragScrollListener(drawer2);
        drawer2.addMouseListener(dl2);
        drawer2.addMouseMotionListener(dl2);

        irrelPanel.add(scrollPane2, BorderLayout.NORTH);

        final JPanel listsPanel = new JPanel();
        final BoxLayout layout = new BoxLayout(listsPanel, BoxLayout.Y_AXIS);
        listsPanel.setLayout(layout);
        listsPanel.add(relPanel);
        listsPanel.add(irrelPanel);

        this.add(listsPanel, BorderLayout.SOUTH);
    }
}