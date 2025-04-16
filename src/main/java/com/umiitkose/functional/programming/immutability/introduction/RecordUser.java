package com.umiitkose.functional.programming.immutability.introduction;

public record RecordUser(String name, int age) {
    // Immutable user ile aynıdır.
}
/*
    [visibility] record [name][<optional generic types>] ([field1], [field2], ...) extends java.lang.Record
    {
    // BODY ..
        // Constructor
        public record([field1], [field2], ...) {
            // Constructor body
        }

        // Optional methods
        public [return type] [method name]([parameters]) {
            // Method body
        }
    }

    visibility: public, private, protected
    record keyword: keyword
    Name : as defined in the Java Language Scecification
    gereric types: generic type
    fields: types
    body: like any other class or interface body

 */

