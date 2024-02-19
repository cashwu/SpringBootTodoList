package com.cashwu.todolist.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author cash
 */
@Getter
@Setter
@ToString
public class FileUploadDto {

    private MultipartFile[] files;
}
