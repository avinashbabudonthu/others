package com.lang3;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Data
@Builder
public class Person {

    private String name;
    private String address;

    @Override
    public String toString(){
        return new ToStringBuilder(this)
                .append("name", name)
                .append("address", address)
                .toString();
    }
}
