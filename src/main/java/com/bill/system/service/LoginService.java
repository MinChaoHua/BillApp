package com.bill.system.service;

import com.bill.system.entity.UserWithBLOBs;

public interface LoginService {

    UserWithBLOBs getLonginUser(UserWithBLOBs userWithBLOBs);
}
