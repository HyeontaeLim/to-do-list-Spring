package com.example.todolist.converter;

import com.example.todolist.domain.memo.OrderType;
import org.springframework.core.convert.converter.Converter;

public class StringToOrderTypeConverter implements Converter<String, OrderType> {

    @Override
    public OrderType convert(String source) {
        OrderType[] values = OrderType.values();
        for (OrderType value : values) {
            if (value.name().equalsIgnoreCase(source)) {
                return value;
            }
        }
        return null;
    }
}
