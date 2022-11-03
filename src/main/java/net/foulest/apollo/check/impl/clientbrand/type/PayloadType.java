package net.foulest.apollo.check.impl.clientbrand.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PayloadType {

    public String data;
    public String name;
    public DataType dataType;
    public boolean blacklisted;
}
