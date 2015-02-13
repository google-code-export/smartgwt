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

package com.smartgwt.client.widgets;

import java.util.Date;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.bean.BeanFactory;
import com.smartgwt.client.core.BaseClass;
import com.smartgwt.client.core.DataClass;
import com.smartgwt.client.core.Function;
import com.smartgwt.client.core.LogicalStructure;
import com.smartgwt.client.core.NativeObject;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ValueEnum;
import com.smartgwt.client.util.IDManager;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.DrawEvent;
import com.smartgwt.client.widgets.events.DrawHandler;
import com.smartgwt.logicalstructure.core.LogicalStructureObject;
import com.smartgwt.logicalstructure.widgets.BaseWidgetLogicalStructure;

public abstract class BaseWidget extends Widget implements HasHandlers, LogicalStructure {

    private Function onRenderFn;

    static {
        init();
    }

    private static String FALSE_PLACEHOLDER;

    private static native void init()/*-{
        $wnd.isc.setAutoDraw(false);
        @com.smartgwt.client.widgets.BaseWidget::FALSE_PLACEHOLDER = new String("false");
    }-*/;

    protected String id;
    protected JavaScriptObject config = JSOHelper.createObject();
    protected String scClassName;
    protected boolean configOnly;
    
    /**
     * Adds this handler to the widget.
     *
     * @param <H>     the type of handler to add
     * @param type    the event type
     * @param handler the handler
     * @return {@link HandlerRegistration} used to remove the handler
     */
    protected final <H extends EventHandler> HandlerRegistration doAddHandler(final H handler, GwtEvent.Type<H> type) {
    	return addHandler(handler, type);
    }

    public int getHandlerCount(GwtEvent.Type<?> type) {
    	return super.getHandlerCount(type);
    };
    
    public BaseWidget() {
        /*empty*/
    }

    public BaseWidget(String id) {
        setID(id);
    }

    public void setJavaScriptObject(JavaScriptObject jsObj) {
        internalSetID(jsObj);
        JSOHelper.setObjectAttribute(jsObj, SC.REF, this);
        JSOHelper.setObjectAttribute(jsObj, SC.MODULE, BeanFactory.getSGWTModule());
        if (!JSOHelper.isScClassInstance(jsObj)) {
            setConfig(jsObj);
            return;
        }
        JSOHelper.setObjectAttribute(getConfig(), SC.REF, this);
        JSOHelper.setObjectAttribute(getConfig(), SC.MODULE, BeanFactory.getSGWTModule());
        onBind();
    }

    public static BaseWidget getRef(JavaScriptObject jsObj) {
        if (jsObj == null) {
            return null;
        } else {
            final Object ref = JSOHelper.getAttributeAsObject((JavaScriptObject)jsObj, SC.REF);
            if (ref == null || !(ref instanceof BaseWidget)) {
                return null;
            } else {
                return (BaseWidget)ref;
            }
        }
    }

    public static boolean hasAutoAssignedID(JavaScriptObject jsObj) {
        return jsObj == null ? false : JSOHelper.getAttributeAsBoolean(jsObj, SC.AUTOID);
    }        

    /**
     * Returns the javascript class name.
     * @return
     */
    public String getClassName(){
        return JSOHelper.getClassName(config);
    }

    /**
     * Get the name of the underlying SmartClient class
     *
     * @return the SmartClient class name
     */
    public String getScClassName() {
        return scClassName;
    }

    /**
     * Set the name of the underlying SmartClient class. This is an advanced setting.
     *
     * @param scClassName the SmartClient class
     */
    public void setScClassName(String scClassName) {
        this.scClassName = scClassName;
    }

    private native void wrapDestroy() /*-{
        var self = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
        if (self == null) {
            var config = this.@com.smartgwt.client.widgets.BaseWidget::getConfig()();
            $wnd.isc.logWarn("wrapDestroy(): the JavaScriptObject is null unexpectedly for " +
                $wnd.isc.echo(config) + " with " + this.@java.lang.Object::getClass()() +
                ".  This may lead to an ID collision after the widget is destroy()ed.");
            return;
        }
        if (self.__sgwtDestroy == null) self.__sgwtDestroy = function () {
            var jObj = this.__ref;
            if (jObj != null) jObj.@com.smartgwt.client.widgets.BaseWidget::destroy()();
        }
    }-*/;

    protected final native void doInit()/*-{
        var self = this.@com.smartgwt.client.widgets.BaseWidget::getOrCreateJsObj()();
        self.__setDragTracker = self.setDragTracker;
        self.setDragTracker = function() {
            var jObj = this.__ref;
            return jObj.@com.smartgwt.client.widgets.BaseWidget::setDragTracker()();
        };

        self.__getInnerHTML = self.getInnerHTML;
        self.getInnerHTML = function() {
            var jObj = this.__ref;
            this.__getInnerHTMLArguments = arguments;
            try {
                var ret = jObj.@com.smartgwt.client.widgets.BaseWidget::getInnerHTML()();
                if (ret === @com.smartgwt.client.widgets.BaseWidget::FALSE_PLACEHOLDER) {
                    return false;
                } else {
                	// Call "String(...)" [note this is not the constructor "new String(...)"]
                	// This will ensure we map any String object to the primitive
                	// (Equivalent to calling someStringObject.valueOf()). This makes sure our
                	// SmartClient String extensions get applied as necessary downstream.
                    return ret == null ? null : String(ret);
                }
            } finally {
                this.__getInnerHTMLArguments = null;
            }
        };

        if (self.shouldRedrawOnResize == $wnd.isc.Canvas.getPrototype().shouldRedrawOnResize) {
        	self.shouldRedrawOnResize = function(deltaX, deltaY) {
        		var redrawOnResize = self.redrawOnResize;
        		if (redrawOnResize == null) {
        			redrawOnResize = !((self.children != null && self.children.length > 0 &&
										!self.allowContentAndChildren) ||
										// we want to redrawOnResize if we have dynamic content
										// Check for getInnerHTML() having been overridden for this (javascript) Canvas subclass
										// This handles SC subclasses (EG detailViewer) where redrawOnResize is required.
										// If the developer overrides the java getInnerHTML() method rely on them
										// explicitly setting redrawOnResize if required.
										(self.__getInnerHTML == $wnd.isc.Canvas.getPrototype().getInnerHTML &&
										!$wnd.isc.isA.Function(self.contents)));
				}
				return redrawOnResize;
			}
    	}

        // onDraw() - undocumented method called from draw() as a draw-complete notification
        // Override this rather than overriding draw() directly - the latter adds a layer to the
        // stack depth on draw and when drawing deeply nested layouts etc increases the likelyhood
        // of seeing an out of stack depth error in IE7 and 8
        self.onDraw = function () {
            var jObj = this.__ref;
            if (jObj != null) jObj.@com.smartgwt.client.widgets.BaseWidget::rendered()();
        }

        this.@com.smartgwt.client.widgets.BaseWidget::wrapDestroy()();
        this.@com.smartgwt.client.widgets.BaseWidget::onInit()();
    }-*/;

    protected void onInit() {}

    // install callbacks for a live SC widget
    protected void onBind() {
        wrapDestroy();
    }

    public boolean isConfigOnly() {
        return configOnly;
    }

    public void setConfigOnly(boolean configOnly) {
        this.configOnly = configOnly;
    }

    protected native boolean setDragTracker() /*-{
        var self = this.@com.smartgwt.client.widgets.BaseWidget::getOrCreateJsObj()();
        return !!self.__setDragTracker();
    }-*/;

    /**
     * Return the inner HTML for this canvas. Called when the canvas is drawn or redrawn;
     * override to customize.
     * <p>
     * <b>Note</b> : {@link Canvas#setRedrawOnResize} should be set to true for components whose inner HTML
     * will not automatically reflow to fit the component's new size.
     *
     * @return HTML contents of this canvas
     */
    public native String getInnerHTML() /*-{
        var self = this.@com.smartgwt.client.widgets.BaseWidget::getOrCreateJsObj()();
        var ret = self.__getInnerHTML.apply(self, self.__getInnerHTMLArguments);
        
        if (ret === false) {
            ret = @com.smartgwt.client.widgets.BaseWidget::FALSE_PLACEHOLDER;
        }
        return ret;
    }-*/;

    /**
     * Draws the widget on the page.&#010
     */
    public native void draw() /*-{
        var self = this.@com.smartgwt.client.widgets.BaseWidget::getOrCreateJsObj()();
        self.draw();
    }-*/;

    /**
     * Permanently destroy a Canvas and all of it's children / members, recursively.
     * <P>
     * Like {@link Canvas#clear()} calling <code>destroy()</code> removes all HTML for the component;
     * unlike clear(), a destroyed Canvas is permanently unusable: it cannot be draw()'n again and
     * cannot be referenced by its global ID. This method also removes all JavaScript references to
     * the Canvas outside of application code, making it eligible for garbage collection (though
     * developers will need to release any references to the canvas held in application code themselves).
     * <P>
     * Any attempt to call a method on a destroyed Canvas will generally result in an error.  If your
     * application is forced to hold onto Canvas's that might be destroy()d without warning, you can
     * avoid errors by checking for the {@link Canvas#getDestroyed()} property.  If you override certain Canvas
     * methods, your code may be called while a Canvas is being destroy()d; in this case you can avoid
     * extra work (and possibly errors) by checking for the +{@link Canvas#getDestroying()} property.
     * <P>
     * Note that <code>destroy()</code> should not be called directly in event handling code for this
     * canvas. For this reason, wherever possible we recommend using {@link Canvas#markForDestroy()}
     * instead of calling this method directly.
     * <P>
     * <b>Note</b>: This is an override point
     */
    public native void destroy() /*-{
        var self = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
	    if (self != null && self.__sgwtDestroy) {
            delete self.__sgwtDestroy;
            if (self.destroy) self.destroy();
        }
	    var id = this.@com.smartgwt.client.widgets.BaseWidget::id;
        if (id != null) {
            this.@com.smartgwt.client.widgets.BaseWidget::clearID()();
            this.@com.smartgwt.client.widgets.Canvas::onDestroy()();
        }
        this.@com.smartgwt.client.widgets.BaseWidget::clearConfigRef()();
    }-*/;

    private void clearID() {
        IDManager.unregisterID(this, this.id);
        this.id = null;
    	JSOHelper.setNullAttribute(config,      "ID");
    	JSOHelper.setNullAttribute(config, SC.AUTOID);
    }

    private void clearConfigRef() {
        JSOHelper.setNullAttribute(this.config, SC.REF);
        JSOHelper.setNullAttribute(this.config, SC.MODULE);
    }

    public void doOnRender(Function function) {
        onRenderFn = function;
    }

    private void rendered() {
        onDraw();
        fireEvent(new DrawEvent(getID()));
        if (onRenderFn != null) {
            onRenderFn.execute();
        }
    }

    public HandlerRegistration addDrawHandler(DrawHandler handler) {
    	setupDrawHandlerEvent();
        return doAddHandler(handler, DrawEvent.getType());
    }

    private native void setupDrawHandlerEvent() /*-{
        var obj = null;
        var selfJ = this;
        var drawn = $entry(function(){
            selfJ.@com.smartgwt.client.widgets.BaseWidget::rendered()();
        });
        if(this.@com.smartgwt.client.widgets.BaseWidget::isCreated()()) {
            obj = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
            obj.addProperties({onDraw: drawn});
        } else {
            obj = this.@com.smartgwt.client.widgets.BaseWidget::getConfig()();
            obj.onDraw = drawn;
        }
    }-*/;

    protected void onDraw() {
    }

    protected void onDestroy() {
    }

    public void setPosition(String position) {
        setAttribute("position", position, false);
    }

    public void setHtmlElement(Element element) {
        setAttribute("htmlElement", element, false);
    }

    public native Element getDOM()/*-{
        var widget = this.@com.smartgwt.client.widgets.BaseWidget::getOrCreateJsObj()();
        return widget.getHandle();
    }-*/;


    public String getID() {
        if (id == null) {
            // Generate an ID because one was requested by the caller.
            final String className = SC.getAUTOIDClass(getClass().getName());
            setAttribute("AUTOIDClass", className, false);
            internalSetID(SC.generateID(className), true);
        }
        assert id != null;
        return id;
    }

    protected final void internalSetID(JavaScriptObject jsObj) {
        if (this.id != null) {
            IDManager.unregisterID(this, this.id);
        }
        String  id   = JSOHelper.getAttribute         (jsObj,      "ID");
        if (this.id != null && !this.id.equals(id) && getAttributeAsBoolean(SC.AUTOID)) {
            SC.releaseID(getClass().getName(), this.id);
        }
        boolean auto = JSOHelper.getAttributeAsBoolean(jsObj, SC.AUTOID);
        IDManager.registerID(this, id, true);
        if (id != null) this.id = id;
        JSOHelper.setAttribute(config,      "ID",   id);
        JSOHelper.setAttribute(config, SC.AUTOID, auto);
    }

    protected final void internalSetID(String id, boolean autoAssigned) {
        // prevent transaction from being started if it cannot complete successfully
        if (isCreated()) {
            error("Attempt to call internalSetID to change id from " + this.id +
                  " to " + id + " after the SC widget has already been created");
            return;
        }
        if (this.id != null) {
            IDManager.unregisterID(this, this.id);
        }
        IDManager.registerID(this, id, false);
        // If we previously auto-assigned an ID, release the ID back to SmartClient if the new
        // ID is different.
        if (this.id != null && !this.id.equals(id) && getAttributeAsBoolean(SC.AUTOID)) {
            SC.releaseID(getClass().getName(), this.id);
        }
        this.id = id;
        setAttribute(     "ID",           id, false);
        setAttribute(SC.AUTOID, autoAssigned, false);
    }

    public void setID(String id) {
        internalSetID(id, false);
    }

    public JavaScriptObject getConfig() {
        return config;
    }

    public void setConfig(JavaScriptObject config) {
        this.config = config;
    }

    public native boolean isCreated()/*-{
        var id = this.@com.smartgwt.client.widgets.BaseWidget::id;
        var obj;
        return id != null && (obj = $wnd.window[id]) != null && obj !== undefined && $wnd.isc.isA.Canvas(obj) === true;
    }-*/;

    protected Boolean isDrawn() {
        return isCreated() && doIsDrawn();
    }

    private native boolean doIsDrawn()/*-{
        var widget = this.@com.smartgwt.client.widgets.BaseWidget::getOrCreateJsObj()();
        return widget.isDrawn();
    }-*/;

    public native JavaScriptObject getJsObj()/*-{
        var id = this.@com.smartgwt.client.widgets.BaseWidget::id;
        if (id != null && $wnd.window[id] != null && $wnd.window[id] !== undefined) {
            return $wnd.window[id];
        } else {
            return null;
        }
    }-*/;

    public JavaScriptObject getOrCreateJsObj() {
        if (!isCreated()) {
            if (id == null) {
                final String className = SC.getAUTOIDClass(getClass().getName());
                setAttribute("AUTOIDClass", className, false);
                internalSetID(SC.generateID(className), true);
            }
            // The SC.REF property will already be set if new was called on a SmartClient
            // JS properties object; warn here if we actually attempt to create() it.
            if (getRef(config) == this) {
                SC.logWarn("Instantiating in SGWT a properties object from the SmartClient " +
                           "side may lead to undefined behavior if the SmartClient Framework " +
                           "is expecting to perform the instantiation itself.");
            } else {
                JSOHelper.setObjectAttribute(config, SC.REF, this);
                JSOHelper.setObjectAttribute(config, SC.MODULE, BeanFactory.getSGWTModule());
            }
            JavaScriptObject jsObj = create();
            return jsObj;
        } else {
            return getJsObj();
        }
    }

    protected native JavaScriptObject create()/*-{
        var config = this.@com.smartgwt.client.widgets.BaseWidget::getConfig()();
        return $wnd.isc.Canvas.create(config);
    }-*/;

    public String getAttribute(String attribute) {
        return getAttributeAsString(attribute);
    }


    protected native String getAttributeAsString(String property)/*-{
        var ret;
        if(this.@com.smartgwt.client.widgets.BaseWidget::isCreated()()) {
            var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
            ret = widget.getProperty(property);
        } else {
            var config = this.@com.smartgwt.client.widgets.BaseWidget::config;
            if(config[property] != undefined) {
                ret = config[property];
            } else {
               var scClassName = this.@com.smartgwt.client.widgets.BaseWidget::scClassName;
               ret = $wnd.isc[scClassName].getInstanceProperty(property);
            }
        }
        return ret == null || ret === undefined ? null : String(ret) ;
    }-*/;

    protected native String[] getAttributeAsStringArray(String property)/*-{
        var ret;
        if(this.@com.smartgwt.client.widgets.BaseWidget::isCreated()()) {
            var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
            ret = widget.getProperty(property);
        } else {
            var config = this.@com.smartgwt.client.widgets.BaseWidget::config;
            if(config[property] != undefined) {
                ret = config[property];
            } else {
               var scClassName = this.@com.smartgwt.client.widgets.BaseWidget::scClassName;
               ret = $wnd.isc[scClassName].getInstanceProperty(property);
            }
        }
        return ret === undefined ? null : @com.smartgwt.client.util.JSOHelper::convertToJavaStringArray(Lcom/google/gwt/core/client/JavaScriptObject;)(ret);
    }-*/;

    protected native int[] getAttributeAsIntArray(String property)/*-{
        var ret;
        if(this.@com.smartgwt.client.widgets.BaseWidget::isCreated()()) {
            var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
            ret = widget.getProperty(property);
        } else {
            var config = this.@com.smartgwt.client.widgets.BaseWidget::config;
            if(config[property] != undefined) {
                ret = config[property];
            } else {
               var scClassName = this.@com.smartgwt.client.widgets.BaseWidget::scClassName;
               ret = $wnd.isc[scClassName].getInstanceProperty(property);
            }
        }
        return ret === undefined ? null : @com.smartgwt.client.util.JSOHelper::convertToJavaIntArray(Lcom/google/gwt/core/client/JavaScriptObject;)(ret);
    }-*/;

    protected native Float[] getAttributeAsFloatArray(String property)/*-{
        var ret;
        if(this.@com.smartgwt.client.widgets.BaseWidget::isCreated()()) {
            var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
            ret = widget.getProperty(property);
        } else {
            var config = this.@com.smartgwt.client.widgets.BaseWidget::config;
            if(config[property] != undefined) {
                ret = config[property];
            } else {
               var scClassName = this.@com.smartgwt.client.widgets.BaseWidget::scClassName;
               ret = $wnd.isc[scClassName].getInstanceProperty(property);
            }
        }
        return ret === undefined ? null : @com.smartgwt.client.util.JSOHelper::convertToJavaFloatArray(Lcom/google/gwt/core/client/JavaScriptObject;)(ret);
    }-*/;

    protected native Date getAttributeAsDate(String property)/*-{
        var ret;
        if(this.@com.smartgwt.client.widgets.BaseWidget::isCreated()()) {
            var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
            ret = widget.getProperty(property);
        } else {
            var config = this.@com.smartgwt.client.widgets.BaseWidget::config;
            if(config[property] != undefined) {
                ret = config[property];
            } else {
               var scClassName = this.@com.smartgwt.client.widgets.BaseWidget::scClassName;
               ret = $wnd.isc[scClassName].getInstanceProperty(property);
            }
        }
        return ret == null || ret === undefined ? null : @com.smartgwt.client.util.JSOHelper::toDate(D)(ret.getTime());
    }-*/;

    protected native Date[] getAttributeAsDateArray(String property)/*-{

        var ret;
        if(this.@com.smartgwt.client.widgets.BaseWidget::isCreated()()) {
            var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
            ret = widget.getProperty(property);
        } else {
            var config = this.@com.smartgwt.client.widgets.BaseWidget::config;
            if(config[property] != undefined) {
                ret = config[property];
            } else {
               var scClassName = this.@com.smartgwt.client.widgets.BaseWidget::scClassName;
               ret = $wnd.isc[scClassName].getInstanceProperty(property);
            }
        }
        if (!$wnd.isc.isA.Array(ret)) return null;

        return @com.smartgwt.client.util.JSOHelper::convertToJavaDateArray(Lcom/google/gwt/core/client/JavaScriptObject;)(ret);

    }-*/;

    protected native Integer getAttributeAsInt(String property)/*-{
        var ret;
        if(this.@com.smartgwt.client.widgets.BaseWidget::isCreated()()) {
            var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
            ret = widget.getProperty(property);
        } else {
            var config = this.@com.smartgwt.client.widgets.BaseWidget::config;
            if(config[property] != undefined) {
                ret = config[property];
            } else {
               var scClassName = this.@com.smartgwt.client.widgets.BaseWidget::scClassName;
               ret = $wnd.isc[scClassName].getInstanceProperty(property);
            }
        }
        return ret == null || ret === undefined ? null : @com.smartgwt.client.util.JSOHelper::toInteger(I)(ret);
    }-*/;

    protected native Double getAttributeAsDouble(String property)/*-{
        var ret;
        if(this.@com.smartgwt.client.widgets.BaseWidget::isCreated()()) {
            var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
            ret = widget.getProperty(property);
        } else {
            var config = this.@com.smartgwt.client.widgets.BaseWidget::config;
            if(config[property] != undefined) {
                ret = config[property];
            } else {
               var scClassName = this.@com.smartgwt.client.widgets.BaseWidget::scClassName;
               ret = $wnd.isc[scClassName].getInstanceProperty(property);
            }
        }
        return ret == null || ret === undefined ? null : @com.smartgwt.client.util.JSOHelper::toDouble(D)(ret);
    }-*/;

    protected native Element getAttributeAsElement(String property)/*-{
        var ret;
        if(this.@com.smartgwt.client.widgets.BaseWidget::isCreated()()) {
            var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
            ret = widget.getProperty(property);
        } else {
            var config = this.@com.smartgwt.client.widgets.BaseWidget::config;
            if(config[property] != undefined) {
                ret = config[property];
            } else {
               var scClassName = this.@com.smartgwt.client.widgets.BaseWidget::scClassName;
               ret = $wnd.isc[scClassName].getInstanceProperty(property);
            }
        }
        return ret === undefined ? null : ret;
    }-*/;

    protected native JavaScriptObject getAttributeAsJavaScriptObject(String property)/*-{
        var ret;
        if(this.@com.smartgwt.client.widgets.BaseWidget::isCreated()()) {
            var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
            ret = widget.getProperty(property);
        } else {
            var config = this.@com.smartgwt.client.widgets.BaseWidget::config;
            if(config[property] != undefined) {
                ret = config[property];
            } else {
               var scClassName = this.@com.smartgwt.client.widgets.BaseWidget::scClassName;
               ret = $wnd.isc[scClassName].getInstanceProperty(property);
            }
        }
        return ret === undefined ? null : ret;
    }-*/;

    protected native Float getAttributeAsFloat(String property)/*-{
        var ret;
        if(this.@com.smartgwt.client.widgets.BaseWidget::isCreated()()) {
            var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
            ret = widget.getProperty(property);
        } else {
            var config = this.@com.smartgwt.client.widgets.BaseWidget::config;
            if(config[property] != undefined) {
                ret = config[property];
            } else {
               var scClassName = this.@com.smartgwt.client.widgets.BaseWidget::scClassName;
               ret = $wnd.isc[scClassName].getInstanceProperty(property);
            }
        }
        return ret == null || ret === undefined ? null : @com.smartgwt.client.util.JSOHelper::toFloat(F)(ret);
    }-*/;

    protected native Boolean getAttributeAsBoolean(String property)/*-{
        var ret;
        if(this.@com.smartgwt.client.widgets.BaseWidget::isCreated()()) {
            var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
            ret = widget.getProperty(property);
        } else {
            var config = this.@com.smartgwt.client.widgets.BaseWidget::config;
            if(config[property] != undefined) {
                ret = config[property];
            } else {
               var scClassName = this.@com.smartgwt.client.widgets.BaseWidget::scClassName;
               ret = $wnd.isc[scClassName].getInstanceProperty(property);
            }
        }
        return ret == null || ret === undefined ? null : @com.smartgwt.client.util.JSOHelper::toBoolean(Z)(ret);
    }-*/;

    protected native Map getAttributeAsMap(String property)/*-{
        var ret;
        if(this.@com.smartgwt.client.widgets.BaseWidget::isCreated()()) {
            var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
            ret = widget.getProperty(property);
        } else {
            var config = this.@com.smartgwt.client.widgets.BaseWidget::config;
            if(config[property] != undefined) {
                ret = config[property];
            } else {
               var scClassName = this.@com.smartgwt.client.widgets.BaseWidget::scClassName;
               ret = $wnd.isc[scClassName].getInstanceProperty(property);
            }
        }
        return ret == null || ret === undefined ? null : @com.smartgwt.client.util.JSOHelper::convertToMap(Lcom/google/gwt/core/client/JavaScriptObject;)(ret);
    }-*/;

    protected native Record getAttributeAsRecord(String property)/*-{
        var ret;
        if(this.@com.smartgwt.client.widgets.BaseWidget::isCreated()()) {
            var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
            ret = widget.getProperty(property);
        } else {
            var config = this.@com.smartgwt.client.widgets.BaseWidget::config;
            if(config[property] != undefined) {
                ret = config[property];
            } else {
               var scClassName = this.@com.smartgwt.client.widgets.BaseWidget::scClassName;
               ret = $wnd.isc[scClassName].getInstanceProperty(property);
            }
        }
        return ret == null || ret === undefined ? null : @com.smartgwt.client.data.Record::getOrCreateRef(Lcom/google/gwt/core/client/JavaScriptObject;)(ret);
    }-*/;

    private void error(String attribute, String value) throws IllegalStateException {
        error("Cannot change configuration property '" + attribute + "' to " + value + " now that component " + id + " has been created.");
    }

    protected void errorIfNotCreated(String property) throws IllegalStateException {
        if (!isCreated()) {
            throw new IllegalStateException("Cannot access property " + property + " before the widget has been created.");
        }
    }

    protected void error(String message) throws IllegalStateException {
        if (!GWT.isScript()) {
            Window.alert("Error :" + message);
            throw new IllegalStateException(message);
        } else {
            SC.logWarn(message);
        }
    }

    protected void setAttribute(String attribute, String value, boolean allowPostCreate) {
        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, value);
        } else if (allowPostCreate) {
            setProperty(attribute, value);
        } else {
            error(attribute, value);
        }
    }

    protected void setAttribute(String attribute, ValueEnum value, boolean allowPostCreate) {
        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, value.getValue());
        } else if (allowPostCreate) {
            setProperty(attribute, value.getValue());
        } else {
            error(attribute, value.getValue());
        }
    }

    protected void setAttribute(String attribute, BaseWidget value, boolean allowPostCreate) {
        JavaScriptObject valueJS = value.isConfigOnly() ? value.getConfig() : value.getOrCreateJsObj();
        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, valueJS);
        } else if (allowPostCreate) {
            setProperty(attribute, valueJS);
        } else {
            error(attribute, value.toString());
        }
    }

    protected void setAttribute(String attribute, Map value, boolean allowPostCreate) {
        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, value);
        } else if (allowPostCreate) {
            setProperty(attribute, JSOHelper.convertMapToJavascriptObject(value));
        } else {
            error(attribute, value.toString());
        }
    }

    protected void setAttribute(String attribute, int[] value, boolean allowPostCreate) {
        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, value);
        } else if (allowPostCreate) {
            setProperty(attribute, JSOHelper.convertToJavaScriptArray(value));
        } else {
            error(attribute, value.toString());
        }
    }

    protected void setAttribute(String attribute, float[] value, boolean allowPostCreate) {
        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, value);
        } else if (allowPostCreate) {
            setProperty(attribute, JSOHelper.convertToJavaScriptArray(value));
        } else {
            error(attribute, value.toString());
        }
    }

    protected void setAttribute(String attribute, double[] value, boolean allowPostCreate) {
        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, value);
        } else if (allowPostCreate) {
            setProperty(attribute, JSOHelper.convertToJavaScriptArray(value));
        } else {
            error(attribute, value.toString());
        }
    }

    protected void setAttribute(String attribute, Float[] value, boolean allowPostCreate) {
        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, value);
        } else if (allowPostCreate) {
            setProperty(attribute, JSOHelper.convertToJavaScriptArray(value));
        } else {
            error(attribute, value.toString());
        }
    }

    protected void setAttribute(String attribute, DataClass value, boolean allowPostCreate) {

        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, value.getJsObj());
        } else if (allowPostCreate) {
            setProperty(attribute, value.getJsObj());
        } else {
            error(attribute, value.toString());
        }
    }

    protected void setAttribute(String attribute, DataClass[] value, boolean allowPostCreate) {

        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, JSOHelper.convertToJavaScriptArray(value));
        } else if (allowPostCreate) {
            setProperty(attribute, JSOHelper.convertToJavaScriptArray(value));
        } else {
            error(attribute, value.toString());
        }
    }

    protected void setAttribute(String attribute, JavaScriptObject[] value, boolean allowPostCreate) {

        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, JSOHelper.convertToJavaScriptArray(value));
        } else if (allowPostCreate) {
            setProperty(attribute, JSOHelper.convertToJavaScriptArray(value));
        } else {
            error(attribute, value.toString());
        }
    }

    protected void setAttribute(String attribute, BaseClass[] value, boolean allowPostCreate) {

        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, JSOHelper.convertToJavaScriptArray(value));
        } else if (allowPostCreate) {
            setProperty(attribute, JSOHelper.convertToJavaScriptArray(value));
        } else {
            error(attribute, value.toString());
        }
    }

    protected void setAttribute(String attribute, BaseWidget[] value, boolean allowPostCreate) {

        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, JSOHelper.convertToJavaScriptArray(value));
        } else if (allowPostCreate) {
            setProperty(attribute, JSOHelper.convertToJavaScriptArray(value));
        } else {
            error(attribute, value.toString());
        }
    }

    protected void setAttribute(String attribute, float value, boolean allowPostCreate) {
        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, value);
        } else if (allowPostCreate) {
            setProperty(attribute, value);
        } else {
            error(attribute, String.valueOf(value));
        }
    }

    protected void setAttribute(String attribute, double value, boolean allowPostCreate) {
        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, value);
        } else if (allowPostCreate) {
            setProperty(attribute, value);
        } else {
            error(attribute, String.valueOf(value));
        }
    }

    protected void setAttribute(String attribute, Integer value, boolean allowPostCreate) {
        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, value);
        } else if (allowPostCreate) {
            if (value == null) {
                setNullProperty(attribute);
            } else {
                setProperty(attribute, value.intValue());
            }
        } else {
            error(attribute, String.valueOf(value));
        }
    }

    public native void setNullProperty(String property)/*-{
        var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
        widget.setProperty(property, null);
    }-*/;

    public native void setProperty(String property, String value)/*-{
        var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
        widget.setProperty(property, value);
    }-*/;

    public native void setProperty(String property, boolean value)/*-{
        var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
        widget.setProperty(property, value);
    }-*/;

    public native void setProperty(String property, int value)/*-{
        var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
        widget.setProperty(property, value);
    }-*/;

    public native void setProperty(String property, float value)/*-{
        var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
        widget.setProperty(property, value);
    }-*/;

    public native void setProperty(String property, double value)/*-{
        var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
        widget.setProperty(property, value);
    }-*/;

    public native void setProperty(String property, Element value)/*-{
        var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
        widget.setProperty(property, value);
    }-*/;

    public native void setProperty(String property, JavaScriptObject value)/*-{
        var widget = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
        widget.setProperty(property, value);
    }-*/;

    protected void setAttribute(String attribute, Date value, boolean allowPostCreate) {
        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, value);
        } else if (allowPostCreate) {
            if (value == null) {
                setNullProperty(attribute);
            } else {
                setProperty(attribute, JSOHelper.convertToJavaScriptDate(value));
            }
        } else {
            error(attribute, String.valueOf(value));
        }
    }

    protected void setAttribute(String attribute, JavaScriptObject value, boolean allowPostCreate) {
        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, value);
        } else if (allowPostCreate) {
            setProperty(attribute, value);
        } else {
            error(attribute, String.valueOf(value));
        }
    }

    protected void setAttribute(String attribute, String[] value, boolean allowPostCreate) {
        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, JSOHelper.convertToJavaScriptArray(value));
        } else if (allowPostCreate) {
            setProperty(attribute, JSOHelper.convertToJavaScriptArray(value));
        } else {
            error(attribute, String.valueOf(value));
        }
    }

    protected void setAttribute(String attribute, Object[] value, boolean allowPostCreate) {
        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, JSOHelper.convertToJavaScriptArray(value));
        } else if (allowPostCreate) {
            setProperty(attribute, JSOHelper.convertToJavaScriptArray(value));
        } else {
            error(attribute, String.valueOf(value));
        }
    }

    protected void setAttribute(String attribute, Boolean value, boolean allowPostCreate) {
        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, value);
        } else if (allowPostCreate) {
            if (value == null) {
                setNullProperty(attribute);
            } else {
                setProperty(attribute, value.booleanValue());
            }
        } else {
            error(attribute, String.valueOf(value));
        }
    }

    protected void setAttribute(String attribute, Element value, boolean allowPostCreate) {
        if (!isCreated()) {
            JSOHelper.setAttribute(config, attribute, value);
        } else if (allowPostCreate) {
            setProperty(attribute, value);
        } else {
            error(attribute, String.valueOf(value));
        }
    }

    //override default behavior of setting title for SmartGWT widgets
    public void setTitle(String title) {
        //do nothing
    }

    public String getTitle() {
        return "";
    }

    public native String toString()/*-{
        try {
            var self;
            if (this.@com.smartgwt.client.widgets.BaseWidget::isCreated()()) {
                self = this.@com.smartgwt.client.widgets.BaseWidget::getJsObj()();
            } else {
                self = this.@com.smartgwt.client.widgets.BaseWidget::getConfig()();
            }
            return $wnd.isc.echo(self);
        } catch (e) {
            return "ERROR: " + e.name + " -- " + e.message;
        }
    }-*/;

    public boolean equals(Object obj) {
        if (obj instanceof BaseWidget) {
            if (obj == this) {
                return true;
            } else {
                BaseWidget other = (BaseWidget) obj;
                if (other.id == null || id == null) return false;
                if (other.id.equals(id)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return getID().hashCode();
    }

    public NativeObject nativeObject;

    public LogicalStructureObject setLogicalStructure(LogicalStructureObject s) {
        s.scClassName = getScClassName();
        return s;
    }

    public LogicalStructureObject getLogicalStructure() {
        BaseWidgetLogicalStructure s = new BaseWidgetLogicalStructure();
        setLogicalStructure(s);
        return s;
    }

    public void initNativeObject() {
        this.nativeObject = new NativeObject(this);
    }
}
