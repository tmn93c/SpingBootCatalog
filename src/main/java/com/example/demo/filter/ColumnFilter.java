package com.example.demo.filter;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        visible = true,
        property = "filterType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextColumnFilter.class, name = "text"),
        @JsonSubTypes.Type(value = NumberColumnFilter.class, name = "number"),
        @JsonSubTypes.Type(value = SetColumnFilter.class, name = "set") 
})
public abstract class ColumnFilter {
    String filterType;
}