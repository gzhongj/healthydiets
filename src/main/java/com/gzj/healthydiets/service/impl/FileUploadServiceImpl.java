package com.gzj.healthydiets.service.impl;

import com.gzj.healthydiets.service.FileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
@Service
public class FileUploadServiceImpl implements FileUploadService {
    public String fileUpload(MultipartFile file){
        if (file.isEmpty()){
            return "error400";
        }
        String originFileName=file.getOriginalFilename();
        String fileName=System.currentTimeMillis()+"."+originFileName.substring(originFileName.lastIndexOf(".")+1);
        String filePath="/Users/jessica/img/";
        File dest=new File(filePath+fileName);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return "error500";
        }
        return fileName;
    }
}
