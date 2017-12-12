package com.silv.api.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by yao on 2017/12/12.
 */
@Entity
@Table(name = "file_attachment", schema = "silv", catalog = "")
public class FileAttachment {
    private long id;
    private String name;
    private String filename;
    private String size;
    private String extension;
    private String fileurl;
    private String fileurlFull;
    private String fileurlThumb;
    private String fileurlOriginal;
    private boolean canread;
    private boolean canwrite;
    private int fileTypeId;
    private int usersId;
    private int categoryId;
    private boolean status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "filename")
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Basic
    @Column(name = "size")
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Basic
    @Column(name = "extension")
    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Basic
    @Column(name = "fileurl")
    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    @Basic
    @Column(name = "fileurl_full")
    public String getFileurlFull() {
        return fileurlFull;
    }

    public void setFileurlFull(String fileurlFull) {
        this.fileurlFull = fileurlFull;
    }

    @Basic
    @Column(name = "fileurl_thumb")
    public String getFileurlThumb() {
        return fileurlThumb;
    }

    public void setFileurlThumb(String fileurlThumb) {
        this.fileurlThumb = fileurlThumb;
    }

    @Basic
    @Column(name = "fileurl_original")
    public String getFileurlOriginal() {
        return fileurlOriginal;
    }

    public void setFileurlOriginal(String fileurlOriginal) {
        this.fileurlOriginal = fileurlOriginal;
    }


    @Basic
    @Column(name = "file_type_id")
    public int getFileTypeId() {
        return fileTypeId;
    }

    public void setFileTypeId(int fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    @Basic
    @Column(name = "users_id")
    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    @Basic
    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "canread")
    public boolean isCanread() {
        return canread;
    }

    public void setCanread(boolean canread) {
        this.canread = canread;
    }

    @Basic
    @Column(name = "canwrite")
    public boolean isCanwrite() {
        return canwrite;
    }

    public void setCanwrite(boolean canwrite) {
        this.canwrite = canwrite;
    }

    @Basic
    @Column(name = "status")
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
