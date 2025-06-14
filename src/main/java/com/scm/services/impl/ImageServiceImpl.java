package com.scm.services.impl;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.helpers.AppConstants;
import com.scm.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService
{

    private Cloudinary cloudinary;


    // idhar humne constructor injection ka use kiya (agar hum ek yahan constructor banate hai toh spring boot automatically bean ko create karega hume aur kuch karne ki jarurat nhi hai bas yahi hai constructor injection jo ki "Autowired" ke replace mein aata hai )
    public ImageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    } // Itne tak hume coudinary ke andar data mil chuka hai  ab aage chalke clodinary ka use karke contactImage ko uplaod karna hai

    @Override
    public String uploadImage(MultipartFile contactImage, String filename ) {

        // Code for image upload
        try {
            byte[] data = new byte[contactImage.getInputStream().available()];//humare contactImage mein jo inputStream hai jahan se hume data read karna hai uspe jitne byte available hai utne byte ki ek Array ban jayegi.

            contactImage.getInputStream().read(data);//data ko read karna hai
            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                "public_id",  filename ));
            return this.getUrlFromPublicId(filename);


        } catch (IOException e) {
            
            e.printStackTrace();
            return null;
        }  


        //Using cloudnary

       
    }

    @Override
    public String getUrlFromPublicId(String publicId) {
        return cloudinary
        .url()
        .transformation(
            new Transformation<>()
            .width(AppConstants.CONTACT_IMAGE_WIDTH)
            .height(AppConstants.CONTACT_IMAGE_HEIGHT)
            .crop(AppConstants.CONTACT_IMAGE_CROP)
        )
        .generate(publicId); // humne cloudinary ka url banaya hai aur usme transformation kiya hai aur usme crop kiya hai
    }

}
