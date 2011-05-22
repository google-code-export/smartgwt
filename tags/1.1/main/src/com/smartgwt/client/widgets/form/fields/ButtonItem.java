/*
 * SmartGWT (GWT for SmartClient)
 * Copyright 2008 and beyond, Isomorphic Software, Inc.
 *
 * SmartGWT is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.  SmartGWT is also
 * available under typical commercial license terms - see
 * http://smartclient.com/license
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */
 
package com.smartgwt.client.widgets.form.fields;



import com.smartgwt.client.event.*;
import com.smartgwt.client.core.*;
import com.smartgwt.client.types.*;
import com.smartgwt.client.data.*;
import com.smartgwt.client.data.events.*;
import com.smartgwt.client.rpc.*;
import com.smartgwt.client.widgets.*;
import com.smartgwt.client.widgets.events.*;
import com.smartgwt.client.widgets.form.*;
import com.smartgwt.client.widgets.form.validator.*;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.tile.*;
import com.smartgwt.client.widgets.tile.events.*;
import com.smartgwt.client.widgets.grid.*;
import com.smartgwt.client.widgets.grid.events.*;
import com.smartgwt.client.widgets.layout.*;
import com.smartgwt.client.widgets.menu.*;
import com.smartgwt.client.widgets.tab.*;
import com.smartgwt.client.widgets.toolbar.*;
import com.smartgwt.client.widgets.tree.*;
import com.smartgwt.client.widgets.tree.events.*;
import com.smartgwt.client.widgets.viewer.*;
import com.smartgwt.client.widgets.calendar.*;
import com.smartgwt.client.widgets.calendar.events.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.util.EnumUtil;
import com.google.gwt.event.shared.*;
import com.google.gwt.event.shared.HasHandlers;
   /**
    * FormItem for adding a Button to a form.

    */

public class ButtonItem extends CanvasItem  implements com.smartgwt.client.widgets.form.fields.events.HasClickHandlers {

    public static ButtonItem getOrCreateRef(JavaScriptObject jsObj) {
        if(jsObj == null) return null;
        RefDataClass obj = RefDataClass.getRef(jsObj);
        if(obj != null) {
            obj.setJsObj(jsObj);
            return (ButtonItem) obj;
        } else {
            return new ButtonItem(jsObj);
        }
    }


    public ButtonItem(){
        setType("ButtonItem");
    }

    public ButtonItem(JavaScriptObject jsObj){
        super(jsObj);
    }

    public ButtonItem(String name) {
        setName(name);
        setType("ButtonItem");
    }

    public ButtonItem(String name, String title) {
        setName(name);
		setTitle(title);
        setType("ButtonItem");
    }

    // ********************* Properties / Attributes ***********************

    /**
    * Optional <code>baseStyle</code> will be applied to the button.
    *
    * @param baseStyle baseStyle Default value is null
    */
    public void setBaseStyle(String baseStyle) {
        setAttribute("baseStyle", baseStyle);
    }
    /**
     * Optional <code>baseStyle</code> will be applied to the button.
     *
     *
     * @return String
     *
     */
    public String getBaseStyle()  {
        return getAttributeAsString("baseStyle");
    }

    /**
    * Buttons do not show a title by default.
    *
    * @param showTitle showTitle Default value is false
    */
    public void setShowTitle(Boolean showTitle) {
        setAttribute("showTitle", showTitle);
    }
    /**
     * Buttons do not show a title by default.
     *
     *
     * @return Boolean
     *
     */
    public Boolean getShowTitle()  {
        return getAttributeAsBoolean("showTitle");
    }

    /**
    * These items are in a row by themselves by default
    *
    * @param startRow startRow Default value is true
    */
    public void setStartRow(Boolean startRow) {
        setAttribute("startRow", startRow);
    }
    /**
     * These items are in a row by themselves by default
     *
     *
     * @return Boolean
     *
     */
    public Boolean getStartRow()  {
        return getAttributeAsBoolean("startRow");
    }

    /**
    * These items are in a row by themselves by default
    *
    * @param endRow endRow Default value is true
    */
    public void setEndRow(Boolean endRow) {
        setAttribute("endRow", endRow);
    }
    /**
     * These items are in a row by themselves by default
     *
     *
     * @return Boolean
     *
     */
    public Boolean getEndRow()  {
        return getAttributeAsBoolean("endRow");
    }

    /**
    * Should the button auto fit to its title. Maps to {@link com.smartgwt.client.widgets.Button#getAutoFit autoFit} attribute.&#010 Note that if an explicit width or height is specified for this item, it will be respected,&#010 disabling autoFit behavior
    *
    * @param autoFit autoFit Default value is true
    */
    public void setAutoFit(Boolean autoFit) {
        setAttribute("autoFit", autoFit);
    }
    /**
     * Should the button auto fit to its title. Maps to {@link com.smartgwt.client.widgets.Button#getAutoFit autoFit} attribute.&#010 Note that if an explicit width or height is specified for this item, it will be respected,&#010 disabling autoFit behavior
     *
     *
     * @return Boolean
     *
     */
    public Boolean getAutoFit()  {
        return getAttributeAsBoolean("autoFit");
    }

    // ********************* Methods ***********************

        /**
         * Add a click handler.
         * <p>
         * Called when a ButtonItem is clicked on.&#010&#010
         *
         * @param handler the click handler
         * @return {@link HandlerRegistration} used to remove this handler
         */
        public HandlerRegistration addClickHandler(com.smartgwt.client.widgets.form.fields.events.ClickHandler handler) {
            if(getHandlerCount(com.smartgwt.client.widgets.form.fields.events.ClickEvent.getType()) == 0) setupClickEvent();
            return doAddHandler(handler, com.smartgwt.client.widgets.form.fields.events.ClickEvent.getType());
        }
        private native void setupClickEvent() /*-{
            var obj = null;
                obj = this.@com.smartgwt.client.core.DataClass::getJsObj()();
                var selfJ = this;
                obj.click = function(){
                    var param = {"form" : arguments[0], "item" : arguments[1]};
                    var event = @com.smartgwt.client.widgets.form.fields.events.ClickEvent::new(Lcom/google/gwt/core/client/JavaScriptObject;)(param);
                    selfJ.@com.smartgwt.client.core.DataClass::fireEvent(Lcom/google/gwt/event/shared/GwtEvent;)(event);
                };
        }-*/;


    // ********************* Static Methods ***********************





    /**
     * Set the icon.
     *
     * @param icon the icon
     */
    public void setIcon(String icon) {
        setAttribute("icon", icon);
    }

    /**
     * Return the icon.
     *
     * @return the icon
     */
    public String getIcon() {
        return getAttribute("icon");
    }

    public Canvas getCanvas() {
        return Button.getOrCreateRef(getAttributeAsJavaScriptObject("canvas"));
    }

}


