/*
 * @(#)FormattedTextFieldTest.java 
 *
 * Copyright (c) 2004-2010 Werner Randelshofer
 * Hausmatt 10, Immensee, CH-6405, Switzerland.
 * All rights reserved.
 *
 * The copyright of this software is owned by Werner Randelshofer. 
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * Werner Randelshofer. For details see accompanying license terms. 
 */

package test;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.*;

/**
 * FormattedTextFieldTest.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class FormattedTextFieldTest extends javax.swing.JPanel {
    
    /** Creates new form. */
    public FormattedTextFieldTest() {
        initComponents();
        
        remove(doubleField);
      
        doubleField = new JFormattedTextField() {
            public void processFocusEvent(FocusEvent e) {
                if (e.getID() == FocusEvent.FOCUS_GAINED) {
                FocusListener[] listeners = getFocusListeners();
                for (int i=0; i < listeners.length; i++) {
                    FocusListener listener = listeners[i];
                    int id = e.getID();
                    switch(id) {
                        case FocusEvent.FOCUS_GAINED:
                            listener.focusGained(e);
                            break;
                        case FocusEvent.FOCUS_LOST:
                            listener.focusLost(e);
                            break;
                    }
                }
                } else {
                    super.processFocusEvent(e);
                }
            }
        };
        doubleField.setValue(new Double(Math.PI));
        GridBagConstraints gridBagConstraints;
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(doubleField, gridBagConstraints);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        editableField = new javax.swing.JFormattedTextField();
        editableLabel = new javax.swing.JLabel();
        dateField = new javax.swing.JFormattedTextField();
        dateLabel = new javax.swing.JLabel();
        integerField = new javax.swing.JFormattedTextField();
        integerLabel = new javax.swing.JLabel();
        doubleField = new javax.swing.JFormattedTextField();
        doubleLabel = new javax.swing.JLabel();
        nonEditableField = new javax.swing.JFormattedTextField();
        nonEditableLabel = new javax.swing.JLabel();
        disabledField = new javax.swing.JFormattedTextField();
        disabledLabel = new javax.swing.JLabel();
        separator = new javax.swing.JSeparator();
        smallEditableField = new javax.swing.JFormattedTextField();
        smallLabel = new javax.swing.JLabel();
        smallNonEditableField = new javax.swing.JFormattedTextField();
        smallDisabledField = new javax.swing.JFormattedTextField();
        editableField1 = new javax.swing.JFormattedTextField();
        editableLabel1 = new javax.swing.JLabel();
        separator1 = new javax.swing.JSeparator();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 17, 17, 17));
        setLayout(new java.awt.GridBagLayout());

        editableField.setText("Ångström H");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        add(editableField, gridBagConstraints);

        editableLabel.setText("Editable");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(editableLabel, gridBagConstraints);

        dateField.setValue(new Date());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        add(dateField, gridBagConstraints);

        dateLabel.setText("Date Formatted");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(dateLabel, gridBagConstraints);

        integerField.setValue(new Integer(42));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        add(integerField, gridBagConstraints);

        integerLabel.setText("Integer Formatted");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(integerLabel, gridBagConstraints);

        doubleField.setValue(new Double(Math.PI));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        add(doubleField, gridBagConstraints);

        doubleLabel.setText("processFocusEvent overriden");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(doubleLabel, gridBagConstraints);

        nonEditableField.setEditable(false);
        nonEditableField.setText("Ångström H");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        add(nonEditableField, gridBagConstraints);

        nonEditableLabel.setText("Non-Editable");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(nonEditableLabel, gridBagConstraints);

        disabledField.setText("Ångström H");
        disabledField.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        add(disabledField, gridBagConstraints);

        disabledLabel.setText("Disabled");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(disabledLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 12, 0);
        add(separator, gridBagConstraints);

        smallEditableField.setText("Ångström H");
        smallEditableField.setFont(new java.awt.Font("Lucida Grande", 0, 11));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        add(smallEditableField, gridBagConstraints);

        smallLabel.setFont(new java.awt.Font("Lucida Grande", 0, 11));
        smallLabel.setText("Small Size");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(smallLabel, gridBagConstraints);

        smallNonEditableField.setEditable(false);
        smallNonEditableField.setText("Ångström H");
        smallNonEditableField.setFont(new java.awt.Font("Lucida Grande", 0, 11));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        add(smallNonEditableField, gridBagConstraints);

        smallDisabledField.setText("Ångström H");
        smallDisabledField.setEnabled(false);
        smallDisabledField.setFont(new java.awt.Font("Lucida Grande", 0, 11));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        add(smallDisabledField, gridBagConstraints);

        editableField1.setBackground(new java.awt.Color(181, 212, 107));
        editableField1.setBorder(null);
        editableField1.setText("Ångström H");
        editableField1.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        gridBagConstraints.weighty = 1.0;
        add(editableField1, gridBagConstraints);

        editableLabel1.setText("Borderless and Colored");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE_LEADING;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(editableLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 12, 0);
        add(separator1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField dateField;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JFormattedTextField disabledField;
    private javax.swing.JLabel disabledLabel;
    private javax.swing.JFormattedTextField doubleField;
    private javax.swing.JLabel doubleLabel;
    private javax.swing.JFormattedTextField editableField;
    private javax.swing.JFormattedTextField editableField1;
    private javax.swing.JLabel editableLabel;
    private javax.swing.JLabel editableLabel1;
    private javax.swing.JFormattedTextField integerField;
    private javax.swing.JLabel integerLabel;
    private javax.swing.JFormattedTextField nonEditableField;
    private javax.swing.JLabel nonEditableLabel;
    private javax.swing.JSeparator separator;
    private javax.swing.JSeparator separator1;
    private javax.swing.JFormattedTextField smallDisabledField;
    private javax.swing.JFormattedTextField smallEditableField;
    private javax.swing.JLabel smallLabel;
    private javax.swing.JFormattedTextField smallNonEditableField;
    // End of variables declaration//GEN-END:variables
    
}
