package com.reading.member.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.FileInputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class UploadProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void uploadMemberImgTest() throws Exception{
        //Given
        final String fileName = "book"; //파일명
        final String contentType = "png"; //파일타입
        final String filePath = "src/test/resources/testImage/" + fileName + "." + contentType; //파일경로
        FileInputStream fileInputStream = new FileInputStream(filePath);

        //Mock파일생성
        MockMultipartFile image1 = new MockMultipartFile(
                "UploadProfileDTO", //name
                fileName + "." + contentType, //originalFilename
                contentType,
                fileInputStream
        );

        //When & Then
        mockMvc.perform(
                multipart("/profile/uploadMemberImg")
                        .file(image1)
        ).andExpect(status().isOk());
    }









}