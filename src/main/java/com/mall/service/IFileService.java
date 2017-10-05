package com.mall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by root on 9/13/17.
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}
