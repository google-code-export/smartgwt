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

package com.smartgwt.client.data;

import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.core.DataClass;
import com.smartgwt.client.core.RefDataClass;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Criteria for selecting only a matching set of records from a DataSource.  Criteria can be applied on the client and
 * server.  Unless configured otherwise, criteria will generally be applied client-side by {@link
 * com.smartgwt.client.data.ResultSet}s via {@link com.smartgwt.client.data.ResultSet#applyFilter ResultSet.applyFilter}.  
 * <P> Client- and server-side systems built into Smart GWT understand two criteria formats by default: simple key-value
 * pairs (Criteria) or the {@link com.smartgwt.client.data.AdvancedCriteria} format.   <P>  <P> Use {@link
 * com.smartgwt.client.data.DataSource#combineCriteria DataSource.combineCriteria} to combine two Criteria objects
 * (including Criteria and AdvancedCriteria) or {@link com.smartgwt.client.data.DataSource#convertCriteria
 * DataSource.convertCriteria} to convert simple Criteria to the AdvancedCriteria format. <P>  When sent to the Smart GWT
 * server, the Java representation of the criteria is described {@link com.smartgwt.client.rpc.RPCRequest#getData here}. 
 * When sent to other servers, the {@link com.smartgwt.client.types.DSProtocol operationBinding.dataProtocol} affects the
 * format.
 */
public class Criteria extends DataClass {

    public Criteria() {
    }

    public Criteria(String field, String value) {
        this();
        addCriteria(field, value);
    }

    public Criteria(JavaScriptObject jsObj) {
        super(jsObj);
    }

    public native static Criteria getOrCreateRef(JavaScriptObject jsObj) /*-{
        if (jsObj == null) return null;
        var instance = jsObj["__ref"];
        if (instance == null) {
            return @com.smartgwt.client.data.Criteria::new(Lcom/google/gwt/core/client/JavaScriptObject;)(jsObj);
        } else {
            return instance;
        }
    }-*/;

    // The addCriteria() APIs are simply convenience wrappers for JSOHelper routines
    // that take the associated type of argument.  At the JSOHelper level, there may
    // not be an exact match, resulting in Integer, Float, or Double matching Number.
    // However, the APIs here can't removed to match as they've already been exposed.
    public void addCriteria(String field, String value) {
        JSOHelper.setAttribute(jsObj, field, value);
    }

    public void addCriteria(String field, Boolean value) {
        JSOHelper.setAttribute(jsObj, field, value);
    }

    public void addCriteria(String field, Integer value) {
        JSOHelper.setAttribute(jsObj, field, value);
    }

    public void addCriteria(String field, Long value) {
        JSOHelper.setAttribute(jsObj, field, value);
    }

    public void addCriteria(String field, Float value) {
        JSOHelper.setAttribute(jsObj, field, value);
    }

    public void addCriteria(String field, Double value) {
        JSOHelper.setAttribute(jsObj, field, value);
    }

    public void addCriteria(String field, Date value) {
        JSOHelper.setAttribute(jsObj, field, value);
    }

    public void addCriteria(String field, String[] value) {
        JSOHelper.setAttribute(jsObj, field, value);
    }

    public void addCriteria(String field, Boolean[] value) {
        JSOHelper.setAttribute(jsObj, field, value);
    }

    public void addCriteria(String field, Integer[] value) {
        JSOHelper.setAttribute(jsObj, field, value);
    }

    public void addCriteria(String field, Long[] value) {
        JSOHelper.setAttribute(jsObj, field, value);
    }

    public void addCriteria(String field, Float[] value) {
        JSOHelper.setAttribute(jsObj, field, value);
    }

    public void addCriteria(String field, Double[] value) {
        JSOHelper.setAttribute(jsObj, field, value);
    }

    public void addCriteria(String field, Object value) {
        if (value == null) return;

        if (value instanceof String) {
            addCriteria(field, (String) value);

        } else if (value instanceof Number) {
            if (value instanceof Integer) {
                addCriteria(field, (Integer) value);
            } else if (value instanceof Long) {
                addCriteria(field, (Long) value);
            } else if (value instanceof Float) {
                addCriteria(field, (Float) value);
            } else if (value instanceof Double) {            
                addCriteria(field, (Double) value);
            }

        } else if (value instanceof Boolean) {
            addCriteria(field, (Boolean) value);

        } else if (value.getClass().isArray()) {
            if (value instanceof String[]) {
                addCriteria(field, (String[]) value);
            } else if (value instanceof Boolean[]) {
                addCriteria(field, (Boolean[]) value);
            } else if (value instanceof Integer[]) {
                addCriteria(field, (Integer[]) value);
            } else if (value instanceof Long[]) {
                addCriteria(field, (Long[]) value);
            } else if (value instanceof Float[]) {
                addCriteria(field, (Float[]) value);
            } else if (value instanceof Double[]) {
                addCriteria(field, (Double[]) value);
            }

        } else if (value instanceof Date) {
            addCriteria(field, (Date) value);
        }
    }

    /**
     * Adds the criteria from the passed criteria object.
     *
     * @param otherCriteria the passed criteria object
     */
    public void addCriteria(Criteria otherCriteria) {
        if (otherCriteria instanceof AdvancedCriteria || otherCriteria instanceof Criterion) {
            throw new IllegalArgumentException("Cannot add a Criterion or AdvancedCriteria to simple Criteria. " +
                            "Use Criteria.asAdvancedCriteria() to convert to AdvancedCriteria");
        }
        Map otherMap = otherCriteria.getValues();
        Set otherKeys = otherMap.keySet();
        for (Iterator i=otherKeys.iterator(); i.hasNext(); ) {
            String field = (String) i.next();
            Object value = otherMap.get(field);

            JSOHelper.setAttribute(jsObj, field, value);
        }
    }

    /**
     * Return the criteria values as a name-value Map.
     *
     * @return the criteria values as a Map
     */
    public Map getValues() {
        return JSOHelper.convertToMap(jsObj);
    }

    public static Criteria[] convertToCriteriaArray(JavaScriptObject nativeArray) {
        if (nativeArray == null) {
            return new Criteria[]{};
        }
        if (JSOHelper.isArray(nativeArray)) {
            JavaScriptObject[] criteriasJS = JSOHelper.toArray(nativeArray);
            Criteria[] objects = new Criteria[criteriasJS.length];
            for (int i = 0; i < criteriasJS.length; i++) {
                JavaScriptObject criteriaJS = criteriasJS[i];
                Criteria obj = new Criteria(criteriaJS);
                objects[i] = obj;
            }
            return objects;
        } else {
            Criteria[] ret = new Criteria[1];
            ret[0] = new Criteria(nativeArray);
            return ret;
        }
    }
    
    /**
     * Does this represent an underlying AdvancedCriteria object in JavaScript?
     * @return
     */
    public boolean isAdvanced() {
       return ("AdvancedCriteria".equals(getAttributeAsString("_constructor")));
    }
    
    /**
     * Returns an AdvancedCriteria derived from this Criteria object.
     * @return
     */
    public AdvancedCriteria asAdvancedCriteria() {
        if (isAdvanced()) return new AdvancedCriteria(this.getJsObj());
        else {
            return new AdvancedCriteria(convertToAdvancedCriteria(this.getJsObj()));
        }
    }
    private native JavaScriptObject convertToAdvancedCriteria(JavaScriptObject simpleCriteria) /*-{
        return $wnd.isc.DataSource.convertCriteria(simpleCriteria);
    }-*/;

}
