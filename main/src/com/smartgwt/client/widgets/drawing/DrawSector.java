/*
 * Smart GWT (GWT for SmartClient)
 * Copyright 2008 and beyond, Isomorphic Software, Inc.
 *
 * Smart GWT is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.  Smart GWT is also
 * available under typical commercial license terms - see
 * http://smartclient.com/license
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */
 
package com.smartgwt.client.widgets.drawing;


import com.smartgwt.client.event.*;
import com.smartgwt.client.core.*;
import com.smartgwt.client.types.*;
import com.smartgwt.client.data.*;
import com.smartgwt.client.data.events.*;
import com.smartgwt.client.rpc.*;
import com.smartgwt.client.callbacks.*;
import com.smartgwt.client.widgets.*;
import com.smartgwt.client.widgets.events.*;
import com.smartgwt.client.widgets.form.*;
import com.smartgwt.client.widgets.form.validator.*;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.tile.*;
import com.smartgwt.client.widgets.tile.events.*;
import com.smartgwt.client.widgets.grid.*;
import com.smartgwt.client.widgets.grid.events.*;
import com.smartgwt.client.widgets.chart.*;
import com.smartgwt.client.widgets.layout.*;
import com.smartgwt.client.widgets.layout.events.*;
import com.smartgwt.client.widgets.menu.*;
import com.smartgwt.client.widgets.tab.*;
import com.smartgwt.client.widgets.toolbar.*;
import com.smartgwt.client.widgets.tree.*;
import com.smartgwt.client.widgets.tree.events.*;
import com.smartgwt.client.widgets.viewer.*;
import com.smartgwt.client.widgets.calendar.*;
import com.smartgwt.client.widgets.calendar.events.*;
import com.smartgwt.client.widgets.cube.*;
import com.smartgwt.client.widgets.drawing.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;
import com.smartgwt.client.util.*;
import com.smartgwt.client.util.workflow.*;
import com.google.gwt.event.shared.*;
import com.google.gwt.event.shared.HasHandlers;
import com.smartgwt.logicalstructure.core.*;
import com.smartgwt.logicalstructure.widgets.*;
import com.smartgwt.logicalstructure.widgets.drawing.*;
import com.smartgwt.logicalstructure.widgets.plugins.*;
import com.smartgwt.logicalstructure.widgets.form.*;
import com.smartgwt.logicalstructure.widgets.tile.*;
import com.smartgwt.logicalstructure.widgets.grid.*;
import com.smartgwt.logicalstructure.widgets.chart.*;
import com.smartgwt.logicalstructure.widgets.layout.*;
import com.smartgwt.logicalstructure.widgets.menu.*;
import com.smartgwt.logicalstructure.widgets.tab.*;
import com.smartgwt.logicalstructure.widgets.tableview.*;
import com.smartgwt.logicalstructure.widgets.toolbar.*;
import com.smartgwt.logicalstructure.widgets.tree.*;
import com.smartgwt.logicalstructure.widgets.viewer.*;
import com.smartgwt.logicalstructure.widgets.calendar.*;
import com.smartgwt.logicalstructure.widgets.cube.*;

/**
 * DrawItem subclass to render Pie Slices.
 */
public class DrawSector extends DrawItem {

    public static DrawSector getOrCreateRef(JavaScriptObject jsObj) {
        if(jsObj == null) return null;
        BaseClass obj = BaseClass.getRef(jsObj);
        if(obj != null) {
            return (DrawSector) obj;
        } else {
            return new DrawSector(jsObj);
        }
    }

    public void setJavaScriptObject(JavaScriptObject jsObj) {
        id = JSOHelper.getAttribute(jsObj, "ID");
    }



    public DrawSector(){
        scClassName = "DrawSector";
    }

    public DrawSector(JavaScriptObject jsObj){
        scClassName = "DrawSector";
        setJavaScriptObject(jsObj);
        
    }

    public native JavaScriptObject create()/*-{
        var config = this.@com.smartgwt.client.core.BaseClass::getConfig()();
        var scClassName = this.@com.smartgwt.client.core.BaseClass::scClassName;
        return $wnd.isc[scClassName].create(config);
    }-*/;

    // ********************* Properties / Attributes ***********************


    /**
     * Center point of the sector
     *
     * <br><br>If this method is called after the component has been drawn/initialized:
     * Change the center point for this sector.
     *
     * @param centerPoint left coordinate. Default value is [0,0]
     */
    public void setCenterPoint(Point centerPoint) {
        setAttribute("centerPoint", centerPoint.getJsObj(), true);
    }

    /**
     * Center point of the sector
     *
     * @return Point
     */
    public Point getCenterPoint()  {
        return new Point(getAttributeAsJavaScriptObject("centerPoint"));
    }


    /**
     * end angle of the sector
     *
     * @param endAngle endAngle Default value is 20.0
     * @throws IllegalStateException this property cannot be changed after the underlying component has been created
     */
    public void setEndAngle(float endAngle)  throws IllegalStateException {
        setAttribute("endAngle", endAngle, false);
    }

    /**
     * end angle of the sector
     *
     * @return float
     */
    public float getEndAngle()  {
        return getAttributeAsFloat("endAngle");
    }


    /**
     * Radius of the sector.
     *
     * @param radius radius Default value is 100
     * @throws IllegalStateException this property cannot be changed after the underlying component has been created
     */
    public void setRadius(int radius)  throws IllegalStateException {
        setAttribute("radius", radius, false);
    }

    /**
     * Radius of the sector.
     *
     * @return int
     */
    public int getRadius()  {
        return getAttributeAsInt("radius");
    }


    /**
     * start angle of the sector
     *
     * @param startAngle startAngle Default value is 0.0
     * @throws IllegalStateException this property cannot be changed after the underlying component has been created
     */
    public void setStartAngle(float startAngle)  throws IllegalStateException {
        setAttribute("startAngle", startAngle, false);
    }

    /**
     * start angle of the sector
     *
     * @return float
     */
    public float getStartAngle()  {
        return getAttributeAsFloat("startAngle");
    }

    // ********************* Methods ***********************
	/**
     * Move the drawSector to the specified position.
     * @param x number of pixels to move by horizontally
     * @param y number of pixels to move by vertically
     */
    public native void moveBy(int x, int y) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.moveBy(x, y);
    }-*/;
	/**
     * Move the drawSector by the specified delta
     * @param x coordinate to move to horizontally
     * @param y coordinate to move to vertically
     */
    public native void moveTo(int x, int y) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.moveTo(x, y);
    }-*/;
	/**
     * Resize by the specified delta. Note that the resize delta will be applied to each point.
     * @param dX number of pixels to resize by horizontally
     * @param dY number of pixels to resize by vertically
     */
    public native void resizeBy(int dX, int dY) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.resizeBy(dX, dY);
    }-*/;

    // ********************* Static Methods ***********************
    /**
     * Class level method to set the default properties of this class. If set, then all subsequent instances of this
     * class will automatically have the default properties that were set when this method was called. This is a powerful
     * feature that eliminates the need for users to create a separate hierarchy of subclasses that only alter the default
     * properties of this class. Can also be used for skinning / styling purposes.
     * <P>
     * <b>Note:</b> This method is intended for setting default attributes only and will effect all instances of the
     * underlying class (including those automatically generated in JavaScript).
     * This method should not be used to apply standard EventHandlers or override methods for
     * a class - use a custom subclass instead.
     *
     * @param drawSectorProperties properties that should be used as new defaults when instances of this class are created
     */
    public static native void setDefaultProperties(DrawSector drawSectorProperties) /*-{
    	var properties = $wnd.isc.addProperties({},drawSectorProperties.@com.smartgwt.client.core.BaseClass::getConfig()());
    	delete properties.ID;
        $wnd.isc.DrawSector.addProperties(properties);
    }-*/;

    // ***********************************************************


    

}




