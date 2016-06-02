// IUserDataService.aidl
package com.ddd.demo.aidl;
import com.ddd.demo.aidl.User;
// Declare any non-default types here with import statements

interface IUserDataService {

    User lookUpUser(long userId);
}
