package com.wedding.usermanage.service.impl;

import com.wedding.mapper.AlbumMapper;
import com.wedding.mapper.Album_photoMapper;
import com.wedding.mapper.UserMapper;
import com.wedding.model.ReturnMessage;
import com.wedding.model.po.Album;
import com.wedding.model.po.Album_photo;
import com.wedding.model.po.User;
import com.wedding.usermanage.service.PhotoService;
import com.wedding.usermanage.vo.AlbumVO;
import com.wedding.usermanage.vo.PhotoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.List;

@Service("photoService")
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private Album_photoMapper album_photoMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public synchronized ReturnMessage uploadPhotos(int userid,MultipartFile[] files) {
        OutputStream os = null;
        InputStream inputStream = null;
        String fileName = null;
        User user=userMapper.selectByPrimaryKey(userid);
        Album album=albumMapper.selectByPrimaryKey(user.getAlbumid());
        if(album.getMaxNumber()-album.getCurrentNumber()<files.length){
            return new ReturnMessage(false,"相册所余空间不足，上传失败！");
        }
        for(int i=0;i<files.length;i++) {
            MultipartFile filecontent=files[i];
            //向数据库存入路径
            Album_photo album_photo=new Album_photo();
            album_photo.setAddress("/album/"+userid);
            album_photo.setUploadTime(new Date());
            album_photo.setAlbumid(album.getId());
            album_photo.setOrderNumber(album.getCurrentNumber()+1);
            album_photoMapper.insert(album_photo);

            try {
                inputStream = filecontent.getInputStream();
                fileName = filecontent.getOriginalFilename();
                String suffix=fileName.substring(fileName.lastIndexOf(".")+1);
                fileName=album_photo.getId()+"."+suffix;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                String path = "./photos/album/"+userid;
                // 2、保存到临时文件
                // 1K的数据缓冲
                byte[] bs = new byte[1024];
                // 读取到的数据长度
                int len;
                // 输出的文件流保存到本地文件
                File tempFile = new File(path);
                if (!tempFile.exists()) {
                    tempFile.mkdirs();
                }
                os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
                // 开始读取
                while ((len = inputStream.read(bs)) != -1) {
                    os.write(bs, 0, len);
                }

                album_photo.setAddress(album_photo.getAddress()+"/"+fileName);
                album_photoMapper.updateByPrimaryKey(album_photo);
                album.setCurrentNumber(album.getCurrentNumber()+1);
                albumMapper.updateByPrimaryKey(album);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 完毕，关闭所有链接
                try {
                    os.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ReturnMessage(true,"上传成功");
    }

    @Override
    public AlbumVO getAlbum(int userid) {
        User user=userMapper.selectByPrimaryKey(userid);
        Album album=albumMapper.selectByPrimaryKey(user.getAlbumid());
        List<Album_photo> photos=album_photoMapper.selectByAlbumId(user.getAlbumid());
        PhotoVO[] photoVOS=new PhotoVO[photos.size()];
        AlbumVO albumVO=new AlbumVO();
        albumVO.setAlbumid(album.getId());
        albumVO.setCurrentNumber(album.getCurrentNumber());
        albumVO.setMaxNumber(album.getMaxNumber());
        for(int i=0;i<photos.size();i++){
            for(int j=0;j<photos.size();j++){
                if(photos.get(j).getOrderNumber()==(i+1)){
                    PhotoVO photoVO=new PhotoVO();
                    photoVO.setUrl(photos.get(j).getAddress());
                    photoVO.setOrder_number(photos.get(j).getOrderNumber());
                    photoVO.setId(photos.get(j).getId());
                    photoVOS[i]=photoVO;
                    break;
                }
            }
        }
        albumVO.setPhotos(photoVOS);
        return albumVO;
    }

    @Override
    public ReturnMessage changePhotoOrder(PhotoVO photoVO) {
        Album_photo album_photo1=album_photoMapper.selectByPrimaryKey(photoVO.getId());
        List<Album_photo>photos=album_photoMapper.selectByAlbumId(album_photo1.getAlbumid());
        for(int i=0;i<photos.size();i++){
            if(photos.get(i).getOrderNumber()==photoVO.getOrder_number()){
                Album_photo album_photo2=photos.get(i);
                album_photo2.setOrderNumber(album_photo1.getOrderNumber());
                album_photo1.setOrderNumber(photoVO.getOrder_number());
                album_photoMapper.updateByPrimaryKey(album_photo1);
                album_photoMapper.updateByPrimaryKey(album_photo2);
            }
        }

        return new ReturnMessage(true,"修改成功！");
    }

    @Override
    public ReturnMessage deletePhoto(PhotoVO photoVO) {
        Album_photo album_photo=album_photoMapper.selectByPrimaryKey(photoVO.getId());
        List<Album_photo> photos=album_photoMapper.selectByAlbumId(album_photo.getAlbumid());
        for(int i=0;i<photos.size();i++){
            Album_photo album_photo1=photos.get(i);
            if(album_photo1.getOrderNumber()>album_photo.getOrderNumber()){
                album_photo1.setOrderNumber(album_photo1.getOrderNumber()-1);
                album_photoMapper.updateByPrimaryKey(album_photo1);
            }
        }
        Album album=albumMapper.selectByPrimaryKey(album_photo.getAlbumid());
        album.setCurrentNumber(album.getCurrentNumber()-1);
        File file=new File("./photos"+album_photo.getAddress());
        file.delete();
        albumMapper.updateByPrimaryKey(album);
        album_photoMapper.deleteByPrimaryKey(album_photo.getId());
        return new ReturnMessage(true,"删除成功！");
    }


}
