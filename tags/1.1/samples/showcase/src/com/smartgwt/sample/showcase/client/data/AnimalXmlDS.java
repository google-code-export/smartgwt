package com.smartgwt.sample.showcase.client.data;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.fields.DataSourceFloatField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.data.fields.DataSourceImageField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;

public class AnimalXmlDS extends DataSource {

    private static AnimalXmlDS instance = null;

    public static AnimalXmlDS getInstance() {
        if (instance == null) {
            instance = new AnimalXmlDS("animalDS");
        }
        return instance;
    }

    public AnimalXmlDS(String id) {

        setID(id);
        setRecordXPath("/List/Object");
        DataSourceTextField commonNameField = new DataSourceTextField("commonName", "Animal");

        DataSourceTextField scientificName = new DataSourceTextField("scientificName", "Scientific Name");
        scientificName.setRequired(true);
        scientificName.setPrimaryKey(true);

        DataSourceIntegerField lifeSpanField = new DataSourceIntegerField("lifeSpan", "Life Span");

        DataSourceTextField statusField = new DataSourceTextField("status", "Endangered Status");
        statusField.setValueMap("Threatened", "Endangered", "Not Endangered", "Not currently listed",
             "May become threatened","Protected");

        DataSourceTextField dietField = new DataSourceTextField("diet", "Diet");

        DataSourceTextField infoField = new DataSourceTextField("information", "Interesting Facts");
        infoField.setLength(1000);

        DataSourceImageField pictureField = new DataSourceImageField("picture", "Picture");
        pictureField.setImageURLPrefix("animals/");

        setFields(commonNameField, scientificName, lifeSpanField, statusField, dietField, infoField, pictureField);

        setDataURL("ds/test_data/animals.data.xml");
        setClientOnly(true);
    }

    protected Object transformRequest(DSRequest dsRequest) {
        return super.transformRequest(dsRequest);
    }
}