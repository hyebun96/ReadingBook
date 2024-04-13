package com.reading.member.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UploadProfileDTO {

    private List<MultipartFile> files;
}
