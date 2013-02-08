/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */

package javafx.scene.control;

import javafx.css.StyleableIntegerProperty;
import javafx.css.CssMetaData;
import javafx.css.StyleOrigin;
import com.sun.javafx.css.converters.SizeConverter;
import com.sun.javafx.scene.control.skin.PaginationSkin;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.beans.DefaultProperty;
import javafx.beans.property.*;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;
import javafx.scene.Node;
import javafx.util.Callback;

/**
 * <p>
 * A Pagination control is used for navigation between pages of a single content,
 * which has been divided into smaller parts.
 * </p>
 *
 * <h3>Styling the page indicators</h3>
 * <p>
 * The control can be customized to display numeric page indicators or bullet style indicators by
 * setting the style class {@link #STYLE_CLASS_BULLET}.  The
 * {@link #maxPageIndicatorCountProperty() maxPageIndicatorCountProperty} can be used to change
 * the maximum number of page indicators.  The property value can also be changed
 * via CSS using -fx-max-page-indicator-count.
 *</p>
 *
 * <h3>Page count</h3>
 * <p>
 * The {@link #pageCountProperty() pageCountProperty} controls the number of
 * pages this pagination control has.  If the page count is
 * not known {@link #INDETERMINATE} should be used as the page count.
 * </p>
 *
 * <h3>Page factory</h3>
 * <p>
 * The {@link #pageFactoryProperty() pageFactoryProperty} is a callback function
 * that is called when a page has been selected by the application or
 * the user.  The function is required for the functionality of the pagination
 * control.  The callback function should load and return the contents of the selected page.
 * Null should be returned if the selected page index does not exist.
 * </p>
 *
 * <h3>Creating a Pagination control:</h3>
 * <p>
 * A simple example of how to create a pagination control with ten pages and
 * each page containing ten hyperlinks.
 * </p>
 *
 * <pre>
 * {@code
 *   Pagination pagination = new Pagination(10, 0);
 *   pagination.setPageFactory(new Callback<Integer, Node>() {
 *       public Node call(Integer pageIndex) {
 *           VBox box = new VBox(5);
 *           for (int i = 0; i < pageIndex + 10; i++) {
 *               Hyperlink link = new Hyperlink(myurls[i]);
 *               box.getChildren().add(link);
 *           }
 *           return box;
 *       }
 *   });
 * }</pre>
 * @since 2.2
 */
@DefaultProperty("pages")
public class Pagination extends Control {

    private static final int DEFAULT_MAX_PAGE_INDICATOR_COUNT = 10;

    /**
     * The style class to change the numeric page indicators to
     * bullet indicators.
     */
    public static final String STYLE_CLASS_BULLET = "bullet";

    /**
     * Value for indicating that the page count is indeterminate.
     *
     * @see #setPageCount
     */
    public static final int INDETERMINATE = Integer.MAX_VALUE;

    /**
     * Constructs a new Pagination control with the specified page count
     * and page index.
     *
     * @param pageCount the number of pages for the pagination control
     * @param pageIndex the index of the first page.
     *
     */
    public Pagination(int pageCount, int pageIndex) {
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
        setPageCount(pageCount);
        setCurrentPageIndex(pageIndex);
    }

    /**
     * Constructs a new Pagination control with the specified page count.
     *
     * @param pageCount the number of pages for the pagination control
     *
     */
    public Pagination(int pageCount) {
        this(pageCount, 0);
    }

    /**
     * Constructs a Pagination control with an {@link #INDETERMINATE} page count
     * and a page index equal to zero.
     */
    public Pagination() {
        this(INDETERMINATE, 0);
    }

    /***************************************************************************
     *                                                                         *
     * Properties                                                              *
     *                                                                         *
     **************************************************************************/

    private int oldMaxPageIndicatorCount = DEFAULT_MAX_PAGE_INDICATOR_COUNT;
    private IntegerProperty maxPageIndicatorCount;

    /**
     * Sets the maximum number of page indicators.
     *
     * @param value the number of page indicators.  The default is 10.
     */
    public final void setMaxPageIndicatorCount(int value) { maxPageIndicatorCountProperty().set(value); }

    /**
     * Returns the maximum number of page indicators.
     */
    public final int getMaxPageIndicatorCount() {
        return maxPageIndicatorCount == null ? DEFAULT_MAX_PAGE_INDICATOR_COUNT : maxPageIndicatorCount.get();
    }

    /**
     * The maximum number of page indicators to use for this pagination control.
     * The maximum number of pages indicators will remain unchanged if the value is less than 1
     * or greater than the {@link #pageCount}.  The number of page indicators will be
     * reduced to fit the control if the {@code maxPageIndicatorCount} cannot fit.
     *
     * The default is 10 page indicators.
     */
    public final IntegerProperty maxPageIndicatorCountProperty() {
        if (maxPageIndicatorCount == null) {
            maxPageIndicatorCount = new StyleableIntegerProperty(DEFAULT_MAX_PAGE_INDICATOR_COUNT) {

                @Override protected void invalidated() {
                    if (!maxPageIndicatorCount.isBound()) {
                        if (getMaxPageIndicatorCount() < 1 || getMaxPageIndicatorCount() > getPageCount()) {
                            setMaxPageIndicatorCount(oldMaxPageIndicatorCount);
                        }
                        oldMaxPageIndicatorCount = getMaxPageIndicatorCount();
                    }
                }

                @Override
                public CssMetaData<Pagination,Number> getCssMetaData() {
                    return StyleableProperties.MAX_PAGE_INDICATOR_COUNT;
                }

                @Override
                public Object getBean() {
                    return Pagination.this;
                }

                @Override
                public String getName() {
                    return "maxPageIndicatorCount";
                }
            };
        }
        return maxPageIndicatorCount;
    }

    private int oldPageCount = INDETERMINATE;
    private IntegerProperty pageCount = new SimpleIntegerProperty(this, "pageCount", INDETERMINATE) {
        @Override protected void invalidated() {
            if (!pageCount.isBound()) {
                if (getPageCount() < 1) {
                    setPageCount(oldPageCount);
                }
                oldPageCount = getPageCount();
            }
        }
    };

    /**
     * Sets the number of pages.
     *
     * @param value the number of pages
     */
    public final void setPageCount(int value) { pageCount.set(value); }

    /**
     * Returns the number of pages.
     */
    public final int getPageCount() { return pageCount.get(); }

    /**
     * The number of pages for this pagination control.  This
     * value must be greater than or equal to 1. {@link #INDETERMINATE}
     * should be used as the page count if the total number of pages is unknown.
     *
     * The default is an {@link #INDETERMINATE} number of pages.
     */
    public final IntegerProperty pageCountProperty() { return pageCount; }

    private final IntegerProperty currentPageIndex = new SimpleIntegerProperty(this, "currentPageIndex", 0) {
        @Override protected void invalidated() {
            if (!currentPageIndex.isBound()) {
                if (getCurrentPageIndex() < 0) {
                    setCurrentPageIndex(0);
                } else if (getCurrentPageIndex() > getPageCount() - 1) {
                    setCurrentPageIndex(getPageCount() - 1);
                }
            }
        }
    };

    /**
     * Sets the current page index.
     * @param value the current page index.
     */
    public final void setCurrentPageIndex(int value) { currentPageIndex.set(value); }

    /**
     * Returns the current page index.
     */
    public final int getCurrentPageIndex() { return currentPageIndex.get(); }

    /**
     * The current page index to display for this pagination control.  The first page will be
     * the current page if the value is less than 0.  Similarly the last page
     * will be the current page if the value is greater than the {@link #pageCount}
     *
     * The default is 0 for the first page.
     */
    public final IntegerProperty currentPageIndexProperty() { return currentPageIndex; }

    private ObjectProperty<Callback<Integer, Node>> pageFactory =
            new SimpleObjectProperty<Callback<Integer, Node>>(this, "pageFactory");

    /**
     * Sets the page factory callback function.
     */
    public final void setPageFactory(Callback<Integer, Node> value) { pageFactory.set(value); }

    /**
     * Returns the page factory callback function.
     */
    public final Callback<Integer, Node> getPageFactory() {return pageFactory.get(); }

    /**
     * The pageFactory callback function that is called when a page has been
     * selected by the application or the user.
     *
     * This function is required for the functionality of the pagination
     * control.  The callback function should load and return the contents the page index.
     * Null should be returned if the page index does not exist.  The currentPageIndex
     * will not change when null is returned.
     *
     * The default is null if there is no page factory set.
     */
    public final ObjectProperty<Callback<Integer, Node>> pageFactoryProperty() { return pageFactory; }


    /***************************************************************************
     *                                                                         *
     * Methods                                                                 *
     *                                                                         *
     **************************************************************************/

    /** {@inheritDoc} */
    @Override protected Skin<?> createDefaultSkin() {
        return new PaginationSkin(this);
    }

    /***************************************************************************
     *                                                                         *
     *                         Stylesheet Handling                             *
     *                                                                         *
     **************************************************************************/

    private static final String DEFAULT_STYLE_CLASS = "pagination";

    private static class StyleableProperties {
        private static final CssMetaData<Pagination,Number> MAX_PAGE_INDICATOR_COUNT =
            new CssMetaData<Pagination,Number>("-fx-max-page-indicator-count",
                SizeConverter.getInstance(), DEFAULT_MAX_PAGE_INDICATOR_COUNT) {

            @Override
            public void set(Pagination node, Number value, StyleOrigin origin) {
                super.set(node, value.intValue(), origin);
            }

            @Override
            public boolean isSettable(Pagination n) {
                return n.maxPageIndicatorCount == null || !n.maxPageIndicatorCount.isBound();
            }

            @Override
            public StyleableProperty<Number> getStyleableProperty(Pagination n) {
                return (StyleableProperty<Number>)n.maxPageIndicatorCountProperty();
            }
        };
        private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;
        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables =
                new ArrayList<CssMetaData<? extends Styleable, ?>>(Control.getClassCssMetaData());
            styleables.add(MAX_PAGE_INDICATOR_COUNT);
            STYLEABLES = Collections.unmodifiableList(styleables);
        }
    }

    /**
     * @return The CssMetaData associated with this class, which may include the
     * CssMetaData of its super classes.
     */
    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return StyleableProperties.STYLEABLES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return getClassCssMetaData();
    }

}
