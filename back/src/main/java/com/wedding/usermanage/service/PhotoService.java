package com.wedding.usermanage.service;

import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.vo.AlbumVO;
import com.wedding.usermanage.vo.PhotoVO;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
    ReturnMessage uploadPhotos(int userid,MultipartFile[] files);
    AlbumVO getAlbum(int userid);
    ReturnMessage changePhotoOrder(PhotoVO photoVO);
    ReturnMessage deletePhoto(PhotoVO photoVO);
}
