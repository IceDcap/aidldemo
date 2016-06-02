// IPhoneBookService.aidl
package com.ddd.demo.aidl;

// Declare any non-default types here with import statements

interface IPhoneBookService {
    //PhoneBook remote service, provide the list of phone numbers by giving a person name.
    List lookUpPhone(String name);
}
