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
 
package com.smartgwt.client.widgets.menu;



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
    * The Menu widget class implements interactive menu widgets, with optional icons, submenus,&#010 and shortcut keys.&#010 <p>&#010 A Menu is initialized with an Array of items, specified as menu.data, each of which represents&#010 one row in the menu's display and specifies the action to take when that menu item is selected.&#010 <p>&#010 Generally to create a context menu for a component, provide a Menu instance for the &#010 <code>contextMenu</code> property.  Note that some components have special context menu support&#010 because they have distinct regions or because they have a default set of context menu actions&#010 available.&#010 <p>&#010 If you want a button that pops up a menu when clicked, or a bar of such buttons, see the&#010 MenuButton and MenuBar classes.

    */

public class Menu extends ListGrid  implements com.smartgwt.client.widgets.menu.events.HasItemClickHandlers {

    public static Menu getOrCreateRef(JavaScriptObject jsObj) {
        if(jsObj == null) return null;
        BaseWidget obj = BaseWidget.getRef(jsObj);
        if(obj != null) {
            return (Menu) obj;
        } else {
            return new Menu(jsObj);
        }
    }


    public Menu(){
        
    }

    public Menu(JavaScriptObject jsObj){
        super(jsObj);
    }

    protected native JavaScriptObject create()/*-{
        var config = this.@com.smartgwt.client.widgets.BaseWidget::getConfig()();
        var widget = $wnd.isc.Menu.create(config);
        this.@com.smartgwt.client.widgets.BaseWidget::doInit()();
        return widget;
    }-*/;
    // ********************* Properties / Attributes ***********************
            
    /**
    * Optional target canvas for this menu. Available as a parameter to dynamic menuItem configuration&#010 methods such as {@link com.smartgwt.client.widgets.menu.MenuItem#checkIf}.&#010 <P>&#010 If this item has any {@link com.smartgwt.client.widgets.menu.MenuItem#getSubmenu submenu} the <code>target</code> will be propogated down&#010 to these child menus.
    *
    * @param target target Default value is null
    */
    public void setTarget(Canvas target) {
        setAttribute("target", target.getOrCreateJsObj(), true);
    }
    /**
     * Optional target canvas for this menu. Available as a parameter to dynamic menuItem configuration&#010 methods such as {@link com.smartgwt.client.widgets.menu.MenuItem#checkIf}.&#010 <P>&#010 If this item has any {@link com.smartgwt.client.widgets.menu.MenuItem#getSubmenu submenu} the <code>target</code> will be propogated down&#010 to these child menus.
     *
     *
     * @return Canvas
     *
     */
    public Canvas getTarget()  {
            return Canvas.getOrCreateRef(getAttributeAsJavaScriptObject("target"));
    }

    /**
    * The default menu width.
    *
    * @param defaultWidth defaultWidth Default value is 150
    */
    public void setDefaultWidth(int defaultWidth) {
        setAttribute("defaultWidth", defaultWidth, true);
    }
    /**
     * The default menu width.
     *
     *
     * @return int
     *
     */
    public int getDefaultWidth()  {
        return getAttributeAsInt("defaultWidth");
    }

    /**
    * The height of each item in the menu, in pixels.
    *
    * @param cellHeight cellHeight Default value is 20
    */
    public void setCellHeight(int cellHeight) {
        setAttribute("cellHeight", cellHeight, true);
    }
    /**
     * The height of each item in the menu, in pixels.
     *
     *
     * @return int
     *
     */
    public int getCellHeight()  {
        return getAttributeAsInt("cellHeight");
    }

    /**
    * Menus will not draw on initialization, until they're explicitly show()n
    * <p><b>Note : </b> This is an advanced setting</p>
    *
    * @param autoDraw autoDraw Default value is false
    */
    public void setAutoDraw(Boolean autoDraw) {
        setAttribute("autoDraw", autoDraw, true);
    }
    /**
     * Menus will not draw on initialization, until they're explicitly show()n
     *
     *
     * @return Boolean
     *
     */
    public Boolean getAutoDraw()  {
        return getAttributeAsBoolean("autoDraw");
    }

    /**
    * A boolean indicating whether this menu should use shortcut keys. Set useKeys to&#010 false in a menu's initialization block to explicitly disable shortcut keys.
    *
    * @param useKeys useKeys Default value is true
    */
    public void setUseKeys(Boolean useKeys) {
        setAttribute("useKeys", useKeys, true);
    }
    /**
     * A boolean indicating whether this menu should use shortcut keys. Set useKeys to&#010 false in a menu's initialization block to explicitly disable shortcut keys.
     *
     *
     * @return Boolean
     *
     */
    public Boolean getUseKeys()  {
        return getAttributeAsBoolean("useKeys");
    }

    /**
    * A boolean, indicating whether the shortcut key column should be displayed. If&#010 showKeys is not set, the menu will show the key column only if one of its items&#010 specifies a keys property. If showKeys is false, the keys will not be displayed,&#010 but will still function.
    *
    * @param showKeys showKeys Default value is true
    */
    public void setShowKeys(Boolean showKeys) {
        setAttribute("showKeys", showKeys, true);
    }
    /**
     * A boolean, indicating whether the shortcut key column should be displayed. If&#010 showKeys is not set, the menu will show the key column only if one of its items&#010 specifies a keys property. If showKeys is false, the keys will not be displayed,&#010 but will still function.
     *
     *
     * @return Boolean
     *
     */
    public Boolean getShowKeys()  {
        return getAttributeAsBoolean("showKeys");
    }

    /**
    * A boolean, indicating whether the checkmark/custom icon column should be displayed.&#010 If showIcons is not set, the menu will show the icon column only if one of its items&#010 specifies an icon, checked, checkIf, or dynamicIcon property.
    *
    * @param showIcons showIcons Default value is true
    */
    public void setShowIcons(Boolean showIcons) {
        setAttribute("showIcons", showIcons, true);
    }
    /**
     * A boolean, indicating whether the checkmark/custom icon column should be displayed.&#010 If showIcons is not set, the menu will show the icon column only if one of its items&#010 specifies an icon, checked, checkIf, or dynamicIcon property.
     *
     *
     * @return Boolean
     *
     */
    public Boolean getShowIcons()  {
        return getAttributeAsBoolean("showIcons");
    }

    /**
    * A boolean, indicating whether the submenu indicator column should be displayed. If&#010 showSubmenus is not set, the menu will show the indicator column only if one of its&#010 items specifies a submenu property. If showSubmenus is false, the submenu arrows&#010 will not be displayed, but submenus will still appear on rollover.
    *
    * @param showSubmenus showSubmenus Default value is true
    */
    public void setShowSubmenus(Boolean showSubmenus) {
        setAttribute("showSubmenus", showSubmenus, true);
    }
    /**
     * A boolean, indicating whether the submenu indicator column should be displayed. If&#010 showSubmenus is not set, the menu will show the indicator column only if one of its&#010 items specifies a submenu property. If showSubmenus is false, the submenu arrows&#010 will not be displayed, but submenus will still appear on rollover.
     *
     *
     * @return Boolean
     *
     */
    public Boolean getShowSubmenus()  {
        return getAttributeAsBoolean("showSubmenus");
    }

    /**
    * Should submenus show up on our left or right. Can validly be set to <code>"left"</code>&#010  or <code>"right"</code>
    *
    * @param submenuDirection submenuDirection Default value is "right"
    */
    public void setSubmenuDirection(String submenuDirection) {
        setAttribute("submenuDirection", submenuDirection, true);
    }
    /**
     * Should submenus show up on our left or right. Can validly be set to <code>"left"</code>&#010  or <code>"right"</code>
     *
     *
     * @return String
     *
     */
    public String getSubmenuDirection()  {
        return getAttributeAsString("submenuDirection");
    }

    /**
    * Message to show when a menu is shown with no items.
    * <p><b>Note : </b> This is an advanced setting</p>
    *
    * @param emptyMessage emptyMessage Default value is "[Empty menu]"
    */
    public void setEmptyMessage(String emptyMessage) {
        setAttribute("emptyMessage", emptyMessage, true);
    }
    /**
     * Message to show when a menu is shown with no items.
     *
     *
     * @return String
     *
     */
    public String getEmptyMessage()  {
        return getAttributeAsString("emptyMessage");
    }

    /**
    * The default width applied to custom icons in this menu. This is used whenever&#010          item.iconWidth is not specified.
    *
    * @param iconWidth iconWidth Default value is 16
    */
    public void setIconWidth(int iconWidth) {
        setAttribute("iconWidth", iconWidth, true);
    }
    /**
     * The default width applied to custom icons in this menu. This is used whenever&#010          item.iconWidth is not specified.
     *
     *
     * @return int
     *
     */
    public int getIconWidth()  {
        return getAttributeAsInt("iconWidth");
    }

    /**
    * The default height applied to custom icons in this menu. This is used whenever&#010          item.iconHeight is not specified.
    *
    * @param iconHeight iconHeight Default value is 16
    */
    public void setIconHeight(int iconHeight) {
        setAttribute("iconHeight", iconHeight, true);
    }
    /**
     * The default height applied to custom icons in this menu. This is used whenever&#010          item.iconHeight is not specified.
     *
     *
     * @return int
     *
     */
    public int getIconHeight()  {
        return getAttributeAsInt("iconHeight");
    }

    /**
    * When this menu is shown how should it animate into view? By default the menu will just&#010 show at the specified size/position. Options for animated show effects are <code>"fade"</code>&#010 to fade from transparent to visible, <code>"slide"</code> to slide the menu into view,&#010 or <code>"wipe"</code> to have the menu grow into view, revealing its content as it&#010 grows. Can be overridden by passing the 'animationEffect' parameter to 'menu.show()'
    * <p><b>Note : </b> This is an advanced setting</p>
    *
    * @param showAnimationEffect showAnimationEffect Default value is null
    */
    public void setShowAnimationEffect(String showAnimationEffect) {
        setAttribute("showAnimationEffect", showAnimationEffect, true);
    }
    /**
     * When this menu is shown how should it animate into view? By default the menu will just&#010 show at the specified size/position. Options for animated show effects are <code>"fade"</code>&#010 to fade from transparent to visible, <code>"slide"</code> to slide the menu into view,&#010 or <code>"wipe"</code> to have the menu grow into view, revealing its content as it&#010 grows. Can be overridden by passing the 'animationEffect' parameter to 'menu.show()'
     *
     *
     * @return String
     *
     */
    public String getShowAnimationEffect()  {
        return getAttributeAsString("showAnimationEffect");
    }

    /**
    * If true, clicking or pressing Enter on a menu item that has a submenu will&#010  select that item (with standard behavior of hiding the menus, calling click&#010  handlers, etc) instead of showing the submenu.
    *
    * @param canSelectParentItems canSelectParentItems Default value is null
    */
    public void setCanSelectParentItems(Boolean canSelectParentItems) {
        setAttribute("canSelectParentItems", canSelectParentItems, true);
    }
    /**
     * If true, clicking or pressing Enter on a menu item that has a submenu will&#010  select that item (with standard behavior of hiding the menus, calling click&#010  handlers, etc) instead of showing the submenu.
     *
     *
     * @return Boolean
     *
     */
    public Boolean getCanSelectParentItems()  {
        return getAttributeAsBoolean("canSelectParentItems");
    }

    /**
    * When true, when a menu item is chosen (via mouse click or keyboard), the menu is not &#010 automatically hidden, staying in place for further interactivity
    *
    * @param autoDismiss autoDismiss Default value is true
    */
    public void setAutoDismiss(Boolean autoDismiss) {
        setAttribute("autoDismiss", autoDismiss, true);
    }
    /**
     * When true, when a menu item is chosen (via mouse click or keyboard), the menu is not &#010 automatically hidden, staying in place for further interactivity
     *
     *
     * @return Boolean
     *
     */
    public Boolean getAutoDismiss()  {
        return getAttributeAsBoolean("autoDismiss");
    }

    // ********************* Methods ***********************

        /**
         * Add a itemClick handler.
         * <p>
         * Executed when a menu item with no click handler is clicked by the user. This&#010          itemClick handler must be specified as a function. It is passed an item parameter that&#010          is a reference to the clicked menu item.&#010&#010
         *
         * @param handler the itemClick handler
         * @return {@link HandlerRegistration} used to remove this handler
         */
        public HandlerRegistration addItemClickHandler(com.smartgwt.client.widgets.menu.events.ItemClickHandler handler) {
            if(getHandlerCount(com.smartgwt.client.widgets.menu.events.ItemClickEvent.getType()) == 0) setupItemClickEvent();
            return doAddHandler(handler, com.smartgwt.client.widgets.menu.events.ItemClickEvent.getType());
        }
        private native void setupItemClickEvent() /*-{
            var obj = null;
            var selfJ = this;
            if(this.@com.smartgwt.client.widgets.BaseWidget::isCreated()()) {
                obj = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
                obj.addProperties({itemClick:function(){
                        var param = {"item" : arguments[0], "colNum" : arguments[1]};
                        var event = @com.smartgwt.client.widgets.menu.events.ItemClickEvent::new(Lcom/google/gwt/core/client/JavaScriptObject;)(param);
                        selfJ.@com.smartgwt.client.widgets.BaseWidget::fireEvent(Lcom/google/gwt/event/shared/GwtEvent;)(event);
                        var ret = event.@com.smartgwt.client.event.Cancellable::isCancelled()();
                        return !ret;
                    }
                });
            } else {
                obj = this.@com.smartgwt.client.widgets.BaseWidget::getConfig()();
                obj.itemClick = function(){
                    var param = {"item" : arguments[0], "colNum" : arguments[1]};
                    var event = @com.smartgwt.client.widgets.menu.events.ItemClickEvent::new(Lcom/google/gwt/core/client/JavaScriptObject;)(param);
                    selfJ.@com.smartgwt.client.widgets.BaseWidget::fireEvent(Lcom/google/gwt/event/shared/GwtEvent;)(event);
                    var ret = event.@com.smartgwt.client.event.Cancellable::isCancelled()();
                    return !ret;
                };
            }
        }-*/;

        /**
         * Show this menu as a context menu, that is, immediately adjacent to the current mouse position.&#010&#010
         *
         * @return false == stop processing this event
         */
        public native Boolean showContextMenu() /*-{
            var self = this.@com.smartgwt.client.widgets.BaseWidget::getOrCreateJsObj()();
            var retVal =self.showContextMenu();
            if(retVal == null || retVal === undefined) {
                return null;
            } else {
                return @com.smartgwt.client.util.JSOHelper::toBoolean(Z)(retVal);
            }
        }-*/;



        /**
         * Hide the context menu - alias for hide()&#010
         */
        public native void hideContextMenu() /*-{
            var self = this.@com.smartgwt.client.widgets.BaseWidget::getOrCreateJsObj()();
            self.hideContextMenu();
        }-*/;

        /**
         * Get a particular MenuItem by index.&#010 <P>&#010 If passed a MenuItem, returns it.&#010&#010
         * @param item index of the MenuItem
         *
         * @return the MenuItem, Pointer to the item, or null if not defined
         */
        public native MenuItem getItem(int item) /*-{
            var self = this.@com.smartgwt.client.widgets.BaseWidget::getOrCreateJsObj()();
            var ret = self.getItem(item);
            if(ret == null || ret === undefined) return null;
            var retVal = @com.smartgwt.client.core.RefDataClass::getRef(Lcom/google/gwt/core/client/JavaScriptObject;)(ret);
            if(retVal == null) {
                retVal = @com.smartgwt.client.widgets.menu.MenuItem::new(Lcom/google/gwt/core/client/JavaScriptObject;)(ret);
            }
            return retVal;
        }-*/;









    // ********************* Static Methods ***********************






    //override to avoid call of ListGrid's onInit
    protected void onInit() {

    }

    /**
     * An array of menuItem objects, specifying the menu items this menu should show.
     *
     * @param data menu items
     */
    public void setData(MenuItem... data) {
        setAttribute("data", data, true);
    }

    /**
     * An array of Record objects, specifying the data to be used to populate the DataBoundComponent. Note that not
     * all DataBoundComponents observe the changes to the data to redraw themselves. Refer to the version of setData
     * that accepts component specific records.
     *
     * @param data array of Record objects.
     * @see #setData(MenuItem[])
     */
    public void setData(Record[] data) {
        setAttribute("data", data, true);
    }

    /**
     * Synonym for {@link com.smartgwt.client.widgets.menu.Menu#getData data} Synonym for {@link
     * com.smartgwt.client.widgets.menu.Menu#setData}.
     *
     * @param items new items for this menu. Default value is null
     */
    public void setItems(MenuItem... items) {
        setAttribute("items", items, true);
    }

    /**
     * Return the menu items.
     *
     * @return the menu items
     */
    public MenuItem[] getItems() {
        JavaScriptObject dataJS = getAttributeAsJavaScriptObject("data");
        MenuItem[] data = convertToMenuItemArray(dataJS);
        return data;
    }

    private static MenuItem[] convertToMenuItemArray(JavaScriptObject nativeArray) {
        if (nativeArray == null) {
            return new MenuItem[]{};
        }
        JavaScriptObject[] componentsj = JSOHelper.toArray(nativeArray);
        MenuItem[] objects = new MenuItem[componentsj.length];
        for (int i = 0; i < componentsj.length; i++) {
            JavaScriptObject componentJS = componentsj[i];
            MenuItem obj = MenuItem.getOrCreateRef(componentJS);
            objects[i] = obj;
        }
        return objects;
    }

    /**
     * When used in a MenuBar, the title of the menu button create will be the title of the Menu.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        setAttribute("title", title, true);
    }

    public native void removeItem(MenuItem item) /*-{
        var self = this.@com.smartgwt.client.widgets.BaseWidget::getOrCreateJsObj()();
        var itemJS = item.@com.smartgwt.client.core.DataClass::getJsObj()();
        self.removeItem(itemJS);
    }-*/;


    public void addItem(MenuItem item) {
        JavaScriptObject itemJS = item.getJsObj();

        if (isCreated()) {
            addItemPostCreate(itemJS);

        } else {
            addItemPreCreate(itemJS);
        }
    }

    private native void addItemPreCreate(JavaScriptObject itemJS) /*-{
		var config = this.@com.smartgwt.client.widgets.BaseWidget::config;

        if(!config.items) {
            config.items = @com.smartgwt.client.util.JSOHelper::createJavaScriptArray()();
        }
        config.items.push(itemJS);
    }-*/;

    private native void addItemPostCreate(JavaScriptObject itemJS) /*-{
        var self = this.@com.smartgwt.client.widgets.BaseWidget::getOrCreateJsObj()();
        self.addItem(itemJS);
    }-*/;

    public native void addItem(MenuItem item, int index) /*-{
        var self = this.@com.smartgwt.client.widgets.BaseWidget::getOrCreateJsObj()();
        var itemJS = item.@com.smartgwt.client.core.DataClass::getJsObj()();
        self.addItem(itemJS, index);
    }-*/;

    /**
     * Given a MenuItem, return it's index in the items array.
     * @param item the MenuItem
     *
     * @return index of the item, or -1 if not defined.
     */
    public native int getItemNum(MenuItem  item) /*-{
        var self = this.@com.smartgwt.client.widgets.BaseWidget::getOrCreateJsObj()();
        var itemJS = item.@com.smartgwt.client.core.DataClass::getJsObj()();
        return self.getItemNum(itemJS);
    }-*/;

}



