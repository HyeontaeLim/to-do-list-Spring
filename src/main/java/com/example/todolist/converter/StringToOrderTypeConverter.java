package com.example.todolist.converter;

import com.example.todolist.controller.OrderType;
import org.springframework.core.convert.converter.Converter;

public class StringToOrderTypeConverter implements Converter<String, OrderType> {

    @Override
    public OrderType convert(String source) {
        OrderType[] values = OrderType.values();
        for (OrderType value : values) {
            if (value.toString().equalsIgnoreCase(source)) {
                return value;
            }
        }
        return null;
    }
}
