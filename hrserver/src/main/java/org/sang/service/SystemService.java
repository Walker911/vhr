package org.sang.service;

import org.sang.mapper.SystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sang
 * @date 2018/1/26
 */
@Service
@Transactional
public class SystemService {
    @Autowired
    SystemMapper systemMapper;

}
