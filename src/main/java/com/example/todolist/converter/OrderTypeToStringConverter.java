package com.example.todolist.converter;

import com.example.todolist.domain.memo.OrderType;
import org.springframework.core.convert.converter.Converter;

public class OrderTypeToStringConverter implements Converter<OrderType, String> {

    @Override
    public String convert(OrderType source) {
        return source.name();
    }
}
