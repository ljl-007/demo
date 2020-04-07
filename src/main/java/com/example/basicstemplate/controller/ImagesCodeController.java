package com.example.basicstemplate.controller;

import com.example.basicstemplate.untils.ImagesCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@Api(value = "验证码", tags = "验证码接口")
public class ImagesCodeController {


    @GetMapping("/imagesCode")
    @ApiOperation(value = "验证码", notes = "验证码")
    public void imageCode(HttpServletResponse response, HttpServletRequest request) throws IOException {

        try {
            ImagesCode verifyCode = new ImagesCode();
            BufferedImage bufferedImage = verifyCode.getImage();
            //verifyCode.output(verifyCode.getImage(), new FileOutputStream("D:/test.jpg"));
            String str = verifyCode.getText();
            request.getSession().setAttribute("img_code", str.toUpperCase());//
//          request.getSession().setAttribute("img_time", System.currentTimeMillis());
            response.setContentType("image/jpeg");
            OutputStream outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpeg", outputStream);// 将图片数据流用response读出去
            System.out.println(str);
            //outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
