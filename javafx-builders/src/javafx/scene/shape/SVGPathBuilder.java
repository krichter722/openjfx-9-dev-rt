/* 
 * Copyright (c) 2011, 2013, Oracle and/or its affiliates. All rights reserved.
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
 */

package javafx.scene.shape;

/**
Builder class for javafx.scene.shape.SVGPath
@see javafx.scene.shape.SVGPath
@deprecated This class is deprecated and will be removed in the next version
* @since JavaFX 2.0
*/
@javax.annotation.Generated("Generated by javafx.builder.processor.BuilderProcessor")
@Deprecated
public class SVGPathBuilder<B extends javafx.scene.shape.SVGPathBuilder<B>> extends javafx.scene.shape.ShapeBuilder<B> implements javafx.util.Builder<javafx.scene.shape.SVGPath> {
    protected SVGPathBuilder() {
    }
    
    /** Creates a new instance of SVGPathBuilder. */
    @SuppressWarnings({"deprecation", "rawtypes", "unchecked"})
    public static javafx.scene.shape.SVGPathBuilder<?> create() {
        return new javafx.scene.shape.SVGPathBuilder();
    }
    
    private int __set;
    public void applyTo(javafx.scene.shape.SVGPath x) {
        super.applyTo(x);
        int set = __set;
        if ((set & (1 << 0)) != 0) x.setContent(this.content);
        if ((set & (1 << 1)) != 0) x.setFillRule(this.fillRule);
    }
    
    private java.lang.String content;
    /**
    Set the value of the {@link javafx.scene.shape.SVGPath#getContent() content} property for the instance constructed by this builder.
    */
    @SuppressWarnings("unchecked")
    public B content(java.lang.String x) {
        this.content = x;
        __set |= 1 << 0;
        return (B) this;
    }
    
    private javafx.scene.shape.FillRule fillRule;
    /**
    Set the value of the {@link javafx.scene.shape.SVGPath#getFillRule() fillRule} property for the instance constructed by this builder.
    */
    @SuppressWarnings("unchecked")
    public B fillRule(javafx.scene.shape.FillRule x) {
        this.fillRule = x;
        __set |= 1 << 1;
        return (B) this;
    }
    
    /**
    Make an instance of {@link javafx.scene.shape.SVGPath} based on the properties set on this builder.
    */
    public javafx.scene.shape.SVGPath build() {
        javafx.scene.shape.SVGPath x = new javafx.scene.shape.SVGPath();
        applyTo(x);
        return x;
    }
}
