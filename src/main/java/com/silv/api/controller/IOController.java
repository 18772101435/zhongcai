package com.silv.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("test")
public class IOController {

    /**
     * 递归遍历文件夹子目录
     *
     * @param dir
     */
    public static void listDirectory(File dir) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                listDirectory(file);
                // System.out.println("目录::" + file);
            }
            System.out.println("文件::" + file);
        }
    }

    public static void main(String[] args) {
        File file = new File("D:\\asd");
        listDirectory(file);
    }
}
