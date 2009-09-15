/*
 * @(#)QuaquaLeopardFileChooserLAF.java  1.0  2009-04-01
 *
 * Copyright (c) 2009 Werner Randelshofer
 * Staldenmattweg 2, Immensee, CH-6405, Switzerland.
 * All rights reserved.
 *
 * The copyright of this software is owned by Werner Randelshofer. 
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * Werner Randelshofer. For details see accompanying license terms. 
 */
package ch.randelshofer.quaqua.subset;

import ch.randelshofer.quaqua.*;
import ch.randelshofer.quaqua.color.AlphaColorUIResource;
import ch.randelshofer.quaqua.color.InactivatableColorUIResource;
import ch.randelshofer.quaqua.util.*;
import javax.swing.*;
import javax.swing.plaf.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.net.*;
import java.security.*;

/**
 * The QuaquaLeopardFileChooserLAF is an extension for Apple's Aqua Look and Feel
 * for Java 1.5 on Mac OS X 10.5 (Leopard). 
 * <p>
 * The Quaqua Look and Feel can not be used on other platforms than Mac OS X.
 * <p>
 * <b>Important:</b> This class is a cut down version of the
 * Quaqua15LeopardLookAndFeel. It is for use in environments, where the size of
 * the whole Quaqua look and feel would be too excessive.
 * <p>
 * <h3>Fixes and Enhancements</h3>
 * This class provides the following bug fixes end enhancements to Apple's Aqua
 * Look and Feel:
 *
 * <h4>FileChooserUI</h4>
 * <ul>
 * <li>FileChooserUI uses a column view similar to the native file dialog of
 * Mac OS X 10.5 Leopard.</li>
 * <li>The FileChooserUI resolves aliases to files and folders. </li>
 * </ul>
 *
 * <h3>Usage</h3>
 * Please use the <code>QuaquaManager</code> to activate this look and feel in
 * your application. Or use the generic <code>QuaquaLookAndFeel</code>. Both
 * are designed to automatically detect the appropriate Quaqua Look and Feel
 * implementation for current Java VM.
 *
 * @see QuaquaManager
 * @see QuaquaLookAndFeel
 *
 * @author Werner Randelshofer
 * @version 1.0 2009-04-01  Created.
 */
public class QuaquaLeopardFileChooserLAF extends LookAndFeelProxy {

    protected final static String commonDir = "/ch/randelshofer/quaqua/images/";
    protected final static String jaguarDir = "/ch/randelshofer/quaqua/jaguar/images/";
    protected final static String leopardDir = "/ch/randelshofer/quaqua/leopard/images/";
    /**
     * Holds a bug fixed version of the UIDefaults provided by the target
     * LookAndFeel.
     * @see #initialize
     * @see #getDefaults
     */
    private UIDefaults myDefaults;
    /**
     * The small system font (Lucida Grande Regular 11 pt) is used for
     * informative text in alerts. It is also the default font for column
     * headings in lists, for help tags, and for small controls. You can also
     * use it to provide additional information about settings in various
     * windows, such as the QuickTime pane in System Preferences.
     */
    protected static final FontUIResource SMALL_SYSTEM_FONT =
            new FontUIResource("Lucida Grande", Font.PLAIN, 11);

    /**
     * Creates a new instance.
     */
    public QuaquaLeopardFileChooserLAF() {
        String targetClassName = "apple.laf.AquaLookAndFeel";
        try {
            setTarget((LookAndFeel) Class.forName(targetClassName).newInstance());
        } catch (Exception e) {
            throw new InternalError(
                    "Unable to instanciate target Look and Feel \"" + targetClassName + "\". " + e.getMessage());
        }
    }

    /**
     * Return a one line description of this look and feel implementation,
     * e.g. "The CDE/Motif Look and Feel".   This string is intended for
     * the user, e.g. in the title of a window or in a ToolTip message.
     */
    public String getDescription() {
        return "The Quaqua Leopard FileChooser Look and Feel for Java 1.5";
    }

    /**
     * Return a short string that identifies this look and feel, e.g.
     * "CDE/Motif".  This string should be appropriate for a menu item.
     * Distinct look and feels should have different names, e.g.
     * a subclass of MotifLookAndFeel that changes the way a few components
     * are rendered should be called "CDE/Motif My Way"; something
     * that would be useful to a user trying to select a L&F from a list
     * of names.
     */
    public String getName() {
        return "Quaqua FileChooser-only LAF";
    }

    /**
     * UIManager.setLookAndFeel calls this method before the first
     * call (and typically the only call) to getDefaults().  Subclasses
     * should do any one-time setup they need here, rather than
     * in a static initializer, because look and feel class uiDefaults
     * may be loaded just to discover that isSupportedLookAndFeel()
     * returns false.
     *
     * @see #uninitialize
     * @see UIManager#setLookAndFeel
     */
    public void initialize() {
        // Note: We initialize in a privileged block, because if we are
        //       installed as a Standard Extension in the Java VM, we
        //       are allowed to access our resources (i.e. images),
        //       even then, when the calling application is not allowed
        //       to do so.
        AccessController.doPrivileged(new PrivilegedAction() {

            public Object run() {
                target.initialize();
                myDefaults = target.getDefaults();
                initResourceBundle(myDefaults);
                initClassDefaults(myDefaults);
                initFontDefaults(myDefaults);
                initGeneralDefaults(myDefaults);
                initComponentDefaults(myDefaults);
                return null;
            }
        });
    }

    /**
     * This method is called once by UIManager.setLookAndFeel to create
     * the look and feel specific defaults table.  Other applications,
     * for example an application builder, may also call this method.
     *
     * @see #initialize
     * @see #uninitialize
     * @see UIManager#setLookAndFeel
     */
    public UIDefaults getDefaults() {
        return myDefaults;
    }

    protected void initResourceBundle(UIDefaults table) {
        // The following line of code does not work, when Quaqua has been loaded with
        // a custom class loader. That's why, we have to inject the labels
        // by ourselves:
        //table.addResourceBundle( "ch.randelshofer.quaqua.Labels" );
        ResourceBundle bundle = ResourceBundle.getBundle(
                "ch.randelshofer.quaqua.Labels",
                Locale.getDefault(),
                getClass().getClassLoader());
        for (Enumeration i = bundle.getKeys(); i.hasMoreElements();) {
            String key = (String) i.nextElement();
            table.put(key, bundle.getObject(key));
        }
    }

    /**
     * Initialize the uiClassID to BasicComponentUI mapping.
     * The JComponent classes define their own uiClassID constants
     * (see AbstractComponent.getUIClassID).  This table must
     * map those constants to a BasicComponentUI class of the
     * appropriate type.
     *
     * @see #getDefaults
     */
    protected void initClassDefaults(UIDefaults table) {
        String basicPrefix = "javax.swing.plaf.basic.Basic";
        String quaquaPrefix = "ch.randelshofer.quaqua.Quaqua";
        String quaquaLeopardPrefix = "ch.randelshofer.quaqua.leopard.QuaquaLeopard";

        // NOTE: Uncomment parts of the code below, to override additional
        // UI classes of the target look and feel.
        Object[] uiDefaults = {
            "BrowserUI", quaquaPrefix + "BrowserUI",
            "FileChooserUI", quaquaLeopardPrefix + "FileChooserUI",};
        table.putDefaults(uiDefaults);
    }

    protected void initGeneralDefaults(UIDefaults table) {
        Object[] uiDefaults;
        uiDefaults = new Object[]{
                    "ClassLoader", getClass().getClassLoader(),};
        table.putDefaults(uiDefaults);
    }

    /**
     * Returns the base font for which system fonts are derived.
     * This is Lucida Grande, Plain, 13.
     */
    protected Font getBaseSystemFont() {
        return new Font("Lucida Grande", Font.PLAIN, 13);
    }

    protected void initFontDefaults(UIDefaults table) {
        Font baseSystemFont = getBaseSystemFont();

        // *** Shared Fonts
        // Some of the following comments have been taken from Apples Human Interface
        // Guidelines, Revision 2004-12-02.
        float fourteen = 14f;
        float thirteen = 13f;
        float twelve = 12f;
        float eleven = 11f;
        float ten = 11f;
        float nine = 9f;
        int fontPlain = Font.PLAIN;
        int fontBold = Font.BOLD;
        // The system font (Lucida Grande Regular 13 pt) is used for text in
        // menus, dialogs, and full-size controls.
        Object systemFont = new UIDefaults.ProxyLazyValue(
                "javax.swing.plaf.FontUIResource",
                null,
                new Object[]{baseSystemFont.deriveFont(fontPlain, thirteen)});
        // Use the emphasized system font (Lucida Grande Bold 13 pt) sparingly. It
        // is used for the message text in alerts.
        Object emphasizedSystemFont = new UIDefaults.ProxyLazyValue(
                "javax.swing.plaf.FontUIResource",
                null,
                new Object[]{baseSystemFont.deriveFont(fontBold, thirteen)});
        // The small system font (Lucida Grande Regular 11 pt) is used for
        // informative text in alerts. It is also the default font for column
        // headings in lists, for help tags, and for small controls. You can also
        // use it to provide additional information about settings in various
        // windows, such as the QuickTime pane in System Preferences.
        Object smallSystemFont = new UIDefaults.ProxyLazyValue(
                "javax.swing.plaf.FontUIResource",
                null,
                new Object[]{baseSystemFont.deriveFont(fontPlain, eleven)});
        // Use the emphasized small system font (Lucida Grande Bold 11 pt)
        // sparingly. You might use it to title a group of settings that appear
        // without a group box, or for brief informative text below a text field.
        Object emphasizedSmallSystemFont = new UIDefaults.ProxyLazyValue(
                "javax.swing.plaf.FontUIResource",
                null,
                new Object[]{baseSystemFont.deriveFont(fontBold, eleven)});
        // The mini system font (Lucida Grande Regular 9 pt) is used for mini
        // controls. It can also be used for utility window labels and text.
        Object miniSystemFont = new UIDefaults.ProxyLazyValue(
                "javax.swing.plaf.FontUIResource",
                null,
                new Object[]{baseSystemFont.deriveFont(fontPlain, nine)});
        // An emphasized mini system font (Lucida Grande Bold 9 pt) is available for
        // cases in which the emphasized small system font is too large.
        Object emphasizedMiniSystemFont = new UIDefaults.ProxyLazyValue(
                "javax.swing.plaf.FontUIResource",
                null,
                new Object[]{baseSystemFont.deriveFont(fontBold, nine)});
        // If your application creates text documents, use the application font
        // (Lucida Grande Regular 13 pt) as the default font for user-created
        // content.
        Object applicationFont = baseSystemFont;
        // The label font (Lucida Grande Regular 10 pt) is used for the labels on
        // toolbar buttons and to label tick marks on full-size sliders. You should
        // rarely need to use this font. For an example of this font used to label a
        // slider controlBackground, see the Spoken User Interface pane in Speech preferences.
        Object labelFont = new UIDefaults.ProxyLazyValue(
                "javax.swing.plaf.FontUIResource",
                null,
                new Object[]{baseSystemFont.deriveFont(fontPlain, ten)});
        // Use the view font (Lucida Grande Regular 12pt) as the default font of
        // text in lists and tables.
        Object viewFont = new UIDefaults.ProxyLazyValue(
                "javax.swing.plaf.FontUIResource",
                null,
                new Object[]{baseSystemFont.deriveFont(fontPlain, twelve)});
        // The menu font (Lucida Grande Regular 14 pt) is used for text in menus and
        // window title bars.
        Object menuFont = new UIDefaults.ProxyLazyValue(
                "javax.swing.plaf.FontUIResource",
                null,
                new Object[]{baseSystemFont.deriveFont(fontPlain, fourteen)});

        // Set font sizes according to default size style.
        if (QuaquaManager.getProperty("Quaqua.sizeStyle", "regular").equals("small")) {
            viewFont = smallSystemFont;
            systemFont = smallSystemFont;
            emphasizedSystemFont = emphasizedSmallSystemFont;
            //smallSystemFont = smallSystemFont;
            menuFont = smallSystemFont;
            applicationFont = smallSystemFont;
            labelFont = labelFont;
        }

        Object[] uiDefaults = {
            "SystemFont", systemFont,
            "EmphasizedSystemFont", emphasizedSystemFont,
            "SmallSystemFont", smallSystemFont,
            "EmphasizedSmallSystemFont", emphasizedSmallSystemFont,
            "MiniSystemFont", miniSystemFont,
            "EmphasizedMiniSystemFont", miniSystemFont,
            "ApplicationFont", applicationFont,
            "LabelFont", labelFont,
            "ViewFont", viewFont,
            "MenuFont", menuFont,
            "Browser.font", viewFont,
            "Button.font", systemFont,
            "Button.smallFont", smallSystemFont, // Maybe we should use Component.smallFont instead?

            "FileChooser.previewLabelFont", emphasizedSmallSystemFont,
            "FileChooser.previewValueFont", smallSystemFont,};

        putDefaults(table, uiDefaults);
    }

    protected void initComponentDefaults(UIDefaults table) {
        Font smallSystemFont = SMALL_SYSTEM_FONT;
        Color grayedFocusCellBorderColor = (Color) table.get("listHighlight");
        Integer zero = new Integer(0);
        Integer one = new Integer(1);
        Integer two = new Integer(2);
        Integer three = new Integer(3);
        Integer four = new Integer(4);
        Integer five = new Integer(5);
        Integer six = new Integer(6);
        // Autovalidation
        Boolean autovalidate = new Boolean(QuaquaManager.getProperty("Quaqua.FileChooser.autovalidate", "true").equals("true"));
        String sideBarIconsStart = "/System/Library/CoreServices/CoreTypes.bundle/Contents/Resources/Toolbar";
        String sideBarIconsEnd = "FolderIcon.icns";

        Object[] uiDefaults = {
            "Browser.expandedIcon", new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.QuaquaIconFactory", "createIcon",
            new Object[]{jaguarDir + "Browser.disclosureIcons.png", six, Boolean.TRUE, zero}),
            "Browser.expandingIcon", new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.QuaquaIconFactory", "createIcon",
            new Object[]{jaguarDir + "Browser.disclosureIcons.png", six, Boolean.TRUE, one}),
            "Browser.focusedSelectedExpandedIcon", new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.QuaquaIconFactory", "createIcon",
            new Object[]{jaguarDir + "Browser.disclosureIcons.png", six, Boolean.TRUE, two}),
            "Browser.focusedSelectedExpandingIcon", new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.QuaquaIconFactory", "createIcon",
            new Object[]{jaguarDir + "Browser.disclosureIcons.png", six, Boolean.TRUE, three}),
            "Browser.selectedExpandedIcon", new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.QuaquaIconFactory", "createIcon",
            new Object[]{jaguarDir + "Browser.disclosureIcons.png", six, Boolean.TRUE, four}),
            "Browser.selectedExpandingIcon", new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.QuaquaIconFactory", "createIcon",
            new Object[]{jaguarDir + "Browser.disclosureIcons.png", six, Boolean.TRUE, five}),
            //
            "Browser.selectionBackground", new ColorUIResource(56, 117, 215),
            "Browser.selectionForeground", new ColorUIResource(255, 255, 255),
            "Browser.inactiveSelectionBackground", new ColorUIResource(208, 208, 208),
            "Browser.inactiveSelectionForeground", new ColorUIResource(0, 0, 0),
            "Browser.sizeHandleIcon", new UIDefaults.ProxyLazyValue(
            "ch.randelshofer.quaqua.QuaquaIconFactory", "createIcon",
            new Object[]{commonDir + "Browser.sizeHandleIcon.png", one, Boolean.TRUE, one}),
            "FileChooser.previewLabelForeground", new ColorUIResource(0x808080),
            "FileChooser.previewValueForeground", new ColorUIResource(0x000000),
            "FileChooser.previewLabelInsets", new InsetsUIResource(1, 0, 0, 4),
            "FileChooser.previewLabelDelimiter", "",
            "FileChooser.cellTipOrigin", new Point(18, 1),
            "FileChooser.splitPaneDividerSize", new Integer(1),
            "FileChooser.browserCellFocusBorder",
            new UIDefaults.ProxyLazyValue("javax.swing.plaf.BorderUIResource$EmptyBorderUIResource",
            new Object[]{new Insets(1, 1, 1, 1)}),
            "FileChooser.browserCellFocusBorderGrayed",
            new UIDefaults.ProxyLazyValue("javax.swing.plaf.BorderUIResource$MatteBorderUIResource",
            new Object[]{new Integer(1), new Integer(1), new Integer(1), new Integer(1), grayedFocusCellBorderColor}),
            "FileChooser.browserCellBorder",
            new UIDefaults.ProxyLazyValue("javax.swing.plaf.BorderUIResource$EmptyBorderUIResource",
            new Object[]{new Insets(1, 1, 1, 1)}),
            "FileChooser.browserCellColorLabelInsets", new InsetsUIResource(0, 1, 0, 0),
            "FileChooser.browserCellSelectedColorLabelInsets", new InsetsUIResource(0, 0, 0, 0),
            "FileChooser.browserCellTextIconGap", new Integer(5),
            "FileChooser.browserCellTextArrowIconGap", new Integer(5),
            "FileChooser.browserUseUnselectedExpandIconForLabeledFile", Boolean.TRUE,
            "FileChooser.sideBarIcon.Applications", makeNativeIcon(sideBarIconsStart + "Apps" + sideBarIconsEnd, 16),
            "FileChooser.sideBarIcon.Desktop", makeNativeIcon(sideBarIconsStart + "Desktop" + sideBarIconsEnd, 16),
            "FileChooser.sideBarIcon.Documents", makeNativeIcon(sideBarIconsStart + "Documents" + sideBarIconsEnd, 16),
            "FileChooser.sideBarIcon.Downloads", makeNativeIcon(sideBarIconsStart + "Downloads" + sideBarIconsEnd, 16),
            "FileChooser.sideBarIcon.Library", makeNativeIcon(sideBarIconsStart + "Library" + sideBarIconsEnd, 16),
            "FileChooser.sideBarIcon.Movies", makeNativeIcon(sideBarIconsStart + "Movies" + sideBarIconsEnd, 16),
            "FileChooser.sideBarIcon.Music", makeNativeIcon(sideBarIconsStart + "Music" + sideBarIconsEnd, 16),
            "FileChooser.sideBarIcon.Pictures", makeNativeIcon(sideBarIconsStart + "Pictures" + sideBarIconsEnd, 16),
            "FileChooser.sideBarIcon.Public", makeNativeIcon(sideBarIconsStart + "Public" + sideBarIconsEnd, 16),
            "FileChooser.sideBarIcon.Sites", makeNativeIcon(sideBarIconsStart + "Sites" + sideBarIconsEnd, 16),
            "FileChooser.sideBarIcon.Utilities", makeNativeIcon(sideBarIconsStart + "Utilities" + sideBarIconsEnd, 16),
            //
            "FileView.computerIcon", makeIcon(getClass(), leopardDir + "FileView.computerIcon.png"),
            "FileView.fileIcon", makeIcon(getClass(), leopardDir + "FileView.fileIcon.png"),
            "FileView.directoryIcon", makeIcon(getClass(), leopardDir + "FileView.directoryIcon.png"),
            "FileView.hardDriveIcon", makeIcon(getClass(), leopardDir + "FileView.hardDriveIcon.png"),
            "FileView.floppyDriveIcon", makeIcon(getClass(), leopardDir + "FileView.floppyDriveIcon.png"),
            //
            "FileChooser.orderByType", new Boolean(QuaquaManager.getProperty("Quaqua.FileChooser.speed") != null && QuaquaManager.getProperty("Quaqua.FileChooser.orderByType").equals("true")),
            "FileChooser.browserFocusCellHighlightBorder",
            new UIDefaults.ProxyLazyValue(
            "javax.swing.plaf.BorderUIResource$LineBorderUIResource",
            new Object[]{table.get("listHighlightBorder")}),
            "FileChooser.browserCellBorder",
            new UIDefaults.ProxyLazyValue(
            "javax.swing.plaf.BorderUIResource$EmptyBorderUIResource",
            new Object[]{new Insets(1, 1, 1, 1)}),
            "FileChooser.previewLabelForeground", new ColorUIResource(0x808080),
            "FileChooser.previewValueForeground", new ColorUIResource(0x000000),
            "FileChooser.previewLabelInsets", new InsetsUIResource(1, 0, 0, 4),
            "FileChooser.previewLabelDelimiter", "",
            "FileChooser.cellTipOrigin", new Point(18, 1),
            "FileChooser.splitPaneDividerSize", new Integer(1),
            "FileChooser.splitPaneBackground", new ColorUIResource(0xa5a5a5),
            "FileChooser.browserFocusCellHighlightBorder",
            new UIDefaults.ProxyLazyValue(
            "javax.swing.plaf.BorderUIResource$EmptyBorderUIResource",
            new Object[]{new Insets(1, 1, 1, 1)}),
            "FileChooser.browserFocusCellHighlightBorderGrayed",
            new UIDefaults.ProxyLazyValue(
            "javax.swing.plaf.BorderUIResource$MatteBorderUIResource",
            new Object[]{new Integer(1), new Integer(1), new Integer(1), new Integer(1), grayedFocusCellBorderColor}),
            "FileChooser.browserCellBorder",
            new UIDefaults.ProxyLazyValue(
            "javax.swing.plaf.BorderUIResource$EmptyBorderUIResource",
            new Object[]{new Insets(1, 1, 1, 1)}),
            "FileView.computerIcon", makeIcon(getClass(), leopardDir + "FileView.computerIcon.png"),
            "FileView.fileIcon", makeIcon(getClass(), leopardDir + "FileView.fileIcon.png"),
            "FileView.directoryIcon", makeIcon(getClass(), leopardDir + "FileView.directoryIcon.png"),
            "FileView.hardDriveIcon", makeIcon(getClass(), leopardDir + "FileView.hardDriveIcon.png"),
            "FileView.floppyDriveIcon", makeIcon(getClass(), leopardDir + "FileView.floppyDriveIcon.png"),
            "FileChooser.enforceQuaquaTreeUI", Boolean.TRUE,
            //
            "Label.embossForeground", new AlphaColorUIResource(0x7effffff),
            "Label.shadowForeground", new AlphaColorUIResource(0x7e000000),
            //
            "Tree.collapsedIcon", makeIcon(getClass(), leopardDir + "Tree.collapsedIcon.png"),
            "Tree.expandedIcon", makeIcon(getClass(), leopardDir + "Tree.expandedIcon.png"),
            "Tree.leafIcon", makeIcon(getClass(), leopardDir + "Tree.leafIcon.png"),
            "Tree.openIcon", makeIcon(getClass(), leopardDir + "Tree.openIcon.png"),
            "Tree.closedIcon", makeIcon(getClass(), leopardDir + "Tree.closedIcon.png"),
            "Tree.background.sideBar", new InactivatableColorUIResource(0xd5dde5, 0xe8e8e8),
            "Tree.selectionBorder.sideBar", new UIDefaults.ProxyLazyValue("ch.randelshofer.quaqua.leopard.QuaquaLeopardSideBarSelectionBorder"),
            "Tree.leftChildIndent", new Integer(8), // 7
            "Tree.rightChildIndent", new Integer(12), // 13
            "Tree.icons", makeIcons(leopardDir + "Tree.icons.png", 12, true),
            // CHANGE The new properties for the "sideBar" Tree style
            "Tree.sideBarCategory.foreground", new InactivatableColorUIResource(0x728194, 0x5f5f5f),
            "Tree.sideBarCategory.selectionForeground", new InactivatableColorUIResource(0xffffff, 0xffffff),
            "Tree.sideBarCategory.font", new FontUIResource("Lucida Grande", Font.BOLD, 11),
            "Tree.sideBar.foreground", new InactivatableColorUIResource(0x000000, 0x000000),
            "Tree.sideBar.selectionForeground", new InactivatableColorUIResource(0xffffff, 0xffffff),
            "Tree.sideBar.font", new FontUIResource("Lucida Grande", Font.PLAIN, 11),
            "Tree.sideBar.selectionFont", new FontUIResource("Lucida Grande", Font.BOLD, 11),
            "Tree.sideBar.icons", makeIcons(leopardDir + "Tree.sideBar.icons.png", 12, true),
            "Tree.sideBarCategory.foreground",
            new InactivatableColorUIResource(0x5c5c5c, 0x5c5c5c),
            "Tree.sideBarCategory.selectionForeground",
            new InactivatableColorUIResource(0xffffff, 0xffffff),
            "Sheet.showAsSheet", Boolean.TRUE,//
        };
        table.putDefaults(uiDefaults);
    }

    protected URL getResource(String location) {
        URL url = getClass().getResource(location);
        if (url == null) {
            throw new InternalError("image resource missing: " + location);
        }
        return url;
    }

    protected Image createImage(String location) {
        return Toolkit.getDefaultToolkit().createImage(getResource(location));
    }

    protected Icon[] makeIcons(String location, int count, boolean horizontal) {
        Icon[] icons = new Icon[count];

        BufferedImage[] images = Images.split(
                createImage(location),
                count, horizontal);

        for (int i = 0; i < count; i++) {
            icons[i] = new IconUIResource(new ImageIcon(images[i]));
        }
        return icons;
    }

    /**
     * Puts defaults into the specified UIDefaults table.
     * Honors QuaquaManager.getIncludedUIs() and QuaquaManager.getExcludedUIs().
     * 
     * @param table
     * @param keyValueList
     */
    protected void putDefaults(UIDefaults table, Object[] keyValueList) {
        Set included = QuaquaManager.getIncludedUIs();
        Set excluded = QuaquaManager.getExcludedUIs();

        if (excluded == null) {
            // everyting is excluded
            return;
        } else if (included == null && excluded.size() == 0) {
            // everyting is included, nothing is explicitly excluded
            table.putDefaults(keyValueList);
        } else if (included != null && excluded.size() == 0) {
            // something is explicitly included, nothing is explicitly excluded
            for (int i = 0; i < keyValueList.length; i += 2) {
                if (keyValueList[i] instanceof String) {
                    String name = (String) keyValueList[i];
                    int p = name.indexOf('.');
                    if (p == -1 && name.endsWith("UI")) {
                        name = name.substring(0, name.length() - 2);
                        p = 1;
                    } else if (p != -1) {
                        name = name.substring(0, p);
                    }
                    if (p == -1 || included.contains(name)) {
                        table.put(keyValueList[i], keyValueList[i + 1]);
                    }
                } else {
                    table.put(keyValueList[i], keyValueList[i + 1]);
                }
            }
        } else if (included == null) {
            // something is explicitly excluded, nothing is explicitly included
            for (int i = 0; i < keyValueList.length; i += 2) {
                table.put(keyValueList[i], keyValueList[i + 1]);
            }
        } else {
            // something is explicitly included, something is explicitly excluded
            for (int i = 0; i < keyValueList.length; i += 2) {
                table.put(keyValueList[i], keyValueList[i + 1]);
            }
        }
    }

    public static Object makeNativeIcon(String path, int size) {
        return new UIDefaults.ProxyLazyValue(
                "ch.randelshofer.quaqua.QuaquaIconFactory", "createNativeIcon",
                new Object[]{path, new Integer(size)});
    }
}
